package com.albenyuan.spring.batch.jobs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatCallback;
import org.springframework.batch.repeat.RepeatContext;
import org.springframework.batch.repeat.RepeatListener;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.repeat.policy.SimpleCompletionPolicy;
import org.springframework.batch.repeat.support.RepeatTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Configuration
public class SimpleTaskConfiguration extends AbstractJobConfiguration {

    @Bean
    public Job simpleJob() {
        return jobBuilderFactory.get("simpleJob")
                .start(simpleStep())
//                .next(taskletStep())
                .build();
    }


    @Bean
    public Step taskletStep() {
        return stepBuilderFactory.get("taskletStep")
                .allowStartIfComplete(true)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

                        log.info("execute tasklet.");
                        ExecutionContext executionContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
                        Object timesObject = executionContext.get("times");
                        Integer times = 0;
                        if (timesObject instanceof Integer) {
                            times = (Integer) timesObject;
                        }
                        times++;
                        executionContext.put("times", times);
                        log.info("tasklet: times={}", times);
                        return times > 4 ? RepeatStatus.FINISHED : RepeatStatus.CONTINUABLE;
                    }
                })
//                .stepOperations(new RepeatOperations() {
//                    @Override
//                    public RepeatStatus iterate(RepeatCallback callback) throws RepeatException {
//                        log.info("repeat operation:{}", callback);
//                        return RepeatStatus.CONTINUABLE;
//                    }
//                })
//                .stepOperations(repeatTemplate())
                .build();

    }


    @Bean
    public RepeatTemplate repeatTemplate() {
        RepeatTemplate template = new RepeatTemplate() {
            @Override
            public RepeatStatus iterate(RepeatCallback callback) {
                log.info("repeat iterate:{}", callback);
                return super.iterate(callback);
            }
        };
        template.setCompletionPolicy(new SimpleCompletionPolicy(5));
        template.registerListener(new RepeatListener() {
            @Override
            public void before(RepeatContext context) {
                log.warn("repeat before: context={}", context);
            }

            @Override
            public void after(RepeatContext context, RepeatStatus result) {
                log.warn("repeat after: context={}, result={}", context, result);
            }

            @Override
            public void open(RepeatContext context) {
                log.warn("repeat open: context={}", context);
            }

            @Override
            public void onError(RepeatContext context, Throwable e) {
                log.warn("repeat onError: context={}", context);
            }

            @Override
            public void close(RepeatContext context) {
                log.warn("repeat close: context={}", context);
            }
        });
        return template;
    }

    @Bean
    public Step simpleStep() {
        return stepBuilderFactory.get("simpleStep")
                .<Integer, Integer>chunk(2) // 设置chunk的大小，表示一次读取数据的记录数的最大值
//                .faultTolerant().retryPolicy(new SimpleRetryPolicy())
//                .retryLimit(3)
                .reader(listReader())
                .processor(new ItemProcessor<Integer, Integer>() {
                    @Override
                    public Integer process(Integer item) throws Exception {
                        log.info("process: item={}", item);
                        if (Objects.equals(4, item)) {
                            throw new RuntimeException("item is 4");
                        }
                        if (item == 7) {
                            // TODO 当返回null值时，表示该对象数据需要丢弃
                            return null;
                        }
                        return item * -1;
                    }
                })
                .writer(new ItemWriter<Integer>() {
                    @Override
                    public void write(List<? extends Integer> items) throws Exception {
                        log.info("write: length={}, item={}", items.size(), items);
                    }
                })
                .listener(new StepExecutionListener() {
                    @Override
                    public void beforeStep(StepExecution stepExecution) {
                        log.info("step before execution:{}", stepExecution);
                    }

                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
                        log.info("step after execution:{}", stepExecution);
                        return stepExecution.getExitStatus();
                    }
                })
                .build();
    }


    @Bean
    @StepScope
    public ItemReader<Integer> listReader() {
        return new ListItemReader<Integer>(Stream.<Integer>iterate(1, n -> n + 1).limit(5).collect(Collectors.toList())) {
            @Override
            public Integer read() {
                Integer item = super.read();
                log.info("reader: item={}", item);
                // TODO 当返回的item为null，则认为数据读取完毕
                return item;
            }
        };
    }


}
