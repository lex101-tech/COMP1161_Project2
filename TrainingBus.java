import java.util.Scanner;

/**
 * TrainingBus - A specialized Bus for training purposes
 * Each bus has a name,size,price,comfort level,and a list of approved trips.
 * It is associated with a Ministry and can be reserved for trips if suitable.
 * Subclass of Bus superclass
 */
public class TrainingBus extends Bus {
    private int teacherArea;
    
    /**
     * Constructor for TrainingBus
     * @param name The name of the bus
     * @param size The size of the bus
     * @param price The price of the bus
     * @param lev The level of the bus
     * @param mny The Ministry managing this bus
     * @param teacherArea The area reserved for teachers
     */
    public TrainingBus(String name, int size, int price, int lev, Ministry mny, int teacherArea) {
        super(name, size, price, lev, mny);
        this.teacherArea = teacherArea;
        this.tripTypes = "BASICTRANSPORT";
    }
    
    /**
     * Gets the teacher area allocated in the bus.
     * 
     * @return the teacher area
     */
    public int getTeacherArea() {
        return teacherArea;
    }
    
    /**
     * Sets the teacher area for the bus.
     * 
     * @param teacherArea the new teacher area
     */
    public void setTeacherArea(int teacherArea) {
        this.teacherArea = teacherArea;
    }
    
    /**
     * Updates the bus details interactively using user input.
     * Allows modification of name, size, price, level, and teacher area.
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
        int currTeacherArea = getTeacherArea();
        
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
        
        System.out.println("Hit enter to keep teacher area at [" + currTeacherArea + "] or enter new teacher area:");
        String teacherAreaEntry = scan.nextLine();
        int teacherArea;
        if (teacherAreaEntry.equals(""))
            teacherArea = currTeacherArea;
        else
            teacherArea = Integer.parseInt(teacherAreaEntry);
        
        setName(name);
        setSize(size);
        setPrice(price);
        setLevel(level);
        setTeacherArea(teacherArea);
    }
    
    /**
     * Returns a string representation of the SportsBus.
     * 
     * @return formatted string containing bus details
     */
    @Override
    public String toString() {
        return "ID:" + this.getId() + ";" + this.getName() + ";#Price:" + this.getPrice() 
               + ";Area:" + this.getSize() + ";TeacherArea:" + teacherArea;
    }
    
    /**
     * Converts the TrainingBus data into a file-friendly format.
     * 
     * @return formatted string for file storage
     */
    @Override
    public String toFile() {
        return "" + this.getId() + ";" + this.getName() + ";" + this.getPrice() + ";" + teacherArea;
    }
}

