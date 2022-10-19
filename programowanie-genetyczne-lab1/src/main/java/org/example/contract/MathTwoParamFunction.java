package org.example.contract;

import org.example.model.DataCreatorRequestDto;

public interface MathTwoParamFunction {
    double call(double x, double y);
    DataCreatorRequestDto getRequest();
}
