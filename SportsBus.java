import java.util.Scanner;

/**
 * Represents a SportsBus, a specialized type of Bus used for transporting
 * competitors and staff for sporting events.
 * 
 * A SportsBus includes additional space for competitors and a security level.
 * It extends the Bus superclass and overrides methods for updating its data.
 */
public class SportsBus extends Bus {
    private int competitorArea;
    private int security;
    
    /**
     * Constructor for SportsBus
     * @param name The name of the bus
     * @param size The size of the bus
     * @param price The price of the bus
     * @param lev The level of the bus
     * @param mny The Ministry managing this bus
     * @param competitorArea The area reserved for competitors
     * @param security The security level
     */
    public SportsBus(String name, int size, int price, int lev, Ministry mny, int competitorArea, int security) {
        super(name, size, price, lev, mny);
        this.competitorArea = competitorArea;
        this.security = security;
        this.tripTypes = "BASICTRANSPORT";
    }
    
    /**
     * Gets the competitor area of the bus.
     * 
     * @return the competitor area
     */
    public int getCompetitorArea() {
        return competitorArea;
    }
    
    /** Gets the competitor area of the bus.*/
    public void setCompetitorArea(int competitorArea) {
        this.competitorArea = competitorArea;
    }
    
    /**
     * Gets the security level of the bus.
     * 
     * @return the security level
     */
    public int getSecurity() {
        return security;
    }
    
    /**
     * Sets the security level of the bus.
     * 
     * @param security the new security level
     */
    public void setSecurity(int security) {
        this.security = security;
    }
    

    /**
     * Updates the bus details interactively using user input.
     * Allows modification of name, size, price, level,
     * competitor area, and security level.
     * Pressing enter keeps the current value.
     * 
     * @param scan Scanner used to read user input
     */
    @Override
    public void updateLocalData(Scanner scan) {
        scan.nextLine();
        String currname = getName();
        int currSize = getSize();
        int currPrice = getPrice();
        int currLevel = getLevel();
        int currCompetitorArea = getCompetitorArea();
        int currSecurity = getSecurity();
        
        System.out.println("Hit enter to keep name as [" + currname + "], or enter new name:");
        String name = scan.nextLine();
        if (name.equals(""))
            name = currname;
        
        System.out.println("Hit enter to keep size at [" + currSize + "] or enter new size:");
        String sizeEntry = scan.nextLine();
        int size;
        if (sizeEntry.equals(""))
            size = currSize;
        else
            size = Integer.parseInt(sizeEntry);
        
        System.out.println("Hit enter to keep price at [" + currPrice + "] or enter new price:");
        String priceEntry = scan.nextLine();
        int price;
        if (priceEntry.equals(""))
            price = currPrice;
        else
            price = Integer.parseInt(priceEntry);
        
        System.out.println("Hit enter to keep level at [" + currLevel + "] or enter new level:");
        String levelEntry = scan.nextLine();
        int level;
        if (levelEntry.equals(""))
            level = currLevel;
        else
            level = Integer.parseInt(levelEntry);
        
        System.out.println("Hit enter to keep competitor area at [" + currCompetitorArea + "] or enter new competitor area:");
        String competitorAreaEntry = scan.nextLine();
        int competitorArea;
        if (competitorAreaEntry.equals(""))
            competitorArea = currCompetitorArea;
        else
            competitorArea = Integer.parseInt(competitorAreaEntry);
        
        System.out.println("Hit enter to keep security at [" + currSecurity + "] or enter new security:");
        String securityEntry = scan.nextLine();
        int security;
        if (securityEntry.equals(""))
            security = currSecurity;
        else
            security = Integer.parseInt(securityEntry);
        
        setName(name);
        setSize(size);
        setPrice(price);
        setLevel(level);
        setCompetitorArea(competitorArea);
        setSecurity(security);
    }
    
    /**
     * Returns a string representation of the SportsBus.
     * 
     * @return formatted string containing bus details
     */
    @Override
    public String toString() {
        return "ID:" + this.getId() + ";" + this.getName() + ";#Price:" + this.getPrice() 
               + ";Area:" + this.getSize() + ";CompetitorArea:" + competitorArea + ";Security:" + security;
    }
    
    /**
     * Converts the TrainingBus data into a file-friendly format.
     * 
     * @return formatted string for file storage
     */
    @Override
    public String toFile() {
        return "" + this.getId() + ";" + this.getName() + ";" + this.getPrice() + ";" + competitorArea + ";" + security;
    }
}