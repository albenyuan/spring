package com.albenyuan.spring.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogJobExecutionListener implements JobExecutionListener {


    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("before job: execution={}", jobExecution);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("after job: execution={}", jobExecution);
    }


}
