package com.skr1l.service;

import com.skr1l.dto.BeverageRequestDto;
import com.skr1l.exception.ConflictException;
import com.skr1l.exception.NotFoundException;
import com.skr1l.exception.RandomDeleteException;
import com.skr1l.model.Beverage;
import com.skr1l.repository.BeverageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BeverageService {

    private final BeverageRepository repository;

    public BeverageService(BeverageRepository repository) {
        this.repository = repository;
    }

    public List<Beverage> getAll() {
        return repository.findAll(); // [] если пусто - 200
    }

    public Beverage create(BeverageRequestDto dto) {
        if (repository.existsByName(dto.getName())) {
            throw new ConflictException("Beverage already exists");
        }

        return repository.save(new Beverage(null, dto.getName(), dto.getPrice()));
    }

    public void delete(Long id) {
        if (Math.random() < 0.5) {
            throw new RandomDeleteException("Random failure during delete");
        }
        Beverage beverage = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Beverage not found with id " + id));
        repository.deleteById(id);
    }
}
