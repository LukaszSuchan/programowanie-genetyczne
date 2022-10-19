package org.example;

import org.example.contract.MathOneParamFunction;
import org.example.model.DataCreatorRequestDto;

import static java.lang.Math.*;
import static java.lang.Math.log;

public class FunctionOne implements MathOneParamFunction {

    private final DataCreatorRequestDto request;
    private final int function;

    public FunctionOne(DataCreatorRequestDto request, int functionNumber) {
        this.request = request;
        this.function = functionNumber;
    }

    @Override
    public double call(double x) {
        return switch (function) {
            case 1 -> function1(x);
            case 2 -> function2(x);
            case 3 -> function3(x);
            case 7 -> function7(x);
            default -> x;
        };
    }

    @Override
    public DataCreatorRequestDto getRequest() {
        return request;
    }

    public double function1(double x) {
        return (5 * pow(x, 3) - 2 * pow(x, 2) + 3 * x - 17);
    }

    public double function2(double x) {
        return (sin(x) + cos(x));
    }

    public double function3(double x) {
        return (2 * log(x));
    }

    public double function7(double x) {
        return (sin(x) + PI / 2);
    }
}
