package com.returns.coffee.mapper;

import com.returns.coffee.dto.CoffeePatchDto;
import com.returns.coffee.dto.CoffeePostDto;
import com.returns.coffee.dto.CoffeeResponseDto;
import com.returns.coffee.entity.Coffee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {

    Coffee coffeePostDtoToCoffee(CoffeePostDto coffeePostDto);
    Coffee coffeePatchDtoToCoffee(CoffeePatchDto coffeePatchDto);
    CoffeeResponseDto coffeeToCoffeeResponseDto(Coffee coffee);
}
