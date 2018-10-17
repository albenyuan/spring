package com.albenyuan.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Author Alben Yuan
 * @Date 2018-10-15 11:06
 */
public class ServiceApplication {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:provider.xml");
        System.out.println(context.getDisplayName());
        context.start();
        System.out.println("服务已经启动...");
        System.in.read();
    }


}
