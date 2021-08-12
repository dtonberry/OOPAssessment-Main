package models;

/**
 *
 * @author mark
 */
public class Clothing extends Product
{
    
    //getters and setters
    /**
     * @return the measurement
     */
    public String getMeasurement() {
        return measurement;
    }

    /**
     * @param measurement the measurement to set
     */
    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
    
    //private variable for measurement
    private String measurement;
    
    //constructors
    Clothing() 
    {
        super();
        measurement = "";
    }
    
    //constructor for *everything except productId*
    public Clothing(String productNameIn, double priceIn, int stockLevelIn, 
            String measurementIn)
    {
        super(productNameIn, priceIn, stockLevelIn);
        measurement = measurementIn;
    }
    
    //constructor for *everything*
    public Clothing(int productIdIn, String productNameIn, double priceIn, int stockLevelIn,
            String measurementIn) 
    {
        super(productIdIn, productNameIn, priceIn, stockLevelIn);
        measurement = measurementIn;
    }
    
    
}
