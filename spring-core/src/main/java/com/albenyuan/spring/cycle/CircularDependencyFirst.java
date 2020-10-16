package com.albenyuan.spring.cycle;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

public class CircularDependencyFirst implements InitializingBean {

    private CircularDependencySecond dependency;

    private Logger logger = LoggerFactory.getLogger(CircularDependencyFirst.class);

    public CircularDependencyFirst() {
        logger.info("CircularDependencyFirst()");
    }


    public CircularDependencySecond getDependency() {
        return dependency;
    }

    public void setDependency(CircularDependencySecond first) {
        logger.info("set first:{}, class={}", first, first.getClass().getSimpleName());
        this.dependency = first;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("CircularDependencyFirst afterPropertiesSet");
    }
}
