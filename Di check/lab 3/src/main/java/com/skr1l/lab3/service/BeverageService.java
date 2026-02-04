package com.skr1l.lab3.service;

import com.skr1l.lab3.dto.BeverageRequestDto;
import com.skr1l.lab3.exception.ConflictException;
import com.skr1l.lab3.exception.NotFoundException;
import com.skr1l.lab3.exception.RandomDeleteException;
import com.skr1l.lab3.model.Beverage;
import com.skr1l.lab3.repository.BeverageJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BeverageService {

    private final BeverageJpaRepository repository;

    public BeverageService(BeverageJpaRepository repository) {
        this.repository = repository;
    }

    public List<Beverage> getAll() {
        return repository.findAll(); // [] если пусто - 200
    }

    public void validateUniqueName(String name){
        boolean exists = repository.findAll().stream()
                .anyMatch(beverage -> beverage.getName().equalsIgnoreCase(name));
        if (exists){
            throw new ConflictException("Beverage already exists");
        }
    }

    public Beverage create(BeverageRequestDto dto) {
        validateUniqueName(dto.getName());
        return repository.save(new Beverage(null, dto.getName(), dto.getPrice()));
    }

    @Transactional
    public Beverage update(Long id, BeverageRequestDto dto) {

        Beverage beverage = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Beverage not found"));
        boolean nameExists = repository.findAll().stream()
                .anyMatch(b -> b.getName().equalsIgnoreCase(dto.getName())
                                && !b.getId().equals(id)
                );

        if (nameExists) {
            throw new ConflictException("Beverage already exists");
        }

        beverage.setName(dto.getName());
        beverage.setPrice(dto.getPrice());

        return repository.save(beverage);
    }

    @Transactional
    public void delete(Long id) {
        if (Math.random() < 0.5) {
            throw new RandomDeleteException("Random failure during delete");
        }
        Beverage beverage = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Beverage not found with id " + id));
        repository.delete(beverage);
    }
}
