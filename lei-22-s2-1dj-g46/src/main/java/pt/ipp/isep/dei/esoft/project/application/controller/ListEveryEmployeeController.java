package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Users.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The ListEveryEmployeeController class represents a controller for listing all employees.
 */
public class ListEveryEmployeeController {
    /**
     * Retrieves the list of stores.
     *
     * @return The list of stores.
     */
    public List<Store> getListOfStores() {
        StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();
        return storeRepository.getStores();
    }

    /**
     * Sorts the list of stores by the number of properties.
     *
     * @param stores The list of stores.
     * @return The sorted list of stores by the number of properties.
     */
    public List<Store> sortStoresByNumOfProperties(List<Store> stores) {
        List<Store> mutableStoreList = new ArrayList<>(stores);
        for (int i = 0; i < mutableStoreList.size() - 1; i++) {
            for (int j = i + 1; j < mutableStoreList.size(); j++) {
                if (mutableStoreList.get(i).compareTo(mutableStoreList.get(j)) < 0) {
                    Collections.swap(mutableStoreList, i, j);
                }
            }
        }
        return Collections.unmodifiableList(mutableStoreList);
    }

    /**
     * Sorts the list of employees alphabetically.
     *
     * @param employeeList The list of employees.
     * @return The sorted list of employees alphabetically.
     */
    private List<Employee> sortEmployeesAlphabetically(List<Employee> employeeList) {
        List<Employee> mutableEmployeeList = new ArrayList<>(employeeList);
        for (int i = 0; i < mutableEmployeeList.size() - 1; i++) {
            for (int j = i + 1; j < mutableEmployeeList.size(); j++) {
                if (mutableEmployeeList.get(i).compareTo(mutableEmployeeList.get(j)) > 0) {
                    Collections.swap(mutableEmployeeList, i, j);
                }
            }
        }
        return Collections.unmodifiableList(mutableEmployeeList);
    }

    /**
     * Prints all employees for a given store.
     *
     * @param store The store to retrieve employees from.
     */
    public void printAllEmployeesByStore(Store store) {
        List<Employee> employeeList = store.getEmployeeList();
        if (employeeList.isEmpty()) {
            System.out.println("This store has no employees registered yet.");
        } else {
            if (employeeList.size() > 1) {
                employeeList = sortEmployeesAlphabetically(store.getEmployeeList());
            }
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
        }
    }
}
