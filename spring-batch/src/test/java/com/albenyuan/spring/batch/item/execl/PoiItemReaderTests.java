package com.albenyuan.spring.batch.item.execl;

import com.albenyuan.spring.batch.item.excel.AbstractExcelItemReader;
import com.albenyuan.spring.batch.item.excel.RowCallbackHandler;
import com.albenyuan.spring.batch.item.excel.mapping.PassThroughRowMapper;
import com.albenyuan.spring.batch.item.excel.poi.PoiItemReader;
import com.albenyuan.spring.batch.item.excel.support.rowset.RowSet;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.StringUtils;

@Slf4j
public class PoiItemReaderTests {

    protected AbstractExcelItemReader itemReader;

    private ExecutionContext executionContext;


    @Before
    public void setup() throws Exception {
        this.itemReader = createExcelItemReader();
        this.itemReader.setLinesToSkip(1); //First line is column names
        this.itemReader.setResource(new FileSystemResource("/Users/macuser/Desktop/data/1575187276559.xlsx"));
        this.itemReader.setRowMapper(new PassThroughRowMapper());
        this.itemReader.setSkippedRowsCallback(new RowCallbackHandler() {

            public void handleRow(RowSet rs) {
                log.info("Skipping: " + StringUtils.arrayToCommaDelimitedString(rs.getCurrentRow()));
            }
        });
        configureItemReader(this.itemReader);
        this.itemReader.afterPropertiesSet();
        executionContext = new ExecutionContext();
        this.itemReader.open(executionContext);
    }

    @Test
    public void test() throws Exception {
        String[] row;
        do {
            row = (String[]) this.itemReader.read();
            log.info("Read: {}", StringUtils.arrayToCommaDelimitedString(row));
        } while (row != null);
    }


    @After
    public void after() throws Exception {
        this.itemReader.close();
    }


    protected void configureItemReader(AbstractExcelItemReader itemReader) {
    }


    protected AbstractExcelItemReader createExcelItemReader() {
        return new PoiItemReader();
    }
}
