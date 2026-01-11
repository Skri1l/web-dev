package com.skr1l.config;

import com.skr1l.repository.FileBeverageRepository;
import com.skr1l.repository.JDBCBeverageRepository;
import com.skr1l.service.BeverageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.skr1l.repository.BeverageRepository;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfiguration {

    @Value("${repository.type}")
    private String repoType;

    @Bean
    public BeverageRepository beverageRepository(){
        if ("jdbc".equals(repoType)) {
            return new JDBCBeverageRepository();
        }
        return new FileBeverageRepository();
    }

    @Bean
    public BeverageService beverageService(BeverageRepository repository){
        return new BeverageService(repository);
    }
}
