import java.util.Scanner;

/**
 * Represents a PartyBus, a specialized type of SportsBus used for
 * entertainment and party transport.
 * 
 * A PartyBus includes additional features such as a bar area, along with
 * competitor/party space and security. It extends SportsBus and allows
 * updating of all inherited and additional attributes.
 */
public class PartyBus extends SportsBus {
    private int barArea;
    
    /**
     * Constructor for PartyBus
     * @param name The name of the bus
     * @param size The size of the bus
     * @param price The price of the bus
     * @param lev The level of the bus
     * @param mny The Ministry managing this bus
     * @param competitorArea The area reserved for competitors/party area
     * @param security The security level
     * @param barArea The area reserved for the bar
     */
    public PartyBus(String name, int size, int price, int lev, Ministry mny, int competitorArea, int security, int barArea) {
        super(name, size, price, lev, mny, competitorArea, security);
        this.barArea = barArea;
        this.tripTypes = "BASICTRANSPORT";
    }
    
    /**
     * Gets the bar area of the bus.
     * 
     * @return the bar area
     */
    public int getBarArea() {
        return barArea;
    }
    
    /**
     * Sets the bar area of the bus.
     * 
     * @param barArea the new bar area
     */
    public void setBarArea(int barArea) {
        this.barArea = barArea;
    }
    
    /**
     * Updates the bus details interactively using user input.
     * Allows modification of name, size, price, level,
     * competitor area, security level, and bar area.
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
        int currBarArea = getBarArea();
        
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
        
        System.out.println("Hit enter to keep bar area at [" + currBarArea + "] or enter new bar area:");
        String barAreaEntry = scan.nextLine();
        int barArea;
        if (barAreaEntry.equals(""))
            barArea = currBarArea;
        else
            barArea = Integer.parseInt(barAreaEntry);
        
        setName(name);
        setSize(size);
        setPrice(price);
        setLevel(level);
        setCompetitorArea(competitorArea);
        setSecurity(security);
        setBarArea(barArea);
    }
    
    /**
     * Returns a string representation of the PartyBus.
     * 
     * @return formatted string containing bus details
     */
    @Override
    public String toString() {
        return "ID:" + this.getId() + ";" + this.getName() + ";#Price:" + this.getPrice() 
               + ";Area:" + this.getSize() + ";CompetitorArea:" + getCompetitorArea() 
               + ";Security:" + getSecurity() + ";BarArea:" + barArea;
    }
    
    /**
     *  Converts the TrainingBus data into a file-friendly format.
     * 
     * @return formatted string for file storage
     */
    @Override
    public String toFile() {
        return "" + this.getId() + ";" + this.getName() + ";" + this.getPrice() + ";" + barArea + ";" + getSecurity();
    }
}