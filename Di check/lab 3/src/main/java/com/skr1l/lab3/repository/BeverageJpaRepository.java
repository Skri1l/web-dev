package com.skr1l.lab3.repository;


import com.skr1l.lab3.model.Beverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeverageJpaRepository extends JpaRepository<Beverage,Long> {
}
