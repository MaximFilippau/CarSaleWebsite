package com.filippau.tc.controller;

import com.filippau.tc.domain.User;
import com.filippau.tc.repository.UserRepository;
import com.filippau.tc.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    public final UserRepository userRepository;
    public final UserService userService;

    @GetMapping("/registration")
    public String registration(User user,
                               Model model
    ){
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ){
        if (bindingResult.hasErrors()){
            model.addAttribute("user", user);
            return "registration";
        } else {
            userService.addUser(user);
            return "redirect:/mailsend";
        }
    }

    @GetMapping("/mailsend")
    public String mailSendPage(
            Model model
    ) {
        model.addAttribute("message","A confirmation message has been sent to your mail");
        return "mailsend";
    }

    @GetMapping("/activate/{code}")
    public String activate(
            Model model,
            @PathVariable String code
    ){
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "You have successfully verified your mail.");
        } else {
            model.addAttribute("message", "Error, no such code exists.");
        }
        return "activation";
    }
}
