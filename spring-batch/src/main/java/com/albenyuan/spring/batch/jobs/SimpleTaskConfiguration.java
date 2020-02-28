package com.albenyuan.spring.batch.jobs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Configuration
public class SimpleTaskConfiguration extends AbstractJobConfiguration {

    @Bean
    public Job simpleJob() {
        return jobBuilderFactory.get("simpleJob")
                .start(simpleStep())
                .build();
    }

    @Bean
    public Step simpleStep() {
        return stepBuilderFactory.get("simpleStep")
                .<Integer, Integer>chunk(4) // 设置chunk的大小，表示一次读取数据的记录数的最大值
                .reader(new ListItemReader<Integer>(Stream.<Integer>iterate(1, n -> n + 1).limit(10).collect(Collectors.toList())) {
                    @Override
                    public Integer read() {
                        Integer item = super.read();
                        log.info("reader: item={}", item);

                        // TODO 当返回的item为null，则认为数据读取完毕
                        return item;
                    }
                })
                .processor(new ItemProcessor<Integer, Integer>() {
                    @Override
                    public Integer process(Integer item) throws Exception {
                        log.info("process: item={}", item);
                        if (item == 7) {
                            // TODO 当返回null值时，表示该对象数据需要丢弃
                            return null;
                        }
                        return item * -1;
                    }
                })
                .writer(new ItemWriter<Integer>() {
                    @Override
                    public void write(List<? extends Integer> items) throws Exception {
                        log.info("write: length={}, item={}", items.size(), items);
                    }
                })
                .build();
    }
}
