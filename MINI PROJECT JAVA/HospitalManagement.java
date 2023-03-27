import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;  

class Hospital 
{   
    // create data fields for patient
    private String pname;
    private String address;
    private long ph_no;      
    private Date adm_date;
    private int floorno;
    private int bedno; 

    public void patientdetails(int bedno,int floorno) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Name of the Patient:");
        pname=sc.next();
        System.out.println("Enter the Address of the Patient:");
        address=sc.next();
        System.out.println("Enter the Phone No: of the Patient:");
        ph_no=sc.nextLong();
        System.out.println("Enter the Admisson Date of the Patient:");
        //reading date
        String sdate=sc.next();
        //Conversion of date from string representation to Date
        adm_date=new SimpleDateFormat("dd/MM/yyyy").parse(sdate); 
        this.floorno=floorno;
        this.bedno=bedno;
    }
    void getpatientdetails()
    {
        
    //Date Formation
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
    String adm_date_str = formatter.format(adm_date);

    //Displaying Patient Details
    System.out.print(pname+"\t"+address+"\t\t"+ph_no+"\t"+adm_date_str+"\t"+bedno+"\t"+floorno);
    
    }

    static int search_patient(Hospital p[],int pc,String type)
    {
        int i,found=0;
        String na;
        System.out.println("");
        System.out.println("Enter the Name of the patient to be "+ type);
        Scanner sc=new Scanner(System.in);
        na=sc.next();
        // sequential search
        for(i=0;i<pc;i++)
        {
            if(p[i].pname.equals(na))
            {
                found=1;
                break;
            }    

        }  // end of for   
        
        // If i is from 0 to pc-1 then product found otherwise not found
        return i;

    } // end of search_product()
  
    static void update_patient(Hospital p[],int pc)
    {
        int sid=search_patient(p,pc,"Updated");
        if(sid<pc)
        {

            System.out.println("Press 1 To Update Patientname and 2 for Updation Of Contact");
            Scanner sc=new Scanner(System.in);
            int choice=sc.nextInt();
            switch(choice)
            {

            case 1: // price update
                    System.out.println("");
                    System.out.println("Enter the new Patientname");
                    String name=sc.next();
                    p[sid].pname=name;
                    System.out.println("PATIENT NAME UPDATED SUCCESSFULLY");
                    break;
            case 2: // quantity update
                    System.out.println("");
                    System.out.println("Enter the new Contact");
                    long contact=sc.nextLong();
                    p[sid].ph_no=contact;
                    System.out.println("Patient Contact UPDATED SUCCESSFULLY");
                    break;             
            default:
                    System.out.println("Invalid Option");
                    break;
            }

        }
        else
        {
                System.out.println("Patient doesn't exists in our databse");
        }    



    } // end of update_product()
}

class HospitalManagement 
{
    public static void main(String args[]) throws Exception
    {
        // Maximum size is kept as 50
        Hospital p[]=new Hospital[50];
        int pc=0,id=0,i,bedno=0,floorno=0;
        Scanner sc=new Scanner(System.in);
        
        while(true)  
        {    
            System.out.println("");
            System.out.println("*******************************************");
            System.out.println("Enter the choice as per given options:");
            System.out.println("1 - Add and 2-Display");
            System.out.println("3 - Total Beds and 4-Search");
            System.out.println("5 - Update Details and 6-ABOUT US");
            System.out.println("7 - Exit");
            System.out.println("*******************************************");
            System.out.println("Enter Your Choice:");
            int choice=sc.nextInt();    
            int floor;
            switch(choice)
            {
                case 1:
                        p[id]=new Hospital();
                        bedno=bedno+1;
                        if(bedno<=10)
                        {
                        floorno=(bedno>0 && bedno<=3)?(1):((bedno>3 && bedno<=6)?(2):(3));
                        p[id].patientdetails(bedno,floorno);
                        id++;
                        pc++;
                        }
                        else{
                            System.out.println("Sorry all the Beds are Full..");
                        }
                        
                        break;
                case 2: 
                        if(pc==0)
                        {
                             System.out.println("There is no Bed available in the database");   
                        }    
                        else
                        {   
                            System.out.println(""); 
                            System.out.println("Patient Details are as follows:");
                            System.out.println("pname\t Address\t Contact\t Adm_date\t Bedno\t floorno");
                            for(i=0;i<pc;i++)
                            {    
                            p[i].getpatientdetails();
                            System.out.println("");                            
                            
                        }}
                        break;

                case 3:
                         System.out.println("");   
                         System.out.println("Total Beds occupied="+pc);
                         System.out.println("Total Beds left="+(10-pc));
                         break;
        
                case 4:         
                        int sid= Hospital.search_patient(p,pc,"Searched"); // array and count
                        if(sid<pc) // if sid < pc , product is found 
                        {   
                            System.out.println(""); 
                            System.out.println("Bed is FOUND and details are as follows:-\n");
                            System.out.println("pname\t Address\t Contact\t Adm_date\t Bedno\t floorno");
                            p[sid].getpatientdetails();
                            System.out.println("");

                        }
                        else // if sid=pc (entire array is traversed but product not found)
                            System.out.println("Patient not found"); 

                        break;

                case 5:
                        Hospital.update_patient(p,pc);
                        break;

                
                case 6: 
                        System.out.println("");
                        System.out.println("WELCOME TO AIIMS HOSPITAL IN DELHI");
                        System.out.println("All India Institute of Medical Sciences, New Delhi, also known as AIIMS Delhi, is a public medical research university and hospital in New Delhi, India. The institute is governed by the AIIMS Act, 1956 and operates autonomously under the Ministry of Health and Family Welfare");
                        break;

                case 7:
                         System.out.println("");   
                         System.out.println("Have a Good Day!!");
                         System.out.println("End of Program");
                         System.exit(0);
                         break;
               }
        } // end of while loop  
    }
}