//package com.albenyuan.spring.batch.jobs;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.support.ListItemReader;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//@Slf4j
//@Configuration
//@EnableBatchProcessing
//public class GatewayBatchConfiguration {
//
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    private JobRepository jobRepository;
//
//    @Bean
//    public Job gatewayJob() {
//        return jobBuilderFactory.get("gatewayJob")
//                .incrementer(new RunIdIncrementer())
//                .start()
//                .end()
//                .build();
//    }
//
//
//    @Bean
//    public Step gatewayStep() {
//        return stepBuilderFactory.get("gatewayStep")
//                .chunk(1)
//                .reader(new ListItemReader<Integer>(Stream.iterate(0, n -> n + 1).collect(Collectors.toList())) {
//                })
//                .reader(new ItemReader<Integer>() {
//                })
//                .build();
//    }
//
//
//}
