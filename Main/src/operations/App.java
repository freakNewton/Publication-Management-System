package operations;
import java.util.Scanner;
import java.sql.*;
import operations.Initializer;
import operations.Production;
import operations.Distribution;
import operations.Edit_Publish;
import operations.Report;

public class App {
    //database parameters Replace with your parameters
    static String user = "ssingha2";
    static String password = "200370963";

    public static void main(String[] args) throws Exception {
        
        try{ 
            //INITIALIZE PROJECT
            //create scanner to read user input
            Scanner inputReader = new Scanner(System.in);  // Create a Scanner object
            String userInput = "";

            //connect to the database
            Class.forName("org.mariadb.jdbc.Driver"); 
             Connection con=DriverManager.getConnection("jdbc:mariadb://classdb2.csc.ncsu.edu:3306/" + user,user,password);  
                    
            //drop, and re-create all tables
            Initializer.dropTables(con);
            Initializer.createTables(con);

            System.out.println("\n\n+-------------------------------+\n|\tTABLES CREATED!!!\t|\n+-------------------------------+\n\n");
            // Initializer.addDummyValues(con);

            //USER INPUT PROCESSING
            do {
                //Calls prompt method which gives the list of commands user could use.
                prompt();
                System.out.print("Input Command: ");
                userInput = inputReader.nextLine();  // Read user input
                
                //TODO: Replace the println's with calls to the different methods in the Distribution, Edit_Publish, Report, Production.java files.
                //check case 7 for example
                // input routing
                con.setAutoCommit(false);
                switch(userInput){
                	case "0" : break;
                    case "1" :  con.setSavepoint("beforeProdInsert");
                                if(Production.newPublication(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback();
                                break; //call createPublication method in Production.java file
                    case "2" : System.out.println("Unimplemented");
                            break;
                    case "3" : System.out.println("Unimplemented");
                                break;
                    case "4" : System.out.println("Unimplemented");
                                break;
                    case "5" : System.out.println("Unimplemented");
                                break;
                    case "6" : System.out.println("Unimplemented");
                                break;
                    case "7" : con.setSavepoint("beforeProdInsert");
                                if(Production.newPublication(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback();
                                break; //call createPublication method in Production.java file
                    case "8" : System.out.println("Unimplemented");
                                break;
                    case "9" : System.out.println("Unimplemented");
                                break;
                    case "10" : System.out.println("Unimplemented");
                                break;
                    case "11" : System.out.println("Unimplemented");
                                break;
                    case "12" : System.out.println("Unimplemented");
                                break;
                    case "13" : System.out.println("Unimplemented");
                                break;
                    // case "14" : con.setSavepoint("beforeDistInsert");
                    //             if(Distribution.newDist(con,inputReader))
                    //                 con.commit();
                    //             else
                    //                 con.rollback();
                    //             break;
                    case "15" : System.out.println("Unimplemented");
                                break;
                    // case "16" : con.setSavepoint("beforeDistInsert");
                    //             if(Distribution.deleteDist(con,inputReader))
                    //                 con.commit();
                    //             else
                    //                 con.rollback();
                    //             break;
                    case "17" : System.out.println("Unimplemented");
                                break;
                    case "18" : System.out.println("Unimplemented");
                                break;
                    case "19" : System.out.println("Unimplemented");
                                break;
                    case "20" : con.setSavepoint("beforeDistInsert");
			                    if(Report.monthlyPublication(con,inputReader))
			                        con.commit();
			                    else
			                        con.rollback();
			                    break;	
                    case "21" : System.out.println("Unimplemented");
                                break;
                    case "22" : System.out.println("Unimplemented");
                                break;
                    case "23" : System.out.println("Unimplemented");
                                break;
                    case "24" : System.out.println("Unimplemented");
                                break;
                    case "25" : System.out.println("Unimplemented");
                                break;
                    case "26" : Statement st=con.createStatement();
                            ResultSet rs=st.executeQuery("Select * from Publication");
                            while(rs.next()){
                                System.out.println(rs.getInt(1));
                            }
                            break;
        
                    
                    case "27" : con.setSavepoint("beforeEditorInsert");
                                if(Edit_Publish.newEditor(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback();
                                break;
                    case "28" : con.setSavepoint("AfterEditorInsert");
                                if(Edit_Publish.newStaffEditor(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback();
                                break;
                    case "29" : con.setSavepoint("AfterStaffEditorInsert");
                                if(Edit_Publish.newInvitedAuthor(con,inputReader))
                                    con.commit();
                                else
                                    con.rollback();
                                break;

                    default:  System.out.println("Invalid Input");
                                break;
                    
                }
     
            } while(!userInput.equals("0"));

            //DE-INITIALIZE PROJECT
            
            //close sql connection and scanner object
            con.close();
            inputReader.close();
            
               
               
        }catch(Exception e){ 
            System.out.println(e);
        }
    }

    public static void prompt(){
        System.out.println();
        System.out.println("DBMS Group 22 Project");
        System.out.println("Instructions: Enter the number associated with the given operation to perform it.");
        System.out.println("\t\t [0] - Close");

        //instructions for editing/publishing
        System.out.println("\tEditing/Publishing:");
        System.out.println("\t\t [1] - Enter basic information on a new publication ");
        System.out.println("\t\t [2] - Update Publication Information ");
        System.out.println("\t\t [3] - Assign Editor(s) to Publication");
        System.out.println("\t\t [4] - Let each editor view the information on the publications he/she is responsible for");        
        System.out.println("\t\t [5] - Add Articles/Chapters to Publications ");
        System.out.println("\t\t [6] - Delete Articles/Chapters to Publications ");
        System.out.println();

        //instructions for production
        System.out.println("\tProduction:");
        System.out.println("\t\t [7] - Enter a new book edition or new issue of a publication");
        System.out.println("\t\t [8] - Update, Delete a book edition or publication issue.");
        System.out.println("\t\t [9] - Enter/Update an article or chapter");
        System.out.println("\t\t [10] - Enter/Update text of an article");
        System.out.println("\t\t [11] - Find books and articles by topic, date, author's name");
        System.out.println("\t\t [12] - Enter payment for author or editor");
        System.out.println("\t\t [13] - Keep track of when each payment was claimed by its addressee");
        System.out.println();
    
        //instructions for distribution
        System.out.println("\tDistribution");
        System.out.println("\t\t [14] - Enter new distributor");
        System.out.println("\t\t [15] - Update distributor information");
        System.out.println("\t\t [16] - Delete a distributor. ");
        System.out.println("\t\t [17] - Input orders from distributors, for a book edition or an issue of a publication per distributor, for a certain date.");
        System.out.println("\t\t [18] - Bill distributor for an order");
        System.out.println("\t\t [19] - Change outstanding balance of a distributor on receipt of a payment.");
        System.out.println();   
        
        //instructions for reports
        System.out.println("\tReports");
        System.out.println("\t\t [20] - Generate montly reports: number and total price of copies of each publication bought per distributor per month");
        System.out.println("\t\t [21] - Generate montly reports: total revenue of the publishing house");
        System.out.println("\t\t [22] - Generate montly reports: total expenses (i.e., shipping costs and salaries)");
        System.out.println("\t\t [23] - Calculate the total current number of distributors");
        System.out.println("\t\t [24] - Calculate total revenue (since inception) per city, per distributor, and per location");
        System.out.println("\t\t [25] - Calculate total payments to the editors and authors, per time period and per work type (book authorship, article authorship, or editorial work)");
        System.out.println();
        
        //instructions for editor
        System.out.println("\tEditor");
        System.out.println("\t\t [27] - Enter basic information of a Editor");
        System.out.println("\t\t [28] - Enter basic information of a Staff Editor");
        System.out.println("\t\t [29] - Enter basic information of a Invited Author");

    }

    
}
