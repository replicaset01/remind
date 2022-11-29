package com.returns.coffee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class CoffeeDto {
    private String korName;
    private String engName;
    private int price;
}
