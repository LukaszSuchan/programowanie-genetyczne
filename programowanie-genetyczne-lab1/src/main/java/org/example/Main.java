package org.example;

import org.example.model.DataCreatorRequestDto;

import java.io.IOException;
import java.util.Random;

import static java.lang.Math.*;


public class Main
{
    public static void main(String[] args) throws IOException {
//        Random rd = new Random();
//        DataCreator.createDataFotOneParam(new FunctionOne());
//        TinyGp tinyGp = new TinyGp("src/main/resources/function1.dat", abs(rd.nextInt()));
//        tinyGp.evolve();
        var function = "(-5.0 - ((((-5.0 * (-5.0 / ((-5.0 / (((-5.0 * (X1  - -5.0)) - (-5.0 + ((-5.0 * X1 ) * (((-5.0 - -5.0) + (X1  - -5.0)) + X1 )))) - (-5.0 + -5.0))) * -5.0))) / -5.0) - -5.0) + (-5.0 * (((X1  * (X1  - (-5.0 / -5.0))) * (X1  - (X1  / (-5.0 * X1 )))) - (-5.0 / -5.0)))))";
//        ExcelConverter excelConverter = new ExcelConverter();
//        excelConverter.parseFunctionToExcel(function, -10, 10 ,0.1, new FunctionOne());

        ResultsOptimizer optimizer = new ResultsOptimizer();
        System.out.println(optimizer.optimize(function));
    }


    public double function2(double x){
        return (sin(x) + cos(x));
    }
    public double function3(double x){
        return (2 * log(x));
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