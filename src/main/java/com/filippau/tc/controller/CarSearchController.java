package com.filippau.tc.controller;

import com.filippau.tc.domain.Car;
import com.filippau.tc.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/search")
@RequiredArgsConstructor
public class CarSearchController {

    public final CarService carService;

    @GetMapping
    public String getCars(
            @RequestParam(required = false) String brandFilter,
            @RequestParam(required = false) String modelFilter,
            @RequestParam(required = false) String yearFilter,
            @PageableDefault(sort = {"id", "brand"}, direction = Sort.Direction.ASC) Pageable pageable,
            Model model
    ) {
        System.out.println(brandFilter + modelFilter + yearFilter);

        Page<Car> page = carService.findAllCarss(brandFilter, modelFilter, yearFilter, pageable);
        model.addAttribute("search", page);

        System.out.println(page.getTotalElements());
        System.out.println(page.getSize());

        model.addAttribute("hasContent", page.hasContent());

        model.addAttribute("url", "/search");
        model.addAttribute("brandFilter", brandFilter);
        model.addAttribute("modelFilter", modelFilter);
        model.addAttribute("yearFilter", yearFilter);

        return "search";
    }
}
