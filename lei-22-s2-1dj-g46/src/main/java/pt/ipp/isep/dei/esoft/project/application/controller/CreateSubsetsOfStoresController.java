package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.domain.Tuple;

import java.util.ArrayList;
import java.util.List;

public class CreateSubsetsOfStoresController {
    /**
     * Calculates every possible partition of cardinal 2 for each store, and returns the pair with the minimum difference
     * @param stores the list of all stores in the system
     * @return the pair of partitions with the tiniest difference of stores between them
     */
    public List<List<Tuple>> generatePartitions(List<Store> stores) {
        int n = stores.size();
        int minDifference = Integer.MAX_VALUE;
        List<Tuple> minSubset1 = new ArrayList<>();
        List<Tuple> minSubset2 = new ArrayList<>();

        for (int i = 0; i < Math.pow(2, n); i++) {
            List<Tuple> subset1 = new ArrayList<>();
            List<Tuple> subset2 = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                if ((i & (int)Math.pow(2, j)) != 0) {
                    subset1.add(new Tuple(stores.get(j).getId(), stores.get(j).getPropertyList().size()));
                } else {
                    subset2.add(new Tuple(stores.get(j).getId(), stores.get(j).getPropertyList().size()));
                }
            }

            int difference = Math.abs(getTotalProperties(subset1) - getTotalProperties(subset2));

            if (difference < minDifference) {
                minDifference = difference;
                minSubset1 = subset1;
                minSubset2 = subset2;
            }
        }
        List<List<Tuple>> allPartitions = new ArrayList<>();
        allPartitions.add(minSubset1);
        allPartitions.add(minSubset2);
        return allPartitions;
    }

    /**
     * gets total number of properties from one list of tuples
     * @param tuples a list of tuples
     * @return the number of properties
     */
    public int getTotalProperties(List<Tuple> tuples) {
        int total = 0;
        for (Tuple tuple : tuples) {
            total += tuple.getNumberOfProperties();
        }
        return total;
    }
}
