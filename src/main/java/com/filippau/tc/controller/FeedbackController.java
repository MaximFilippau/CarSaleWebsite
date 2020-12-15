package com.filippau.tc.controller;

import com.filippau.tc.domain.User;
import com.filippau.tc.services.MailSenderService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    public final MailSenderService mailSenderService;

    @GetMapping
    public String feedback(Model model) {
        model.addAttribute("title", "Feedback");
        return "feedback";
    }

    @PostMapping
    public String sendFeed(
            @RequestParam String message,
            @AuthenticationPrincipal User user
    ) {
        mailSenderService.sendFeed(user.getEmail(), message);

        return "redirect:/";
    }
}
