# US 013 - List all Employees

# 4. Tests

**Test 1:** Check that the StoreRepository works
```java
    @Test void storeSuccessfullyAdded(){
        StoreRepository storeRepository = new StoreRepository();
        Store store1 = new Store("RealEstateStore", "here", "98765", 123, "store@this.company.com");
        storeRepository.add(store1);
        assertTrue(storeRepository.getStores().contains(store1));
    }
```
**Test 2:** Check that the EmployeeRepository works
```java
    @Test void testAddEmployee() {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Employee employee = new Employee(123456789, "test@this.app", "Test", "Employee", "999999999", "Agency", 987654321, "Home", "password");
        employeeRepository.add(employee);
        assertTrue(employeeRepository.getEmployees().contains(employee));
    }
```

# 5. Construction (Implementation)

## Class ListEveryEmployeeUI
```java
    public void run() {
        Scanner ler = new Scanner(System.in);
        List<Store> listOfStoresToPrint = ctrl.getListOfStores();
        if (listOfStoresToPrint.isEmpty()) {
            System.out.println("There are no stores, register some stores in the system and try again");
        } else {
            listOfStoresToPrint = ctrl.sortStoresByNumOfProperties(listOfStoresToPrint);
            printAllStoresAndItsEmployees(listOfStoresToPrint);
        }
        System.out.print("\n\nPress ENTER to continue");
        ler.nextLine();
    }
```
```java
    private void printAllStoresAndItsEmployees(List<Store> storeList) {
        for (Store store : storeList) {
            System.out.println(store);
            System.out.printf("Number of properties associated with %s: %d%n", store.getStoreName(), store.getPropertyList().size());
            ctrl.printAllEmployeesByStore(store);
        }
    }
```
## Class ListEveryEmployeeController
```java
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
```

# 6. Integration and Demo

* A new option was added to the Network Manager's menu in order to list all employees (grouped by store).

# 7. Observations

n/a