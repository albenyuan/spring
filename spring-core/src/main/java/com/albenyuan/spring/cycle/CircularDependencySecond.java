package com.albenyuan.spring.cycle;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

public class CircularDependencySecond implements InitializingBean {

    private CircularDependencyFirst dependency;

    private Logger logger = LoggerFactory.getLogger(CircularDependencySecond.class);

    public CircularDependencySecond() {
        logger.info("CircularDependencySecond()");
    }


    public CircularDependencyFirst getDependency() {
        return dependency;
    }

    public void setDependency(CircularDependencyFirst first) {
        logger.info("set first:{}, class={}", first, first.getClass().getSimpleName());
        this.dependency = first;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("CircularDependencySecond afterPropertiesSet");
    }

}
