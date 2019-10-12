package com.solo.tacocloud.domain;

import lombok.Data;

@Data
public class Order {
    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String creditCardNumber;
    private String creditCardExpiry;
    private String creditCardCvv;
}
