package com.example.CarRental.repositories.impl;

import com.example.CarRental.domain.Payment;
import com.example.CarRental.repositories.AbstractRepository;
import com.example.CarRental.repositories.PaymentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentRepositoryImpl extends AbstractRepository<Payment> implements PaymentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Payment findByRequest(int idRequest) {
        TypedQuery<Payment> query = entityManager.createQuery(
                "select p from Payment p " +
                        "join p.request r " +
                        "where r.id = :idRequest ", Payment.class);
        return query.setParameter("idRequest", idRequest)
                .getSingleResult();
    }

    @Override
    public List<Payment> findByClientId(int clientId) {
        TypedQuery<Payment> query = entityManager.createQuery(
                "select p from Payment p " +
                        "join p.request r " +
                        "where r.clientId = :clientId ", Payment.class);
        return query.setParameter("clientId", clientId)
                .getResultList();
    }
}
