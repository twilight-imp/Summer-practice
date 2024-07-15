package com.example.CarRental.repositories.impl;

import com.example.CarRental.domain.Client;
import com.example.CarRental.repositories.AbstractRepository;
import com.example.CarRental.repositories.ClientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepositoryImpl extends AbstractRepository<Client> implements ClientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Client findByPhone(String phone) {
        TypedQuery<Client> query = entityManager.createQuery(
                "select cl from Client cl where cl.phone = :phone", Client.class);
        return query.setParameter("phone", phone)
                .getSingleResult();
    }
}
