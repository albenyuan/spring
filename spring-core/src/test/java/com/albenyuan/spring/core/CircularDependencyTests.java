package com.albenyuan.spring.core;

import com.albenyuan.spring.cycle.CircularDependencyFirst;
import com.albenyuan.spring.cycle.CircularDependencySecond;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@Slf4j
public class CircularDependencyTests {


    @Test
    public void loadEntity() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:bean-circular.xml");
        context.start();
        CircularDependencyFirst first = context.getBean(CircularDependencyFirst.class);
        log.info("bean1: {}", first);

        CircularDependencySecond second = context.getBean(CircularDependencySecond.class);
        log.info("bean2: {}", second);


    }


}
