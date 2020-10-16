package com.albenyuan.spring.core;

import com.albenyuan.spring.dependency.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class DependencyTests {

    @Test
    public void testSingleDependency() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-dependency.xml");
        context.start();
        UserService userService = context.getBean(UserService.class);
        log.info("userService: {}", userService);
    }
}
