package org.example;


import org.example.contract.MathOneParamFunction;
import org.example.contract.MathTwoParamFunction;

import java.io.*;
import java.util.stream.DoubleStream;

import static java.lang.Math.round;

public class DataCreator {

    public static double FIVE = 100000.0;

    public DataCreator() {}

    public static void createDataFotOneParam(MathOneParamFunction function) throws IOException {

        var dataCreatorRequestDto = function.getRequest();
        var range = DoubleStream.iterate(dataCreatorRequestDto.rangeStart(), d -> d <= dataCreatorRequestDto.rangeEnd()
                , d -> d + dataCreatorRequestDto.step())
                .boxed()
                .map(d -> round(d*100.0)/100.0)
                .toList();

        FileWriter fileWriter = new FileWriter("src/main/resources/" + dataCreatorRequestDto.fileName());
        fileWriter.append(1 + " " +
                          dataCreatorRequestDto.numberOfConst() + " " +
                          dataCreatorRequestDto.constStartRange() + " " +
                          dataCreatorRequestDto.constEndRange() + " " +
                          range.size() + "\n");

        range.forEach(
                x -> {
                    try {
                        fileWriter.append(x + "\t" + round(function.call(x)*FIVE)/FIVE + "\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        fileWriter.close();

    }

    public static void createDataForTwoParam(MathTwoParamFunction function) throws IOException {

        var dataCreatorRequestDto = function.getRequest();
        var range = DoubleStream.iterate(dataCreatorRequestDto.rangeStart(), d -> d <= dataCreatorRequestDto.rangeEnd()
                        , d -> d + dataCreatorRequestDto.step())
                .boxed()
                .toList();

        FileWriter fileWriter = new FileWriter("src/main/resources/function.dat");
        fileWriter.append(2 + " " +
                dataCreatorRequestDto.numberOfConst() + " " +
                dataCreatorRequestDto.constStartRange() + " " +
                dataCreatorRequestDto.constEndRange() + " " +
                range.size() + "\n");

        range.forEach(
                x -> {
                    try {
                        fileWriter.append(x + "\t" + x + "\t" + function.call(x, x));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        fileWriter.close();

    }



}
