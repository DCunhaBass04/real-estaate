package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Order;
import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.repository.OrderRepository;
import pt.ipp.isep.dei.esoft.project.repository.PropertyAnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.PropertySoldRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.EditAndSortLists;

import java.util.List;

/**
 * The AcceptOffersController class represents a controller for accepting and declining offers for properties.
 */
public class AcceptOffersController {
    public AcceptOffersController() {
    }

    private EditAndSortLists editAndSortLists = new EditAndSortLists();

    /**
     * Retrieves the list of properties associated with the current agent's email.
     *
     * @param email the email of the current agent
     * @return the filtered list of properties
     */
    public List<Property> getPropertiesByCurrentAgent(String email) {
        List<Property> announcementList = Repositories.getInstance().getPropertyRepository().getProperties();
        announcementList = editAndSortLists.getPropertiesByXAgent(announcementList, email);
        return announcementList;
    }

    /**
     * Retrieves the list of orders received for a specific property, sorted by descending order of amount.
     *
     * @param property the property for which to retrieve the orders
     * @return the sorted list of orders
     */
    public List<Order> getOrdersToXProperty(Property property) {
        List<Order> orderList = Repositories.getInstance().getOrderRepository().getOrders();
        orderList = editAndSortLists.getOrdersDoneToXProperty(orderList, property);
        orderList = editAndSortLists.sortOrdersByDescendingAmount(orderList);
        return orderList;
    }

    /**
     * Accepts an offer for a property and updates the necessary repositories.
     *
     * @param property     the property for which the offer is accepted
     * @param listOfOrders the list of orders
     * @param index        the index of the selected offer in the list
     */
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

    /**
     * Declines an offer for a property and updates the necessary repositories.
     *
     * @param index the index of the offer in the list
     */
    public void declineOffer(int index) {
        OrderRepository orderRepository = Repositories.getInstance().getOrderRepository();
        orderRepository.getOrders().get(index).setState("Declined");
    }
}
