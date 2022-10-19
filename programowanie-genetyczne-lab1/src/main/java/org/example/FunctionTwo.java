package org.example;

import org.example.contract.MathTwoParamFunction;
import org.example.model.DataCreatorRequestDto;

import static java.lang.Math.*;

public class FunctionTwo implements MathTwoParamFunction {

    private final DataCreatorRequestDto request;
    private final int function;

    public FunctionTwo(DataCreatorRequestDto request, int functionNumber) {
        this.request = request;
        this.function = functionNumber;
    }
    @Override
    public double call(double x, double y) {
        return switch (function) {
            case 1 -> function4(x,y);
            case 2 -> function5(x,y);
            case 3 -> function6(x,y);
            default -> x;
        };
    }

    @Override
    public DataCreatorRequestDto getRequest() {
        return null;
    }

    public double function4(double x, double y){
        return x + 2*y;
    }
    public double function5(double x, double y){
        return sin(x/2) + 2*cos(x);
    }
    public double function6(double x, double y) {
        return pow(x, 2) + 3 * x * y - 7 * y + 1;
    }
}
