package com.solo.tacocloud.repository;

import com.solo.tacocloud.tacos.Ingredient;
import com.solo.tacocloud.tacos.Ingredient.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Repository
public class JdbcIngredientRepository implements IngredientRepository{


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbcTemplate.query("select id, name, type from ingredient", this::mapRowToIngredient);
    }

    @Override
    public Ingredient findOne(String id) {
        return null;
    }

    @Override
    public boolean save(Ingredient ingredient) {
        return false;
    }

    private Ingredient mapRowToIngredient(ResultSet resultSet, int rowNum) throws SQLException {
        String id = resultSet.getString("id");
        String name = resultSet.getString("name");
        Type ingredientType = Type.valueOf(resultSet.getString("type"));
        return new Ingredient(id, name, ingredientType);
    }
}
