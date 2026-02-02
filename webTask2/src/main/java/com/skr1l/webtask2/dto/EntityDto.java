package com.skr1l.webtask2.dto;

import com.skr1l.webtask2.Entity.Welcome;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EntityDto {

    @NotBlank
    private String name;
    @NotNull
    @Min(1)
    private int volume;

    public EntityDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVolume(int volume){
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }
}
