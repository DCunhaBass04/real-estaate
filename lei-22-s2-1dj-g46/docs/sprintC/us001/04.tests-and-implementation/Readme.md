# US001 - Display Listed Properties

# 4. Tests

**Test 1:** Check that an empty PropertyRepository is indeed Empty.
```java
@Test void ensureEmptyPropertyRepositoryIsEmpty(){
    PropertyAnnouncementRepository propertyAnnouncementRepository = new PropertyAnnouncementRepository();
    propertyAnnouncementRepository.isEmpty();
}
```
**Test 2:** Check that a Property is successfully added to the list of Properties in PropertyRepository.
```java
@Test void ensureLandIsAddedToList(){
    PropertyAnnouncementRepository propertyAnnouncementRepository = new PropertyAnnouncementRepository();
    Agent agent = new Agent("John", "john.doe@this.company.com", "john");
    File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
    String currentDate = "24/04/2023";
    Land land = new Land(999.99f, "here", 55.55f, agent, 999.99f, photograph, 4.44f, currentDate);
    propertyAnnouncementRepository.add(land);
    propertyAnnouncementRepository.getProperties().contains(land);
}
```
```java
@Test void ensureApartmentIsAddedToList(){
    PropertyAnnouncementRepository propertyAnnouncementRepository = new PropertyAnnouncementRepository();
    Agent agent = new Agent("John", "john.doe@this.company.com", "john");
    File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
    String currentDate = "24/04/2023";
    Apartment apartment = new Apartment(999.99f, "here", 55.55f, agent, 999.99f, 2, 1, 5, "n/a", photograph, 4.44f, currentDate);
    propertyAnnouncementRepository.add(apartment);
    propertyAnnouncementRepository.getProperties().contains(apartment);
}
```
```java
@Test void ensureHouseIsAddedToList(){
    PropertyAnnouncementRepository propertyAnnouncementRepository = new PropertyAnnouncementRepository();
    Agent agent = new Agent("John", "john.doe@this.company.com", "john");
    File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
    String currentDate = "24/04/2023";
    House house = new House(999.99f, "here", 55.55f, agent, 999.99f, 2, 1, 5, "n/a", true, true, "north", photograph, 4.44f, currentDate);
    propertyAnnouncementRepository.add(house);
    propertyAnnouncementRepository.getProperties().contains(house);
}
```
# 5. Construction (Implementation)

Just like we decided in the Design section of the US, the Unregistered User starts using the ListPropertiesUI from the MainMenuUI
```java
public void run() {
    List<MenuItem> options = new ArrayList<>();
    (...)
--> options.add(new MenuItem("List all Properties", new ListPropertiesUI()));
    (...)
    int option = 0;
    do {
        option = Utils.showAndSelectIndex(options, "\n\nMain Menu");
        if ((option >= 0) && (option < options.size())) {
       ---> options.get(option).run();
        }
    } while (option != -1);
}
```

ListPropertiesUI calls a ListPropertiesController...
```java
public void run() {
    ListPropertiesController listPropertiesController = new ListPropertiesController();
    listPropertiesController.printPropertyListToUser();
}
```
...that gets Properties from PropertyAnnouncementRepository (in instance of Repositories).
```java
public void printPropertyListToUser(){
    Scanner ler = new Scanner(System.in);
--> PropertyRepository propertyAnnouncementRepository = Repositories.getInstance().getPropertyRepository();
    System.out.println();
    if(!propertyAnnouncementRepository.isEmpty()) {
    --> propertyAnnouncementRepository.printProperties();
    } else {
        System.out.print("The Property Repository is empty, publish some sale announcements and try again.");
    }
    System.out.print("\n\nPress ENTER to continue");
    ler.nextLine();
}
```
# 6. Integration and Demo

We made sure that the option to list properties (access to ListPropertiesUI.java) was available to any type of user (unregistered, admin, agent and client).

We also made sure that, after being set by the agent, the property is sent to the Property list.

# 7. Observations

This functionality makes the system print all of its properties, along with all their necessary details.

A property request sent from the client to the agent does not appear in this list, since its commission hasn't been set by the requested agent.