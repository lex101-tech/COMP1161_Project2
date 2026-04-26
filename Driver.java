import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
// import  project.Tester;
public class Driver {
    WorkArea workArea = new WorkArea();

    public static void main(String[] args) {
        // Tester.main(args);;
        // TODO Auto-generated method stub
        
        Scanner scan = new Scanner(System.in);
        ;
        int selCase =-1;
        EntryScreen e= new EntryScreen();
        ReportScreen r= new ReportScreen();
        WorkArea work = new WorkArea();
        SystemInfo sys = new SystemInfo();
        // SystemInfo s;

        char mchoice = 'c';
        boolean listdata = true;
        String menu="";
        String stuId="";

        while (mchoice!='X')
        {
            try
            {
                Scanner sc = new Scanner(new File("id.txt"));
                stuId = sc.nextLine();
                sc.close();
            }
            catch (IOException ioe){}

            String menuOptions = "-----User Data";
            if (selCase==-1)
                menuOptions = "---"+menuOptions +"--------\n";
            else
                menuOptions+=" Case "+ selCase+"-----------\n";

            menuOptions+="[P]lanner data\n[B]us management\n[M]inistry interface\n";
            menuOptions+="[L]oad test case\n[S]ave test case\n[T]est all cases\n";
            menuOptions+="-------System settings--------\n[I]D:";

            if (stuId.length()==0)
                menuOptions+="Not yet set.\n";
            else
                menuOptions+=stuId+"\n";

            menuOptions+="Code folder:"+sys.getCodeFolder()+"\n";
            menuOptions+="Test case folder:"+sys.getTestCaseFolder()+"\n";
            menuOptions+="Javadoc folder:"+sys.getJavaDocFolder()+"\n";
            menuOptions+="Update s[y]stem settings\n";
            menuOptions+="[A]utodetect system settings\n";
            menuOptions+="E[x]it";

            System.out.println(menuOptions);
            menu = scan.next().toUpperCase();
            mchoice = menu.charAt(0);
            switch(mchoice)
            {
                case 'P':{
                        work.planners = e.managePlanners(scan,  work.planners,  work.mny, work.buses);
                        break;
                    }

                case 'M':{
                        work.mny.showPublicInfo(scan,work);
                        break;

                    }
                case 'L':{
                        System.out.println("Please enter the number of the test case to be loaded[0..11]:");
                        int caseNo = Integer.parseInt(scan.next());
                        work.loadTestCase(caseNo,scan);
                        break;

                    }
                case 'T':{
                        Tester.runCases(work,sys);
                        //s = e.editSystemInfo(s);
                        System.out.println(System.getProperty("user.dir"));
                        break;

                    }
                case 'S':{
                        Tester.saveTest(scan, work);
                        // s = e.editSystemInfo(s);
                        break;

                    }
                case 'Y':{
                        sys.readFromScreen(scan);
                        break;

                    }
                case 'I':
                    {
                        System.out.println("Please enter your student ID number:");
                        String stuid = scan.next();

                        try{    
                            int stuIdNum = Integer.parseInt(stuid);

                            try{    
                                PrintStream p=new PrintStream(new FileOutputStream("id.txt"));
                                p.println(stuid);
                                p.close();
                            }
                            catch(IOException ioe){}

                        }
                        catch(NumberFormatException ne){
                            System.out.println (stuid + " is not a valid UWI ID number");
                        }

                        break;
                    } 
                case 'A':
                    {
                        File f = new File("Sysinfo.txt");
                        f.delete();
                        sys = new SystemInfo();           }

                case 'B':{
                    // Bus management menu
                    ReportScreen reportScreen = new ReportScreen();
                    char busChoice = 'c';
                    String busMenu = "";
                    while (busChoice != 'X')
                    {
                        String busMenuOptions = "[A]dd bus\n[E]dit bus\n[L]ist bus\n[D]elete bus\nE[x]it\n";
                        System.out.println(busMenuOptions);
                        busMenu = scan.next().toUpperCase();
                        busChoice = busMenu.charAt(0);
                        
                        switch(busChoice)
                        {
                            case 'A':{
                                Bus b = e.createBus(scan, work.mny);
                                if (b != null)
                                    work.buses.add(b);
                                break;
                            }
                            case 'L':{
                                reportScreen.listBuses(work.buses, System.out);
                                break;
                            }
                            case 'E':{
                                System.out.println("Please enter the ID of the bus to be updated:");
                                int bid = Integer.parseInt(scan.next());
                                int bdx = e.findBus(work.buses, bid);
                                if (bdx >= 0)
                                    work.buses.get(bdx).updateLocalData(scan);
                                else
                                    System.out.println("Bus with id " + bid + " not found.");
                                break;
                            }
                            case 'D':{
                                System.out.println("Please enter the ID of the bus to be deleted:");
                                int bid = Integer.parseInt(scan.next());
                                int bdx = e.findBus(work.buses, bid);
                                if (bdx >= 0)
                                    work.buses.remove(bdx);
                                else
                                    System.out.println("Bus with id " + bid + " not found.");
                                break;
                            }
                        }
                    }
                    break;
                }

            }
        }
    }

}