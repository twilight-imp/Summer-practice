package com.example.CarRental.repositories.impl;

import com.example.CarRental.domain.Car;
import com.example.CarRental.domain.CarStatus;
import com.example.CarRental.repositories.AbstractRepository;
import com.example.CarRental.repositories.CarRepository;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CarRepositoryImpl extends AbstractRepository<Car> implements CarRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Car> findByCharacteristicsAndRegionAndCost(String city, String district, int numberSeats, int costPerDay, CarStatus carStatus) {
        TypedQuery<Car> query = entityManager.createQuery(
                "select c from Car c " +
                        "join c.office o " +
                        "where o.city = :city and o.district = :district " +
                        "and c.costPerDay <= :cost and c.numberSeats = :numberSeats " +
                        "and c.carStatus = :carStatus " , Car.class);
        return query.setParameter("city", city)
                .setParameter("district", district)
                .setParameter("cost", costPerDay)
                .setParameter("numberSeats", numberSeats)
                .setParameter("carStatus", carStatus)
                .getResultList();
    }

    @Override
    public Car findByDateRequest(int idCar, LocalDateTime dateStart, LocalDateTime dateEnd) {
        try {
            TypedQuery<Car> query = entityManager.createQuery(
                    "select c from Car c " +
                            "join c.request r " +
                            "where c.id = :id " +
                            "and ((:dateStart < r.dateStart and :dateEnd < r.dateStart) or (:dateStart > r.dateEnd and :dateEnd > r.dateEnd))"
                    , Car.class);
            return query.setParameter("id", idCar)
                    .setParameter("dateStart", dateStart)
                    .setParameter("dateEnd", dateEnd)
                    .getSingleResult();
        }catch (NoResultException exception){}
        return null;
    }


}
