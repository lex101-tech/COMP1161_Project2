import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class EntryScreen {

    public EntryScreen() {}

    public ArrayList<Planner> managePlanners(Scanner scan,ArrayList<Planner> plans, Ministry mny, ArrayList<Bus> buses) throws NumberFormatException
    {
        ReportScreen r = new ReportScreen();
        char mchoice = 'c';
        String menu="";
        while (mchoice!='X')
        {
            String menuOptions = "[A]dd/Create planner\n[E]dit/Update planner\n";
            menuOptions+="[L]ist/Read planner\n[D]elete planner\nE[x]it\n";
            System.out.println(menuOptions);
            menu = scan.next().toUpperCase();
            mchoice = menu.charAt(0);
            switch(mchoice)
            {
            case 'A':{
                Planner p = createPlanner(scan, mny, buses);
                if (p!=null)
                    plans.add(p);
                break;
            }
            case 'L':{
                Collections.sort(plans); 
                r.listPlanners(plans, System.out);
                break;
            }
            case 'E':{
                System.out.println("Please enter the ID of the planner to be updated:");
                int pid = Integer.parseInt(scan.next());
                int pdx = findPlanner(plans,pid);
                if (pdx>=0)
                    plans.get(pdx).updateLocalData(scan);
                else
                    System.out.println("Planner with id "+pid+ " not found.");
                break;
            }
            case 'D':{
                System.out.println("Please enter the ID of the planner to be deleted:");
                int pid = Integer.parseInt(scan.next());
                int pdx = findPlanner(plans,pid);

                if (pdx>=0)
                    plans.remove(pdx);
                else
                    System.out.println("Planner with id "+pid+ " not found.");
                break;
            }


            }

        }
        return plans;
    }



    public Planner createPlanner( Scanner scan, Ministry mny, ArrayList<Bus> buses)
    {
        Planner p = null;
        try
        {
        System.out.println("Please enter Planner Name:");
        String name = scan.next();
        System.out.println("Please enter Planner Budget:");
        int budget = Integer.parseInt(scan.next());
        p = new Planner(name, budget, mny, buses);
        }
        catch(NumberFormatException nfe)
        {}
        return p;
    }

      
    


    public int findPlanner(ArrayList<Planner> plans, int pid)
    {
        int pdx =-1;
        int currdx=0;
        while ((currdx<plans.size())&&(pdx==-1))
        {
            if (plans.get(currdx).getId()==pid)
                pdx = currdx;
            currdx++;

        }

        return pdx;

    }

    public int findBus(ArrayList<Bus> buses, int bid)
    {
        int bdx=-1;
        int currdx=0;
        while ((currdx<buses.size())&&(bdx==-1))
        {
            if (buses.get(currdx).getId()==bid)
                bdx = currdx;
            currdx++;
        }
        return bdx;
    }

    public Bus createBus(Scanner scan, Ministry mny)
    {
        Bus b = null;
        try
        {
            System.out.println("Please enter bus name:");
            String name = scan.next();
            System.out.println("Please enter bus size:");
            int size = Integer.parseInt(scan.next());
            System.out.println("Please enter bus price:");
            int price = Integer.parseInt(scan.next());
            System.out.println("Please enter bus level:");
            int level = Integer.parseInt(scan.next());
            
            System.out.println("Please enter bus type [1=Basic, 2=Training, 3=Sports, 4=Party]:");
            int busType = Integer.parseInt(scan.next());
            
            switch(busType)
            {
                case 1:
                    b = new Bus(name, size, price, level, mny);
                    break;
                case 2:
                    System.out.println("Please enter teacher area:");
                    int teacherArea = Integer.parseInt(scan.next());
                    b = new TrainingBus(name, size, price, level, mny, teacherArea);
                    break;
                case 3:
                    System.out.println("Please enter competitor area:");
                    int competitorArea = Integer.parseInt(scan.next());
                    System.out.println("Please enter security:");
                    int security = Integer.parseInt(scan.next());
                    b = new SportsBus(name, size, price, level, mny, competitorArea, security);
                    break;
                case 4:
                    System.out.println("Please enter competitor area:");
                    int compArea = Integer.parseInt(scan.next());
                    System.out.println("Please enter security:");
                    int sec = Integer.parseInt(scan.next());
                    System.out.println("Please enter bar area:");
                    int barArea = Integer.parseInt(scan.next());
                    b = new PartyBus(name, size, price, level, mny, compArea, sec, barArea);
                    break;
                default:
                    b = new Bus(name, size, price, level, mny);
            }
        }
        catch(NumberFormatException nfe)
        {}
        return b;
    }

}