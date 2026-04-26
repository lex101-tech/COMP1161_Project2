import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkArea {

    public WorkArea() {}

    ArrayList<Planner> planners = new ArrayList<Planner>();
    ArrayList<Bus> buses = new ArrayList<Bus>();
    Ministry mny = new Ministry("TRANSPORT" );
    ReportScreen r= new ReportScreen();


    public void clearData()
    {
        planners.clear();
        buses.clear();
        Planner.resetId();
        Bus.resetId();
    }


    public ArrayList<Planner> loadPlanners(String pfile, Ministry mny, ArrayList<Bus> bus )
    {
        Scanner pscan = null;
        ArrayList<Planner> plist = new ArrayList<Planner>();
        try
        {
            pscan  = new Scanner(new File(pfile));
            while(pscan.hasNext())
            {
                String [] nextLine = pscan.nextLine().split(" ");
                String name = nextLine[0];
                int budget = Integer.parseInt(nextLine[1]);
                Planner p = new Planner(name, budget,mny, buses);
                plist.add(p);
            }

            pscan.close();
        }
        catch(IOException e)
        {}
        catch(NumberFormatException nfe)
        {}

        return plist;

    }

    public ArrayList<Planner> loadTripsToPlanners(ArrayList<Planner> plist, String tfile)
    {
        Scanner tscan = null;
        try
        {
            tscan  = new Scanner(new File(tfile));
            while(tscan.hasNext())
            {
                String [] nextLine = tscan.nextLine().split(" ");
                String name = nextLine[0];
                int numPatrons = Integer.parseInt(nextLine[1]);
                String ttype = nextLine[2];
                Date day = new Date(Integer.parseInt(nextLine[3]));
                int comfort = Integer.parseInt(nextLine[4]);
                
                int pdx= findPlannerByName(plist, name);
                if (pdx>=0)
                    plist.get(pdx).addPlan(new Plan(numPatrons, ttype,day, comfort));
            }

            tscan.close();
        }
        catch(IOException e)
        {}
        catch(NumberFormatException nfe)
        {}

        return plist;

    }



    public ArrayList<Bus> loadBuses(String bfile) {
        ArrayList<Bus> blist = new ArrayList<>();
        
        try (Scanner bscan = new Scanner(new File(bfile))) {
            while (bscan.hasNextLine()) {
                String line = bscan.nextLine().trim();
                
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                
                String[] fields = line.split("\\s+");
                
                if (fields.length < 4) {
                    continue;
                }
                
                try {
                    String name = fields[0];
                    int size = Integer.parseInt(fields[1]);
                    int price = Integer.parseInt(fields[2]);
                    int lev = Integer.parseInt(fields[3]);
                    
                    Bus b = null;
                    
                    switch (fields.length) {
                        case 4:
                            b = new Bus(name, size, price, lev, mny);
                            break;
                        case 5:
                            int teacherArea = Integer.parseInt(fields[4]);
                            b = new TrainingBus(name, size, price, lev, mny, teacherArea);
                            break;
                        case 6:
                            int competitorArea = Integer.parseInt(fields[4]);
                            int security = Integer.parseInt(fields[5]);
                            b = new SportsBus(name, size, price, lev, mny, competitorArea, security);
                            break;
                        case 7:
                            int compArea = Integer.parseInt(fields[4]);
                            int sec = Integer.parseInt(fields[5]);
                            int barArea = Integer.parseInt(fields[6]);
                            b = new PartyBus(name, size, price, lev, mny, compArea, sec, barArea);
                            break;
                        default:
                            b = new Bus(name, size, price, lev, mny);
                    }
                    
                    if (b != null) {
                        blist.add(b);
                    }
                } catch (NumberFormatException e) {
                }
            }
        } catch (IOException e) {
        }
        
        return blist;
    }

    public void loadTestCase(int caseNo, Scanner scan )
    {
        loadData(caseNo);
        System.out.print("Test case " + caseNo+ " loaded: Show data?[y/n]");
        String response  = scan.next();
        if (response.toUpperCase().charAt(0)=='Y')
        {
            ReportScreen r= new ReportScreen();
            r.listBuses(buses, System.out);
            r.listPlanners(planners, System.out);
            
        }
  
    }
    
    
    
    public void loadData(int caseNo)
    {
        clearData();
        buses = loadBuses(getBusFile(caseNo));
        planners  =  loadPlanners(getPlFile(caseNo),  mny,  buses );
        planners  =  loadTripsToPlanners(planners, getTripFile(caseNo) );

        }
    


    private String getPlFile(int caseNo)
    {
        return "cases/Planner."+caseNo+".txt";
    }


    private String getBusFile(int caseNo)
    {
        return "cases/Bus."+caseNo+".txt";
    }

    private String getTripFile(int caseNo)
    {
        return "cases/Trip."+caseNo+".txt";
    }
    
    public int findPlannerByName(ArrayList<Planner> plans, String name)
    {
        int pdx =-1;
        int currdx=0;
        while ((currdx<plans.size())&&(pdx==-1))
        {
            if (plans.get(currdx).getName().equals(name))
                pdx = currdx;
            currdx++;
        

        }

        return pdx;

    }
}