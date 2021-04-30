package com.kim9fe.test.multiplication.service;

import com.kim9fe.test.multiplication.domain.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultiplicationServiceImpl implements MultiplicationService {
    
    @Autowired
    private RandomGeneratorService randomGeneratorService;

    //private MultiplicationResultAttemptRepository attemptRepository;
    //private UserRepository userRepository;

    //@Autowired
    public MultiplicationServiceImpl(RandomGeneratorService randomGeneratorService/*
                                    , MultiplicationResultAttemptRepository attemptRepository
                                    , UserRepository userRepository*/){
        this.randomGeneratorService = randomGeneratorService;
        // this.attemptRepository = attemptRepository;
        // this.userRepository = userRepository;
    }

    @Override
    public Multiplication createRandomMultiplication() {
        int factorA = randomGeneratorService.generateRandomFactor();
        int factorB = randomGeneratorService.generateRandomFactor();

        return new Multiplication(factorA, factorB);
    }

    @Override
    public boolean checkAttempt(final MultiplicationResultAttempt resultAttempt)  {

        Boolean correct = resultAttempt.getResultAttemp()
            == resultAttempt.getMultiplication().getFactorA() * resultAttempt.getMultiplication().getFactorB();
        
        return correct;
    }
}
