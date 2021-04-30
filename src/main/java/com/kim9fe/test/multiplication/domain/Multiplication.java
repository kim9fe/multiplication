package com.kim9fe.test.multiplication.domain;

//import com.google.gson.Gson;

//import lombok.RequiredArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public final class Multiplication {
    private final int factorA;
    private final int factorB;

    public Multiplication(int factorA, int factorB){
        this.factorA = factorA;
        this.factorB = factorB;
        //this.result = factorA * factorB;
    }

    // deserialize 를 위해 필요
    Multiplication(){
        this(0, 0);
    }

    @Override
    public String toString() {
        //Gson gson = new Gson();
        //return gson.toJson(this);

        return "Multiplication {"
                + "factorA=" + factorA
                + ", factorB=" + factorB
                + "}";
    }
}
