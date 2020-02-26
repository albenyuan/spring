package com.albenyuan.spring.batch.jobs;


import com.albenyuan.spring.batch.Constants;
import com.albenyuan.spring.batch.listener.LogJobExecutionListener;
import com.albenyuan.spring.batch.listener.LogStepExecutionListener;
import com.albenyuan.spring.batch.model.SchemaResourceItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.util.List;

@Slf4j
@Configuration
@EnableBatchProcessing
public class SchemaMultiResourceConfiguration {


    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private LogStepExecutionListener logStepExecutionListener;


    @Autowired
    private LogJobExecutionListener logJobExecutionListener;


    @Bean
    public Job multiSchemaFilesJob() {
        log.info("create bean: schemaJob");
        return jobBuilderFactory.get("decider job")
                .listener(logJobExecutionListener)
                .incrementer(new RunIdIncrementer())
                .start(prepareStep())   // 读取文件列表
                .next(multiFiles2RpcStep()) //  读取文件内容
                .build();

    }

    @Bean
    public Step prepareStep() {
        log.info("create bean: prepareStep");
        return stepBuilderFactory.get("prepareStep")
                .tasklet((contribution, chunkContext) -> {

                    // TODO 读取plan instance 对应的文件列表

                    // TODO: 2020/2/26 判断文件是否是csv / text等文件

                    // TODO: 2020/2/26 将文件列表存放在job的上下文中

                    ExecutionContext executionContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
                    executionContext.put(Constants.CONTEXT_KEY_FILES, Constants.FILENAMES);
                    executionContext.put(Constants.CONTEXT_KEY_FILE_INDEX, 0);
                    return RepeatStatus.FINISHED;
                })
                .listener(logStepExecutionListener)
                .build();

    }


    @Bean
    public Step multiFiles2RpcStep() {
        log.info("create bean: multiFiles2Rpc");
        return stepBuilderFactory.get("multiFiles2Rpc step")
                .<SchemaResourceItem, SchemaResourceItem>chunk(100)
                .reader(multiFilesReader(null, null))
                .writer(items -> log.info("items:{}", items))
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<SchemaResourceItem> multiFilesReader(
            @Value("#{jobExecutionContext['" + Constants.CONTEXT_KEY_FILES + "']}") List<String> list,
            @Value("#{jobExecutionContext['" + Constants.CONTEXT_KEY_FILE_INDEX + "']}") Integer currentIndex) {

        MultiResourceItemReader<SchemaResourceItem> multiResourceItemReader = new MultiResourceItemReader<>();
        FlatFileItemReader<SchemaResourceItem> reader = new FlatFileItemReader<>();
        reader.setLineMapper((line, lineNumber) -> {
            log.info("{}={}", lineNumber, line);
            SchemaResourceItem item = new SchemaResourceItem();
            item.setValue(line);
            return item;
        });
        reader.setLinesToSkip(1);
        multiResourceItemReader.setDelegate(reader);
        Resource[] resources = new Resource[list.size()];
        for (int i = 0; i < list.size(); i++) {
            resources[i] = new FileSystemResource(list.get(i));
        }
        multiResourceItemReader.setResources(resources);
        return multiResourceItemReader;
    }

}

