package com.example.CarRental.repositories.impl;

import com.example.CarRental.domain.Car;
import com.example.CarRental.domain.CarStatus;
import com.example.CarRental.repositories.AbstractRepository;
import com.example.CarRental.repositories.CarRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CarRepositoryImpl extends AbstractRepository<Car> implements CarRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Car> findByCharacteristicsAndRegionAndCost(String city, String district, String brand, String model, String color, int numberSeats, int costPerDay, CarStatus carStatus) {
        TypedQuery<Car> query = entityManager.createQuery(
                "select c from Car c " +
                        "join c.office o " +
                        "where o.city = :city and o.district = :district" +
                        "and c.costPerDay <= :cost and c.brand = :brand and c.model = :model and c.color = :color and c.numberSeats = :numberSeats " +
                        "and c.status = :carStatus " , Car.class);
        return query.setParameter("city", city)
                .setParameter("district", district)
                .setParameter("cost", costPerDay)
                .setParameter("brand", brand)
                .setParameter("model", model)
                .setParameter("color", color)
                .setParameter("numberSeats", numberSeats)
                .setParameter("carStatus", carStatus)
                .getResultList();
    }

    @Override
    public Car findByDateRequest(int idCar, LocalDateTime dateStart, LocalDateTime dateEnd) {
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
    }

    @Override
    public void updateStatus(int id, CarStatus carStatus) {
        TypedQuery<Car> query = entityManager.createQuery(
                "UPDATE Car AS c " +
                        " SET c.requestStatus = :requestStatus " +
                        " WHERE c.id = :id ", Car.class);
        query.setParameter("carStatus", carStatus)
                .setParameter("id", id)
                .getResultList();
    }

}
