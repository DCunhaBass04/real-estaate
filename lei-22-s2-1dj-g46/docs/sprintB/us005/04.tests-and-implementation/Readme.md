# US005 - Register a Store

# 4. Tests
**Test 1:** Check if Store is added to the repository.

	@Test void storeSuccessfullyAdded(){
        StoreRepository storeRepository = new StoreRepository();
        Store store1 = new Store("RealEstateStore", "here", "98765", 123, 45678, 87654, "houses", "www.website.com", "store@this.company.com");
        storeRepository.add(store1);
        assertTrue(storeRepository.getStore().contains(store1));
    }

**Test 2:** Check if 2 Stores has the same Data.

	@Test
    void ensureTwoStoresWithSameDataEquals(){
        Store store1 = new Store("RealEstateStore", "here", "98765", 123, 45678, 87654, "houses", "www.website.com", "store@this.company.com");
        Store store2 = new Store("RealEstateStore", "here", "98765", 123, 45678, 87654, "houses", "www.website.com", "store@this.company.com");
        assertEquals(store1, store2);
    }


# 5. Construction (Implementation)

    public void submitData(Store store){
    Scanner input = new Scanner(System.in);
    VerifyInput verifyInput = new VerifyInput();
    boolean newStore;
    String password;
    int option = verifyInput.verifyInt(input, "an option.%nSubmit data?%n1 - Yes.%n(Select any other NUMBER%nto go back to the Administrator menu.)");
    if (option == 1){
    newStore = controller.verifyData(store);
    if (newStore){
    System.out.println("Operation success!");
    controller.createStore(store);
    }
    else {
    System.out.println("This store is already registered.");
    }
    }
    }

# 6. Integration and Demo

Ensuring a seamless store registration process, we have implemented measures to prevent the user from inputting invalid data, including incorrect formatting and duplicate entries.

To enhance user experience, we provide real-time updates on the status of the operation, indicating whether it was successful or unsuccessful.

# 7. Observations

We are committed to continuously enhancing our system's performance, and in the event of any unknown bugs, we will diligently investigate and strive to implement solutions for improved functionality.