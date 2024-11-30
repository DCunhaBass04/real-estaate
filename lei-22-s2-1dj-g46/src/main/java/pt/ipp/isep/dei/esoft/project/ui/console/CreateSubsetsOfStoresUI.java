package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateSubsetsOfStoresController;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.domain.Tuple;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;
import java.util.Scanner;

public class CreateSubsetsOfStoresUI implements Runnable {
    private CreateSubsetsOfStoresController ctrl = new CreateSubsetsOfStoresController();

    /**
     * Runs this functionality, printing the execution time, difference of properties between partitions and total number of properties for each partition
     */
    @Override
    public void run() {
        List<Store> stores = Repositories.getInstance().getStoreRepository().getStores();
        if(!stores.isEmpty()) {
            long startTime = System.currentTimeMillis();
            List<List<Tuple>> partitions = ctrl.generatePartitions(stores);
            long endTime = System.currentTimeMillis();
            System.out.println("Execution time in milliseconds: " + (endTime-startTime));
            for (int i = 0; i < partitions.size(); i++) {
                System.out.printf("Partition " + (i + 1) + ":%n");
                for (Tuple tuple : partitions.get(i)) {
                    System.out.println(tuple);
                }
                System.out.printf("Total number of properties: %d%n%n%n", ctrl.getTotalProperties(partitions.get(i)));
            }
            int difference = Math.abs(ctrl.getTotalProperties(partitions.get(0)) - ctrl.getTotalProperties(partitions.get(1)));
            String propertyOrProperties = "properties";
            if (difference == 1) {
                propertyOrProperties = "property";
            }
            System.out.printf("Difference of Properties between partitions: %d%s.%n", difference, propertyOrProperties);
        } else {
            System.out.println("There are no stores registered in the system.");
        }
        System.out.println("Press ENTER to continue.");
        new Scanner(System.in).nextLine();
    }
}
