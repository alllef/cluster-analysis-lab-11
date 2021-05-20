package com.github.alllef.datautils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataUtility {

    public static List<double[]> readNumData(File fileToRead) {
        List<double[]> data = new ArrayList<>();

        try (FileReader fileReader = new FileReader(fileToRead)) {
            Scanner scan = new Scanner(fileReader);
            scan.nextLine();

            while (scan.hasNextLine())
                data.add(getRowValues(scan.nextLine()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    private static double[] getRowValues(String row) {

        String[] strValues = row.split(",");
        double[] numValues = new double[strValues.length];

        for (int i = 0; i < strValues.length; i++)
            numValues[i] = Double.parseDouble(strValues[i]);

        return numValues;
    }

    public static void main(String[]args){
        List<double[]> data = DataUtility.readNumData(new File("wine-clustering.csv"));
    }

}
