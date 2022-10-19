package org.example;

import org.example.model.DataCreatorRequestDto;

import java.io.IOException;
import java.util.Random;

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

        DataCreator.createDataFotOneParam(new FunctionOne(request, 7));
        TinyGpExtended tinyGpExtended = new TinyGpExtended("src/main/resources/data/function1.dat", -1);
        tinyGpExtended.evolve();

        var function = "( COS(  COS( ( COS( 5.914799065538958) + -4.122307634293506))) + (((-45.50275332686009 / -41.93761879577522) +  SIN( X1 )) + ( COS( -25.63420283763658) +  SIN( ( SIN( ( SIN( (-45.50275332686009 + (37.98279082013164 + -38.1255508021896))) - ( COS( -52.02533614068236) * (-20.96334258997463 / (-55.96684585728706 - ((-85.20443248080582 * 89.90343033775352) - ((54.930656106835585 / 18.228231637292566) - -29.457774969341372))))))) - -55.447395218894634)))))\n";
        ExcelConverter excelConverter = new ExcelConverter();
        excelConverter.parseFunctionToExcel(function, -PI/2, PI/2 ,0.01, new FunctionOne(request, 7));

        ResultsOptimizer optimizer = new ResultsOptimizer();
        System.out.println(optimizer.optimize(function));
    }
}