package com.github.alllef.clusteralgos;

import java.util.*;

public class TreeClusterAlgo extends ClusterAlgo {
    public double[][] distanceMatrix;
    List<List<Integer>> clusters = new ArrayList<>();

    public TreeClusterAlgo(double[][] data) {
        super(data);
        calculateDistances();
    }

    public void calculateDistances() {

        distanceMatrix = new double[data.length][];
        int tmpMatrixIter = 0;

        for (int i = 0; i < distanceMatrix.length; i++) {
            distanceMatrix[i] = new double[tmpMatrixIter];

            for (int j = 0; j < tmpMatrixIter; j++)
                distanceMatrix[i][j] = calculateEuclidianDistance(data[i], data[j]);
            System.out.println(Arrays.toString(distanceMatrix[i]));
            tmpMatrixIter++;
        }
    }

    @Override
    public void calculateResult() {

        PriorityQueue<PriorityRecord> distanceQueue = new PriorityQueue<>(Comparator.comparingDouble(PriorityRecord::distance));
        Map<Integer, Integer> rowClusterPath = new HashMap<>();

        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix[i].length; j++)
                distanceQueue.add(new PriorityRecord(i, j, distanceMatrix[i][j]));
        }

        do {

            PriorityRecord rec = distanceQueue.poll();

            Map<Integer, Integer> clusterPathTuple = new HashMap<>();
            clusterPathTuple.put(rec.firstRowId(), rowClusterPath.get(rec.firstRowId()));
            clusterPathTuple.put(rec.secondRowId(), rowClusterPath.get(rec.secondRowId()));

            if (rowClusterPath.get(rec.firstRowId()) != null && rowClusterPath.get(rec.secondRowId()) != null) {
                if ((int) clusterPathTuple.get(rec.firstRowId()) == (clusterPathTuple.get(rec.secondRowId()))) continue;
            }

            List<Integer> cluster = getClusterByNearestNeighbor(clusterPathTuple);
            clusters.add(cluster);

            for (Integer row : cluster)
                rowClusterPath.put(row, clusters.size() - 1);

        } while (clusters.get(clusters.size() - 1).size() < distanceMatrix.length);

    }

    List<Integer> getClusterByNearestNeighbor(Map<Integer, Integer> clusterPathTuple) {

        List<Integer> cluster = new ArrayList<>();
        for (Integer key : clusterPathTuple.keySet()) {

            if (clusterPathTuple.get(key) == null)
                cluster.add(key);
            else
                cluster.addAll(clusters.get(clusterPathTuple.get(key)));

        }

        return cluster;
    }
}
