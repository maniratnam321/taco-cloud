package com.solo.tacocloud.controller;


import com.solo.tacocloud.tacos.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/form")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "orders/form";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors) {
        log.info("Received order: " + order.toString());
        if(errors.hasErrors()) {
            return "orders/form";
        }
        return "redirect:";
    }
}
