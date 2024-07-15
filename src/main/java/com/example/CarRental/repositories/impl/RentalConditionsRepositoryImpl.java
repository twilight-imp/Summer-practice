package com.example.CarRental.repositories.impl;

import com.example.CarRental.domain.RentalConditions;
import com.example.CarRental.repositories.AbstractRepository;
import com.example.CarRental.repositories.RentalConditionsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RentalConditionsRepositoryImpl extends AbstractRepository<RentalConditions> implements RentalConditionsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public RentalConditions findByCarId(Integer id) {
        TypedQuery<RentalConditions> query = entityManager.createQuery(
                "select rc from RentalConditions rc " +
                        "join rc.car c where c.id = :id", RentalConditions.class);
        return query.setParameter("id", id)
                .getSingleResult();
    }
}

