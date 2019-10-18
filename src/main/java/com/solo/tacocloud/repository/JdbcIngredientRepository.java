package com.solo.tacocloud.repository;

import com.solo.tacocloud.tacos.Ingredient;
import com.solo.tacocloud.tacos.Ingredient.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class JdbcIngredientRepository implements IngredientRepository{


    public static final int FIRST_ELEMENT = 0;
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
        List<Ingredient> ingredients = jdbcTemplate.query("select id, name, type from ingredient where id=?", this::mapRowToIngredient, id);
        if(ingredients.size() > 1) {
            log.warn("Multiple ingredients found with id: " + id + " | " + "Ingredients: " + ingredients.toString() +
                    "\nData Integrity should be re-checked");
        }
        return ingredients.get(FIRST_ELEMENT) ;
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update("insert into ingredient(id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().name());
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet resultSet, int rowNum) throws SQLException {
        String id = resultSet.getString("id");
        String name = resultSet.getString("name");
        Type ingredientType = Type.valueOf(resultSet.getString("type"));
        return new Ingredient(id, name, ingredientType);
    }
}
