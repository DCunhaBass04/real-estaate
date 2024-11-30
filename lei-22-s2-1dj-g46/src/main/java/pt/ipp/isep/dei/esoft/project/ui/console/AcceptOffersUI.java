package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AcceptOffersController;
import pt.ipp.isep.dei.esoft.project.domain.Order;
import pt.ipp.isep.dei.esoft.project.domain.Property.Property;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.VerifyInput;

import java.util.List;
import java.util.Scanner;

/**
 * The AcceptOffersUI class represents the user interface for accepting offers.
 * It interacts with the AcceptOffersController to manage and process offers.
 */
public class AcceptOffersUI implements Runnable{
    private AcceptOffersController ctrl;
    /**
     * Constructs a new AcceptOffersUI object.
     */
    public AcceptOffersUI(){this.ctrl = new AcceptOffersController();}
    /**
     * Runs the accept offers user interface.
     * Retrieves the current user's email and lists properties associated with the user.
     * Manages offers for each property, allowing the user to accept or decline offers.
     */
    @Override
    public void run() {
        String email = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().getEmail();
        List<Property> listOfProperties = ctrl.getPropertiesByCurrentAgent(email);
        for(Property property : listOfProperties){
            manageOrdersByProperty(property);
        }
    }
    /**
     * Manages the offers for a specific property.
     * Displays the list of orders for the property and allows the user to accept or decline offers.
     *
     * @param property The property for which to manage offers.
     */
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
    /**
     * Prints the list of orders for a specific property.
     *
     * @param orderList The list of orders to print.
     */
    private void printAllOrdersForXProperty(List<Order> orderList){
        for(int i = 0; i < orderList.size(); i++){
            System.out.println((i+1) + " - " + orderList.get(i));
        }
    }
}
