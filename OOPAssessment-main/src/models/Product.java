package models;

/**
 *
 * @author mark clyne
 */
public class Product {
    private int productId;
    private String productName;
    private double price;
    private int stockLevel;

    //string override
    @Override //overrides the default toString() method
    public String toString()
    {
        String display = productName + " | Price: £" + price; //displays product | £price
        return display;
    }

    
    //getters
    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    } 
     
    /**
     * @return the stockLevel
     */
    public int getStockLevel() {
        return stockLevel;
    }
    
    //setters
    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    /**
     * @param price the price to set
     */
    public void setPrice(double priceIn) {
        this.price = priceIn;
    }
    
    /**
     * @param stockLevel the stockLevel to set
     */
    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }
 
    //initiator constructor with no parameters
    public Product()
    {
        productId = 0;
        productName = "";
        price = 0;
        stockLevel = 0;
    }
    
    //constructor with all parameters
    public Product(int productIdIn, String productNameIn, double priceIn, 
            int stockLevelIn)
    {
        productId = productIdIn;
        productName = productNameIn;
        price = priceIn;
        stockLevel = stockLevelIn;
    }
    
    //constructor with all parameters EXCEPT ProductId as this will be manually inserted
    public Product(String productNameIn, double priceIn,
            int stockLevelIn) 
    {
        productId = 0;
        productName = productNameIn;
        price = priceIn;
        stockLevel = stockLevelIn;
    }
    
    
    
}
