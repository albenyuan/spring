package com.albenyuan.spring.batch.jobs;

import com.albenyuan.spring.batch.listener.LogJobExecutionListener;
import com.albenyuan.spring.batch.listener.LogStepExecutionListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@Slf4j
public class AbstractJobConfiguration {


    @Autowired
    protected ApplicationContext context;


    @Autowired
    protected JobBuilderFactory jobBuilderFactory;

    @Autowired
    protected StepBuilderFactory stepBuilderFactory;

    @Autowired
    protected JobRepository jobRepository;

    @Autowired
    protected LogStepExecutionListener logStepExecutionListener;

    @Autowired
    protected LogJobExecutionListener logJobExecutionListener;


}
