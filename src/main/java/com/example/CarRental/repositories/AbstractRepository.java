package com.example.CarRental.repositories;

import com.example.CarRental.domain.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public abstract class AbstractRepository<T>{
        @PersistenceContext
        private EntityManager entityManager;

        @Transactional
        public void create(T entity){
                entityManager.persist(entity);
        };

        @Transactional
        public T findById(Class<T> entityClass, int id){
                return entityManager.find(entityClass,id);
        };

        @Transactional
        public T update(T entity){
                return entityManager.merge(entity);
        };

        @Transactional
        public void delete(T entity) {
                entityManager.remove(entity);
        }


}
