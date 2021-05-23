package com.github.alllef.clusteralgos;

import java.util.Arrays;

public abstract class ClusterAlgo {
    double[][] data;

    ClusterAlgo(double[][] data) {
        this.data = data;
    }

    public double calculateEuclideanDistance(double[] x, double[] y) {
        double result = 0;

        for (int i = 0; i < x.length; i++)
            result += Math.pow(x[i] - y[i], 2);

        return Math.sqrt(result);
    }

    public abstract void calculateResult();
}
