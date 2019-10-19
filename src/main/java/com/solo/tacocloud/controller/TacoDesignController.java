package com.solo.tacocloud.controller;


import com.solo.tacocloud.repository.IngredientRepository;
import com.solo.tacocloud.repository.TacoRepository;
import com.solo.tacocloud.tacos.Ingredient;
import com.solo.tacocloud.tacos.Order;
import com.solo.tacocloud.tacos.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.solo.tacocloud.tacos.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class TacoDesignController {

    private IngredientRepository ingredientRepository;

    private TacoRepository tacoRepository;

    @Autowired
    public TacoDesignController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @GetMapping
    public String showTacoDesign(Model model) {
        log.info("Rendering Taco Designer................................");
        List<Ingredient> ingredients = retrieveIngredients();
        log.info("Retrieved following ingredients from Database: " + ingredients.toString());
        for (Type type : Type.values()) {
            model.addAttribute(type.name().toLowerCase(), filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco designedTaco, Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            log.warn("Entered taco design has validation errors: " + errors.getAllErrors().toString());
            return "design";
        }
        log.warn("Taco Design Page WIP");
        System.out.println("Taco: " + designedTaco.toString());
        tacoRepository.save(designedTaco);
        order.addToOrder(designedTaco);
        return "redirect:orders/form";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(ingredient -> ingredient.getType().equals(type)).collect(Collectors.toList());
    }

    private List<Ingredient> retrieveIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);
        return ingredients;
    }

}
