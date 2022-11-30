package com.returns.coffee.controller;

import com.returns.coffee.dto.CoffeePatchDto;
import com.returns.coffee.dto.CoffeePostDto;
import com.returns.coffee.dto.CoffeeResponseDto;
import com.returns.coffee.entity.Coffee;
import com.returns.coffee.mapper.CoffeeMapper;
import com.returns.coffee.service.CoffeeService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/coffees")
@Validated
public class CoffeeController {

    private CoffeeService coffeeService;
    private CoffeeMapper mapper;

    public CoffeeController(CoffeeService coffeeService, CoffeeMapper mapper) {
        this.coffeeService = coffeeService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postCoffee(@Valid @RequestBody CoffeePostDto coffeeDto) {

        Coffee coffee = mapper.coffeePostDtoToCoffee(coffeeDto);

        Coffee response = coffeeService.createCoffee(coffee);

        return new ResponseEntity<>(mapper.coffeeToCoffeeResponseDto(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(@PathVariable("coffee-id") @Positive long coffeeId,
                                       @Valid @RequestBody CoffeePatchDto coffeeDto) {
        coffeeDto.setCoffeeId(coffeeId);
        Coffee response = coffeeService.updateCoffee(mapper.coffeePatchDtoToCoffee(coffeeDto));

        return new ResponseEntity<>(mapper.coffeeToCoffeeResponseDto(response), HttpStatus.OK);
    }

    @GetMapping("/{coffee-id")
    public ResponseEntity findCoffee(@PathVariable("coffee-id") long coffeeId) {
        Coffee response = coffeeService.findCoffee(coffeeId);

        return new ResponseEntity<>(mapper.coffeeToCoffeeResponseDto(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity findCoffees() {
        List<Coffee> coffees = coffeeService.findCoffees();

        List<CoffeeResponseDto> response =
                coffees.stream()
                        .map(coffee -> mapper.coffeeToCoffeeResponseDto(coffee))
                        .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") long coffeeId) {
        coffeeService.deleteCoffee(coffeeId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

