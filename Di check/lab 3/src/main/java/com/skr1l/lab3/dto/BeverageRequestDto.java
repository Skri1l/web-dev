package com.skr1l.lab3.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class BeverageRequestDto {

    @NotBlank(message = "can't be blank")
    private String name;
    @Min(value = 1, message = "must be greater")
    private int price;
    public BeverageRequestDto() {

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
