package com.skr1l.webtask2.repository;

import com.skr1l.webtask2.Entity.Welcome;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RepoBean implements Repo {

    private final List<Welcome> storage = new ArrayList<>();
    private long id = 1;

    @Override
    public Welcome save(Welcome welcome) {
        Welcome saved = new Welcome(id++, welcome.getName(), welcome.getVolume());
        storage.add(saved);
        return saved;
    }

    @Override
    public List<Welcome> findAll() {
        return storage;
    }
}
