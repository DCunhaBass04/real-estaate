package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.Order;
import pt.ipp.isep.dei.esoft.project.domain.Property.Apartment;
import pt.ipp.isep.dei.esoft.project.domain.Property.House;
import pt.ipp.isep.dei.esoft.project.domain.Property.Land;
import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.domain.Response;
import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.*;

public class EditAndSortLists implements Serializable {

    /**
     * Prepares to sort the list with a Merge Sort or a Bubble Sort.
     * The method to be used is specific in the file 'sortingConfig.properties'
     * @param list the list of messages to be sorted
     * @param ascending is it ascending? or not?
     * @return the sorted list
     */
    public List<Message> sortListThroughConfigFile(List<Message> list, boolean ascending){
        Properties config = new Properties();
        try (InputStream input = new FileInputStream("sortingConfig.properties")) {
            config.load(input);
        }catch (IOException e){
            System.out.printf("Something went wrong when sorting the list. Try again.%n");
        }

        String algorithm = config.getProperty("algorithm");

        if ("bubble".equalsIgnoreCase(algorithm)) {
            list = bubbleSortAscending(list);
        }

        if ("merge".equalsIgnoreCase(algorithm)){
            list = genericMergeSortStart(list, ascending);
        }
        return list;
    }

    /**
     * This method sorts a list of properties in descending order of its area
     * It is using the Bubble Sort method
     * @param propertyList list of properties
     * @return sorted list of properties
     */
    public List<Property> bubbleSortDescending(List<Property> propertyList){
        List<Property> mutableList = new ArrayList<>(propertyList);
        int n = mutableList.size();
        boolean swapped = true;
        for (int i = 0; i < n-1 && swapped; i++) {
            swapped = false;
            for (int j = 0; j < n-i-1; j++) {
                Property current = mutableList.get(j);
                Property next = mutableList.get(j + 1);
                if ((current).getArea() < (next).getArea()) {
                    Collections.swap(mutableList, j, j + 1);
                    swapped = true;
                }
            }
        }
        return Collections.unmodifiableList(mutableList);
    }

    /**
     * This method sorts a list of <E> in ascending order (of the property area, is E is Property).
     * It is using the Bubble Sort method
     * @param list the list of <E>
     * @return the sorted list
     * @param <E> Message or Property
     */
    public <E> List<E> bubbleSortAscending(List<E> list){
        List<E> mutableList = new ArrayList<>(list);
        int n = mutableList.size();
        boolean swapped = true;
        for (int i = 0; i < n-1 && swapped; i++) {
            swapped = false;
            for (int j = 0; j < n-i-1; j++) {
                E current = mutableList.get(j);
                E next = mutableList.get(j + 1);
                if (current instanceof Message){
                    if (((Message)current).getPreferredDateInComparableInt() > ((Message)next).getPreferredDateInComparableInt()) {
                        Collections.swap(mutableList, j, j + 1);
                        swapped = true;
                    }
                }
                if (current instanceof Property){
                    if (((Property)current).getArea() > ((Property)next).getArea()) {
                        Collections.swap(mutableList, j, j + 1);
                        swapped = true;
                    }
                }
            }
        }
        return Collections.unmodifiableList(mutableList);
    }

    /**
     * This method is the preparation to sort a list of <E>.
     * The method that derive from this one will use the Merge Sort method
     * @param list list of <E>
     * @param ascending is it ascending? or not?
     * @return the sorted list
     * @param <E> Message or Property
     */
    public <E> List<E> genericMergeSortStart(List<E> list, boolean ascending){
        E object = list.get(0);
        if (object instanceof Message){
            list = (List<E>) mergeSortMessageList((List<Message>) list);
        }
        if (object instanceof Property){
            list = (List<E>) mergeSortPropertyList((List<Property>) list, ascending);
        }
        return list;
    }

