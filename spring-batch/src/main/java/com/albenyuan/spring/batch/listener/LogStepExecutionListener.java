package com.albenyuan.spring.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogStepExecutionListener implements StepExecutionListener {

    @Override
    public void beforeStep(org.springframework.batch.core.StepExecution stepExecution) {
        log.info("before step: {}, status={}", stepExecution.getStepName(), stepExecution.getExitStatus());
    }

    @Override
    public ExitStatus afterStep(org.springframework.batch.core.StepExecution stepExecution) {
        log.warn("before step: {}, status={}", stepExecution.getStepName(), stepExecution.getExitStatus());
        return stepExecution.getExitStatus();
    }
}
