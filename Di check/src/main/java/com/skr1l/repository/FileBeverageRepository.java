package com.skr1l.repository;

import com.skr1l.exception.NotFoundException;
import com.skr1l.model.Beverage;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Profile("file")
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
    public Beverage save(Beverage beverage) {
        Long id = idGenerator.getAndIncrement();
        Beverage saved = new Beverage(id, beverage.getName(), beverage.getPrice());
        storage.put(id, saved);
        return saved;
    }

    @Override
    public Beverage update(Beverage beverage) {
        Long id = beverage.getId();
        if (!storage.containsKey(id)) {
            throw new NotFoundException("Beverage not found with id " + id);
        }
        storage.put(id, beverage);
        return beverage;
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }
}
