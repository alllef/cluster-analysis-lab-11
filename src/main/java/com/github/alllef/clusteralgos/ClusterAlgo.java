package com.github.alllef.clusteralgos;

import java.util.Arrays;

public abstract class ClusterAlgo {
    public double[][] distanceMatrix;

    ClusterAlgo(double[][] data) {
        calculateDistances(data);
    }

    public double calculateEuclidianDistance(double[] x, double[] y) {
        double result = 0;

        for (int i = 0; i < x.length; i++)
            result += Math.pow(x[i] - y[i], 2);

        return Math.sqrt(result);
    }

    public void calculateDistances(double[][] data) {

        distanceMatrix = new double[data.length][];
        int tmpMatrixIter = 0;

        for (int i = 0; i < distanceMatrix.length; i++) {
            distanceMatrix[i] = new double[tmpMatrixIter];

            for (int j = 0; j <  tmpMatrixIter; j++)
                distanceMatrix[i][j] = calculateEuclidianDistance(data[i], data[j]);
            System.out.println(Arrays.toString(distanceMatrix[i]));
            tmpMatrixIter++;
        }


    }

    public abstract void calculateResult();
}
