package models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mark
 */
public class Footwear extends Product
{
    
    //getters and setters
    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    //private variables
    private int size;
    
    //constructors
    Footwear()
    {
        super();
        size = 0;
    }
    
    //constructor for *everything except productId*
    public Footwear(String productNameIn, double priceIn, int stockLevelIn, 
            int sizeIn)
    {
        super(productNameIn, priceIn, stockLevelIn);
        size = sizeIn;
    }
    
    //constructor for *everything*
    public Footwear(int productIdIn, String productNameIn, double priceIn, int stockLevelIn,
            int sizeIn) 
    {
        super(productIdIn, productNameIn, priceIn, stockLevelIn);
        size = sizeIn;
    }
    
}
