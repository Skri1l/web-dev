package com.skr1l.repository;

import com.skr1l.model.Beverage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface BeverageRepository {

    Beverage save(Beverage beverage);

    List<Beverage> findAll();

    Optional<Beverage> findById(Long id);

    Beverage update(Beverage beverage);

    void deleteById(Long id);
}
