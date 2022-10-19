package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.DoubleStream;

import static java.lang.Math.round;

public class ExcelConverter {

    public ExcelConverter() {
    }

    public void parseFunctionToExcel(String function, int rangeStart, int rangeEnd, double step, MathOneParamFunction originalFunction) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("GP plots");
        XSSFRow row;
        workbook.setForceFormulaRecalculation(true);
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

        Map<Double, String> results = new HashMap<>();


        var range = DoubleStream.iterate(rangeStart, d -> d <= rangeEnd
                        , d -> d + step)
                .boxed()
                .map(d -> round(d * 100.0) / 100.0)
                .toList();
        range.forEach(
                x -> {
                    var functionWithReplacedArguments = function.replace("X1", x.toString());
                    results.put(x, functionWithReplacedArguments);
                }
        );

        int rowId = 0;
        for (Double x : results.keySet().stream().sorted().toList()) {
            row = spreadsheet.createRow(rowId++);
            Cell cellX = row.createCell(0);
            cellX.setCellValue(x);
            Cell cellExp = row.createCell(1);
            cellExp.setCellFormula(results.get(x));
            evaluator.evaluateFormulaCell(cellExp);
            Cell cellOriginalExp = row.createCell(2);
            cellOriginalExp.setCellValue(originalFunction.call(x));
        }

