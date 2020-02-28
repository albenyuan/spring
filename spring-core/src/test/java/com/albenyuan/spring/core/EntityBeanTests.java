package com.albenyuan.spring.core;

import com.albenyuan.spring.bean.EntityBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Alben Yuan
 * @date 2020-02-27 22:17
 */
@Slf4j
public class EntityBeanTests {

    protected Logger logger = Logger.getLogger(getClass());

    @Test
    public void loadEntity() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-entity.xml");
        context.start();
        EntityBean bean1 = context.getBean(EntityBean.class);
        log.info("bean1: {}", bean1);

        EntityBean bean2 = context.getBean(EntityBean.class);
        log.info("bean2: {}", bean2);
        Assert.assertTrue("不是同一实体", bean1 == bean2);


    }
}
