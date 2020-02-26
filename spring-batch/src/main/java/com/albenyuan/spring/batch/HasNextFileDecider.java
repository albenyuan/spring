package com.albenyuan.spring.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.batch.api.Decider;
import javax.batch.runtime.StepExecution;
import java.util.List;
import java.util.Map;


@Slf4j
public class HasNextFileDecider implements Decider {


    @Value("#{jobExecutionContext['" + Constants.CONTEXT_KEY_FILES + "']}")
    private List<String> files;

    @Value("#{jobExecutionContext}")
    private Map<String, Object> executionContext;

    @Value("#{jobExecutionContext['" + Constants.CONTEXT_KEY_FILE_INDEX + "']}")
    private Integer currentIndex;


    public HasNextFileDecider() {
        log.info("HasNextFileDecider");
    }

    @Override
    public String decide(StepExecution[] stepExecutions) throws Exception {
        Integer index = (Integer) executionContext.get(Constants.CONTEXT_KEY_FILE_INDEX);
        List<String> list = (List<String>) executionContext.get(Constants.CONTEXT_KEY_FILES);
        log.info("executionContext:{}", executionContext);
        log.info("files:{}", files);
        log.info("currentIndex:{}", currentIndex);
        if (null == list || null == index || list.isEmpty() || index >= list.size()) {
            return Constants.LOOP_FILE_FINISHED;
        }
        return Constants.LOOP_FILE_LOOP;
    }
}
