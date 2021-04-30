package com.kim9fe.test.multiplication.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public final class MultiplicationResultAttempt {
    private final User user;
    private final Multiplication multiplication;
    private final int resultAttemp;

    private final boolean correct;

    MultiplicationResultAttempt(){
        user = null;
        multiplication = null;
        resultAttemp = -1;
        correct = false;
    }
}
