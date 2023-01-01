import java.util.*;
public class TwoWayValidation {

    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        
        // Scanner sc = new Scanner(System.in);

        //1st option do,while

        // boolean isValid = false;

        // do{

        //     System.out.println("Select Option:\n1. Add More\n2. Back");
        //     int choice = sc.nextInt();

        //     if(choice ==1) {

        //         isValid = true;
        //         System.out.println("INPUT VALID");

        //     }else if(choice ==2){

        //         isValid = true;

        //     }else {

        //         isValid = false;
        //         System.out.println();
        //         System.out.println("INVALID INPUT");

        //     }

        // }while(isValid == false);


        //second option try,catch

      ask();



    }

    static void ask() { 


        try {

            System.out.println("Select Option:\n1. Add More\n2. Back");
            int choice = sc.nextInt();

            if(choice == 1) {

                System.out.println("VALID");

            }else if(choice == 2) {

                System.out.println("VALID");
            }

        }catch (Exception e) {

            System.out.println("INVALID INPUT");
            ask();
            
        }
   
    }
    
}
