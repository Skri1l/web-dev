package com.skr1l.webtask2.Entity;

public class Welcome {

    private Long id;
    private String name;
    private int volume;

    public  Welcome(Long id, String name, int volume) {
        this.id = id;
        this.name = name;
        this.volume = volume;
    }

    public Long getId() {
        return id;
    }

    public int getVolume() {
        return volume;
    }
    public String getName() {
        return name;
    }
}
