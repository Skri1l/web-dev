package com.skr1l.repository;

import com.skr1l.model.Beverage;

import java.util.List;
import java.util.Optional;

public class JDBCBeverageRepository implements BeverageRepository {

    @Override
    public List<Beverage> findAll() {
        throw new UnsupportedOperationException("JDBC repo not implemented");
    }

    @Override
    public Optional<Beverage> findById(Long id) {
        throw new UnsupportedOperationException("JDBC repo not implemented");
    }

    @Override
    public boolean existsByName(String name) {
        throw new UnsupportedOperationException("JDBC repo not implemented");
    }

    @Override
    public Beverage save(Beverage beverage) {
        throw new UnsupportedOperationException("JDBC repo not implemented");
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("JDBC repo not implemented");
    }
}
