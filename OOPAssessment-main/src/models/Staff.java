package models;

/**
 *
 * @author mark
 */
public class Staff extends User {

    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return the salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    
    //private variables
    private String position;
    private double salary;
    
    //constructors
    Staff()
    {
        super();
        position = "";
        salary = 0;
    }
    
    Staff(String usernameIn, String passwordIn, String firstNameIn,
            String lastNameIn)
    {
        super(usernameIn, passwordIn, firstNameIn, lastNameIn);
        position = "";
        salary = 0;
    }
    
    //constructor for *everything*
    Staff(String positionIn, int salaryIn, String usernameIn,
            String passwordIn, String firstNameIn, String lastNameIn)
    {
        super(usernameIn, passwordIn, firstNameIn, lastNameIn);
        position = positionIn;
        salary = salaryIn;
    }

    //display a greeting
    public String displayGreeting()
    {
        String greeting = "<html>Hello " + getFirstName() + "<br>"
        + "You are logged in as a Staff Member </html>";
        
        return greeting;
    }
}
