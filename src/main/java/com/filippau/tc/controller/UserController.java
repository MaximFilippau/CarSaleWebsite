package com.filippau.tc.controller;

import com.filippau.tc.domain.User;
import com.filippau.tc.repository.UserRepository;
import com.filippau.tc.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {

    public final UserRepository userRepository;
    public final UserService userService;

    @GetMapping("/profile")
    public String profile (
            @AuthenticationPrincipal User user,
            Model model){
        model.addAttribute("user",user);
        return "profile";
    }


    @GetMapping("/login")
    public String login(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "login";
    }

    @PostMapping("/login")
    public String logUser(@RequestParam String username,
                          @RequestParam String password,
                          BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            return "login";

        } else {
            return "redirect:/";
        }
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
