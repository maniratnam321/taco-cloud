package com.solo.tacocloud;

import com.solo.tacocloud.tacos.Taco;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;


public class DummyTest {

    @Test
    public void scratchPad() {
        Taco taco = new Taco();
        Set<ConstraintViolation<Taco>> constraintViolations = Validation.buildDefaultValidatorFactory().getValidator().validate(taco);
        for(ConstraintViolation constraintViolation: constraintViolations) {
            System.out.println(constraintViolation.getMessage());
        }

    }
}
