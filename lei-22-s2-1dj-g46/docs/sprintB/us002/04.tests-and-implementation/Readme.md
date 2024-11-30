# US002 - Real State Agent

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
**Test 3:** Check that a property is properly added to the repository
```java
@Test void ensureLandIsAddedToList(){
    PropertyRepository propertyAnnouncementRepository = new PropertyRepository();
    Agent agent = new Agent("John", "john.doe@this.company.com", "john");
    File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
    Land land = new Land(999.99f, "here", 55.55f, agent, 999.99f, photograph, 4.44f, "24/04/2023");
    propertyAnnouncementRepository.add(land);
    assertTrue(propertyAnnouncementRepository.getProperties().contains(land));
}
```
```java
@Test void ensureApartmentIsAddedToList(){
    PropertyRepository propertyAnnouncementRepository = new PropertyRepository();
    Agent agent = new Agent("John", "john.doe@this.company.com", "john");
    File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
    Apartment apartment = new Apartment(999.99f, "here", 55.55f, agent, 999.99f, 2, 1, 5, "n/a", photograph, 4.44f, "24/04/2023");
    propertyAnnouncementRepository.add(apartment);
    assertTrue(propertyAnnouncementRepository.getProperties().contains(apartment));
}
```
```java
@Test void ensureHouseIsAddedToList(){
    PropertyRepository propertyAnnouncementRepository = new PropertyRepository();
    Agent agent = new Agent("John", "john.doe@this.company.com", "john");
    File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
    House house = new House(999.99f, "here", 55.55f, agent, 999.99f, 2, 1, 5, "n/a", true, true, "north", photograph, 4.44f, "24/04/2023");
    propertyAnnouncementRepository.add(house);
    assertTrue(propertyAnnouncementRepository.getProperties().contains(house));
}
```

# 5. Construction (Implementation)

## Class SetCommissionController
```java
public void setCommission(PropertyRequest propertyRequest, VerifyInput verifyInput, Scanner input){
    System.out.printf("In what format do you want to set your commission?%n1 - Percentage%n2 - Fixed Value");
    boolean valid = false;
    int response;
    float commissionValue = 0;
    do{
        response = verifyInput.verifyInt(input, "Response");
        switch(response){
            case 1:
                commissionValue = setCommissionValue(100, "(in percentage, from 0 to 100)", input, verifyInput) * propertyRequest.getRequestedPrice() / 100;
                valid = true;
                break;
            case 2:
                commissionValue = setCommissionValue(propertyRequest.getRequestedPrice(), "(fixed value in €, from 0 to the requested price)", input, verifyInput);
                valid = true;
        }
    }while(!valid);
    PropertyRepository propertyAnnouncementRepository = Repositories.getInstance().getPropertyRepository();
    LocalDate localDate = LocalDate.now();
    String commissionDate = localDate.getDayOfMonth() + "/" + localDate.getMonth() + "/" + localDate.getYear();
    if(propertyRequest instanceof LandRequest){
        propertyAnnouncementRepository.add(new Land((LandRequest) propertyRequest, commissionValue, commissionDate));
    } else if (propertyRequest instanceof ApartmentRequest){
        propertyAnnouncementRepository.add(new Apartment((ApartmentRequest) propertyRequest, commissionValue, commissionDate));
    } else {
        propertyAnnouncementRepository.add(new House((HouseRequest) propertyRequest, commissionValue, commissionDate));
    }
}
```
# 6. Integration and Demo

We made sure that the chosen agent can see their requests and that the date is attached to the property object.
# 7. Observations

n/a