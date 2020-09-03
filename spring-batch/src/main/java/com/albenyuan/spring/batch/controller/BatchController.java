package com.albenyuan.spring.batch.controller;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/batch")
public class BatchController {


    @Autowired
    @Qualifier("schemaJob")
    private Job schemaJob;


    @Autowired
    @Qualifier("multiSchemaFilesJob")
    private Job multiSchemaFilesJob;


    @Autowired
    @Qualifier("fileJob")
    private Job fileJob;

    @Autowired
    @Qualifier("simpleJob")
    private Job simpleJob;

    @Autowired
    @Qualifier("flatFileJob")
    private Job flatFileJob;


    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private JobLauncher jobLauncher;


    @RequestMapping("/simple-job")
    public Object simpleJob(String key) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("key", key)
                .toJobParameters();

        jobLauncher.run(simpleJob, jobParameters);
        return jobParameters;
    }


    @RequestMapping("/flat-file-job")
    public Object flatFileJob(String key) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("key", key)
                .toJobParameters();

        jobLauncher.run(flatFileJob, jobParameters);
        return jobParameters;
    }


}