    /**
     * This method is the 'Division Phase' of the Merge Sort method
     * @param propertyList list of properties
     * @param ascending is it ascending? or not?
     * @return sorted/divided list
     */
    public List<Property> mergeSortPropertyList(List<Property> propertyList, boolean ascending){
        int length = propertyList.size();
        if (length < 2){
            return propertyList;
        }

        int middle = length / 2;
        List<Property> leftSideOfList = new ArrayList<>(middle);
        List<Property> rightSideOfList = new ArrayList<>(length - middle);

        for (int i = 0; i < middle; i++) {
            leftSideOfList.add(propertyList.get(i));
        }

        for (int i = middle; i < length; i++) {
            rightSideOfList.add(propertyList.get(i));
        }

        leftSideOfList = mergeSortPropertyList(leftSideOfList, ascending);
        rightSideOfList = mergeSortPropertyList(rightSideOfList, ascending);

        if (ascending){
            propertyList = mergeAscendingPropertyArrayList(propertyList, leftSideOfList, rightSideOfList);
        } else {
            propertyList = mergeDescendingPropertyArrayList(propertyList, leftSideOfList, rightSideOfList);
        }
        return propertyList;
    }

    /**
     * This method is the ascending version of the 'Merge Phase' of the Merge Sort method
     * @param propertyList property list
     * @param left left half of the list
     * @param right right half of the list
     * @return the sorted list
     */
    public List<Property> mergeAscendingPropertyArrayList(List<Property> propertyList, List<Property> left, List<Property> right){
        List<Property> mutablePropertyList = new ArrayList<>(propertyList);
        int leftLength = left.size();
        int rightLength = right.size();

        int i = 0, j = 0, k = 0;

        while (i < leftLength && j < rightLength){
            if (left.get(i).getArea() <= right.get(j).getArea()){
                mutablePropertyList.set(k, left.get(i));
                i++;
            } else {
                mutablePropertyList.set(k, right.get(j));
                j++;
            }
            k++;
        }

        while (i < leftLength){
            mutablePropertyList.set(k, left.get(i));
            i++;
            k++;
        }

        while (j < rightLength){
            mutablePropertyList.set(k, right.get(j));
            j++;
            k++;
        }
        return Collections.unmodifiableList(mutablePropertyList);
    }
    /**
     * This method is the descending version of the 'Merge Phase' of the Merge Sort method
     * @param propertyList property list
     * @param left left half of the list
     * @param right right half of the list
     * @return the sorted list
     */
    public List<Property> mergeDescendingPropertyArrayList(List<Property> propertyList, List<Property> left, List<Property> right){
        List<Property> mutablePropertyList = new ArrayList<>(propertyList);
        int leftLength = left.size();
        int rightLength = right.size();

        int i = 0, j = 0, k = 0;

        while (i < leftLength && j < rightLength){
            if (left.get(i).getArea() >= right.get(j).getArea()){
                mutablePropertyList.set(k, left.get(i));
                i++;
            } else {
                mutablePropertyList.set(k, right.get(j));
                j++;
            }
            k++;
        }

        while (i < leftLength){
            mutablePropertyList.set(k, left.get(i));
            i++;
            k++;
        }

        while (j < rightLength){
            mutablePropertyList.set(k, right.get(j));
            j++;
            k++;
        }
        return Collections.unmodifiableList(mutablePropertyList);
    }
    /**
     * This method is the 'Division Phase' of the Merge Sort method
     * @param messageList list of messages
     * @return sorted/divided list
     */
    public List<Message> mergeSortMessageList(List<Message> messageList){
        int length = messageList.size();
        if (length < 2 ){
            return messageList;
        }

        int middle = length/2;
        List<Message> leftSideOfList= new ArrayList<Message>(middle);
        List<Message> rightSideOfList = new ArrayList<Message>(length - middle);

        for (int i = 0; i < middle; i++) {
            leftSideOfList.add(messageList.get(i));
        }
        for (int i = middle; i < length; i++) {
            rightSideOfList.add(messageList.get(i));
        }

        leftSideOfList = mergeSortMessageList(leftSideOfList);
        rightSideOfList = mergeSortMessageList(rightSideOfList);

        messageList = mergeMessageArrayList(messageList, leftSideOfList, rightSideOfList);
        return messageList;
    }

    /**
     * This method is the 'Merge Phase' of the Merge Sort method
     * @param messageList message list
     * @param left left half of the list
     * @param right right half of the list
     * @return the sorted list
     */
    public List<Message> mergeMessageArrayList(List<Message> messageList, List<Message> left, List<Message> right){
        List<Message> mutableMessageList = new ArrayList<>(messageList);
        int leftLength = left.size();
        int rightLength = right.size();

        int i = 0, j = 0, k = 0;

        while (i < leftLength && j < rightLength){
            if (left.get(i).getPreferredDateInComparableInt() <= right.get(j).getPreferredDateInComparableInt()){
                mutableMessageList.set(k, left.get(i));
                i++;
            } else {
                mutableMessageList.set(k, right.get(j));
                j++;
            }
            k++;
        }

        while (i < leftLength){
            mutableMessageList.set(k, left.get(i));
            i++;
            k++;
        }

        while (j < rightLength){
            mutableMessageList.set(k, right.get(j));
            j++;
            k++;
        }
        return Collections.unmodifiableList(mutableMessageList);
    }

