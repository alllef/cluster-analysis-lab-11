package com.github.alllef;

public abstract class ClusterAlgo {
    public double[][] distanceMatrix;

    public double calculateEuclidianDistance(double[] x, double[] y) {
        double result = 0;

        for (int i = 0; i < x.length; i++)
            result += Math.pow(x[i] - y[i], 2);

        return Math.sqrt(result);
    }

    public void calculateDistances(double[][] data) {
        distanceMatrix = new double[data.length][];

        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix.length; j++)
                distanceMatrix[i][j] = calculateEuclidianDistance(data[i], data[j]);
        }
    }

    ClusterAlgo(double[][] data) {
        calculateDistances(data);
    }

    public abstract void calculateResult();
}
