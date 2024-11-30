# US 011 - Accept Offers

# 4. Tests

**Test 1:** Check that a different Orders are indeed different, and that 2 equal orders are indeed equal
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
**Test 2:** Check that an order was correctly added to the repository
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
**Test 3:** Check that a sold property is added to the PropertySoldRepository
```java
    @Test void ensureLandIsAddedToList(){
        PropertySoldRepository propertySoldRepository = new PropertySoldRepository();
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Sold", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph, 22222, "17/05/2023", "26/05/2023", request);
        propertySoldRepository.add(land);
        assertTrue(propertySoldRepository.getProperties().contains(land));
    }
```
```java
    @Test void ensureApartmentIsAddedToList(){
        PropertySoldRepository propertySoldRepository = new PropertySoldRepository();
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Sold", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Apartment apartment = new Apartment(999.99f, "here", 55.55f, agent,  2, 1, 5, true, true, photograph, 22222, "17/05/2023", "26/05/2023", request);
        propertySoldRepository.add(apartment);
        assertTrue(propertySoldRepository.getProperties().contains(apartment));
    }
```
```java
    @Test void ensureHouseIsAddedToList(){
        PropertySoldRepository propertySoldRepository = new PropertySoldRepository();
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Sold", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        House house = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "26/05/2023", request);
        propertySoldRepository.add(house);
        assertTrue(propertySoldRepository.getProperties().contains(house));
    }
```
# 5. Construction (Implementation)

## Class AcceptOffersUI
```java
    public void run() {
        String email = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().getEmail();
        List<Property> listOfProperties = ctrl.getPropertiesByCurrentAgent(email);
        for(Property property : listOfProperties){
            manageOrdersByProperty(property);
        }
    }
```
```java
    private void manageOrdersByProperty(Property property){
        VerifyInput verifyInput = new VerifyInput();
        Scanner ler = new Scanner(System.in);
        boolean accepted = false;
        List<Order> listOfOrders = ctrl.getOrdersToXProperty(property);
        System.out.println("Orders for:");
        System.out.println(property);
        printAllOrdersForXProperty(listOfOrders);
        if(!listOfOrders.isEmpty()) {
            for (int i = 0; i < listOfOrders.size() && !accepted; i++) {
                System.out.println("Order no. " + (i + 1));
                boolean response = verifyInput.verifyBoolean(ler, "Do you accept this order");
                if (response) {
                    ctrl.acceptOffer(property, listOfOrders, i);
                    accepted = true;
                    System.out.println("Offer accepted! All other offers for this property were declined.");
                    ler.nextLine();
                } else {
                    ctrl.declineOffer(i);
                }
            }
        }
    }
```

## Class AcceptOffersController
```java
    public List<Property> getPropertiesByCurrentAgent(String email) {
        List<Property> announcementList = Repositories.getInstance().getPropertyRepository().getProperties();
        announcementList = editAndSortLists.getPropertiesByXAgent(announcementList, email);
        return announcementList;
    }
```
```java
    public List<Order> getOrdersToXProperty(Property property) {
        List<Order> orderList = Repositories.getInstance().getOrderRepository().getOrders();
        orderList = editAndSortLists.getOrdersDoneToXProperty(orderList, property);
        orderList = editAndSortLists.sortOrdersByDescendingAmount(orderList);
        return orderList;
    }
```
```java
    public void acceptOffer(Property property, List<Order> listOfOrders, int index) {
        PropertyAnnouncementRepository propertyAnnouncementRepository = Repositories.getInstance().getPropertyRepository();
        PropertySoldRepository propertySoldRepository = Repositories.getInstance().getPropertySoldRepository();
        propertyAnnouncementRepository.remove(property);
        property.setFinalPrice(listOfOrders.get(index).getAmount());
        propertySoldRepository.add(property);
        OrderRepository orderRepository = Repositories.getInstance().getOrderRepository();
        orderRepository.getOrders().get(index).setState("Accepted");
        if (index != (listOfOrders.size() - 1)) {
            for (int i = index + 1; i < listOfOrders.size(); i++) {
                declineOffer(i);
            }
        }
    }
```
```java
    public void declineOffer(int index) {
        OrderRepository orderRepository = Repositories.getInstance().getOrderRepository();
        orderRepository.getOrders().get(index).setState("Declined");
    }
```

# 6. Integration and Demo

* A new option was added to the Agent menu in order to accept offers.
* A new option was also added to the Client menu in order to check responses to said offers.

# 7. Observations

n/a