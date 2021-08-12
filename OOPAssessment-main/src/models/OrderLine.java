/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author mark
 */
public class OrderLine 
{
    //private attributes
    private int orderLineId;
    private Product product;
    private int quantity;
    private double lineTotal;

    
    //getters and setters
    /**
     * @return the orderLineId
     */
    public int getOrderLineId() {
        return orderLineId;
    }

    /**
     * @param orderLineId the orderLineId to set
     */
    public void setOrderLineId(int orderLineId) {
        this.orderLineId = orderLineId;
    }

    /**
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the lineTotal
     */
    public double getLineTotal() {
        return lineTotal;
    }

    /**
     * @param lineTotal the lineTotal to set
     */
    public void setLineTotal(double lineTotal) {
        this.lineTotal = lineTotal;
    }
    
    //constructors
    public OrderLine(int orderLineIdIn, Product productIn, double LineTotalIn, int quantityIn)
    {
        orderLineId = orderLineIdIn;
        quantity = quantityIn;
        lineTotal = LineTotalIn;
        product = productIn;
    }

    //constructor for *EVERYTHING EXCEPT lineTotal*
    public OrderLine(int orderLineIdIn, Product productIn, int quantityIn)
    {
        orderLineId = orderLineIdIn;
        product = productIn;
        quantity = quantityIn;
        lineTotal = product.getPrice();
        lineTotal = lineTotal * quantity; //this gets the price total by multiplying the price per product by the quantity 
    }
}
