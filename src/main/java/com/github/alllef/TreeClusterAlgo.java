package com.github.alllef;

import java.util.*;

public class TreeClusterAlgo extends ClusterAlgo {

    List<List<Integer>> clusters = new ArrayList<>();

    TreeClusterAlgo(double[][] data) {
        super(data);
    }

    public void calculateResult() {
        PriorityQueue<PriorityRecord> distanceQueue = new PriorityQueue<>(Comparator.comparingDouble(PriorityRecord::distance));
        HashMap<Integer, Integer> rowClusterPath = new HashMap<>();

        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix[i].length; j++)
                distanceQueue.add(new PriorityRecord(i, j, distanceMatrix[i][j]));
        }

        do {
            clusters.add(getClusterByNearestNeighbor(distanceQueue.poll(), rowClusterPath));
        } while (clusters.get(clusters.size() - 1).size() < distanceMatrix.length);

    }

    List<Integer> getClusterByNearestNeighbor(PriorityRecord record, HashMap<Integer, Integer> clusterPath) {

        List<Integer> cluster = new ArrayList<>();
        List<Integer> recTuple = new ArrayList<>(Arrays.asList(record.firstRowIdentifier(), record.secondRowIdentifier()));
        boolean b = false;

        for (Integer tmpRecord : recTuple) {

            int num = Integer.parseInt(String.valueOf(b));
            if (clusterPath.containsKey(tmpRecord))
                cluster.addAll(clusters.get(clusterPath.get(tmpRecord)));
            else
                cluster.add(tmpRecord);
            cluster.add(recTuple.get(num));

            for (Integer row : cluster)
                clusterPath.put(row, cluster.size() + 1);

            b = !b;
        }

        return cluster;
    }

}
