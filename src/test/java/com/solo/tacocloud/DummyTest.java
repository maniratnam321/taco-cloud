package com.solo.tacocloud;

import com.solo.tacocloud.domain.Ingredient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

public class DummyTest {

    @Test
    public void scratchPad() {
        Ingredient ingredient = new Ingredient("KCP", "Tomato Ketchup", Ingredient.Type.SAUCE);
    }
}
