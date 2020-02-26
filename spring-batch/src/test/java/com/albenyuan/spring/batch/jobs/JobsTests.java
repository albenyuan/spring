package com.albenyuan.spring.batch.jobs;

import com.albenyuan.spring.batch.SpringBatchApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class JobsTests extends SpringBatchApplicationTests {

    @Autowired
    @Qualifier("schemaJob")
    private Job job;


    @Autowired
    @Qualifier("multiSchemaFilesJob")
    private Job multiSchemaFilesJob;


    @Autowired
    @Qualifier("fileJob")
    private Job fileJob;

    @Autowired
    private JobLauncher jobLauncher;

    @Test
    void testFileJob() throws Exception {
        Map<String, JobParameter> map = new HashMap<>();
        map.put("timestamp", new JobParameter(System.currentTimeMillis()));
        JobExecution jobExecution = jobLauncher.run(fileJob, new JobParameters(map));
        log.info("execution: {}", jobExecution);
    }

    @Test
    void test() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("job: {}", job.getName());
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("plan_id", UUID.randomUUID().toString().replaceAll("-", ""))
                .addString("plan_instance_id", UUID.randomUUID().toString().replaceAll("-", ""))
                .addLong("times", 5L)
                .toJobParameters();
        Map map = jobParameters.getParameters();
//        map.put("list", Stream.iterate(0, n -> n + 1).limit(10).collect(Collectors.toList()));

        JobExecution jobExecution = jobLauncher.run(job, new JobParameters(map));
        log.info("jobExecution: {}", jobExecution);

    }


    @Test
    void testMultiSchemaFilesJob() throws Exception {
        log.info("execution:{}", jobLauncher.run(multiSchemaFilesJob, new JobParameters()));
    }


}
