package org.example;

import org.example.model.DataCreatorRequestDto;

public  interface MathOneParamFunction {
    double call(double x);
    DataCreatorRequestDto getRequest();
}
