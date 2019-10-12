package com.solo.tacocloud.tacos;


import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Taco {


    @Size(min = 5, message = "size must have atleast 5 characters")
    private String name;

    @Size(min = 3, message = "must have atleast 3 ingredients")
    private List<String> ingredients;

}
