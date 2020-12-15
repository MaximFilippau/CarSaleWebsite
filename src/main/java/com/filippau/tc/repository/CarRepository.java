package com.filippau.tc.repository;

import com.filippau.tc.domain.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CarRepository extends JpaRepository<Car, Long> {

    Page<Car> findByBrandStartingWithIgnoreCaseAndModelStartingWithIgnoreCaseAndYear(
            @Param("brand") String brand,
            @Param("model") String model,
            @Param("year") String year,
            Pageable pageable
    );
}
