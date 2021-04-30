package com.kim9fe.test.multiplication;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.kim9fe.test.multiplication.service.RandomGeneratorService;
import com.kim9fe.test.multiplication.service.RandomGeneratorServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomGeneratorServiceTest {

    private RandomGeneratorService randomGeneratorService;

    @BeforeEach
    public void setUp(){
        randomGeneratorService = new RandomGeneratorServiceImpl();
    }

    @Test
    void generateRandomFactorIsBetweenExpectedLimits(){

        List<Integer> randomFactors = IntStream.range(0, 1000)
                .map(i -> randomGeneratorService.generateRandomFactor())
                .boxed()
                .collect(Collectors.toList());
        
        assertThat(randomFactors).allSatisfy(x -> {assertThat(x).isBetween(11, 100);});
    }
}
