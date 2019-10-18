package com.solo.tacocloud.repository;

import com.solo.tacocloud.tacos.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Ingredient findOne(String id);

    boolean save(Ingredient ingredient);
}
