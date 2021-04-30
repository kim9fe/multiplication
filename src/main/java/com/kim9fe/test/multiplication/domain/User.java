package com.kim9fe.test.multiplication.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public final class User {
    private final String alias;

    User(){
        alias = null;
    }
}
