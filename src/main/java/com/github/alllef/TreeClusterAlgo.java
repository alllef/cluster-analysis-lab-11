package com.github.alllef;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class TreeClusterAlgo extends ClusterAlgo {
    ArrayList<ArrayList<String>> clusters = new ArrayList<>();

    public void calculateResult() {
        PriorityQueue<PriorityRecord> distanceQueue = new PriorityQueue<>();

    }

    public record PriorityRecord(String firstPerson, String secondPerson, double result) {

    }

}
