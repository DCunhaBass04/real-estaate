# US 008 - List Property Announcement Requests

# 4. Tests

**Test 1:** Check that a property request is properly added to the repository
```java
@Test void ensureLandRequestIsAddedToList(){
    PropertyRequestRepository requestRepository = new PropertyRequestRepository();
    Agent agent = new Agent("John", "john.doe@this.company.com", "john");
    File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
    LandRequest land = new LandRequest(999.99f, "here", 55.55f, agent, 999.99f, photograph);
    requestRepository.add(land);
    assertTrue(requestRepository.getProperties().contains(land));
}
```
```java
@Test void ensureApartmentRequestIsAddedToList(){
    PropertyRequestRepository requestRepository = new PropertyRequestRepository();
    Agent agent = new Agent("John", "john.doe@this.company.com", "john");
    File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
    ApartmentRequest apartment = new ApartmentRequest(999.99f, "here", 55.55f, agent, 999.99f, 2, 1, 5, "n/a", photograph);
    requestRepository.add(apartment);
    assertTrue(requestRepository.getProperties().contains(apartment));
}
```
```java
@Test void ensureHouseRequestIsAddedToList(){
    PropertyRequestRepository requestRepository = new PropertyRequestRepository();
    Agent agent = new Agent("John", "john.doe@this.company.com", "john");
    File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
    HouseRequest house = new HouseRequest(999.99f, "here", 55.55f, agent, 999.99f, 2, 1, 5, "n/a", true, true, "north", photograph);
    requestRepository.add(house);
    assertTrue(requestRepository.getProperties().contains(house));
}
```
**Test 2:** Check that a Property is not null
```java
@Test void ensureLandDoesNotEqualNull() {
    Agent agent = new Agent("John", "john.doe@this.company.com", "john");
    File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
    Land land1 = new Land(999.99f, "here", 55.55f, agent, 999.99f, photograph, 4.44f, "24/04/2023");
    assertNotEquals(land1, null);
}
```
```java
@Test void ensureApartmentDoesNotEqualNull() {
    Agent agent = new Agent("John", "john.doe@this.company.com", "john");
    File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
    Apartment apartment1 = new Apartment(999.99f, "here", 55.55f, agent, 999.99f, 2, 1, 5, "n/a",  photograph, 4.44f, "24/04/2023");
    assertNotEquals(apartment1, null);
}
```
```java
@Test void ensureHouseDoesNotEqualNull() {
    Agent agent = new Agent("John", "john.doe@this.company.com", "john");
    File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
    House house1 = new House(999.99f, "here", 55.55f, agent, 999.99f, 2, 1, 5, "n/a", true, true, "north",  photograph, 4.44f, "24/04/2023");
    assertNotEquals(house1, null);
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


## Class SetCommissionController

```java
        public List<Property> getAllPropertiesFromCurrentAgent(){
            String agentEmail = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().toString();
            PropertyRequestRepository requestRepository = Repositories.getInstance().getPropertyRequestRepository();
            return requestRepository.getPropertyRequestedToXAgent(agentEmail);
        }
```
```java
        public List<Property> getListOfXTypeOfRequest(List<Property> properties, Scanner sc){
            boolean valid = false;
            int option;
            VerifyInput verifyInput = new VerifyInput();
            do{
                option = verifyInput.verifyInt(sc, "Type of Request");
                if (option == 1 || option == 2 || option == 3){
                    valid = true;
                } else {
                    System.out.println("Please insert a valid number.");
                }
            }while(!valid);
            if(option == 1 || option == 2){
                EditAndSortLists editAndSortLists = new EditAndSortLists();
                switch(option){
                    case 1:
                       properties = editAndSortLists.getPropertiesWithXTypeOfSale(properties, "for sale");
                       break;
                    case 2:
                      properties = editAndSortLists.getPropertiesWithXTypeOfSale(properties, "for rent");
                      break;
                }
            }
            return properties;
        }
```
```java
        public List<Property> getListOfXTypeOfProperty(List<Property> properties, Scanner sc){
            boolean valid = false;
            int option;
            VerifyInput verifyInput = new VerifyInput();
            do{
                option = verifyInput.verifyInt(sc, "Type of Property");
                if (option == 1 || option == 2 || option == 3 || option == 4){
                    valid = true;
                } else {
                    System.out.println("Please insert a valid number.");
                }
            }while(!valid);
            if(option == 1 || option == 2 || option == 3){
                EditAndSortLists editAndSortLists = new EditAndSortLists();
                switch(option){
                    case 1:
                        properties = editAndSortLists.getPropertiesIfTheyAreLand(properties);
                        break;
                    case 2:
                        properties = editAndSortLists.getPropertiesIfTheyAreApartment(properties);
                        break;
                    case 3:
                        properties = editAndSortLists.getPropertiesIfTheyAreHouse(properties);
                        break;
                }
            }
            return properties;
        }
```
# 6. Integration and Demo

* No new option was added to the Agent menu, the menu for US2 was reused.

# 7. Observations

This US ends up being part of US2 due to its nature (US8 prints all requests, US2 sets their commissions).