    /**
     * This method is used to filter the list with only apartments and houses, excluding lands
     * @param properties list of properties
     * @return list of apartments and houses
     */
    public List<Property> getPropertiesIfTheyAreApartmentsOrHouses(List<Property> properties){
        List<Property> apartmentsAndHouses = new ArrayList<>();
        for(Property property : properties){
            if(property instanceof Apartment || property instanceof House){
                apartmentsAndHouses.add(property);
            }
        }
        return apartmentsAndHouses;
    }

    /**
     * This method is used to filter a list of messages, delivering one with only messages that were sent to a certain agent
     * @param messages list of messages
     * @param agentID the desired agent's email
     * @return the filtered list
     */
    public List<Message> getMessagesDoneToXAgent(List<Message> messages, String agentID){
        List<Message> messagesToXAgent = new ArrayList<>();
        for(Message message : messages){
            if(message.getAgentID().equals(agentID)){
                messagesToXAgent.add(message);
            }
        }
        return messagesToXAgent;
    }
    /**
     *
     * @param orders list of orders
     * @param property the requested property
     * @return the updated list
     */
    public List<Order> getOrdersDoneToXProperty(List<Order> orders, Property property){
        List<Order> ordersToXAgent = new ArrayList<>();
        for(Order order : orders){
            if(order.getProperty().equals(property)){
                ordersToXAgent.add(order);
            }
        }
        return ordersToXAgent;
    }

    /**
     *
     * @param propertyList list of properties
     * @param ownerEmail the email of the current owner
     * @return the updated list
     */
    public List<Property> getPropertiesIfNotFromTheSameOwner(List<Property> propertyList, String ownerEmail){
        List<Property> propertiesNotFromXOwner = new ArrayList<>();
        for(Property property : propertyList){
            if(!property.getRequest().getOwner().getEmail().equals(ownerEmail)){
                propertiesNotFromXOwner.add(property);
            }
        }
        return propertiesNotFromXOwner;
    }
    /**
     *
     * @param propertyList list of properties
     * @param type 'for sale' or 'for rent'
     * @return list of properties that meet the specified requirements
     */
    public List<Property> getPropertiesWithXTypeOfSale(List<Property> propertyList, String type){
        List<Property> propertiesWithXType = new ArrayList<>();
        for (Property property : propertyList) {
            if(property.getRequest().getType().equals(type) && !propertiesWithXType.contains(property)){
                propertiesWithXType.add(property);
            }
        }
        return propertiesWithXType;
    }

    /**
     *
     * @param propertyList list of properties
     * @return list of lands
     */
    public List<Property> getPropertiesIfTheyAreLand(List<Property> propertyList){
        List<Property> propertiesWithXType = new ArrayList<>();
        for (Property property : propertyList) {
            if(property instanceof Land && !propertiesWithXType.contains(property)){
                propertiesWithXType.add(property);
            }
        }
        return propertiesWithXType;
    }

    /**
     *
     * @param propertyList list of properties
     * @return list of apartments
     */
    public List<Property> getPropertiesIfTheyAreApartment(List<Property> propertyList){
        List<Property> propertiesWithXType = new ArrayList<>();
        for (Property property : propertyList) {
            if(property instanceof Apartment && !propertiesWithXType.contains(property)){
                propertiesWithXType.add(property);
            }
        }
        return propertiesWithXType;
    }

    /**
     *
     * @param propertyList list of properties
     * @return list of houses
     */
    public List<Property> getPropertiesIfTheyAreHouse(List<Property> propertyList){
        List<Property> propertiesWithXType = new ArrayList<>();
        for (Property property : propertyList) {
            if(property instanceof House && !propertiesWithXType.contains(property)){
                propertiesWithXType.add(property);
            }
        }
        return propertiesWithXType;
    }

