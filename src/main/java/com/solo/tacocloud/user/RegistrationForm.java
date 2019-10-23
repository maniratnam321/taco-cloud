package com.solo.tacocloud.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@RequiredArgsConstructor
public class RegistrationForm {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String city;
    private String zip;
    private String state;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password), firstName + lastName, streetAddress, city, zip, state);
    }
}
