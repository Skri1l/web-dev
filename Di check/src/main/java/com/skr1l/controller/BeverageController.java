package com.skr1l.controller;

import com.skr1l.dto.BeverageRequestDto;
import com.skr1l.model.Beverage;
import com.skr1l.service.BeverageService;

import javax.validation.Valid;
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
        return service.getAll(); // 200 + []
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Beverage create(@Valid @RequestBody BeverageRequestDto dto) {
        return service.create(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
