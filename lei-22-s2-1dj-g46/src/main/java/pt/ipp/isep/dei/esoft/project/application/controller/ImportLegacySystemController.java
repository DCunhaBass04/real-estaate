package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Users.Client;
import pt.ipp.isep.dei.esoft.project.domain.Property.*;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.io.IOException;
import java.util.Scanner;

public class ImportLegacySystemController {

    private final float DOLLARTOEURO = 0.93f;
    private final float FEET2TOMETER2 = 0.0929f;
    private final float MILETOKM = 1.609344f;

    /**
     * Verifies if the given file name is valid. The file name must have the ".csv" extension.
     *
     * @param fileName the name of the file to be verified
     * @return true if the file name is valid, false otherwise
     * @throws IOException if an I/O error occurs
     */
    public boolean verifyFile(String fileName) throws IOException {
        boolean valid = true;
        Scanner fileReader = new Scanner(fileName);
        if (!fileName.contains(".csv")) {
            valid = false;
        }
        return valid;
    }

    /**
     * Imports data from the provided file reader.
     *
     * @param fileReader the scanner for reading the file
     * @return true if the import is successful, false otherwise
     * @throws IOException if an I/O error occurs
     */
    public boolean importFile(Scanner fileReader) throws IOException {
        try {
            fileReader.nextLine();
            PropertySoldRepository propertySoldRepository = Repositories.getInstance().getPropertySoldRepository();
            while (fileReader.hasNext()) {
                String line = fileReader.nextLine();
                Property nextProperty = useLine(line);
                Store responsibleStore = Repositories.getInstance().getStoreRepository().getStoreByEmail(nextProperty.getStoreEmail());
                responsibleStore.addProperty(nextProperty);
                propertySoldRepository.add(nextProperty);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Processes a line from the file and creates a property object based on the line data.
     *
     * @param line the line from the file
     * @return the created property object
     */
    public Property useLine(String line) {
        String[] itemsPerLine = line.split(";");
        String typeOfProperty = itemsPerLine[6];
        float propertyArea = Float.parseFloat(itemsPerLine[7]) * FEET2TOMETER2;
        String propertyLocation = itemsPerLine[8];
        float propertyDistance = Integer.parseInt(itemsPerLine[9]) * MILETOKM;
        Store store = getResponsibleStore(itemsPerLine);
        float finalPrice = Float.parseFloat(itemsPerLine[19]) * DOLLARTOEURO;
        String announcementDate = itemsPerLine[22];
        String saleDate = itemsPerLine[23];
        Request request = createRequest(itemsPerLine);
        if (typeOfProperty.equals("apartment") || typeOfProperty.equals("house")) {
            int numberOfBedrooms = Integer.parseInt(itemsPerLine[10]);
            int numberOfBathrooms = Integer.parseInt(itemsPerLine[11]);
            int numberOfParkingSpaces = Integer.parseInt(itemsPerLine[12]);
            boolean centralHeating = Boolean.parseBoolean(itemsPerLine[13]);
            boolean airConditioning = Boolean.parseBoolean(itemsPerLine[14]);
            if (typeOfProperty.equals("house")) {
                boolean basement = Boolean.parseBoolean(itemsPerLine[15]);
                boolean loft = Boolean.parseBoolean(itemsPerLine[16]);
                String sunExposureDirection = itemsPerLine[17];
                return new House(propertyArea, propertyLocation, propertyDistance, numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, centralHeating, airConditioning, basement, loft, sunExposureDirection, null, finalPrice, announcementDate, saleDate, request, store);
            } else {
                return new Apartment(propertyArea, propertyLocation, propertyDistance, numberOfBedrooms, numberOfBathrooms, numberOfParkingSpaces, centralHeating, airConditioning, null, finalPrice, announcementDate, saleDate, request, store);
            }
        } else {
            return new Land(propertyArea, propertyLocation, propertyDistance, null, finalPrice, announcementDate, saleDate, request, store);
        }
    }

    /**
     * Retrieves the store responsible for the property based on the line data.
     *
     * @param itemsPerLine the array of line data
     * @return the store object responsible for the property
     */
    private Store getResponsibleStore(String[] itemsPerLine) {
        String storeName = itemsPerLine[26];
        int storeID = Integer.parseInt(itemsPerLine[25]);
        String storeAddress = itemsPerLine[27];
        String storePhoneNumber = itemsPerLine[28];
        String storeEmail = itemsPerLine[29];
        Store store = new Store(storeName, storeAddress, storePhoneNumber, storeID, storeEmail);
        createStore(store);
        return store;
    }

    /**
     * Creates a request object based on the line data.
     *
     * @param itemsPerLine the array of line data
     * @return the created request object
     */
    public Request createRequest(String[] itemsPerLine) {
        Client client = createOwner(itemsPerLine);
        String typeOfRequest = "for " + itemsPerLine[24];
        float requestedPrice = Float.parseFloat(itemsPerLine[18]) * DOLLARTOEURO;
        String commissionValue = itemsPerLine[20] + "%";
        int duration = 1;
        String priceUnit = "€";
        if (typeOfRequest.equals("for rent")) {
            duration = Integer.parseInt(itemsPerLine[21]);
            priceUnit = "€/month";
        }
        return new Request(typeOfRequest, requestedPrice, priceUnit, "Sold", "unknown", client, commissionValue, duration);
    }

    /**
     * Creates an owner/client object based on the line data.
     *
     * @param itemsPerLine the array of line data
     * @return the created client object
     */
    public Client createOwner(String[] itemsPerLine) {
        String ownerName = itemsPerLine[1];
        int ownerPassportNumber = Integer.parseInt(itemsPerLine[2]);
        String ownerSSN = itemsPerLine[3];
        String ownerEmail = itemsPerLine[4];
        String ownerPhoneNumber = itemsPerLine[5];
        Client client = new Client(ownerName, ownerPassportNumber, ownerSSN, ownerEmail, ownerPhoneNumber);
        ClientRepository clientRepository = Repositories.getInstance().getClientRepository();
        if (!clientRepository.getClients().contains(client)) {
            clientRepository.add(client);
        }
        return (client);
    }

    /**
     * Creates a store object if it does not already exist in the store repository.
     *
     * @param store the store object to be created
     */
    public void createStore(Store store) {
        StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();
        if (!storeRepository.getStores().contains(store)) {
            storeRepository.add(store);
        }
    }
}
