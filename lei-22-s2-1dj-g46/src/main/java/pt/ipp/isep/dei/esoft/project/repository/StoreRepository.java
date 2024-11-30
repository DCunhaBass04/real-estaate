package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.domain.Store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The StoreRepository class is responsible for managing a list of stores. It contains a static list of Store objects called stores.
 * The add method takes a Store object as input and adds a cloned copy of it to the stores list. It returns an Optional object that contains the cloned copy of the Store object that was added.
 * If the add method is successful, the Optional object will contain the cloned copy of the Store object.
 */
public class StoreRepository implements Serializable {
    private List<Store> stores = new ArrayList<>();
    /**
     * This method adds a Store to the stores list.
     *
     * @param store The Store to be added.
     * @return A copy of the Store.
     */
    public Optional<Store> add(Store store) {

        Optional<Store> newStore = Optional.empty();
        boolean operationSuccess = false;

        if (validateStore(store)) {
            newStore = Optional.of(store.clone());
            operationSuccess = stores.add(newStore.get());
        }

        if (!operationSuccess) {
            newStore = Optional.empty();
        }

        return newStore;
    }
    private boolean validateStore(Store store) {return !stores.contains(store);}

    public List<String> getStoreNames(){
        List<String> names = new ArrayList<>();
        for (Store store : stores) {
            names.add(store.getStoreName());
        }
        return names;
    }

    /**
     * The getStore method returns a list of Store objects.
     * @return returns a list of Store objects.
     */
    public List<Store> getStores() {
        return stores;
    }

    public void printStores(){
        for(int i = 0 ; i < stores.size(); i++){
            System.out.println((i+1) + " - " + stores.get(i));
            System.out.println();
        }
    }

    public int getSize(){return stores.size();}

    public Store getStoreByName(String name){
        Store store = new Store();
        for (int i = 0; i < stores.size(); i++) {
            if (name.equals(stores.get(i).getStoreName())){
                store = stores.get(i);
            }
        }
        return store;
    }
    public Store getStoreByEmail(String email){
        Store store = new Store();
        for (int i = 0; i < stores.size(); i++) {
            if (email.equals(stores.get(i).getEmail())){
                store = stores.get(i);
            }
        }
        return store;
    }
}
