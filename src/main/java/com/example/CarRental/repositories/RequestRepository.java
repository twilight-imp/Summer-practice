package com.example.CarRental.repositories;

import com.example.CarRental.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    @Query(value = "select count(*) from Request r " +
            "join r.client cl where cl.lastName = :lastName " +
            "and r.requestStatus = 'CONFIRMED' " +
            "and r.dateEnd < :dateEnd"
    )
    List<Request> findCountByClientAndStatusAndDate(@Param(value = "lastName") String lastName,
                                           @Param(value = "dateEnd")LocalDateTime dateEnd
    );
}
