# US 012 - Import a Legacy System

# 4. Tests

**Test 1:** Check that the different repositories work
```java
    @Test void storeSuccessfullyAdded(){
        StoreRepository storeRepository = new StoreRepository();
        Store store1 = new Store("RealEstateStore", "here", "98765", 123, "store@this.company.com");
        storeRepository.add(store1);
        assertTrue(storeRepository.getStores().contains(store1));
    }
```
```java
    @Test void ensureAClientIsAddedToTheRepository(){
        ClientRepository clientRepository = new ClientRepository();
        Client client = new Client("john doe", 123, "1232", "johndoe@this.company.com", "123-4567-890", "johndoe");
        clientRepository.add(client);
        assertTrue(clientRepository.getClients().contains(client));
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

## Class ImportLegacySystemUI
```java
    public void run() {
        VerifyInput verifyInput = new VerifyInput();
        Scanner scanner = new Scanner(System.in);
        boolean answer = verifyInput.verifyBoolean(scanner, "Print requested format of the .csv file (not necessary)");
        if(answer) {
            System.out.printf("The file must be in the following format:" +
                "%n-Each line is a different property (except the first one) and must consist of" +
                ":%n  -Owner's name%n  -Owner's Passport Number%n  -Owner's SSN%n  -Owner's Phone Number%n  -Property's Type" +
                "%n  -Property's Area (in square feet)%n  -Property's Location%n  -Distance from City Centre (in miles)" +
                "%n  -Number of Bedrooms ('NA' if property is Land)%n  -Number of Bathrooms ('NA' if property is Land)" +
                "%n  -Number of Parking Spaces ('NA' if property is Land)%n  -Central Heating (Y/N) ('NA' if property is Land)" +
                "%n  -Air Conditioning (Y/N) ('NA' if property is Land)%n  -Basement (Y/N) ('NA' if property is Land)" +
                "%n  -Loft (Y/N) ('NA' if property is Land)%n  -Sun Exposure Direction (N/S/E/W) ('NA' if property is Land)" +
                "%n  -Requested Price (in USD)%n  -Price (in USD)%n  -Commission Value (in %%)" +
                "%n  -Contraction Duration (in months) ('NA' if property is 'for sale')%n  -Date of Request (DD/MM/YYYY)" +
                "%n  -Date of Sale Announcement (DD/MM/YYYY)%n  -Store's ID%n  -Store's Name%n  -Store's Location" +
                "%n  -Store's Phone Number%n  -Store's Email Address%n%n%n");
        }
        String fileName = verifyInput.verifyComplexString(scanner, "Full Path to .csv file");
        try {
            File fileToImport = new File(fileName);
            ImportLegacySystemController importLegacySystemController = new ImportLegacySystemController();
            if (importLegacySystemController.verifyFile(fileName)) {
                Scanner fileReader = new Scanner(fileToImport);
                boolean operationSuccessful = importLegacySystemController.importFile(fileReader);
                fileReader.close();
                if (operationSuccessful) {
                    System.out.println("Operation Successful");
                } else {
                    System.out.println("Operation Failed, file format is incorrect.");
                }
            } else {
                System.out.println("Given file is not a .csv. Please insert a valid file.");
            }
        } catch (IOException e){
            System.out.println("File does not exist");
        }
        System.out.print("\n\nPress ENTER to continue");
        scanner.nextLine();
        }
    }

```

## Class ImportLegacySystemController
```java
    public boolean verifyFile(String fileName) throws IOException {
        boolean valid = true;
        Scanner fileReader = new Scanner(fileName);
        if (!fileName.contains(".csv")) {
            valid = false;
        }
        return valid;
    }
```
```java
    public boolean importFile(Scanner fileReader) throws IOException {
        try {
            fileReader.nextLine();
            PropertySoldRepository propertySoldRepository = Repositories.getInstance().getPropertySoldRepository();
            while (fileReader.hasNext()) {
                String line = fileReader.nextLine();
                propertySoldRepository.add(useLine(line));
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
```

# 6. Integration and Demo

* Two new options were added to the Admin's menu:
  * The option to import the .csv containing the legacy system.
  * The option to list sold properties (also helps with US11).

# 7. Observations

n/a