# US 019 - Getting Close Subsets

# 4. Tests

**Test 1:** Check that two equal stores are equal and that clone() works
```java
    @Test void ensureTwoStoresWithSameDataEquals(){
        Store store1 = new Store("RealEstateStore", "here", "98765", 123, "store@this.company.com");
        Store store2 = new Store("RealEstateStore", "here", "98765", 123, "store@this.company.com");
        assertEquals(store1, store2);
    }
```
```java
    @Test void ensureCloneWorks(){
        Store store1 = new Store("RealEstateStore", "here", "98765", 123, "store@this.company.com");
        Store clone = store1.clone();
        assertEquals(store1, clone);
    }
```
**Test 2:** Check that a store was correctly added to the repository
```java
    @Test void storeSuccessfullyAdded(){
        StoreRepository storeRepository = new StoreRepository();
        Store store1 = new Store("RealEstateStore", "here", "98765", 123, "store@this.company.com");
        storeRepository.add(store1);
        assertTrue(storeRepository.getStores().contains(store1));
    }
```
**Test 3:** Check that clone() works for employees
```java
    @Test void ensureCloneWorks(){
        String email = "john.doe@this.company.com";
        Employee employee = new Employee(email);
        Employee clone = employee.clone();
        assertEquals(employee, clone);
    }
```

# 5. Construction (Implementation)

## Class CreateSubsetsOfStoresUI
```java
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
```
## Class CreateSubsetsOfStoresController
```java
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
```
```java
    public int getTotalProperties(List<Tuple> tuples) {
        int total = 0;
        for (Tuple tuple : tuples) {
            total += tuple.getNumberOfProperties();
        }
        return total;
    }
```

# 6. Integration and Demo

* A new option was added to the network manager's menu (in console and in javaFX).

# 7. Observations

This operation can take some time to execute depending on the number of stores currently registered in the system.