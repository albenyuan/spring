package com.albenyuan.spring.batch.jobs;


import com.albenyuan.spring.batch.Constants;
import com.albenyuan.spring.batch.HasNextFileDecider;
import com.albenyuan.spring.batch.listener.LogJobExecutionListener;
import com.albenyuan.spring.batch.listener.LogStepExecutionListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.jsr.step.DecisionStep;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatCallback;
import org.springframework.batch.repeat.RepeatException;
import org.springframework.batch.repeat.RepeatOperations;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.batch.api.Decider;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class SchemaBatchConfiguration {


    private static boolean isStandard(String planId) {
        return StringUtils.isEmpty(planId);
    }

    @Autowired
    private ApplicationContext context;


    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private LogStepExecutionListener logStepExecutionListener;

    @Autowired
    private LogJobExecutionListener logJobExecutionListener;

    @Bean
    public Job schemaJob() {
        log.info("create bean: schemaJob");
        return jobBuilderFactory.get("decider job")
                .listener(logJobExecutionListener)
                .incrementer(new RunIdIncrementer())
                .start(commit())
                .next(standardDecision())
                .on(Constants.STANDARD_YES).to(json2Rpc())
                .from(standardDecision()).on(Constants.STANDARD_NO).to(files2Rpc())
                .end()
                .build();

    }

    @Bean
    public Step commit() {
        log.info("create bean: commit");

        return stepBuilderFactory.get("commit")
                .tasklet((contribution, chunkContext) -> {

                    ExecutionContext executionContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();

                    List<String> list = new ArrayList<>();
                    list.add("/Users/macuser/Desktop/data/1575187276559.csv");
                    list.add("/Users/macuser/Desktop/data/1575187276559.txt");
                    executionContext.put("list", list);
                    executionContext.put("currentIndex", 0);
                    return RepeatStatus.FINISHED;
                })
                .listener(logStepExecutionListener)
                .build();

    }

    @Bean
    public Flow files2Rpc() {
        log.info("create bean: files2Rpc");
        return new FlowBuilder<SimpleFlow>("files 2 Rpc")
                .start(file2Db())
                .next(hasNextFileDecision())
                .on(Constants.LOOP_FILE_LOOP).to(file2Db())
                .from(hasNextFileDecision()).on(Constants.LOOP_FILE_FINISHED).to(db2Rpc())
                .end();
    }

    @Bean
    public DecisionStep hasNextFileDecision() {
        log.info("create bean: hasNextFileDecision");
        DecisionStep decisionStep = new DecisionStep(hasNextFileDecider2());
        decisionStep.setJobRepository(jobRepository);
        decisionStep.setName("has next file");
        decisionStep.registerStepExecutionListener(logStepExecutionListener);
        return decisionStep;
    }

    @Bean
    @JobScope
    public HasNextFileDecider hasNextFileDecider2() {
        return new HasNextFileDecider();
    }

    @Bean
    public DecisionStep standardDecision() {
        log.info("create bean: standardDecision");
        DecisionStep decisionStep = new DecisionStep(standardDecider(null));
        decisionStep.setJobRepository(jobRepository);
        decisionStep.registerStepExecutionListener(logStepExecutionListener);
        decisionStep.setName("standardDecision");
        return decisionStep;
    }


    @Bean
    @StepScope
    public Decider standardDecider(@Value("#{jobParameters['plan_id']}") String planId) {
        log.info("create bean: standardDecider");
        return stepExecutions -> {
            log.info("standardDecision step executions: {}", stepExecutions);
            return isStandard(planId) ? Constants.STANDARD_YES : Constants.STANDARD_NO;
        };
    }


    @Bean
    public Step file2Db() {
        log.info("create bean: file2Db");

        return stepBuilderFactory.get("file 2 db")

                .<String, String>chunk(1)

                .reader(indexFileReader(null, null))
                .writer(new ItemWriter<String>() {
                    @Override
                    public void write(List<? extends String> items) throws Exception {
                        log.info("items: {}", items);
                    }
                })
                .listener(logStepExecutionListener)
                .stepOperations(new RepeatOperations() {
                    @Override
                    public RepeatStatus iterate(RepeatCallback callback) throws RepeatException {
                        log.info("repeat iterate");
                        return RepeatStatus.CONTINUABLE;
                    }
                })
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<String> indexFileReader(@Value("#{jobExecutionContext['files']}") List<String> list, @Value("#{jobExecutionContext['current_ndex']}") Integer currentIndex) {
        log.info("fileReader: index={}, list= {}", currentIndex, list);
        List<String> list1 = new ArrayList<>();
        if (null != currentIndex && currentIndex > 0 && currentIndex < list.size()) {
            list1.add(list.get(currentIndex));
        }
        return new ListItemReader<>(list1);
    }

    @Bean
    public Step db2Rpc() {
        log.info("create bean: db2Rpc");
        return stepBuilderFactory.get("db 2 rpc")
                .tasklet((contribution, chunkContext) -> {
                    log.info("db 2 rpc executed!");
                    log.info("contribution: {}", contribution);
                    log.info("chunkContext: {}", chunkContext);
                    return RepeatStatus.FINISHED;
                })
                .listener(logStepExecutionListener)
                .build();

    }

    @Bean
    public Step json2Rpc() {
        log.info("create bean: json2Rpc");
        return stepBuilderFactory.get("json 2 rpc")
                .tasklet((contribution, chunkContext) -> {
                    log.info("json 2 rpc executed!");
                    log.info("contribution: {}", contribution);
                    log.info("chunkContext: {}", chunkContext);
                    return RepeatStatus.FINISHED;
                })
                .listener(logStepExecutionListener)
                .build();
    }

}

