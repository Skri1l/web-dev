package com.skr1l.webtask2.service;

import com.skr1l.webtask2.Entity.Welcome;
import com.skr1l.webtask2.dto.EntityDto;
import com.skr1l.webtask2.repository.Repo;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WelcomeService {

    private final Repo repo;

    public WelcomeService(Repo repo) {
        this.repo = repo;
    }

    public List<Welcome> getAll() {
        return repo.findAll();
    }

    private void validateUniqueName(String name) {
        boolean exists = repo.findAll().stream()
                .anyMatch(w -> w.getName().equalsIgnoreCase(name));

        if (exists) {
            throw new RuntimeException("Beverage already exists");
        }
    }

    public Welcome create(EntityDto dto) {
        validateUniqueName(dto.getName());
        return repo.save(new Welcome(null, dto.getName(), dto.getVolume()));
    }
}

