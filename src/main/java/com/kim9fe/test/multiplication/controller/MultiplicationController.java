package com.kim9fe.test.multiplication.controller;

import com.kim9fe.test.multiplication.domain.Multiplication;
import com.kim9fe.test.multiplication.service.MultiplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multiplications")
public class MultiplicationController {
    
    private MultiplicationService multiplicationService;

    @Autowired
    public MultiplicationController(final MultiplicationService multiplicationService){
        this.multiplicationService = multiplicationService;
    }

    @GetMapping(value="/random")
    public Multiplication getRandomMultiplication(){
        return multiplicationService.createRandomMultiplication();
    }
}
