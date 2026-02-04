package com.skr1l.lab3.config;

import com.skr1l.lab3.lifecycle.LifecycleBean;
import com.skr1l.lab3.prototype.RequestIdHolder;
import com.skr1l.lab3.repository.BeverageJpaRepository;
import com.skr1l.lab3.service.BeverageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
@Configuration
public class AppConfig {
    @Value("file")
    private String repoType;

    @Bean
    public BeverageService beverageService(BeverageJpaRepository repository){
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
