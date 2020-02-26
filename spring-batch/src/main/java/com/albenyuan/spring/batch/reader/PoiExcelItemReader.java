package com.albenyuan.spring.batch.reader;

import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.Resource;

public class PoiExcelItemReader<T> extends FlatFileItemReader<T> {

    @Override
    public void setResource(Resource resource) {
        super.setResource(resource);
    }


    @Override
    public T read() throws Exception, UnexpectedInputException, ParseException {
        return super.read();
    }

    @Override
    protected T doRead() throws Exception {
        return super.doRead();
    }


}