        XSSFDrawing drawing = spreadsheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 5, 1, 12, 26);
        XSSFChart chart = drawing.createChart(anchor);
        chart.setTitleText("Tiny Gp");
        chart.setTitleOverlay(false);

        XDDFNumericalDataSource<Double> dataSourceX = XDDFDataSourcesFactory.fromNumericCellRange(spreadsheet,
                new CellRangeAddress(0, range.size()-1, 0, 0));

        XDDFNumericalDataSource<Double> dataSourceY = XDDFDataSourcesFactory.fromNumericCellRange(spreadsheet,
                new CellRangeAddress(0, range.size()-1, 1, 1));

        XDDFNumericalDataSource<Double> dataSourceOriginalY = XDDFDataSourcesFactory.fromNumericCellRange(spreadsheet,
                new CellRangeAddress(0, range.size()-1, 2, 2));

        XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        bottomAxis.setTitle("X");
        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
        leftAxis.setTitle("Y");

        XDDFLineChartData data = (XDDFLineChartData) chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);

        XDDFLineChartData.Series series1 = (XDDFLineChartData.Series) data.addSeries(dataSourceX, dataSourceY);
        series1.setMarkerStyle(MarkerStyle.DOT);
        series1.setSmooth(true);

        XDDFLineChartData.Series series2 = (XDDFLineChartData.Series) data.addSeries(dataSourceX, dataSourceOriginalY);
        series2.setMarkerStyle(MarkerStyle.DOT);
        series2.setSmooth(true);

        if (chart.getCTChartSpace().getSpPr() == null) chart.getCTChartSpace().addNewSpPr();
        if (chart.getCTChartSpace().getSpPr().getSolidFill() == null)
            chart.getCTChartSpace().getSpPr().addNewSolidFill();
        if (chart.getCTChartSpace().getSpPr().getSolidFill().getSrgbClr() == null)
            chart.getCTChartSpace().getSpPr().getSolidFill().addNewSrgbClr();
        chart.getCTChartSpace().getSpPr().getSolidFill().getSrgbClr().setVal(new byte[]{(byte)255,(byte)255,(byte)255});
        if (chart.getCTChartSpace().getSpPr().getLn() == null) chart.getCTChartSpace().getSpPr().addNewLn();
        chart.getCTChartSpace().getSpPr().getLn().setW(Units.pixelToEMU(1));
        if (chart.getCTChartSpace().getSpPr().getLn().getSolidFill() == null)
            chart.getCTChartSpace().getSpPr().getLn().addNewSolidFill();
        if (chart.getCTChartSpace().getSpPr().getLn().getSolidFill().getSrgbClr() == null)
            chart.getCTChartSpace().getSpPr().getLn().getSolidFill().addNewSrgbClr();
        chart.getCTChartSpace().getSpPr().getLn().getSolidFill().getSrgbClr().setVal(new byte[]{(byte)0,(byte)0,(byte)0});

        if (chart.getCTChart().getPlotArea().getCatAxArray(0).getSpPr() == null)
            chart.getCTChart().getPlotArea().getCatAxArray(0).addNewSpPr();
        if (chart.getCTChart().getPlotArea().getCatAxArray(0).getSpPr().getLn() == null)
            chart.getCTChart().getPlotArea().getCatAxArray(0).getSpPr().addNewLn();
        chart.getCTChart().getPlotArea().getCatAxArray(0).getSpPr().getLn().setW(Units.pixelToEMU(1));
        if (chart.getCTChart().getPlotArea().getCatAxArray(0).getSpPr().getLn().getSolidFill() == null)
            chart.getCTChart().getPlotArea().getCatAxArray(0).getSpPr().getLn().addNewSolidFill();
        if (chart.getCTChart().getPlotArea().getCatAxArray(0).getSpPr().getLn().getSolidFill().getSrgbClr() == null)
            chart.getCTChart().getPlotArea().getCatAxArray(0).getSpPr().getLn().getSolidFill().addNewSrgbClr();
        chart.getCTChart().getPlotArea().getCatAxArray(0).getSpPr().getLn().getSolidFill().getSrgbClr()
                .setVal(new byte[]{(byte)0,(byte)0,(byte)0});

        if (chart.getCTChart().getPlotArea().getValAxArray(0).getSpPr() == null)
            chart.getCTChart().getPlotArea().getValAxArray(0).addNewSpPr();
        if (chart.getCTChart().getPlotArea().getValAxArray(0).getSpPr().getLn() == null)
            chart.getCTChart().getPlotArea().getValAxArray(0).getSpPr().addNewLn();
        chart.getCTChart().getPlotArea().getValAxArray(0).getSpPr().getLn().setW(Units.pixelToEMU(1));
        if (chart.getCTChart().getPlotArea().getValAxArray(0).getSpPr().getLn().getSolidFill() == null)
            chart.getCTChart().getPlotArea().getValAxArray(0).getSpPr().getLn().addNewSolidFill();
        if (chart.getCTChart().getPlotArea().getValAxArray(0).getSpPr().getLn().getSolidFill().getSrgbClr() == null)
            chart.getCTChart().getPlotArea().getValAxArray(0).getSpPr().getLn().getSolidFill().addNewSrgbClr();
        chart.getCTChart().getPlotArea().getValAxArray(0).getSpPr().getLn().getSolidFill().getSrgbClr()
                .setVal(new byte[]{(byte)0,(byte)0,(byte)0});

        if (chart.getCTChart().getPlotArea().getLineChartArray(0).getSerArray(0).getSpPr() == null)
            chart.getCTChart().getPlotArea().getLineChartArray(0).getSerArray(0).addNewSpPr();
        if (chart.getCTChart().getPlotArea().getLineChartArray(0).getSerArray(0).getSpPr().getLn() == null)
            chart.getCTChart().getPlotArea().getLineChartArray(0).getSerArray(0).getSpPr().addNewLn();
        chart.getCTChart().getPlotArea().getLineChartArray(0).getSerArray(0)
                .getSpPr().getLn().setW(Units.pixelToEMU(3));
        if (chart.getCTChart().getPlotArea().getLineChartArray(0).getSerArray(0).getSpPr().getLn().getSolidFill() == null)
            chart.getCTChart().getPlotArea().getLineChartArray(0).getSerArray(0).getSpPr().getLn().addNewSolidFill();
        if (chart.getCTChart().getPlotArea().getLineChartArray(0).getSerArray(0).getSpPr().getLn().getSolidFill().getSrgbClr() == null)
            chart.getCTChart().getPlotArea().getLineChartArray(0).getSerArray(0).getSpPr().getLn().getSolidFill().addNewSrgbClr();

        chart.getCTChart().getPlotArea().getLineChartArray(0).getSerArray(0)
                .getSpPr().getLn().getSolidFill().getSrgbClr().setVal(new byte[]{(byte)0,(byte)0,(byte)255});

        chart.getCTChart().getPlotArea().getLineChartArray(0).getSerArray(0).getMarker().getSymbol().setVal(
                org.openxmlformats.schemas.drawingml.x2006.chart.STMarkerStyle.NONE);


        chart.plot(data);

        FileOutputStream out = new FileOutputStream(
                "src/main/resources/GP-plots.xlsx");

        workbook.write(out);
        out.close();

    }
}
