package org.example;

import org.example.model.DataCreatorRequestDto;

import java.io.IOException;

import static java.lang.Math.*;


public class Main
{
    public static void main(String[] args) throws IOException {

        DataCreatorRequestDto request = new DataCreatorRequestDto(
                100,
                -100,
                100,
                -PI/2,
                PI/2,
                0.01,
                "data/function1.dat"
        );

//        DataCreator.createDataFotOneParam(new FunctionOneParam(request, 7));
//        TinyGpExtended tinyGpExtended = new TinyGpExtended("src/main/resources/" + request.fileName(), -1);
//        tinyGpExtended.evolve();

        var function = TxtFileReader.read("zad1.txt");
//        ExcelConverter excelConverter = new ExcelConverter();
//        excelConverter.parseOneParamFunctionToExcel(function, request, new FunctionOneParam(request, 7));

        ResultsOptimizer optimizer = new ResultsOptimizer();
        System.out.println(optimizer.optimize(function));
    }
}