package com.skr1l.repository;

import com.skr1l.model.Beverage;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class FileBeverageRepository implements BeverageRepository {

    private final Map<Long, Beverage> storage = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public List<Beverage> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Beverage> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public boolean existsByName(String name) {
        return storage.values().stream()
                .anyMatch(b -> b.getName().equalsIgnoreCase(name));
    }

    @Override
    public Beverage save(Beverage beverage) {
        Long id = idGenerator.getAndIncrement();
        Beverage saved = new Beverage(id, beverage.getName(), beverage.getPrice());
        storage.put(id, saved);
        return saved;
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }
}
