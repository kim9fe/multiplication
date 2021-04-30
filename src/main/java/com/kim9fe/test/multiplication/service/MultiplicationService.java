package com.kim9fe.test.multiplication.service;

import com.kim9fe.test.multiplication.domain.Multiplication;
import com.kim9fe.test.multiplication.domain.MultiplicationResultAttempt;

public interface MultiplicationService {

    Multiplication createRandomMultiplication();

    boolean checkAttempt(final MultiplicationResultAttempt resultAttempt);    
}
