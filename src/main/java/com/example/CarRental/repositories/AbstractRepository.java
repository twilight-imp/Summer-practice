package com.example.CarRental.repositories;

import com.example.CarRental.domain.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public abstract class AbstractRepository<T> {

        @PersistenceContext
        private EntityManager entityManager;

        @Transactional
        public T create(T entity){
                entityManager.persist(entity);
                return entity;
        };

        @Transactional
        public Optional<T> findById(Class<T> entityClass, int id){
                return Optional.ofNullable(entityManager.find(entityClass,id));
        };

        @Transactional
        public T update(T entity){
                return entityManager.merge(entity);
        };

        @Transactional
        public void delete(T entity) {
                entityManager.remove(entity);
        }

        @Transactional
        public List<T> getAll(Class<T> entityClass) {
                TypedQuery<T> query = entityManager.createQuery("from " + entityClass.getName(), entityClass);
                return query.getResultList();
        }


}
