package com.filippau.tc.services;

import com.filippau.tc.domain.Car;
import com.filippau.tc.domain.User;
import com.filippau.tc.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    public final CarRepository carRepository;

    public void saveCar (Car car, User user){
        car.setCarOwner(user);
        carRepository.save(car);
    }

    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    public Page<Car> findAllCarss(String brandFilter, String modelFilter, String yearFilter, Pageable pageable) {

        if (StringUtils.isEmpty(brandFilter) && StringUtils.isEmpty(modelFilter)&& StringUtils.isEmpty(yearFilter)) {
            return carRepository.findAll(pageable);
        } else {
            return carRepository.findByBrandStartingWithIgnoreCaseAndModelStartingWithIgnoreCaseAndYear(
                    brandFilter, modelFilter, yearFilter, pageable);
        }
    }
}
