package com.skr1l.lab3.controller;

import com.skr1l.lab3.dto.BeverageRequestDto;
import com.skr1l.lab3.model.Beverage;
import com.skr1l.lab3.service.BeverageService;

import jakarta.validation.Valid;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/beverages")
public class BeverageController {

    private final BeverageService service;

    public BeverageController(BeverageService service) {
        this.service = service;
    }

    @GetMapping
    public List<Beverage> getAll() {
        return service.getAll(); // 200
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Beverage create(@Valid @RequestBody BeverageRequestDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public Beverage update(@PathVariable Long id, @Valid @RequestBody BeverageRequestDto dto) {
        return service.update(id,dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
