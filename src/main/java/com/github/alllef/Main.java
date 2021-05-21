package com.github.alllef;

import com.github.alllef.clusteralgos.KMedianClusterAlgo;
import com.github.alllef.clusteralgos.TreeClusterAlgo;
import com.github.alllef.datautils.DataUtility;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        DataUtility utility = new DataUtility(new File("wine-clustering.csv"));
        utility.normalizeData();
        KMedianClusterAlgo algo = new KMedianClusterAlgo(utility.getData(),3);
        algo.calculateResult();
    }

}
