package org.example.contract;

import org.example.model.DataCreatorRequestDto;

public  interface MathOneParamFunction {
    double call(double x);
    DataCreatorRequestDto getRequest();
}
