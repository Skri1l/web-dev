package com.skr1l.webtask2.repository;

import com.skr1l.webtask2.Entity.Welcome;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Repo {

    Welcome save(Welcome welcome);
    List<Welcome> findAll();

}
