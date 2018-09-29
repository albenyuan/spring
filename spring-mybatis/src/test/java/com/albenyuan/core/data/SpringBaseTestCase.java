package com.albenyuan.core.data;

import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author Alben Yuan
 * @Date 2018-03-29 15:23
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringBaseTestCase {
    private ApplicationContext context;

    public void setContext(ApplicationContext context) {
        this.context = context;
    }
}
