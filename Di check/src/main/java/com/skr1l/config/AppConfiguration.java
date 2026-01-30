package com.skr1l.config;

import com.skr1l.lifecycle.LifecycleBean;
import com.skr1l.prototype.RequestIdHolder;
import com.skr1l.repository.JDBCBeverageRepository;
import com.skr1l.service.BeverageService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import com.skr1l.repository.BeverageRepository;

@Configuration
public class AppConfiguration {

    @Value("file")
    private String repoType;

    @Bean
    public BeverageService beverageService(BeverageRepository repository){
        return new BeverageService(repository);
    }

    @Bean
    @Scope("prototype")
    public RequestIdHolder requestIdHolder() {
        return new RequestIdHolder();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public LifecycleBean lifecycleBean() {
        return new LifecycleBean();
    }

}
