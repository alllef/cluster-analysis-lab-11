package com.github.alllef.datautils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataUtility {

    private double[][] data;

    public DataUtility(File fileToRead) {
        readNumData(fileToRead);
    }

    public double[][] getData() {
        return data;
    }

    public void setData(double[][] data) {
        this.data = data;
    }

    public double[][] readNumData(File fileToRead) {
        List<double[]> dataList = new ArrayList<>();

        try (FileReader fileReader = new FileReader(fileToRead)) {
            Scanner scan = new Scanner(fileReader);
            scan.nextLine();

            while (scan.hasNextLine())
                dataList.add(getRowValues(scan.nextLine()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        data = new double[dataList.size()][];

        for (int i = 0; i < data.length; i++)
            data[i] = dataList.get(i);

        return data;
    }

    private double[] getRowValues(String row) {

        String[] strValues = row.split(",");
        double[] numValues = new double[strValues.length];

        for (int i = 0; i < strValues.length; i++)
            numValues[i] = Double.parseDouble(strValues[i]);

        return numValues;
    }

    public void normalizeData() {


        Map<Integer, Double> minColumnValues = new HashMap<>();
        Map<Integer, Double> maxColumnValues = new HashMap<>();


        for (int j = 0; j < data[0].length; j++) {
            for (int i = 0; i < data.length; i++) {
                if (minColumnValues.get(j) == null || minColumnValues.get(j) > data[i][j])
                    minColumnValues.put(j, data[i][j]);
                if (maxColumnValues.get(j) == null || maxColumnValues.get(j) < data[i][j])
                    maxColumnValues.put(j, data[i][j]);
            }
        }

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++)
                data[i][j] = (data[i][j] - minColumnValues.get(j)) / (maxColumnValues.get(j) - minColumnValues.get(j));
        }

    }

}