    /**
     *
     * @param propertyList list of properties
     * @param agentEmail requested agent's email
     * @return list of properties associated with said agent
     */
    public List<Property> getPropertiesByXAgent(List<Property> propertyList, String agentEmail){
        List<Property> propertiesWithXType = new ArrayList<>();
        for (Property property : propertyList) {
            if(property.getAgentEmail().equals(agentEmail) && !propertiesWithXType.contains(property)){
                propertiesWithXType.add(property);
            }
        }
        return propertiesWithXType;
    }

    /**
     *
     * @param propertyList list of properties
     * @param storeEmail requested store's email
     * @return list of properties associated with said agent
     */
    public List<Property> getPropertiesByXStore(List<Property> propertyList, String storeEmail){
        List<Property> propertiesWithXType = new ArrayList<>();
        for (Property property : propertyList) {
            if(property.getStoreEmail().equals(storeEmail) && !propertiesWithXType.contains(property)){
                propertiesWithXType.add(property);
            }
        }
        return propertiesWithXType;
    }

    /**
     *
     * @param orderList list of orders
     * @return same list, but sorted by descending offered amount
     */
    public List<Order> sortOrdersByDescendingAmount(List<Order> orderList){
        List<Order> mutableOrderList = new ArrayList<>(orderList);
        for (int i = 0; i < mutableOrderList.size() - 1; i++) {
            for (int j = i + 1; j < mutableOrderList.size(); j++) {
                if(orderList.get(i).compareTo(orderList.get(j)) < 0){
                    Collections.swap(mutableOrderList, i, j);
                }
            }
        }
        return Collections.unmodifiableList(mutableOrderList);
    }

    /**
     *
     * @param propertyList list of properties
     * @return same list, but sorted by ascending price
     */
    public List<Property> sortPropertiesByAscendingPrice(List<Property> propertyList){
        List<Property> mutablePropertyList = new ArrayList<>(propertyList);
        for (int i = 0; i < mutablePropertyList.size() - 1; i++) {
            for (int j = i + 1; j < mutablePropertyList.size(); j++) {
                if(Float.compare(mutablePropertyList.get(i).getRequest().getTotalPrice(), mutablePropertyList.get(j).getRequest().getTotalPrice()) > 0){
                    Collections.swap(mutablePropertyList, i, j);
                }
            }
        }
        return Collections.unmodifiableList(mutablePropertyList);
    }
    /**
     *
     * @param propertyList list of properties
     * @return same list, but sorted by descending price
     */
    public List<Property> sortPropertiesByDescendingPrice(List<Property> propertyList){
        List<Property> mutablePropertyList = new ArrayList<>(propertyList);
        for (int i = 0; i < mutablePropertyList.size() - 1; i++) {
            for (int j = i + 1; j < mutablePropertyList.size(); j++) {
                if(Float.compare(mutablePropertyList.get(i).getRequest().getTotalPrice(), mutablePropertyList.get(j).getRequest().getTotalPrice()) < 0){
                    Collections.swap(mutablePropertyList, i, j);
                }
            }
        }
        return Collections.unmodifiableList(mutablePropertyList);
    }
    /**
     *
     * @param propertyList list of properties
     * @return same list, but sorted by ascending area
     */
    public List<Property> sortPropertiesByAscendingArea(List<Property> propertyList){
        List<Property> mutablePropertyList = new ArrayList<>(propertyList);
        for (int i = 0; i < mutablePropertyList.size() - 1; i++) {
            for (int j = i + 1; j < mutablePropertyList.size(); j++) {
                if(mutablePropertyList.get(i).getArea() > mutablePropertyList.get(j).getArea()){
                    Collections.swap(mutablePropertyList, i, j);
                }
            }
        }
        return Collections.unmodifiableList(mutablePropertyList);
    }
    /**
     *
     * @param propertyList list of properties
     * @return same list, but sorted by descending area
     */
    public List<Property> sortPropertiesByDescendingArea(List<Property> propertyList){
        List<Property> mutablePropertyList = new ArrayList<>(propertyList);
        for (int i = 0; i < mutablePropertyList.size() - 1; i++) {
            for (int j = i + 1; j < mutablePropertyList.size(); j++) {
                if(mutablePropertyList.get(i).getArea() < mutablePropertyList.get(j).getArea()){
                    Collections.swap(mutablePropertyList, i, j);
                }
            }
        }
        return Collections.unmodifiableList(mutablePropertyList);
    }
    /**
     *
     * @param propertyList list of properties
     * @return same list, but sorted by ascending distance from city centre
     */
    public List<Property> sortPropertiesByAscendingDistance(List<Property> propertyList){
        List<Property> mutablePropertyList = new ArrayList<>(propertyList);
        for (int i = 0; i < mutablePropertyList.size() - 1; i++) {
            for (int j = i + 1; j < mutablePropertyList.size(); j++) {
                if(mutablePropertyList.get(i).getDistance() > mutablePropertyList.get(j).getDistance()){
                    Collections.swap(mutablePropertyList, i, j);
                }
            }
        }
        return Collections.unmodifiableList(mutablePropertyList);
    }
    /**
     *
     * @param propertyList list of properties
     * @return same list, but sorted by descending distance from city centre
     */
    public List<Property> sortPropertiesByDescendingDistance(List<Property> propertyList){
        List<Property> mutablePropertyList = new ArrayList<>(propertyList);
        for (int i = 0; i < mutablePropertyList.size() - 1; i++) {
            for (int j = i + 1; j < mutablePropertyList.size(); j++) {
                if(mutablePropertyList.get(i).getDistance() < mutablePropertyList.get(j).getDistance()){
                    Collections.swap(mutablePropertyList, i, j);
                }
            }
        }
        return Collections.unmodifiableList(mutablePropertyList);
    }

