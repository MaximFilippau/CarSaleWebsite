package com.filippau.tc.controller;

import com.filippau.tc.domain.Car;
import com.filippau.tc.domain.User;
import com.filippau.tc.services.CarService;
import com.filippau.tc.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;
    public final CarService carService;

    @GetMapping("/profile")
    public String profile (
            @AuthenticationPrincipal User user,
            Model model){
        model.addAttribute("user",user);
        model.addAttribute("my_cars", carService.findByUser(user));
        return "profile";
    }

    @PostMapping("/remove/{car}")
    public String deleteCar(
            @PathVariable Car car){
        carService.deleteCar(car);
        return "redirect:/profile";
    }

    @GetMapping("/listofusers")
    public String listOfUsers(Model model){
        List <User> users = userService.users();
        model.addAttribute("users", users);
        model.addAttribute("message1", "");
        return "listofusers";
    }

    @PostMapping("/listofusers/{id}/remove")
    public String deleteUser(@PathVariable(value = "id") long id){
            userService.deleteUser(id);
            return "redirect:/listofusers";
    }
}
