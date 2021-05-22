package com.github.alllef.clusteralgos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KMedianClusterAlgo extends ClusterAlgo {

    int clusterNumber;
    double[][] centers;
    List<List<Integer>> clusters;

    public KMedianClusterAlgo(double[][] data, int clusterNumber) {

        super(data);
        this.clusterNumber = clusterNumber;
        centers = new double[clusterNumber][];

        clusters = initializeCLuster();
    }

    @Override
    public void calculateResult() {
        prepareData();
        List<List<Integer>> prevClusters = initializeCLuster();

        while (!areEqualClusters(prevClusters)) {

            prevClusters = initializeCLuster();
            clusters = initializeCLuster();

            recalculateCenters();
            classifyData();
            clusters.forEach((c) -> System.out.println(c.size()));
            System.out.println();

            for (int i = 0; i < clusters.size(); i++) {
                for (int j = 0; j < clusters.get(i).size(); j++)
                    prevClusters.get(i).add(clusters.get(i).get(j));
            }


        }

    }

    boolean areEqualClusters(List<List<Integer>> prevClusters) {
        prevClusters.sort(Comparator.comparingInt(List::size));
        clusters.sort(Comparator.comparingInt(List::size));

        for (int i = 0; i < clusters.size(); i++) {
            prevClusters.get(i).sort(Double::compare);
            clusters.get(i).sort(Double::compare);
        }

        for (int i = 0; i < clusters.size(); i++) {
            if (clusters.get(i).size() != prevClusters.get(i).size()) return false;
            for (int j = 0; j < clusters.get(i).size(); j++) {
                if (!clusters.get(i).get(j).equals(prevClusters.get(i).get(j))) return false;
            }
        }
        return true;
    }


    List<List<Integer>> initializeCLuster() {

        List<List<Integer>> tmpClusters = new ArrayList<>();
        for (int i = 0; i < clusterNumber; i++)
            tmpClusters.add(new ArrayList<>());
        return tmpClusters;
    }

    private void classifyData() {

        for (int i = 0; i < data.length; i++) {
            int minCenter = getMinCenter(i);
            clusters.get(minCenter).add(i);
        }

    }

    private void prepareData() {
        for (int i = 0; i < clusterNumber; i++)
            centers[i] = data[i];

        for (int i = clusterNumber; i < data.length; i++) {
            int minCenter = getMinCenter(i);
            recalculateCenter(minCenter, i);
        }

        for (int i = 0; i < data.length; i++) {
            int minCenter = getMinCenter(i);
            clusters.get(minCenter).add(i);
        }


    }

    private void recalculateCenters() {
        for (int i = 0; i < data.length; i++) {
            int minCenter = getMinCenter(i);
            recalculateCenter(minCenter, i);
        }
    }

    private int getMinCenter(int curRow) {

        int minCenter = -1;
        double minCenterDistance = Double.MAX_VALUE;

        for (int i = 0; i < centers.length; i++) {
            double distance = calculateEuclidianDistance(centers[i], data[curRow]);

            if (distance < minCenterDistance) {
                minCenterDistance = distance;
                minCenter = i;
            }
        }
        return minCenter;
    }

    private void recalculateCenter(int centerPos, int rowNum) {

        for (int i = 0; i < centers[centerPos].length; i++)
            centers[centerPos][i] = (data[rowNum][i] + centers[centerPos][i]) / 2;
    }

}
