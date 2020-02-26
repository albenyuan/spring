package com.albenyuan.spring.batch.reader;

import com.albenyuan.spring.batch.Constants;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@StepScope
public class IndexFlatFileItemReader extends FlatFileItemReader {


    @Value("#{jobExecutionContext['" + Constants.CONTEXT_KEY_FILES + "']}")
    private List<String> files;

    @Value("#{jobExecutionContext['" + Constants.CONTEXT_KEY_FILE_INDEX + "']}")
    private Integer currentIndex;


}