    /**
     *
     * @param propertyList list of properties
     * @return same list, but sorted by ascending number of photographs
     */
    public List<Property> sortPropertiesByAscendingNumOfPhotographs(List<Property> propertyList){
        List<Property> mutablePropertyList = new ArrayList<>(propertyList);
        for (int i = 0; i < mutablePropertyList.size() - 1; i++) {
            for (int j = i + 1; j < mutablePropertyList.size(); j++) {
                if(mutablePropertyList.get(i).getNumOfPhotographs() > mutablePropertyList.get(j).getNumOfPhotographs()){
                    Collections.swap(mutablePropertyList, i, j);
                }
            }
        }
        return Collections.unmodifiableList(mutablePropertyList);
    }
    /**
     *
     * @param propertyList list of properties
     * @return same list, but sorted by descending number of photographs
     */
    public List<Property> sortPropertiesByDescendingNumOfPhotographs(List<Property> propertyList){
        List<Property> mutablePropertyList = new ArrayList<>(propertyList);
        for (int i = 0; i < mutablePropertyList.size() - 1; i++) {
            for (int j = i + 1; j < mutablePropertyList.size(); j++) {
                if(mutablePropertyList.get(i).getNumOfPhotographs() < mutablePropertyList.get(j).getNumOfPhotographs()){
                    Collections.swap(mutablePropertyList, i, j);
                }
            }
        }
        return Collections.unmodifiableList(mutablePropertyList);
    }

