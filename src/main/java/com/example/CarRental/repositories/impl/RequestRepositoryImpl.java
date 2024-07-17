package com.example.CarRental.repositories.impl;

import com.example.CarRental.domain.Request;
import com.example.CarRental.domain.RequestStatus;
import com.example.CarRental.repositories.AbstractRepository;
import com.example.CarRental.repositories.RequestRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class RequestRepositoryImpl extends AbstractRepository<Request>  implements RequestRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Request> findByClientAndStatusAndDate(String lastName, RequestStatus requestStatus, LocalDateTime dateEnd) {
        TypedQuery<Request> query = entityManager.createQuery(
                "select r from Request r " +
                        "join r.client cl where cl.lastName = :lastName " +
                        "and r.requestStatus = :status " +
                        "and r.dateEnd < :dateEnd", Request.class);
        return query.setParameter("lastName", lastName)
                .setParameter("status", requestStatus)
                .setParameter("dateEnd", dateEnd)
                .getResultList();
    }


    @Override
    public Request findByPayment(int paymentId) {
        TypedQuery<Request> query = entityManager.createQuery(
                "select r from Request r " +
                        "join r.payment p where p.id = :paymentId ", Request.class);
        return query.setParameter("paymentId",paymentId)
                .getSingleResult();
    }
}
