package com.albenyuan.spring.batch.jobs;

import com.albenyuan.spring.batch.Constants;
import com.albenyuan.spring.batch.HasNextFileDecider;
import com.albenyuan.spring.batch.listener.LogStepExecutionListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.jsr.step.DecisionStep;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.batch.api.Decider;
import java.util.List;

@Slf4j
@Configuration
@EnableBatchProcessing
public class FileBatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private LogStepExecutionListener logStepExecutionListener;


    @Autowired
    private ApplicationContext context;

    @Bean
    public Job fileJob() {
        return jobBuilderFactory.get("file job")
                .preventRestart()
                .start(stepBuilderFactory.get("prepare").tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        ExecutionContext executionContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
                        executionContext.put(Constants.CONTEXT_KEY_FILES, Constants.FILENAMES);
                        executionContext.put(Constants.CONTEXT_KEY_FILE_INDEX, 0);
                        return RepeatStatus.FINISHED;
                    }
                }).build())
                .next(fileStep())
                .next(hasNextFileDecisionStep())

                .on(Constants.LOOP_FILE_LOOP).to(fileStep())

                .from(hasNextFileDecisionStep()).on(Constants.LOOP_FILE_FINISHED).end()

                .end()
                .build();
    }

    @Bean
    public DecisionStep hasNextFileDecisionStep() {
        DecisionStep step = new DecisionStep(hasNextFileDecider());
        step.setName("DecisionStep");
        step.setJobRepository(jobRepository);
        step.registerStepExecutionListener(logStepExecutionListener);
        return step;

    }

    @Bean
    @StepScope
    public Decider hasNextFileDecider() {
        return new HasNextFileDecider();
    }

    private Step fileStep() {
        return stepBuilderFactory.get("fileStep").<String, String>chunk(10)
                .reader(indexReader(null, null, null))
                .processor(new ItemProcessor<String, String>() {
                    @Override
                    public String process(String item) throws Exception {
                        log.debug("item: {}", item);
                        return item;
                    }
                })
                .writer(new ItemWriter<String>() {
                    @Override
                    public void write(List<? extends String> items) throws Exception {
                        log.debug("items:{}", items);
                    }
                })
                .listener(new StepExecutionListener() {
                    @Override
                    public void beforeStep(StepExecution stepExecution) {

                    }

                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
                        ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
                        executionContext.put(Constants.CONTEXT_KEY_FILE_INDEX, (Integer) executionContext.get(Constants.CONTEXT_KEY_FILE_INDEX) + 1);
                        return ExitStatus.COMPLETED;
                    }
                })
                .build();
    }


    @Bean
    @StepScope
    public FlatFileItemReader<String> indexReader(
            @Value("#{jobExecutionContext}") Object object,
            @Value("#{jobExecutionContext['" + Constants.CONTEXT_KEY_FILES + "']}") List<String> files,
            @Value("#{jobExecutionContext['" + Constants.CONTEXT_KEY_FILE_INDEX + "']}") Integer fileIndex) {
        log.info("create indexReader: context={}, files={}, index={}", object, files, fileIndex);

        FlatFileItemReader<String> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(files.get(fileIndex)));
        reader.setEncoding("utf-8");
        reader.setLineMapper(new PassThroughLineMapper());
        return reader;
    }

//
//    @Bean
//    public ItemReader<String> reader() {
//        MultiResourceItemReader<String> reader = new MultiResourceItemReader<String>() {
//            @Override
//            public String read() throws Exception, UnexpectedInputException, ParseException {
//                String item = super.read();
//                Resource resource = getCurrentResource();
//                if (null != resource) {
//                    log.info("read: item={}, resource={}", item, resource.getFilename());
//                }
//                return item;
//            }
//        };
//
//        Resource[] resources = {
//                new FileSystemResource("/Users/macuser/Desktop/data/1575187276559.csv"),
//                new FileSystemResource("/Users/macuser/Desktop/data/1575187276559.xlsx"),
//                new FileSystemResource("/Users/macuser/Desktop/data/1575187276559.txt"),
//        };
//        reader.setResources(resources);
//        FlatFileItemReader<String> flatFileItemReader = new FlatFileItemReader<>();
//        flatFileItemReader.setEncoding("utf-8");
//        flatFileItemReader.setLineMapper(new PassThroughLineMapper());
//        flatFileItemReader.setLinesToSkip(1);
//        reader.setDelegate(flatFileItemReader);
//        return reader;
//    }


}
