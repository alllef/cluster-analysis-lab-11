package com.github.alllef;

import java.util.PriorityQueue;

public abstract class ClusterAlgo {
    public double[][] distanceMatrix;

    public double calculateEuclidianDistance(double[] x, double[] y) {
        double result = 0;

        for (int i = 0; i < x.length; i++)
            result += Math.pow(x[i] - y[i], 2);

        return Math.sqrt(result);
    }

    public abstract void calculateResult();
}
