package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mark
 */
public class DBManager {
    // this is loading the driver needed to use the UCanAccess DB Connection
    private final String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
    // This is the connection string needed to access the database
    private final String connectionString = "jdbc:ucanaccess://Data\\ShopDB.accdb";

    //this hashmap returns products
    public HashMap<Integer, Product> loadProducts()
    {
        HashMap<Integer, Product> products = new HashMap<Integer, Product>();
        int productId;
        String productName;
        double price;
        int stockLevel;
        String measurement;
        int size;

        try{
            //this loads the ucanaccess drivers
            //setting the Statement to stmt
            //setting the ResultSet to rs
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Products");

            //iterates through the database table and assigns 
            //the columns to the variables stated
            while (rs.next()){
                productId = rs.getInt("ProductId");
                productName = rs.getString("ProductName");
                price = rs.getDouble("Price");
                stockLevel = rs.getInt("StockLevel");
                measurement = rs.getString("Measurement");
                size = rs.getInt("Size");

                //if the object has no "measurement" it sets it to "footwear"
                //as it uses "size" instead of "measurement"
                if (measurement == null || measurement.equals(""))
                {
                    Footwear footwear = new Footwear(productId, productName, price, stockLevel, size);
                    products.put(productId, footwear);
                }
                //if there is a "measurement", it gets set to "clothing"
                else
                {
                    Clothing clothing = new Clothing(productId, productName, price, stockLevel, measurement);
                    products.put(productId, clothing);
                }
            }
            conn.close();
        }
        catch(Exception ex){
            System.out.println("Error Loading Products: " + ex.getMessage());
        }
        return products;
    }

    // this is a HashMap that returns the customers
    public HashMap<String, Customer> loadCustomers() {
        HashMap<String, Customer> customers = new HashMap<String, Customer>();

        try {
            //this loads the ucanaccess drivers
            //setting the Statement to stmt
            //setting the ResultSet to rs
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customers");

            // this iterates through the database and sets the columns to variables
            while (rs.next()) {
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String addressLine1 = rs.getString("AddressLine1");
                String addressLine2 = rs.getString("AddressLine2");
                String town = rs.getString("Town");
                String postcode = rs.getString("Postcode");

                // Customer(String addressLine1In, String addressLine2In,
                // String townIn, String postcodeIn, String usernameIn,
                // String passwordIn, String firstNameIn, String lastNameIn)

                Customer customer = new Customer(addressLine1, addressLine2, town, postcode, username, password,
                        firstName, lastName);

                // adds the customers to the "customers" hashmap
                customers.put(username, customer);
            }
            // close the connection
            conn.close();
        }

        catch (Exception ex) {
            System.out.println("Error loading Customers: " + ex.getMessage());
        }
        //stage 15 addition that allows customers that have been loaded
        //from the loadOrders method to be returned and in turn
        //loads the customers with orders that have been made
        customers = loadPreviousOrders(customers); 
        return customers;
    }

    public HashMap<String, Staff> loadStaff() {
        HashMap<String, Staff> staffMap = new HashMap<String, Staff>();

        try {
            //this loads the ucanaccess drivers
            //setting the Statement to stmt
            //setting the ResultSet to rs
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Staff");

            // this iterates through the database and sets the columns to variables
            while (rs.next()) {
                int salary = rs.getInt("Salary");
                String position = rs.getString("Position");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");

                // Staff(String positionIn, int salaryIn, String usernameIn,
                // String passwordIn, String firstNameIn, String lastNameIn)

                Staff staff = new Staff(position, salary, username, password, firstName, lastName);

                // adds the staff to the "staffMap" hashmap
                staffMap.put(username, staff);
            }
            // close the connection
            conn.close();
        }

        catch (Exception ex) {
            System.out.println("Error loading Staff: " + ex.getMessage());
        }
        return staffMap;
    }

    //UNIMPLEMENTED
    public int readOrderId(int productId, Order order)
    {
        int orderId = 0; //set default value for orderId to call later
        try(Connection conn = DriverManager.getConnection(connectionString);){
            //this loads the ucanaccess drivers
            Class.forName(driver);
            //insert into the database using a placeholder statement to improve readability
            String query = "SELECT * FROM Orders WHERE ProductId = ";
            PreparedStatement ps = conn.prepareStatement(query);

            ResultSet rs = ps.executeQuery(query);

            while(rs.next())
            {
                orderId = rs.getInt("OrderId");
            }


            ps.executeUpdate();
        }
        catch(Exception ex){
            System.out.println("Error Writing OrderLines: " + ex.getMessage());
        }

        return orderId; //return the orderId from the given productId and Order
    }

