package com.skr1l.service;

import com.skr1l.repository.BeverageRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;


public class BeverageService {

    private static final Log log = LogFactory.getLog(BeverageService.class);
    private final BeverageRepository repository;

    public BeverageService (BeverageRepository repository) {
        this.repository = repository;
    }

    public void getRepository() {
        log.info("we use - " + repository.getSourceName());
    }
}
