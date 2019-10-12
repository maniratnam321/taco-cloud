package com.solo.tacocloud.controller;


import com.solo.tacocloud.domain.Ingredient;
import com.solo.tacocloud.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.solo.tacocloud.domain.Ingredient.*;

@Slf4j
@Controller
@RequestMapping("/design")
public class TacoDesignController {

    @GetMapping
    public String showTacoDesign(Model model) {
        log.info("Rendering Taco Designer................................");
        List<Ingredient> ingredients = retrieveIngredients();
        for(Type type: Type.values()) {
            model.addAttribute(type.name().toLowerCase(), filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(Taco designedTaco) {
        log.warn("Taco Design Page WIP");
        System.out.println("Taco: " + designedTaco.toString());
        return "redirect:";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(ingredient -> ingredient.getType().equals(type)).collect(Collectors.toList());
    }

    private List<Ingredient> retrieveIngredients() {
        return Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CHK", "Chicken", Type.PROTEIN),
                new Ingredient("PPJ", "Pepper Jack", Type.CHEESE),
                new Ingredient("CCH", "Cheddar Cheese", Type.CHEESE),
                new Ingredient("SAL", "Salsa", Type.SAUCE),
                new Ingredient("TMT", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LTC", "Lettuce", Type.VEGGIES)
        );
    }

}
