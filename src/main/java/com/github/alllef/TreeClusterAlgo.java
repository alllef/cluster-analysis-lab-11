package com.github.alllef;

import java.util.*;

public class TreeClusterAlgo extends ClusterAlgo {

    List<List<Integer>> clusters = new ArrayList<>();

    TreeClusterAlgo(double[][] data) {
        super(data);
    }

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

            clusterPathTuple.put(rec.firstRowIdentifier(), rowClusterPath.get(rec.firstRowIdentifier()));
            clusterPathTuple.put(rec.secondRowIdentifier(), rowClusterPath.get(rec.secondRowIdentifier()));
            List<Integer> cluster = getClusterByNearestNeighbor(clusterPathTuple);
            clusters.add(cluster);

            for (Integer row : cluster)
                rowClusterPath.put(row, clusters.size());

        } while (clusters.get(clusters.size()-1).size() < distanceMatrix.length);

    }

    List<Integer> getClusterByNearestNeighbor(Map<Integer, Integer> clusterPathTuple) {

        //boolean b = false;
        List<Integer> cluster = new ArrayList<>();

        for (Integer key : clusterPathTuple.keySet()) {

            //int num = Integer.parseInt(String.valueOf(b));
            if (clusterPathTuple.get(key) != null)
                cluster.addAll(clusters.get(clusterPathTuple.get(key)));
            else
                cluster.add(key);

           // b = !b;
        }

        return cluster;
    }

}
