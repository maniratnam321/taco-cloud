package com.solo.tacocloud.domain;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

@Data
public class Order {

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
}
