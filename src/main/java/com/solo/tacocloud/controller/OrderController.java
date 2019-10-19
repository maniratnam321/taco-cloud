package com.solo.tacocloud.controller;


import com.solo.tacocloud.repository.OrderRepository;
import com.solo.tacocloud.tacos.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/form")
    public String showOrderForm() {
        return "orders/form";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
        log.info("Received order: " + order.toString());
        if(errors.hasErrors()) {
            return "orders/form";
        }
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:";
    }
}
