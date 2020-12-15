package com.filippau.tc.controller;

import com.filippau.tc.domain.Car;
import com.filippau.tc.domain.User;
import com.filippau.tc.repository.CarRepository;
import com.filippau.tc.services.CarService;
import com.filippau.tc.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/catalog")
@RequiredArgsConstructor
public class CarListController {

    public final CarRepository carRepository;
    public final CarService carService;
    public final UserService userService;

    @Value("${upload.path}")
    private String uploadPath;


    @GetMapping("/add")
    public String addTransport() {
        return "addcar";
    }


    @PostMapping("/add")
    public String addTransport(
            @AuthenticationPrincipal User user,
            @Valid Car car,
            @RequestParam("file") MultipartFile[] files,
            BindingResult bindingResult,
            Model model
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("car", car);
            return "addcar";
        } else {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            int count = 0;
            for (MultipartFile file : files) {
                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + "/" + resultFilename));
                switch (count) {
                    case (0):
                        car.setFilename(resultFilename);
                        break;
                    case (1):
                        car.setFilename2(resultFilename);
                        break;
                    case (2):
                        car.setFilename3(resultFilename);
                        break;
                    case (3):
                        car.setFilename4(resultFilename);
                        break;
                    case (4):
                        car.setFilename5(resultFilename);
                        break;
                }
                count++;
                carService.saveCar(car, user);
            }
            return "redirect:/catalog";
        }
    }

    @GetMapping
    public String cars(Model model) {
        List<Car> allCars = carService.findAllCars();
        model.addAttribute("catalog", allCars);
        return "catalog";
    }

    @GetMapping("{id}")
    public String cars(
            @PathVariable("id") Car car,
            @AuthenticationPrincipal User user,
            Model model
    ) {
        model.addAttribute("user", user);
        model.addAttribute("car", car);
        return "car";
    }

}