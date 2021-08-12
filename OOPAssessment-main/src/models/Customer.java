package models;

import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mark
 */
public class Customer extends User 
{

    /**
     * @return the orders
     */
    public HashMap<Integer, Order> getOrders() {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(HashMap<Integer, Order> orders) {
        this.orders = orders;
    }
    
    //getters and setters

    /**
     * @return the addressLine1
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * @param addressLine1 the addressLine1 to set
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * @return the addressLine2
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * @param addressLine2 the addressLine2 to set
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * @return the town
     */
    public String getTown() {
        return town;
    }

    /**
     * @param town the town to set
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * @return the postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * @param postcode the postcode to set
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * @return the isRegistered
     */
    public boolean isIsRegistered() {
        return isRegistered;
    }

    /**
     * @param isRegistered the isRegistered to set
     */
    public void setIsRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }
    
    //private variables
    private String addressLine1;
    private String addressLine2;
    private String town;
    private String postcode;
    private boolean isRegistered;
    private HashMap<Integer, Order> orders;
    
    //constructors
    Customer()
    {
        super();
        addressLine1 = "";
        addressLine2 = "";
        town = "";
        postcode = "";
        isRegistered = false;
        orders = new HashMap<Integer, Order>();
    }
    
    //create a constructor with 8 parameters *all except isRegistered*
    Customer(String addressLine1In, String addressLine2In,
            String townIn, String postcodeIn, String usernameIn,
            String passwordIn, String firstNameIn, String lastNameIn)

    {
        super(usernameIn, passwordIn, firstNameIn, lastNameIn);
        addressLine1 = addressLine1In;
        addressLine2 = addressLine2In;
        town = townIn;
        postcode = postcodeIn;
        isRegistered = false;
        orders = new HashMap<Integer, Order>();
    }
    
    //display a greeting
    public String displayGreeting()
    {
        String greeting = "<html>Welcome " + getFirstName() + "<br>"
        + "Enjoy Shopping! </html>";
        return greeting;
    }
    
}
