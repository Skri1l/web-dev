package com.skr1l.repository;

import com.skr1l.model.Beverage;

import java.util.List;
import java.util.Optional;

public interface BeverageRepository {

    List<Beverage> findAll();

    Optional<Beverage> findById(Long id);

    boolean existsByName(String name);

    Beverage save(Beverage beverage);

    void deleteById(Long id);
}
