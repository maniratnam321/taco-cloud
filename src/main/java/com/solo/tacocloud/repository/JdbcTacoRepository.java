package com.solo.tacocloud.repository;

import com.solo.tacocloud.tacos.Ingredient;
import com.solo.tacocloud.tacos.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.sql.Types;
import java.time.Instant;
import java.util.List;

@Repository
public class JdbcTacoRepository implements TacoRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTacoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for(String ingredientId: taco.getIngredients()) {
            saveIngredientInfo(ingredientId, tacoId);
        }
        return taco;
    }

    private long saveTacoInfo(Taco taco) {
        taco.setCreatedAt(Instant.now());
        PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory(
                "insert into taco(name, created_at) values (?, ?)",
                Types.VARCHAR,
                Types.TIMESTAMP
        );
        preparedStatementCreatorFactory.setGeneratedKeysColumnNames("id");
        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);
        PreparedStatementCreator preparedStatementCreator = preparedStatementCreatorFactory.newPreparedStatementCreator(
                List.of(taco.getName(),
                new Timestamp(taco.getCreatedAt().toEpochMilli()))
        );
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        System.out.println("Key Holder value: " + keyHolder.toString());
        return keyHolder.getKey().longValue();
    }

    private void saveIngredientInfo(String ingredientId, long tacoId) {
        jdbcTemplate.update("insert into taco_ingredients(taco, ingredient) values(?,?)", tacoId, ingredientId);
    }

}
