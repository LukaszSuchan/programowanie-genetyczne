package org.example;

import org.example.model.DataCreatorRequestDto;

import static java.lang.Math.pow;

public class FunctionOne implements MathOneParamFunction{

    private final DataCreatorRequestDto request = new DataCreatorRequestDto(
            100,
            -100,
            100,
            -1000,
            1000,
            10,
            "function1.dat"
    );
    @Override
    public double call(double x) {
        return function1(x);
    }

    @Override
    public DataCreatorRequestDto getRequest() {
        return request;
    }

    private double function1(double x){
        return (5*pow(x, 3) - 2* pow(x, 2) + 3*x - 17);
    }
}
