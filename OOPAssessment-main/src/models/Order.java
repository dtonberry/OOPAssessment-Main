/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mark
 */
public class Order {
    // private variables
    private int orderId;
    private Date orderDate;
    private double orderTotal;
    private String status;
    private HashMap<Integer, OrderLine> orderLines;


     /*********************************************************************
     * this will calculate the total price of the orderlines by adding up
     * all of the lineTotals
     **********************************************************************/
    public void calculateOrderTotal()
    {
        orderTotal = 0; //set the initial order total to 0, as to initialize it
        for (Map.Entry<Integer, OrderLine> olEntry : orderLines.entrySet())
        {
            OrderLine actualOrderLine = olEntry.getValue();

            orderTotal += actualOrderLine.getLineTotal();
        }
    }

    /*********************************************************************
     * this will add an orderline to the orderline hashmap based on the orderline id
     * and the OrderLine "ol"
     **********************************************************************/
    public boolean addOrderLine(OrderLine ol) {
        boolean canBeAdded = true; // boolean to show if a product can be added or not
        for (Map.Entry<Integer, OrderLine> olEntry : orderLines.entrySet()) {
            OrderLine actuaOrderLine = olEntry.getValue();

            // basically if the product id already exists in the orderline, you can't add
            // another
            // of the same product id
            if (ol.getProduct().getProductId() == actuaOrderLine.getProduct().getProductId()) {
                canBeAdded = false;
            }
        }

        if (canBeAdded == false) {
            return false; // return false meaning the orderline can not be added
        }

        else {
            int orderLineId = 0;
            // while loop that creates a unique OrderLineId by increasing from 0 until one
            // doesn't match
            while (orderLines.containsKey(orderLineId)) {
                orderLineId++;
            }

            // set the orderline to the 'orderLineId' number
            ol.setOrderLineId(orderLineId);

            // add that number to the orderLines hashmap (Key, Value) => (orderLineId, ol)
            orderLines.put(orderLineId, ol);

            return true;
        }
    }

    /*********************************************************************
     * this will remove an orderline to the orderline hashmap based on the product id
     * and the OrderLineId
     **********************************************************************/
    public void removeOrderLine(int productId) {
        int orderLineId = -1; // default orderlineid to search for in the hashmap

        for (Map.Entry<Integer, OrderLine> olEntry : orderLines.entrySet()) {
            OrderLine removalOrderLine = olEntry.getValue();

            if(removalOrderLine.getProduct().getProductId() == productId)
            {
                orderLineId = removalOrderLine.getOrderLineId();
            }
        }

        //remove the orderline using the orderlineid key when it does not return -1 (which it never will)
        if (orderLineId != -1)
        {
            orderLines.remove(orderLineId);
        }

    }

    public void removeAllOrderLine()
    {
        orderLines.clear(); //remove all entries from the hashmap
    }

    // getters and setters
    /**
     * @return the orderId
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the orderTotal
     */
    public double getOrderTotal() {
        return orderTotal;
    }

    /**
     * @param orderTotal the orderTotal to set
     */
    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public HashMap<Integer, OrderLine> getOrderLines() {
        return orderLines;
    }

    /**
     * @param orderLines the orderLines to set
     */
    public void setOrderLines(HashMap<Integer, OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    // constructors
    public Order() {
        orderId = 0;
        orderDate = new Date();
        orderTotal = 0;
        status = "IN PROGRESS";
        orderLines = new HashMap<Integer, OrderLine>();
    }

    // constructor with *EVERYTHING EXCEPT orderLines*
    public Order(int orderIdIn, Date orderDateIn, double orderTotalIn, String statusIn) {
        orderId = orderIdIn;
        orderDate = orderDateIn;
        orderTotal = orderTotalIn;
        status = statusIn;
        orderLines = new HashMap<Integer, OrderLine>();
    }
    
    public String DisplayConfirmation()
    {
        //int orderId = db.writeOrderLine(ol, orderId);
        String confirmation = "<html>Thank you for your order \nYour Order ID is: " + getOrderId() + "<br>"
        + "Please shop with us again! </html>";
        
        return confirmation;   
    }
}
