package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.Property.Property;

import java.io.Serializable;


/**
 * The type Order.
 */
public class Order implements Comparable<Order>, Serializable {
    private final String clientEmail;
    private final Property property;
    private final float amount;
    private String state;
    //'Pending', 'Accepted' or 'Declined'

    /**
     * Instantiates a new Order.
     *
     * @param clientEmail the client email
     * @param property    the property
     * @param amount      the amount
     * @param state       the state
     */
    public Order(String clientEmail, Property property, float amount, String state) {
        this.clientEmail = clientEmail;
        this.property = property;
        this.amount = amount;
        this.state = state;
    }
    public boolean equals(Object object){
        if (this == object) {
            return true;
        }
        if (!(object instanceof Order)) {
            return false;
        }
        Order order = (Order) object;
        return clientEmail.equals(order.clientEmail) && property.equals(order.property) && amount == order.amount && state.equals(order.state);
    }

    /**
     * Gets client email.
     *
     * @return the client email
     */
    public String getClientEmail() {
        return clientEmail;
    }

    /**
     * Gets property.
     *
     * @return the property
     */
    public Property getProperty() {
        return property;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Get state string.
     *
     * @return the string
     */
    public String getState(){
        return state;
    }

    /**
     * Set state.
     *
     * @param state the state
     */
    public void setState(String state){this.state = state;}

    public String toString(){
        return String.format("Order by %s%nOffer amount: %.2f%n", clientEmail, amount);
    }
    public Order clone() {
        return new Order(this.clientEmail, this.property, this.amount, this.state);
    }
    @Override
    public int compareTo(Order o) {
        return (int)(this.amount - o.amount);
    }
}