    // checking if the customer has the correct username and password
    public Customer customerLogin(String username, String password) {
        HashMap<String, Customer> customers = loadCustomers();

        if (customers.containsKey(username)) {
            Customer customer = customers.get(username);
            if (customer.getPassword().equals(password)) {
                return customer;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    // checking if the staff has the correct username and password
    public Staff staffLogin(String username, String password) {
        HashMap<String, Staff> staffMap = loadStaff();

        if (staffMap.containsKey(username)) {
            Staff staff = staffMap.get(username);
            if (staff.getPassword().equals(password)) {
                return staff;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /*********************************************************************
     * This will alter the current stock level of the products table in the database
     * Using the productId and quantity as our arguments
     * as that holds all of the information we need
     **********************************************************************/ 
    public void writeStockLevel(int productId, int quantity)
    {
        try(Connection conn = DriverManager.getConnection(connectionString);){
            //this loads the ucanaccess drivers
            Class.forName(driver);
            //insert into the database using a placeholder statement to improve readability
            String query = "UPDATE Products SET StockLevel=(StockLevel - ?) WHERE ProductId=?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, quantity);

            //ensuring that the productId does not change by passing the current productId
            //and setting the update to basically change nothing
            ps.setInt(2, productId);           

            ps.executeUpdate();
        }
        catch(Exception ex){
            System.out.println("Error Updating Stock Level: " + ex.getMessage());
        }
    }

        /*********************************************************************
     * This will fetch all of the orders in the "orderLines" table in the database
     * Using a hashmap of customers an input parameter, this will cross reference
     * orderLines with customers and their orders using the "orderId" key
     **********************************************************************/
    public HashMap<String, Customer> loadAllOrderLines(HashMap<String, Customer> customers)
    {
        try (Connection conn = DriverManager.getConnection(connectionString);) {
            //this loads the ucanaccess drivers
            Class.forName(driver);
            //insert into the database using a placeholder statement to improve readability
            String query = "SELECT * FROM OrderLines";
            Statement ps = conn.createStatement();

            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                int orderLineId = rs.getInt("OrderLineId");
                int productId = rs.getInt("ProductId");
                int quantity = rs.getInt("Quantity");
                double lineTotal = rs.getDouble("LineTotal");
                int orderId = rs.getInt("OrderId");
                
                HashMap<Integer, Product> products = loadProducts();
                Product productBought = products.get(productId);
                
                //putting the orders in to an "order"
                OrderLine ol = new OrderLine(orderLineId, productBought, lineTotal, quantity);

                for(Map.Entry<String, Customer> customerEntry : customers.entrySet())
                {
                    Customer customer = customerEntry.getValue();
                    
                    if(customer.getOrders().containsKey(orderId))
                    {
                        Order order =  customer.getOrders().get(orderId);
                        order.getOrderLines().put(orderLineId, ol);
                    }
                    
                }
            }
            
            
        }
        catch (Exception ex) {
            System.out.println("Error Fetching Orders: " + ex.getMessage());
        }
        return customers;
            
    }

    /*********************************************************************
     * This will fetch all of the orders in the "orders" table in the database
     * Using a hashmap of customers an input parameter, this will cross reference
     * orders with customers using the "username" key
     **********************************************************************/
    public HashMap<String, Customer> loadPreviousOrders(HashMap<String, Customer> customers)
    {
        try(Connection conn = DriverManager.getConnection(connectionString);){
            //this loads the ucanaccess drivers
            Class.forName(driver);
            //insert into the database using a placeholder statement to improve readability
            String query = "SELECT * FROM Orders";
            Statement ps = conn.createStatement();

            ResultSet rs = ps.executeQuery(query);

            while(rs.next())
            {
                int orderId = rs.getInt("OrderId");
                java.util.Date orderDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("OrderDate"));
                String username = rs.getString("Username");
                double orderTotal = rs.getDouble("OrderTotal");
                String status = rs.getString("Status");

                //putting the orders in to an "order"
                Order order = new Order(orderId, orderDate, orderTotal, status);

                //if the hashmap of customers contains the username(key) that
                //is present in the db order, get that username in a Customer object
                if(customers.containsKey(username))
                {
                    Customer cust = customers.get(username);
                    cust.getOrders().put(orderId, order);
                }
            }
        }
        catch(Exception ex){
            System.out.println("Error Fetching Orders: " + ex.getMessage());
        }
        customers = loadAllOrderLines(customers);
        return customers;
    }

    /*********************************************************************
     * This will add the current orderline to the orderline table in the database
     * Using the OrderLine class as that holds all of the information we need
     **********************************************************************/  
    public void writeOrderLine(OrderLine ol, int orderId)
    {
        try(Connection conn = DriverManager.getConnection(connectionString);){
            //this loads the ucanaccess drivers
            Class.forName(driver);
            //insert into the database using a placeholder statement to improve readability
            String query = "INSERT INTO OrderLines (OrderLineId, ProductId, Quantity, LineTotal, OrderId) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, ol.getOrderLineId());
            ps.setInt(2, ol.getProduct().getProductId());
            ps.setInt(3, ol.getQuantity());
            ps.setDouble(4, ol.getLineTotal());
            ps.setInt(5, orderId) ;

            ps.executeUpdate();
        }
        catch(Exception ex){
            System.out.println("Error Writing OrderLines: " + ex.getMessage());
        }
    }

    /*********************************************************************
     * This will add the current orderline to the orders table in the database
     * Using the Order class as that holds all of the information we need
     **********************************************************************/
    public int writeOrder(Order order, String customer)
    {
        var lastInsertedOrderID = 0;

        try(Connection conn = DriverManager.getConnection(connectionString);){
            //this loads the ucanaccess drivers
            Class.forName(driver);
            //insert into the database using a placeholder statement to improve readability
            String query = "INSERT INTO Orders (OrderId, OrderDate, Username, OrderTotal, Status) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, order.getOrderId());
            ps.setObject(2, order.getOrderDate());
            ps.setString(3, customer);
            ps.setDouble(4, order.getOrderTotal());
            ps.setString(5, order.getStatus());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys(); //return the primary key (which is our orderId!!)
            rs.next();
            lastInsertedOrderID = rs.getInt(1); //ensure the first primary key is returned
        }
        catch(Exception ex){
            System.out.println("Error Writing Orders: " + ex.getMessage());
        }
        return lastInsertedOrderID;
    }

    public Customer customerRegister(String addressLine1, String addressLine2,
    String town, String postcode, String username,
    String password, String firstName, String lastName)
    {
        try(Connection conn = DriverManager.getConnection(connectionString);){
            //HashMap to save existing customers
            //this loads the ucanaccess drivers
            Class.forName(driver);
            //insert into the database using a placeholder statement to improve readability
            String query = "INSERT INTO Customers (Username, Password, FirstName, LastName, AddressLine1, AddressLine2, Town, Postcode) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.setString(5, addressLine1);
            ps.setString(6, addressLine2);
            ps.setString(7, town);
            ps.setString(8, postcode);
            
            ps.executeUpdate();

            conn.close();
        }
        catch(Exception ex){
            System.out.println("Error Loading Customers: " + ex.getMessage());
        }
        return null;
    }
    
    public Product editProduct(Product product)
    {
        String measurement = "";
        Integer size = null;

        //if the selected product is Footwear, set the size to getSize()
        //otherwise it will be null by default
        if(product.getClass().getName().equals("models.Footwear"))
        {
            Footwear footwear = (Footwear)product;
            size = Integer.valueOf(footwear.getSize());
        }
        //size stays null and measurement is populated instead
        else
        {
            Clothing clothing = (Clothing)product;
            measurement = String.valueOf(clothing.getMeasurement());
        }

        try(Connection conn = DriverManager.getConnection(connectionString);){
            //this loads the ucanaccess drivers
            Class.forName(driver);
            String query = 
            //updating the Products table with relation to it's product id
            "UPDATE Products SET ProductName=?, Price=?, stockLevel=?, Measurement=?, Size=? WHERE ProductId=?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, product.getProductName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getStockLevel());
            ps.setString(4, measurement);
            //this is done to convert the "Integer" wrapper to a value type int
            ps.setObject(5, size, Types.INTEGER); 
            ps.setInt(6, product.getProductId());
            
            ps.executeUpdate();
        }
        catch(Exception ex)
        {
            System.out.println("Error Updating Products: " + ex.getMessage());
        }
        return null;
    }

    public Product deleteProduct(int productId)
    {
        String query = 
        "DELETE FROM Products WHERE ProductId=?";
        try(Connection conn = DriverManager.getConnection(connectionString);)
        {
            PreparedStatement ps = conn.prepareStatement(query);
            //set the required information to delete the row to "productId"
            //which will be passed in from the method
            ps.setInt(1, productId);

            ps.executeUpdate();
            System.out.println("Product Deleted");
        }
        catch(Exception ex)
        {
            System.out.println("Error Deleteing Products: " + ex.getMessage());
        }
        return null;
    }



}
