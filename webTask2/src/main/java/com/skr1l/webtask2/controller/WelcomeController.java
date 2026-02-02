package com.skr1l.webtask2.controller;
import com.skr1l.webtask2.Entity.Welcome;
import com.skr1l.webtask2.dto.EntityDto;
import com.skr1l.webtask2.service.WelcomeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    private WelcomeService welcomeService;

    public WelcomeController(WelcomeService welcomeService) {
        this.welcomeService = welcomeService;
    }

    @GetMapping
    public List<Welcome> getAll() {
        return welcomeService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Welcome create(@Valid @RequestBody EntityDto dto) {
        return welcomeService.create(dto);
    }

}
