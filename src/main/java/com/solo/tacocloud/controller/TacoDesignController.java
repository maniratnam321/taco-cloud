package com.solo.tacocloud.controller;


import com.solo.tacocloud.repository.IngredientRepository;
import com.solo.tacocloud.tacos.Ingredient;
import com.solo.tacocloud.tacos.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.solo.tacocloud.tacos.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
public class TacoDesignController {

    private IngredientRepository ingredientRepository;

    @Autowired
    public TacoDesignController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
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
    public String processDesign(@Valid Taco designedTaco, Errors errors) {
        if (errors.hasErrors()) {
            log.warn("Entered taco design has validation errors: " + errors.getAllErrors().toString());
            return "design";
        }
        log.warn("Taco Design Page WIP");
        System.out.println("Taco: " + designedTaco.toString());
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
