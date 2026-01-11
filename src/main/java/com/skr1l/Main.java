package com.skr1l;


import com.skr1l.config.AppConfiguration;
import com.skr1l.service.BeverageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        BeverageService beverageService = context.getBean(BeverageService.class);
        beverageService.getRepository();

    }
}