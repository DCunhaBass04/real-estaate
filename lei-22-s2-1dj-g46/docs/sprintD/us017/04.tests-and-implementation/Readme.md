# US017 - Listing all deals

# 4. Tests

**Test 1:** Check that getting the property list works.

```java
    @Test void ensureGetPropertiesWorks(){
        List<Property> propertyList = Repositories.getInstance().getPropertySoldRepository().getProperties();
        assertFalse(propertyList.isEmpty());
        }
```

# 5. Construction (Implementation)

As decided previously, the interaction with the System starts when the user (network managers) requests to list all deals made.

```java
    public void run(){
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("List All Employees by Store", new ListEveryEmployeeUI()));
        options.add(new MenuItem("Divide Stores into Subsets of Stores", new CreateSubsetsOfStoresUI()));
        options.add(new MenuItem("List Properties", new ListPropertiesUI()));
        -->options.add(new MenuItem("List All Deals", new ListAllDealsUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nNetwork Manager Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
```      

ListAllDealsUI calls a ListAllDealsController...

```java
    public class ListAllDealsUI implements Runnable {
        -->private final ListAllDealsController controller = new ListAllDealsController();
        [...]
```

...that is responsible for getting the necessary data to fulfill the request from the user(network manager).

```java
    public List<Property> getProperties(){
        List<Property> properties = Repositories.getInstance().getPropertySoldRepository().getProperties();
        -->return properties;
    }
```

The ListAllDealsUI asks for the user to select how to sort the deals (ascending/descending order and bubble/merge sort)...

```java
    public int getOption() {
        System.out.println("Select and option with 1 or 2:");
        System.out.println("1 - Sort all Properties by area in ascending order");
        System.out.println("2 - Sort all Properties by area in descending order");
        boolean validate = true;
        int option = 0;
        while (validate) {
            option = verifyInput.verifyInt(sc, "option");
            if (option == 1 || option == 2) {
                validate = false;
            } else {
                System.out.println("Select an option with 1 or 2:");
            }
        }
        -->return option;
    }
    public int choosingAlgorithm() {
        System.out.println("Select and option with 1 or 2:");
        System.out.println("1 - Bubble Sort");
        System.out.println("2 - Merge Sort");
        boolean validate = true;
        int option = 0;
        while (validate) {
            option = verifyInput.verifyInt(sc, "option");
            if (option == 1 || option == 2) {
                validate = false;
            } else {
                System.out.println("Select and option with 1 or 2:");
            }
        }
        -->return option;
    }
```

...and the Controller sort the list accordingly.

```java
    public List<Property> sortProperties(int option, int option2, List<Property> properties){
        switch (option) {
            case 1:
                switch (option2) {
                    case 1:
                        properties = editAndSortLists.bubbleSortAscending(properties);
                        break;
                    case 2:
                        properties = editAndSortLists.genericMergeSortStart(properties, true);
                        break;
                }
                break;
            case 2:
                switch (option2) {
                    case 1:
                        properties = editAndSortLists.bubbleSortDescending(properties);
                        break;
                    case 2:
                        properties = editAndSortLists.genericMergeSortStart(properties, false);
                        break;
                }
                break;
        }
        -->return properties;
    }
```

Finally, the CheckVisitsUI shows the sorted list of deals.

```java
    public void printPropertyList(List<Property> propertyList) {
        System.out.println();
        for (int i = 0; i < propertyList.size(); i++) {
            Property property = propertyList.get(i);
            System.out.println((i + 1) + ". " + property);
        }
    }
```

# 6. Integration and Demo

* A new option was added to the network manager UI.

# 7. Observations

There are no observations to be made.