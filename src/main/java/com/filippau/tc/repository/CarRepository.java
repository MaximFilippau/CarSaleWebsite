package com.filippau.tc.repository;

import com.filippau.tc.domain.Car;
import com.filippau.tc.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    Page<Car> findByBrandStartingWithIgnoreCaseAndModelStartingWithIgnoreCaseAndYearStartingWith(
            @Param("brand") String brand,
            @Param("model") String model,
            @Param("year") String year,
            Pageable pageable
    );

    List<Car> findByCarOwner(User user);
    Car findById (Car car);
}
