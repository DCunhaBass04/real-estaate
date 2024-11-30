package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.VerifyInput;

import java.util.List;
import java.util.Scanner;

public class CreateStoreController {

    private final StoreRepository repository = Repositories.getInstance().getStoreRepository();

    /**
     * The requestData() method is a public method that creates a new Store object by taking input from the user through the console using the Scanner class. It prompts the user to enter data for the Store object and uses the VerifyInput class to validate the input.
     * @return returns a new Store object that contains the data entered by the user.
     */
    public Store requestData() {
        Scanner input = new Scanner(System.in);
        System.out.printf("Insert the data in the appropriate format.%nA Store cannot be registered more than once.%n");
        VerifyInput verifyInput = new VerifyInput();

        String storeName = verifyInput.verifyComplexString(input, "Store Name");

        String address = verifyInput.verifyComplexString(input, "Store Address");

        String phoneNumber = verifyInput.verifySimpleString(input, "Phone Number");

        int id = verifyInput.verifyInt(input, "id");

        String email = verifyInput.verifySimpleString(input, "Email");

        return new Store(storeName, address, phoneNumber,id, email);
    }

    /**
     * The verifyData method takes a Store object as an argument and returns a boolean value. It checks if the store is new or already exists in the system.
     * First, it retrieves the various properties of the store object using getter methods. Then, it calls the verifyIfNewStore() method, which takes these values as arguments and checks if that store  already exists in the system.
     * @param store: an object representing a store.
     * @return if the store is new or not.
     */
    public boolean verifyData(Store store){
        boolean newStore;
        int id = store.getId();
        String storeName = store.getStoreName(), address = store.getAddress(), phoneNumber = store.getPhoneNumber(), email = store.getEmail();
        newStore = verifyIfNewStore(storeName, address, phoneNumber, id, email);
        return newStore;
    }

    /**
     *This method checks if a new store is different from any already registered store in the repository. It takes in as parameters all the attributes that define a store.
     * It begins by retrieving the list of all stores in the repository using the getStore() method.
     * Then, for each store in the list, it compares the attributes of the existing store with the attributes of the new store being verified. If any of the attributes match, it sets the different flag to false, indicating that the new store is not unique and has already been registered. If no matching attributes are found, the different flag remains true, indicating that the new store is unique and can be registered.
     * @param id: an integer representing the store ID.
     * @param storeName: a String representing the store name.
     * @param address: a String representing the store address.
     * @param phoneNumber: a String representing the store phone number.
     * @param email: a String representing the store email.
     * @return returns the value of the different flag to indicate whether the new store is unique or not.
     */
    public boolean verifyIfNewStore(String storeName, String address, String phoneNumber,int id, String email){
        List<Store> storeList = repository.getStores();
        int otherId;
        String otherStoreName, otherAddress, otherPhoneNumber, otherEmail;
        Store alreadyRegistered;
        boolean different = true;
        for (int i = 0; i < storeList.size() && different; i++) {
            alreadyRegistered = storeList.get(i);
            otherId = alreadyRegistered.getId();
            otherStoreName = alreadyRegistered.getStoreName();
            otherAddress = alreadyRegistered.getAddress();
            otherPhoneNumber = alreadyRegistered.getPhoneNumber();
            otherEmail = alreadyRegistered.getEmail();

            if (otherId == id ||
                otherStoreName.equals(storeName) || otherAddress.equals(address) || otherPhoneNumber.equals(phoneNumber) ||
                    otherEmail.equals(email)){
                different = false;
            }
        }
        return different;
    }

    /**
     * The createStore method creates a new Store object from the input Store object and adds it to the StoreRepository. It takes a Store object as input parameter and extracts its properties using getter methods. It then creates a copy of the input Store object with the extracted properties and adds it to the StoreRepository.
     * The add method is called on the StoreRepository to add the new Store object to the repository.
     * @param store: an object representing a store.
     */
    public  void createStore(Store store){
        String storeName = store.getStoreName();

        String address = store.getAddress();

        String phoneNumber = store.getPhoneNumber();

        int id = store.getId();

        String email = store.getEmail();

        Store copyOfStore = new Store(storeName, address, phoneNumber,id, email);
        StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();
        storeRepository.add(copyOfStore);
    }
}