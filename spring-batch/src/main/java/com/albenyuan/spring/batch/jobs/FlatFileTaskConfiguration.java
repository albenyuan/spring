package com.albenyuan.spring.batch.jobs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.Objects;


@Slf4j
@Configuration
public class FlatFileTaskConfiguration extends AbstractJobConfiguration {
    @Bean
    public Job flatFileJob() {
        return jobBuilderFactory.get("flatFileJob")
                .start(restartTaskletStep())
                .next(flatFileStep())
                .build();
    }


    @Bean
    public Step restartTaskletStep() {

        return stepBuilderFactory.get("restartTaskletStep")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        log.info("tasklet: contribution={}", contribution);
                        return RepeatStatus.FINISHED;
                    }
                })
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step flatFileStep() {
        return stepBuilderFactory.get("flatFileStep")
                .<String, Integer>chunk(2)// 设置chunk的大小，表示一次读取数据的记录数的最大值
//                .readerIsTransactionalQueue()
                .reader(itemReader())
                .processor(new ItemProcessor<String, Integer>() {
                    @Override
                    public Integer process(String item) throws Exception {
                        log.info("process: item={}", item);
                        if (Objects.equals("4", item)) {
                            throw new RuntimeException("item is 4");
                        }
                        return Integer.valueOf(item) * -1;
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
    public MultiResourceItemReader<String> itemReader() {
        MultiResourceItemReader<String> reader = new MultiResourceItemReader<>();
        reader.setResources(new Resource[]{new ClassPathResource("data.csv"), new ClassPathResource("data2.csv")});
        FlatFileItemReader<String> delegate = new FlatFileItemReader<>();
        delegate.setLineMapper(new PassThroughLineMapper());
        delegate.setEncoding("utf-8");
        reader.setDelegate(delegate);
        return reader;
    }

//    @Bean
//    @StepScope
//    public FlatFileItemReader<String> itemReader() {
//        FlatFileItemReader<String> reader = new FlatFileItemReader<>();
//        reader.setResource(new ClassPathResource("data.csv"));
//        reader.setLineMapper(new PassThroughLineMapper());
//        reader.setEncoding("utf-8");
//        return reader;
//    }

}
