package com.skr1l.lab3.manager;

import com.skr1l.lab3.model.Beverage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BeverageEntityManager {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Beverage beverage) {
        entityManager.persist(beverage);
    }

    public Beverage findById(Long id) {
        return entityManager.find(Beverage.class, id);
    }

    public List<Beverage> findAll() {
        return entityManager
                .createQuery("SELECT b FROM Beverage b", Beverage.class)
                .getResultList();
    }

    @Transactional
    public Beverage update(Beverage beverage) {
        return entityManager.merge(beverage);
    }

    @Transactional
    public void delete(Long id) {
        Beverage beverage = entityManager.find(Beverage.class, id);
        if (beverage != null) {
            entityManager.remove(beverage);
        }
    }
}
