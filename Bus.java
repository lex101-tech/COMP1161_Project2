 
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *Represents a Bus in the transporation system.
 *Each bus has a name,size,price,comfort level,and a list of approved trips.
 * It is associated with a Ministry and can be reserved for trips if suitable.
 * Implements Comparable to allow sorting by bus ID
 */
public class Bus implements Comparable<Bus> {
    private String name;
    private int size;
    private int price;
    private ArrayList<Trip> approvedTrips;
    private int level; // 1,2,3 for low,medium, high repectively;
    private int id;
    private static int nextId=0;
    private Ministry mny;
    protected String tripTypes;

    /**
     * Generated the next unique ID for a bus.
     * @return the next bus ID
     */
    
    private int getNextId(){
       return ++nextId; 
       }
    
    /**Default constructor.Inializes an empty list of approved trips.*/
    public Bus(){
        approvedTrips=new ArrayList<Trip>();
        }

    /**
     * Constructor to inialize a bus with details
     * @param name the name of te bus
     * @param size the seating capacit of he bus
     * @param price the price of reserving the bus
     * @param lev the comfort level(1 = low, 2 = medium, 3 = high)
     * @param mny the Ministry responsible for the 
     */
            
        public Bus( String name, int size, int price, int lev, Ministry mny) {
            approvedTrips=new ArrayList<Trip>();
            this.name = name;
            this.size =size;
            this.price = price;
            this.level = lev;
            this.id = getNextId();
            this.mny = mny;
            tripTypes = "BASICTRANSPORT";
        
        }

        /**
         * Compares two buses by their ID.
         * @param other another bus object
         * @return negative,zero, or positive integer based on comparison
         */
        public int compareTo(Bus other)
        {
            return this.getId() - other.getId();
        }


       /**
        * Checks if the bus is available on a specific date.
        * @param date the date to check
        * @return true  if the bus is free,false if it has a trip
        */
        public boolean available(Date date){
            boolean retval = true;
            for (Trip t:approvedTrips)
               if  (t.getDate().getDay() == date.getDay())
                   retval = false;
            return retval;
        }
        
        /** Getters and Setters */
        public int getPrice(){
            return price;
        }
        public int getId(){
            return id;
        }
        
        public String getName(){
            return name;
        }
        
        public int getSize(){
            return size;
        }
        
        public int getLevel(){
            return level;
        }
        
        public Ministry getMinistry(){
            return mny;
        }
        
        public void setName(String name){
            this.name = name;
        }
                
        public void setPrice(int price){
            this.price = price;
        }
        public void setSize(int size){
            this.size = size;
        }
        
        public void setLevel(int level){
            this.level = level;
        }
        
        /**
         * Updates bus information interactively using Scanner.
         * Prompts user for each field and keeps existing value if enter is hit.
         * @param scan Scanner to read input
         */

        public void updateLocalData(Scanner scan) {
            scan.nextLine();
            String currname = getName();
            int currSize = getSize();
            int currPrice = getPrice();
            int currLevel = getLevel();
            
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
            
            setName(name);
            setSize(size);
            setPrice(price);
            setLevel(level);
        }
        
        /**
         * Checks if the bus is suitable for the type of trip.
         * @param type the trip type
         * @return true if bus can be used for this trip type
         */
        public boolean isSuitable(String type){
            return tripTypes.contains(type);
        }
        
         /**
          * Estimates cost for a trip.
          *  @param type trip type
          * @param numPassengers number of passengers
          * @param comfortLevel comfort level
          * @return estimated price
          */
        public int getEstimate(String type, int numPassengers, int comfortLevel){              
            return price;
        }
        
         /**
          * Checks if bus can hold a number of passengers with a given comfort level.
          * @param numPassengers number of passengers
          * @param comfortLevel comfort level
          * @return true if capacity allows
          */
        public boolean canHold(int numPassengers, int comfortLevel){
            int capacity = (int)(size / mny.getSeparation(comfortLevel));
            return numPassengers <=capacity;
            
        }
        
        
        /**Prints trips assigned to this bus console */
        public void promoteTrips(){
          System.out.println();
          System.out.println("TRIPS ON " +getName());
          System.out.println("===================");
          for (Trip t:approvedTrips)
              System.out.println(t);
                            
        }
        
        /**
         * Attempts to reserve a trip for this bus.
         * @param trip the trip to reserve
         * @param mny the Ministry to check approval.
         * @return result of approval or -1 if failed.
         */
        public int reserve(Trip trip,  Ministry mny){
            int retval = -1;
            ApprovalRequest ar = new ApprovalRequest(trip, this);
            int result = mny.checkApproval(ar);
            if (result >=0){
                int est = getEstimate(trip.getType(), trip.getNumPeople(), trip.getComfortLevel());
                  if (trip.getPlanner().getBudget() >= getEstimate(trip.getType(), trip.getNumPeople(), trip.getComfortLevel())){
                    approvedTrips.add(trip);
                    trip.setBus(this);
                    retval = result;
                }
            }
            return retval;
             
            
            
        }


    /**Prints trips assigned to this bus to a given PrinStream */
    public void promoteTrips(PrintStream outStream)
    {
        outStream.println("TRIPS ON "+getName());
        outStream.println("===================");
        for(Trip t:approvedTrips)
           outStream.println(t);
        outStream.println("--------------------");
           
    }
    
    public ArrayList<Trip> getApprovedTrips()
    {
        
        return approvedTrips;
    }
    

    public String toString()
    {
        return "ID:"+this.getId()+";"+this.name +";#Price:"+this.getPrice()+";Area:"+this.getSize();
        
    }
    
    public String toFile()
    {
        return ""+this.getId()+";"+this.name +";"+this.getPrice()+";"+this.getSize();
        
    }
      
    
    /**Resets the static bus ID counter to 0 */
    public static void resetId()
    {
        
        nextId=0;
    }
    
   

    
    
        
}