    /**
     *
     * @param propertyList list of properties
     * @return same list, but sorted by ascending number of bedrooms
     */
    public List<Property> sortPropertiesByAscendingNumOfBedrooms(List<Property> propertyList){
        List<Property> mutablePropertyList = new ArrayList<>(propertyList);
        for (int i = 0; i < mutablePropertyList.size() - 1; i++) {
            for (int j = i + 1; j < mutablePropertyList.size(); j++) {
                if(mutablePropertyList.get(i).getNumOfBedrooms() > mutablePropertyList.get(j).getNumOfBedrooms()){
                    Collections.swap(mutablePropertyList, i, j);
                }
            }
        }
        return Collections.unmodifiableList(mutablePropertyList);
    }
    /**
     *
     * @param propertyList list of properties
     * @return same list, but sorted by descending number of bedrooms
     */
    public List<Property> sortPropertiesByDescendingNumOfBedrooms(List<Property> propertyList){
        List<Property> mutablePropertyList = new ArrayList<>(propertyList);
        for (int i = 0; i < mutablePropertyList.size() - 1; i++) {
            for (int j = i + 1; j < mutablePropertyList.size(); j++) {
                if(mutablePropertyList.get(i).getNumOfBedrooms() < mutablePropertyList.get(j).getNumOfBedrooms()){
                    Collections.swap(mutablePropertyList, i, j);
                }
            }
        }
        return Collections.unmodifiableList(mutablePropertyList);
    }
    /**
     *
     * @param propertyList list of properties
     * @return same list, but sorted by ascending number of bathrooms
     */
    public List<Property> sortPropertiesByAscendingNumOfBathrooms(List<Property> propertyList){
        List<Property> mutablePropertyList = new ArrayList<>(propertyList);
        for (int i = 0; i < mutablePropertyList.size() - 1; i++) {
            for (int j = i + 1; j < mutablePropertyList.size(); j++) {
                if(mutablePropertyList.get(i).getNumOfBathrooms() > mutablePropertyList.get(j).getNumOfBathrooms()){
                    Collections.swap(mutablePropertyList, i, j);
                }
            }
        }
        return Collections.unmodifiableList(mutablePropertyList);
    }
    /**
     *
     * @param propertyList list of properties
     * @return same list, but sorted by descending number of bathrooms
     */
    public List<Property> sortPropertiesByDescendingNumOfBathrooms(List<Property> propertyList){
        List<Property> mutablePropertyList = new ArrayList<>(propertyList);
        for (int i = 0; i < mutablePropertyList.size() - 1; i++) {
            for (int j = i + 1; j < mutablePropertyList.size(); j++) {
                if(mutablePropertyList.get(i).getNumOfBathrooms() < mutablePropertyList.get(j).getNumOfBathrooms()){
                    Collections.swap(mutablePropertyList, i, j);
                }
            }
        }
        return Collections.unmodifiableList(mutablePropertyList);
    }
    /**
     *
     * @param propertyList list of properties
     * @return same list, but sorted by ascending number of parking spaces
     */
    public List<Property> sortPropertiesByAscendingNumOfParkingSpaces(List<Property> propertyList){
        List<Property> mutablePropertyList = new ArrayList<>(propertyList);
        for (int i = 0; i < mutablePropertyList.size() - 1; i++) {
            for (int j = i + 1; j < mutablePropertyList.size(); j++) {
                if(mutablePropertyList.get(i).getNumOfParkingSpaces() > mutablePropertyList.get(j).getNumOfParkingSpaces()){
                    Collections.swap(mutablePropertyList, i, j);
                }
            }
        }
        return Collections.unmodifiableList(mutablePropertyList);
    }
    /**
     *
     * @param propertyList list of properties
     * @return same list, but sorted by descending number of parking spaces
     */
    public List<Property> sortPropertiesByDescendingNumOfParkingSpaces(List<Property> propertyList){
        List<Property> mutablePropertyList = new ArrayList<>(propertyList);
        for (int i = 0; i < mutablePropertyList.size() - 1; i++) {
            for (int j = i + 1; j < mutablePropertyList.size(); j++) {
                if(mutablePropertyList.get(i).getNumOfParkingSpaces() < mutablePropertyList.get(j).getNumOfParkingSpaces()){
                    Collections.swap(mutablePropertyList, i, j);
                }
            }
        }
        return Collections.unmodifiableList(mutablePropertyList);
    }
    /**
     * This method is used to filter a list of responses, delivering one with only responses that were sent by a certain agent
     * @param responsesList list of responses
     * @param agent the desired agent
     * @return the filtered list
     */
    public List<Response> getResponsesByAgent(List<Response> responsesList, Agent agent){
        List<Response> responsesWithAgent = new ArrayList<>();
        for (Response response : responsesList) {
            if(response.getSender().equals(agent) && !responsesWithAgent.contains(response)){
                responsesWithAgent.add(response);
            }
        }
        return responsesWithAgent;
    }
    /**
     * This method is used to filter a list of responses, delivering one with only responses that were sent to a certain client
     * @param responsesList list of responses
     * @param client the desired client
     * @return the filtered list
     */
    public List<Response> getResponsesByClient(List<Response> responsesList, Client client){
        List<Response> responsesWithClient = new ArrayList<>();
        for (Response response : responsesList) {
            if(response.getRecipient().equals(client) && !responsesWithClient.contains(response)){
                responsesWithClient.add(response);
            }
        }
        return responsesWithClient;
    }
    /**
     * This method is used to filter a list of responses, delivering one with only responses that are still 'Pending'
     * @param responsesList list of responses
     * @return the filtered list
     */
    public List<Response> getResponsesPending(List<Response> responsesList) {
        List<Response> responsesPending = new ArrayList<>();
        for (Response response : responsesList) {
            if(response.getState().equals("Pending") && !responsesPending.contains(response)){
                responsesPending.add(response);
            }
        }
        return responsesPending;
    }
}