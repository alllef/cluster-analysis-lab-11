package com.github.alllef.clusteralgos;

import java.util.*;

public class TreeClusterAlgo extends ClusterAlgo {

    List<List<Integer>> clusters = new ArrayList<>();

    public TreeClusterAlgo(double[][] data) {
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
            clusterPathTuple.put(rec.firstRowId(), rowClusterPath.get(rec.firstRowId()));
            clusterPathTuple.put(rec.secondRowId(), rowClusterPath.get(rec.secondRowId()));

            if (rowClusterPath.get(rec.firstRowId()) != null && rowClusterPath.get(rec.secondRowId()) != null) {
                if ((int)clusterPathTuple.get(rec.firstRowId()) == (clusterPathTuple.get(rec.secondRowId()))) continue;
            }

            List<Integer> cluster = getClusterByNearestNeighbor(clusterPathTuple);
            clusters.add(cluster);

            for (Integer row : cluster)
                rowClusterPath.put(row, clusters.size() - 1);

        } while (clusters.get(clusters.size() - 1).size() < distanceMatrix.length);

    }

    List<Integer> getClusterByNearestNeighbor(Map<Integer, Integer> clusterPathTuple) {
        //System.out.println(clusterPathTuple);
        List<Integer> cluster = new ArrayList<>();
        //int i = 0;
        for (Integer key : clusterPathTuple.keySet()) {

            if (clusterPathTuple.get(key) == null /*&& !cluster.contains(key)*/)
                cluster.add(key);

            else {
                cluster.addAll(clusters.get(clusterPathTuple.get(key)));
                //      System.out.println("Added cluster"+clusters.get(clusterPathTuple.get(key)));
            }
            //i++;
        }

        return cluster;
    }
}
