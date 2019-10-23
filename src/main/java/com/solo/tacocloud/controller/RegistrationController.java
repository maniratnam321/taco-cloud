package com.solo.tacocloud.controller;

import com.solo.tacocloud.repository.UserRepository;
import com.solo.tacocloud.user.RegistrationForm;
import com.solo.tacocloud.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute
    public RegistrationForm registrationForm() {
        return new RegistrationForm();
    }

    @GetMapping
    public String showRegistrationPage() {
        return "registration";
    }

    @PostMapping
    public String processRegistrationForm(RegistrationForm registrationForm) {
        User savedUser = userRepository.save(registrationForm.toUser(passwordEncoder));
        if (savedUser != null) {
            log.info("User registered: " + savedUser);
            return "redirect:/";
        }
        log.error("Error occurred while trying to register : " + registrationForm.toString());
        return "redirect:/registration";
    }
}
