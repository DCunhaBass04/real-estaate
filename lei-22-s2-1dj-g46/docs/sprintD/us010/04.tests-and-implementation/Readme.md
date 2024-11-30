# US 010 - Place an order

# 4. Tests

**Test 1:** Check that a different Orders are indeed different, and that 2 equal orders are indeed equal (to help with AC3)
```java
    @Test void ensureTwoOrdersWithTheSameDataEqual(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Order order1 = new Order("client@this.app", land, 22221, "Pending");
        Order order2 = new Order("client@this.app", land, 22221, "Pending");
        assertEquals(order1, order2);
        }
    }
```
```java
    @Test void ensureTwoOrdersFromDifferentClientNotEqual(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Order order1 = new Order("client1@this.app", land, 22221, "Pending");
        Order order2 = new Order("client2@this.app", land, 22221, "Pending");
        assertNotEquals(order1, order2);
    }
```
```java
    @Test void ensureTwoOrdersToDifferentPropertiesNotEqual(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land1 = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Land land2 = new Land(888.88f, "here", 55.55f, agent, photograph, 22222, "17/05/2023", "n/a", request);
        Order order1 = new Order("client@this.app", land1, 22221, "Pending");
        Order order2 = new Order("client@this.app", land2, 22221, "Pending");
        assertNotEquals(order1, order2);
    }
```
```java
    @Test void ensureTwoOrdersWithDifferentAmountsNotEqual(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Order order1 = new Order("client@this.app", land, 22221, "Pending");
        Order order2 = new Order("client@this.app", land, 22220, "Pending");
        assertNotEquals(order1, order2);
    }
```
```java
    @Test void ensureTwoOrdersWithDifferentStatesNotEqual(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Order order1 = new Order("client@this.app", land, 22221, "Pending");
        Order order2 = new Order("client@this.app", land, 22221, "Declined");
        assertNotEquals(order1, order2);
    }
```
**Test 2:** Check that an Order is added/removed
```java
    @Test void ensureOrderIsAdded(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Order order = new Order("client@this.app", land, 22221, "Pending");
        OrderRepository orderRepository = new OrderRepository();
        orderRepository.add(order);
        assertTrue(orderRepository.getOrders().contains(order));
    }
```
```java
    @Test void ensureOrderIsRemoved(){
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Published", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph,22222, "17/05/2023", "n/a", request);
        Order order = new Order("client@this.app", land, 22221, "Pending");
        OrderRepository orderRepository = new OrderRepository();
        orderRepository.add(order);
        orderRepository.remove(order);
        assertFalse(orderRepository.getOrders().contains(order));
    }
```

# 5. Construction (Implementation)


## Class SetCommissionUI

```java
public void run(){
        Scanner ler = new Scanner(System.in);
        SetCommissionController setCommissionController = new SetCommissionController();
        List<Property> propertiesFromCurrentAgent = setCommissionController.getAllPropertiesFromCurrentAgent();
        if(!propertiesFromCurrentAgent.isEmpty()) {
            propertiesFromCurrentAgent = askAboutFilters(propertiesFromCurrentAgent, setCommissionController, ler);
            setCommissionController.setCommissionFromOwner(propertiesFromCurrentAgent);
        } else {
            System.out.print("The Property Request Repository is empty, get some sale announcements and try again.");
        }
            System.out.print("\n\nPress ENTER to continue");
            ler.nextLine();
        }   
```


## Class PlaceOrderUI
```java
        public void run() {
            Scanner ler = new Scanner(System.in);
            VerifyInput verifyInput = new VerifyInput();
            List<Property> propertyList = Repositories.getInstance().getPropertyRepository().getProperties();
            System.out.println();
            String email = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().toString();
            EditAndSortLists editAndSortLists = new EditAndSortLists();
            propertyList = editAndSortLists.getPropertiesIfNotFromTheSameOwner(propertyList, email);
            if (!propertyList.isEmpty()) {
                propertyList = askAboutFilters(propertyList, ler);
                if (!propertyList.isEmpty()) {
                    printPropertyList(propertyList);
                    int option = verifyOption(propertyList);
                    if (option != 0) {
                        controller.chooseProperty(propertyList, option);
                        controller.requestAmount(propertyList.get(option - 1));
                        float amount = verifyInput.verifyFloat(ler, "order amount");
                        controller.verifyAmount(amount);
                        boolean valid = controller.verifyOrder(amount, email, propertyList.get(option - 1));
                        if (valid) {
                            controller.createAndRegisterNewOrder(amount, email, propertyList.get(option - 1));
                            System.out.println("Order placed!");
                        }
                    }
                } else {
                    System.out.println("No properties to be listed");
                }
            } else {
                System.out.println("No properties to be listed");
            }
        }
```
## Class PlaceOrderController
```java
        public void chooseProperty(List<Property> properties, int option) {
            if (option >= 1 && option <= properties.size()) {
                int i = option - 1;
                Property chosenProperty = Repositories.getInstance().getPropertyRepository().getProperties().get(i);
                System.out.println("Chosen Property: " + chosenProperty);
            }
        }
```
```java
        public boolean verifyOrder(float amount, String email, Property property) {
            List<Order> orderList = orderRepository.getOrders();
            boolean valid = true;
            boolean already = true;
            Order orderToCreate = new Order(email, property, amount, "Pending");
            for (int i = 0; i < orderList.size() && valid; i++) {
                if (orderToCreate.getProperty() == orderList.get(i).getProperty()) {
                    if (orderToCreate.getClientEmail().equals(orderList.get(i).getClientEmail())) {
                        System.out.println("Order already placed for this property");
                        valid = false;
                        already = false;
                    }
                    if (orderToCreate.getAmount() == orderList.get(i).getAmount() && already) {
                        System.out.println("An order with the same amount has already been registered by another client for this property");
                        valid = false;
                    }
                }
            }
            return valid;
        }
```
```java
    public void createAndRegisterNewOrder(float amount, String email, Property property) {
        Order order = new Order(email, property, amount, "Pending");
        orderRepository.add(order);
    }
```
# 6. Integration and Demo

* A new option was added to the Client menu (Place An Order).

# 7. Observations

n/a