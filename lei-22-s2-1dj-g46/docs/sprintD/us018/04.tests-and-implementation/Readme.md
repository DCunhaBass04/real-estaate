# US 018 - Deal Analysis

# 4. Tests

**Test 1:** Check if the deal repository is empty (if it is, the user shouldn't be able to proceed).
```java
    @Test void ensureEmptyPropertyRepositoryIsEmpty(){
        PropertySoldRepository propertySoldRepository = new PropertySoldRepository();
        propertySoldRepository.isEmpty();
    }
```
**Test 2:** Check that the deal was correctly added to the system.
```java
    @Test void ensureGetDealsWorks(){
        PropertySoldRepository propertySoldRepository = new PropertySoldRepository();
        Agent agent = new Agent("john.doe@this.company.com");
        File[] photograph = {new File("C:\\Users\\35193\\Desktop\\test.jpg")};
        Request request = new Request("for sale", 22222, "€", "Sold", "22/02/2023", new Client("owner", "owner@this.app", "password"), "4,4%", 1);
        Land land = new Land(999.99f, "here", 55.55f, agent, photograph, 22222, "17/05/2023", "26/05/2023", request);
        House house = new House(999.99f, "here", 55.55f, agent, 2, 1, 5, true, true, true, true, "north", photograph, 22222, "17/05/2023", "26/05/2023", request);
        propertySoldRepository.add(land);
        propertySoldRepository.add(house);
        List<Property> propertyList = new ArrayList<>();
        propertyList.add(land);
        propertyList.add(house);
        assertEquals(propertyList, propertySoldRepository.getProperties());
    }
```
# 5. Construction (Implementation)

## Class AnalyzeDealsUI
```java
    public void run() {
        List<Property> soldApartmentsOrHouses = ctrl.getApartmentsOrHouses();
        if(!(soldApartmentsOrHouses.size() < 2)) {
            System.out.printf("What linear regression model do you desire to see?%n1 - 'Area' to 'Price'.%n2 - 'Distance from city centre' to 'Price'" +
                "%n3 - 'Number of Bedrooms' to 'Price'%n4 - 'Number of Bathrooms' to 'Price'%n5 - 'Number of Parking Spaces' to 'Price'%n6 - Multilinear regression%n0 - Exit%n");
            int option = getOption();
            if(option != 0) {
                double[] listOfValues;
                if(option != 6) {
                    listOfValues = getDesiredLinearRegression(option);
                    doLinearRegression(listOfValues);
                } else {
                    listOfValues = ctrl.getMultipleLinearRegression();
                    doMultipleLinearRegression(listOfValues);
                }
                boolean proceed = verifyInput.verifyBoolean(sc, "Do you desire to use this formula to predict property prices");
                if (proceed) {
                    soldApartmentsOrHouses = askAboutFilters(soldApartmentsOrHouses);
                    soldApartmentsOrHouses = askAboutSortSetting(soldApartmentsOrHouses);
                    printPropertyList(soldApartmentsOrHouses, listOfValues);
                }
            }
        } else {
            System.out.println("There are no sold properties to study yet.");
        }
        System.out.println("Press ENTER to continue.");
        sc.nextLine();
    }
```
```java
    private void doLinearRegression(double[] listOfValues){
        System.out.printf("price = %.2f * %s + %.2f%n", listOfValues[0], item, listOfValues[1]);
        //y = ax + b
        System.out.printf("R = %.2f, %s%nR^2 = %.2f, %s%n", listOfValues[2], ctrl.getRRating(listOfValues[2]), listOfValues[3], ctrl.getRRating(listOfValues[3]));
    }
```
```java
    private double[] getDesiredLinearRegression(int option){
        double[] listOfValues = new double[4];
        //slope, intercept, r, r^2
        switch(option){
            case 1:
                listOfValues = ctrl.getAreaToPriceLinearRegression();
                item = AREA;
                break;
            case 2:
                listOfValues = ctrl.getDistanceToPriceLinearRegression();
                item = DISTANCE;
                break;
            case 3:
                listOfValues = ctrl.getNumOfBedroomsToPriceLinearRegression();
                item = NUMBEROFBEDROOMS;
                break;
            case 4:
                listOfValues = ctrl.getNumOfBathroomsToPriceLinearRegression();
                item = NUMBEROFBATHROOMS;
                break;
            case 5:
                listOfValues = ctrl.getNumOfParkingSpacesToPriceLinearRegression();
                item = NUMBEROFPARKINGSPACES;
                break;
        }
        return listOfValues;
    }
```
```java
    private void doMultipleLinearRegression(double[] listOfValues){
        //x0, betaArea, betaDistance, betaBedrooms, betaBathrooms, betaParkingSpaces, r, r2, adjusted r2
        System.out.printf("price = %.2f * %s + %.2f * %s + %.2f * %s + %.2f * %s + %.2f * %s + %.2f%n", listOfValues[1], AREA, listOfValues[2], DISTANCE, listOfValues[3],
            NUMBEROFBEDROOMS, listOfValues[4], NUMBEROFBATHROOMS, listOfValues[5], NUMBEROFPARKINGSPACES, listOfValues[0]);
        //y = ax + b
        System.out.printf("R = %.2f, %s%nR^2 = %.2f, %s%nAdjusted R^2 = %.2f, %s%n", listOfValues[6], ctrl.getRRating(listOfValues[6]), listOfValues[7], ctrl.getRRating(listOfValues[7]), listOfValues[8], ctrl.getRRating(listOfValues[8]));
    }
```
```java
    public void printPropertyList(List<Property> propertyList, double[] data) {
        if (!propertyList.isEmpty()) {
            System.out.println();
            if(data.length == 4) {
                for (Property property : propertyList) {
                    double estimatedPrice = ctrl.calculateSimpleLinearPrice(data, item, property);
                    System.out.printf("%sEstimated Price: %.2f$ (%.2f€)%n%n", property, estimatedPrice, estimatedPrice / EUROTODOLLAR);
                }
            } else {
                for (Property property : propertyList) {
                    double estimatedPrice = ctrl.calculateMultipleLinearPrice(data, property);
                    System.out.printf("%sEstimated Price: %.2f$ (%.2f€)%n%n", property, estimatedPrice, estimatedPrice / EUROTODOLLAR);
                }
            }
        } else {
            System.out.println("There are no properties with the requested characteristics");
        }
    }
```
## Class AnalyzeDealsController
```java
    public double[] getAreaToPriceLinearRegression(){
        List<Property> apartmentsAndHouses = getApartmentsOrHouses();
        return getEquation(getAreaArray(apartmentsAndHouses), getPriceArray(apartmentsAndHouses));
    }
```
```java
    public double[] getDistanceToPriceLinearRegression(){
        List<Property> apartmentsAndHouses = getApartmentsOrHouses();
        return getEquation(getDistanceArray(apartmentsAndHouses), getPriceArray(apartmentsAndHouses));
    }
```
```java
    public double[] getNumOfBedroomsToPriceLinearRegression(){
        List<Property> apartmentsAndHouses = getApartmentsOrHouses();
        return getEquation(getBedroomArray(apartmentsAndHouses), getPriceArray(apartmentsAndHouses));
    }
```
```java
    public double[] getNumOfBathroomsToPriceLinearRegression(){
        List<Property> apartmentsAndHouses = getApartmentsOrHouses();
        return getEquation(getBathroomsArray(apartmentsAndHouses), getPriceArray(apartmentsAndHouses));
    }
```
```java
    public double[] getNumOfParkingSpacesToPriceLinearRegression(){
        List<Property> apartmentsAndHouses = getApartmentsOrHouses();
        return getEquation(getParkingSpacesArray(apartmentsAndHouses), getPriceArray(apartmentsAndHouses));
    }
```
```java
    public double[] getMultipleLinearRegression(){
        List<Property> apartmentsAndHouses = getApartmentsOrHouses();
        double[] y = getPriceArray(apartmentsAndHouses);
        double[][] x = getX(apartmentsAndHouses);
        olsMultipleLinearRegression.newSampleData(y, x);
        double[] parameters = olsMultipleLinearRegression.estimateRegressionParameters();
        double rSquare = olsMultipleLinearRegression.calculateRSquared();
        double r = Math.sqrt(rSquare);
        double adjustedRSquare = olsMultipleLinearRegression.calculateAdjustedRSquared();
        return new double[]{parameters[0], parameters[1], parameters[2], parameters[3], parameters[4], parameters[5], r, rSquare, adjustedRSquare};
    }
```
```java
    public double[] getEquation(double[] x, double[] y){
        SimpleRegression simpleRegression = new SimpleRegression();
        for (int i = 0; i < x.length; i++) {
            simpleRegression.addData(x[i], y[i]);
        }
        return new double[]{simpleRegression.getSlope(), simpleRegression.getIntercept(), simpleRegression.getR(), simpleRegression.getRSquare()};
    }
```
```java
    public String getRRating(double r){
        String rating = "";
        if(r <= 0.25){
            rating = "Very Unreliable";
        }else if(r <= 0.50){
            rating = "Unreliable";
        }else if(r <= 0.70){
            rating = "Reliable";
        }else if(r <= 0.90){
            rating = "Very Reliable";
        }else{
            rating = "Extremely Reliable";
        }
        return rating;
    }
```
```java
    public double calculateSimpleLinearPrice(double[] data, String item, Property property) {
        double x = getX(item, property);
        return (data[0] * x + data[1]);
        //y = a * x + b
    }
```
```java
    public double calculateMultipleLinearPrice(double[] data, Property property){
        return (data[1] * (property.getArea() * METER2TOFEET2)) + (data[2] * (property.getDistance() * KMTOMILE)) + (data[3] * property.getNumOfBedrooms()) + (data[4] * property.getNumOfBathrooms()) + (data[5] * property.getNumOfParkingSpaces()) + data[0];
    }
```
# 6. Integration and Demo

* A new option was added to Store Manager's menu (on console and on JavaFX)

# 7. Observations

n/a