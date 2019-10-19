package com.solo.tacocloud.tacos;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
public class Order {

    private long  id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "State cannot be blank")
    private String state;

    @Digits(integer = 5, fraction = 0, message = "zip code must be 5 digits long")
    private String zip;

    @NotBlank(message = "not a valid credit card number")
    private String creditCardNumber;

    @NotBlank(message = "Credit card expiry cannot be blank")
    private String creditCardExpiry;

    @Digits(integer = 3, fraction = 0, message = "CVV must be 3 digits long")
    private String creditCardCvv;

    private Instant placedAt;

    private List<Taco> orderedTacos;

    public void addToOrder(Taco taco) {
        if(orderedTacos == null) {
            orderedTacos = new ArrayList<>();
        }
        orderedTacos.add(taco);
    }
}
