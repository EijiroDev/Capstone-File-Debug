import java.util.*;
import java.io.*;
import java.nio.file.*;
import static java.nio.file.StandardOpenOption.*;

public class StocksManagement extends AdminMethod{

    static Scanner input = new Scanner(System.in); // Scanner for the user admin to have the ability to input in the console.

    //global instance of class
    BasedPath pathsEncap = new BasedPath();
    addMessage addMsg = new addMessage();
    removeMessage removeMsg = new removeMessage();
    invalidMessage invMessage = new invalidMessage();

    //global variable
    String delimeter = ","; //for splitting purposes;
    
    static StocksManagement sManagement; // sManagement is an instance of a class called StocksManagement. 
    BufferedWriter bWriter; // BufferedWriter used to write characters to a stream.
    static boolean isValid = false; // static boolean variable called isValid that is initialized to false.

    //global paths
    Path mainProductsDB;
    Path tempProductsDB;

    Path mainQuantityDB;
    Path tempQuantityDB;

    //-----
    //output stream
    OutputStream mainProductsOutput; 
    OutputStream tempProductsOuput;
    OutputStream mainQuantityOutput;
    OutputStream tempQuantityOutput;

    //writers

    BufferedWriter mainProductsWriter, tempProductsWriter, mainQuantityWriter, tempQuantityWriter;

    //readers
    BufferedReader mainProductReader, tempProductsReader, mainQuantityReader, tempQuantityReader;

    //inputStream
    InputStream mainProductsInput, tempProductsInput, mainQuantityInput, tempQuantityInput;

    public static void main(String[] args) {

        System.out.println("=============================== Welcome Admin ==============================\n\n\n\n");

        sManagement = new StocksManagement(); // Creates a new instance of the StocksManagement class and assigns it to the sManagement variable.
        sManagement.Run(); // Call the methods on the sManagement object to perform operations on it.
        
    }

    @Override
    public void AddItem() {

        pathsEncap = new BasedPath();

        mainProductsDB = Paths.get("D:\\Capstone Project\\productsDB.txt");
        pathsEncap.setMainProductPath(mainProductsDB);

        tempProductsDB = Paths.get("D:\\Capstone Project\\productTemp.txt");
        pathsEncap.setTempProductPath(tempProductsDB);

        mainQuantityDB = Paths.get("D:\\Capstone Project\\quantityDB.txt");
        pathsEncap.setMainQuantityPath(mainQuantityDB);

        tempQuantityDB = Paths.get("D:\\Capstone Project\\quantityTemp.txt");
        pathsEncap.setTempQuantityPath(tempQuantityDB);


        try {

            // newOutputStream method, which creates an output stream to the file at the specified path.
            mainProductsOutput = new BufferedOutputStream(Files.newOutputStream(pathsEncap.getMainProductPath(), APPEND));
            tempProductsOuput = new BufferedOutputStream(Files.newOutputStream(pathsEncap.getTempProductPath(), APPEND));

            mainQuantityOutput = new BufferedOutputStream(Files.newOutputStream(pathsEncap.getMainQuantityPath(), APPEND));
            tempQuantityOutput = new BufferedOutputStream(Files.newOutputStream(pathsEncap.getTempQuantityPath(), APPEND));

            mainProductsWriter = new BufferedWriter(new OutputStreamWriter(mainProductsOutput));
            tempProductsWriter = new BufferedWriter(new OutputStreamWriter(tempProductsOuput));
            mainQuantityWriter = new BufferedWriter(new OutputStreamWriter(mainQuantityOutput));
            tempQuantityWriter = new BufferedWriter(new OutputStreamWriter(tempQuantityOutput));

            mainProductsInput = Files.newInputStream(pathsEncap.getMainProductPath());
            tempProductsInput = Files.newInputStream(pathsEncap.getTempProductPath());

            mainQuantityInput = Files.newInputStream(pathsEncap.getMainQuantityPath());
            tempQuantityInput = Files.newInputStream(pathsEncap.getTempQuantityPath());

            mainProductReader = new BufferedReader(new InputStreamReader(mainProductsInput));
            tempProductsReader = new BufferedReader(new InputStreamReader(tempProductsInput));

            mainQuantityReader = new BufferedReader(new InputStreamReader(mainQuantityInput));
            tempQuantityReader = new BufferedReader(new InputStreamReader(tempQuantityInput));


        } catch (IOException e1) {
            
            e1.printStackTrace();
        }

        try {

            System.out.print("What is this product? ");
            String prdName = input.next().toUpperCase(); 
            pathsEncap.setProductName(prdName);
    
            System.out.print("How many " + prdName + " you wish to add: ");
            String prdQnt = input.next();
            pathsEncap.setProductQuantity(prdQnt);

            //sulat the product name to the notepad
            mainProductsWriter.write(pathsEncap.getProductName() + delimeter);
            mainProductsWriter.newLine();

            //sulat the product quantity to the notepad
            mainQuantityWriter.write(pathsEncap.getProductQuantity() + delimeter);
            mainQuantityWriter.newLine();

            mainProductsWriter.close();
            // tempProductsWriter.close();

            mainQuantityWriter.close();
            // tempQuantityWriter.close();
    
            System.out.println("+──────────────────────────────────────────────────────────────────────────+");
            System.out.println(addMsg.message());
            System.out.println("+──────────────────────────────────────────────────────────────────────────+");

            // System.out.println(pathsEncap.getProductQuantity()); 

            System.out.println("What do you want to do now? ");
            System.out.println("Available Options: ");
            System.out.println("1. Add more || 2. Back\n");
            int answer = input.nextInt();

            if (answer == 1) {
                AddItem();
            } else if (answer == 2){
                Run();
            } else {
                System.out.println(invMessage.message());
                Run();
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }

     
       
    }

    String mainProductReaderFile, tempProductReaderFile, mainQuantityReaderFile, tempQuantityReaderFile;
    // BufferedReader bReader; // used to read text from a character-input stream.
    // InputStream inputS;
    // InputStream inputS1;
    String prodName, prodQnt;
    // // Path file = Paths.get("D:\\Capstone Project\\_temp_.txt");


    @Override
    public void ViewItem() {

        pathsEncap = new BasedPath();

        mainProductsDB = Paths.get("D:\\Capstone Project\\productsDB.txt");
        pathsEncap.setMainProductPath(mainProductsDB);

        tempProductsDB = Paths.get("D:\\Capstone Project\\productTemp.txt");
        pathsEncap.setTempProductPath(tempProductsDB);

        mainQuantityDB = Paths.get("D:\\Capstone Project\\quantityDB.txt");
        pathsEncap.setMainQuantityPath(mainQuantityDB);

        tempQuantityDB = Paths.get("D:\\Capstone Project\\quantityTemp.txt");
        pathsEncap.setTempQuantityPath(tempQuantityDB);
        
        try {

            mainProductsInput = Files.newInputStream(pathsEncap.getMainProductPath());
            tempProductsInput = Files.newInputStream(pathsEncap.getTempProductPath());

            mainQuantityInput = Files.newInputStream(pathsEncap.getMainQuantityPath());
            tempQuantityInput = Files.newInputStream(pathsEncap.getTempQuantityPath());

            mainProductReader = new BufferedReader(new InputStreamReader(mainProductsInput));
            mainQuantityReader = new BufferedReader(new InputStreamReader(mainQuantityInput));

            tempProductsReader = new BufferedReader(new InputStreamReader(tempProductsInput));
            tempQuantityReader = new BufferedReader(new InputStreamReader(tempQuantityInput));

            System.out.println("    ========== List Of Products ==========\n");

            if ((mainProductReaderFile = mainProductReader.readLine()) == null && (mainQuantityReaderFile = mainQuantityReader.readLine()) == null && (tempProductReaderFile = tempProductsReader.readLine()) == null && (tempQuantityReaderFile = tempQuantityReader.readLine()) == null) {

                System.out.println("List is empty!");

            }else if((mainProductReaderFile = mainProductReader.readLine()) == null && (mainQuantityReaderFile = mainQuantityReader.readLine()) == null ) {

                viewProductsFromTemp();


            }else { 

                readFromDB();
                
            }

        } catch (Exception e) {
            
            e.printStackTrace();
        }

            System.out.println("\nWhat to do now? Available Option(s): 1.Back");
            System.out.print("Choice: ");
            String choices = input.next();
            
            if (choices.equals("1")) {
                Run();
        } else {
            System.out.println(invMessage.message());
        } Run();
    }

    void viewProductsFromTemp() { // this function is for viewing the products from the temp when the product is removed from the original DB

        pathsEncap = new BasedPath();

        mainProductsDB = Paths.get("D:\\Capstone Project\\productsDB.txt");
        pathsEncap.setMainProductPath(mainProductsDB);

        tempProductsDB = Paths.get("D:\\Capstone Project\\productTemp.txt");
        pathsEncap.setTempProductPath(tempProductsDB);

        mainQuantityDB = Paths.get("D:\\Capstone Project\\quantityDB.txt");
        pathsEncap.setMainQuantityPath(mainQuantityDB);

        tempQuantityDB = Paths.get("D:\\Capstone Project\\quantityTemp.txt");
        pathsEncap.setTempQuantityPath(tempQuantityDB);

        try {

            mainProductsInput = Files.newInputStream(pathsEncap.getMainProductPath());
            tempProductsInput = Files.newInputStream(pathsEncap.getTempProductPath());

            mainQuantityInput = Files.newInputStream(pathsEncap.getMainQuantityPath());
            tempQuantityInput = Files.newInputStream(pathsEncap.getTempQuantityPath());

            mainProductReader = new BufferedReader(new InputStreamReader(mainProductsInput));
            mainQuantityReader = new BufferedReader(new InputStreamReader(mainQuantityInput));

            tempProductsReader = new BufferedReader(new InputStreamReader(tempProductsInput));
            tempQuantityReader = new BufferedReader(new InputStreamReader(tempQuantityInput));

            while((tempProductReaderFile = tempProductsReader.readLine()) != null && (tempQuantityReaderFile = tempQuantityReader.readLine()) != null) {

                String[] prodNameData = tempProductReaderFile.split(delimeter);
                String[] prodQuantityData = tempQuantityReaderFile.split(delimeter);

                prodName = pathsEncap.getProductName();
                prodQnt = pathsEncap.getProductQuantity(); //working

                prodName = prodNameData[0];
                prodQnt = prodQuantityData[0];

                System.out.println("==========================================");
                System.out.println("Product Name: " + prodName + "\nProduct Quantity: " + prodQnt );
                System.out.println("==========================================");
                System.out.println();

            }
        } catch (Exception e) {
        }

    }

    void readFromDB() { //reads the productDB.txt and quantityDB.txt

        mainProductsDB = Paths.get("D:\\Capstone Project\\productsDB.txt");
        pathsEncap.setMainProductPath(mainProductsDB);

        tempProductsDB = Paths.get("D:\\Capstone Project\\productTemp.txt");
        pathsEncap.setTempProductPath(tempProductsDB);

        mainQuantityDB = Paths.get("D:\\Capstone Project\\quantityDB.txt");
        pathsEncap.setMainQuantityPath(mainQuantityDB);

        tempQuantityDB = Paths.get("D:\\Capstone Project\\quantityTemp.txt");
        pathsEncap.setTempQuantityPath(tempQuantityDB);

        try {

            mainProductsInput = Files.newInputStream(pathsEncap.getMainProductPath());
            tempProductsInput = Files.newInputStream(pathsEncap.getTempProductPath());

            mainQuantityInput = Files.newInputStream(pathsEncap.getMainQuantityPath());
            tempQuantityInput = Files.newInputStream(pathsEncap.getTempQuantityPath());

            mainProductReader = new BufferedReader(new InputStreamReader(mainProductsInput));
            tempProductsReader = new BufferedReader(new InputStreamReader(tempProductsInput));

            mainQuantityReader = new BufferedReader(new InputStreamReader(mainQuantityInput));
            tempQuantityReader = new BufferedReader(new InputStreamReader(tempQuantityInput));

            System.out.println("==========================================\n");

            while((mainProductReaderFile = mainProductReader.readLine()) != null &&
            (mainQuantityReaderFile = mainQuantityReader.readLine()) != null ) {

            String[] prodNameData = mainProductReaderFile.split(delimeter);
            String[] prodQuantityData = mainQuantityReaderFile.split(delimeter);

            prodName = pathsEncap.getProductName();
            prodQnt = pathsEncap.getProductQuantity(); //encapsulated version of this shit
                
            prodName = prodNameData[0];
            prodQnt = prodQuantityData[0];

            System.out.println("Product Name: " + prodName + "\nProduct Quantity: " + prodQnt );
            System.out.println();

            }

            //the second loop is for displaying the products from the product temp and quantity temp txt
            while((tempProductReaderFile = tempProductsReader.readLine()) != null && (tempQuantityReaderFile = tempQuantityReader.readLine()) != null) {

                String[] prodNameDataTemp = tempProductReaderFile.split(delimeter);
                String[] prodQuantityDataTemp = tempQuantityReaderFile.split(delimeter);

                prodName = pathsEncap.getProductName();
                prodQnt = pathsEncap.getProductQuantity();
                    
                prodName = prodNameDataTemp[0];
                prodQnt = prodQuantityDataTemp[0];
    
                // System.out.println("==========================================");
                System.out.println("Product Name: " + prodName + "\nProduct Quantity: " + prodQnt );
                // System.out.println("==========================================");
                System.out.println();

            }

            System.out.println("==========================================");
        } catch (IOException e) {
            
            e.printStackTrace();
        }

    }

    String lineToRemove, quantityToRemove = null;

    String currentLine, currentLine1;
    String trimmedLine, trimmedLine1 = null;

    @Override
    public void RemoveItem() {

        mainProductsDB = Paths.get("D:\\Capstone Project\\productsDB.txt");
        pathsEncap.setMainProductPath(mainProductsDB);

        tempProductsDB = Paths.get("D:\\Capstone Project\\productTemp.txt");
        pathsEncap.setTempProductPath(tempProductsDB);

        mainQuantityDB = Paths.get("D:\\Capstone Project\\quantityDB.txt");
        pathsEncap.setMainQuantityPath(mainQuantityDB);

        tempQuantityDB = Paths.get("D:\\Capstone Project\\quantityTemp.txt");
        pathsEncap.setTempQuantityPath(tempQuantityDB);

            File mainProductFile = new File("D:\\Capstone Project\\productsDB.txt");
            pathsEncap.setMainProductFile(mainProductFile);

            File tempProductFile = new File("D:\\Capstone Project\\productTemp.txt");
            pathsEncap.setTempProductFile(tempProductFile);

            File mainQuantityFile = new File("D:\\Capstone Project\\quantityDB.txt");
            pathsEncap.setMainQuantityFile(mainQuantityFile);

            File tempQuantityFile = new File("D:\\Capstone Project\\quantityTemp.txt");
            pathsEncap.setTempQuantityFile(tempQuantityFile);

        try {
            
            BufferedReader prodMainReader = new BufferedReader(new FileReader(pathsEncap.getMainProductFile()));
            BufferedReader quantMainReader = new BufferedReader(new FileReader(pathsEncap.getMainQuantityFile()));

            BufferedWriter prodMainWriter = new BufferedWriter(new FileWriter(pathsEncap.getTempProductFile()));
            BufferedWriter quantMainWriter = new BufferedWriter(new FileWriter(pathsEncap.getTempQuantityFile()));

            String mainProductRemoveFile, mainQuantityRemoveFile;

            if((mainProductRemoveFile = prodMainReader.readLine()) == null && (mainQuantityRemoveFile =  quantMainReader.readLine()) == null) { 

                removeV2();

            }else {

                System.out.println("REMOVE V1");

                System.out.print("Enter the product you wish to remove: ");
                lineToRemove = input.next().toUpperCase();
    
                System.out.print("Enter the quantity of that product (STRICTLY THE SAME WITH THE INPUT): ");
                quantityToRemove = input.next();
    
                    while((currentLine = prodMainReader.readLine()) != null ) { 
                        // trim newline when comparing with lineToRemove
                        trimmedLine = currentLine.trim();
                        if(trimmedLine.equals(lineToRemove+ ",")) continue;
                        prodMainWriter.write(currentLine + "\n");
                    }
            
                    while((currentLine1 = quantMainReader.readLine()) != null ) {
                        // trim newline when comparing with lineToRemove
                        trimmedLine1 = currentLine1.trim();
            
                        if(trimmedLine1.equals(quantityToRemove + ",")) continue;
                        quantMainWriter.write(currentLine1 + "\n");
                        
                    }
            
                    prodMainReader.close(); 
                    prodMainWriter.close();
            
                    quantMainReader.close();
                    quantMainWriter.close();
            
                     BufferedWriter deleteFromDb = Files.newBufferedWriter(pathsEncap.getMainProductPath()); //deleting from original DB when product is removed
                     deleteFromDb.write("");
                     deleteFromDb.flush();
            
                     BufferedWriter deleteFromQuantityDb = Files.newBufferedWriter(pathsEncap.getMainQuantityPath());
                     deleteFromQuantityDb.write("");
                     deleteFromQuantityDb.flush();
                

            }

        // this block of code reads the lines of a file and writes them to an output stream,
        // except for a specific line that contains a given string.
        }
         catch (IOException e) {
            e.printStackTrace();
            
        }finally {
            System.out.println(removeMsg.message());
        }

         //boolean successful = pathsEncap.renameTo();
        RunS();
        
    }

    void removeV2() {

        System.out.println("REMOVE V2");

        mainProductsDB = Paths.get("D:\\Capstone Project\\productsDB.txt");
        pathsEncap.setMainProductPath(mainProductsDB);

        tempProductsDB = Paths.get("D:\\Capstone Project\\productTemp.txt");
        pathsEncap.setTempProductPath(tempProductsDB);

        mainQuantityDB = Paths.get("D:\\Capstone Project\\quantityDB.txt");
        pathsEncap.setMainQuantityPath(mainQuantityDB);

        tempQuantityDB = Paths.get("D:\\Capstone Project\\quantityTemp.txt");
        pathsEncap.setTempQuantityPath(tempQuantityDB);

            File mainProductFile = new File("D:\\Capstone Project\\productsDB.txt");
            pathsEncap.setMainProductFile(mainProductFile);

            File tempProductFile = new File("D:\\Capstone Project\\productTemp.txt");
            pathsEncap.setTempProductFile(tempProductFile);

            File mainQuantityFile = new File("D:\\Capstone Project\\quantityDB.txt");
            pathsEncap.setMainQuantityFile(mainQuantityFile);

            File tempQuantityFile = new File("D:\\Capstone Project\\quantityTemp.txt");
            pathsEncap.setTempQuantityFile(tempQuantityFile);

        try {

            System.out.print("Enter the product you wish to remove: ");
            String lineToRemove1 = input.next().toUpperCase();

            System.out.print("Enter the quantity of that product (STRICTLY THE SAME WITH THE INPUT): ");
            String quantityToRemove1= input.next();

            BufferedReader prodTempReader = new BufferedReader(new FileReader(pathsEncap.getTempProductFile()));
            BufferedReader quantTempReader = new BufferedReader(new FileReader(pathsEncap.getTempQuantityFile()));

            BufferedWriter prodTempWriter = new BufferedWriter(new FileWriter(pathsEncap.getMainProductFile()));
            BufferedWriter quantTempWriter = new BufferedWriter(new FileWriter(pathsEncap.getMainQuantityFile()));
            
            String currentLineV2, currentLineV21;
            String trimmedLineV2, trimmedLinev21;

            while((currentLineV2 = prodTempReader.readLine()) != null ) { 
                
                trimmedLineV2 = currentLineV2.trim();
                if(trimmedLineV2.equals(lineToRemove1+ ",")) continue;
                prodTempWriter.write(currentLineV2 + "\n");
            }

            while((currentLineV21 = quantTempReader.readLine()) != null ) {
                // trim newline when comparing with lineToRemove
                trimmedLinev21 = currentLineV21.trim();
    
                if(trimmedLinev21.equals(quantityToRemove1 + ",")) continue;
                quantTempWriter.write(currentLineV21 + "\n");
                
            }

            prodTempReader.close(); 
            prodTempWriter.close();
    
            prodTempWriter.close();
            quantTempWriter.close();

            BufferedWriter deleteFromDb = Files.newBufferedWriter(pathsEncap.getTempProductPath()); //deleting from original DB when product is removed
            deleteFromDb.write("");
            deleteFromDb.flush();
   
            BufferedWriter deleteFromQuantityDb = Files.newBufferedWriter(pathsEncap.getTempQuantityPath());
            deleteFromQuantityDb.write("");
            deleteFromQuantityDb.flush();

        } catch (Exception e) {
            // TODO: handle exception
        }

        System.out.println("\nWhat to do now? Available Option(s): 1.Back");
            System.out.print("Choice: ");
            String choices = input.next();
            
            if (choices.equals("1")) {
                Run();
        } else {
            System.out.println(invMessage.message());
        } Run();


    }
    @Override
    public void Exit() {
        System.out.println("Thank you.");
        System.exit(0);
        
    }

    @Override
    public void Run() {

        boolean isValid = true;
            
                do {

                System.out.println("Choose Option: ");
                System.out.println("+───────────────+─────────────────────────────+────────────────+───────────+");
                System.out.println("|1. Add Item    |2. View Item Existing Item   |3. Remove Item  | 4. Exit   |");
                System.out.println("+───────────────+─────────────────────────────+────────────────+───────────+");
                System.out.print("|Choice: ");
                String choices = input.next();
                System.out.println("+───────────────+─────────────────────────────+────────────────+───────────+");

                if (choices.equals("1")) {

                    isValid = true;
                    sManagement.AddItem();
                }
                else if (choices.equals("2")) {

                    isValid = true;
                    sManagement.ViewItem();
                }
                else if (choices.equals("3")) {

                    isValid = true;
                    sManagement.RemoveItem();
                }
                else if (choices.equals("4")) {

                    isValid = true;
                    sManagement.Exit();
                }else { 

                    isValid = false;
                    System.out.println("Invalid Input. Please try again with numbers 1 - 4\n");
                }

                } while (isValid == false);
        
    }

    void readUpdatedDB() { //read the productTemp.txt and quantityTemp.txt

        pathsEncap = new BasedPath();

        mainProductsDB = Paths.get("D:\\Capstone Project\\productsDB.txt");
        pathsEncap.setMainProductPath(mainProductsDB);

        tempProductsDB = Paths.get("D:\\Capstone Project\\productTemp.txt");
        pathsEncap.setTempProductPath(tempProductsDB);

        mainQuantityDB = Paths.get("D:\\Capstone Project\\quantityDB.txt");
        pathsEncap.setMainQuantityPath(mainQuantityDB);

        tempQuantityDB = Paths.get("D:\\Capstone Project\\quantityTemp.txt");
        pathsEncap.setTempQuantityPath(tempQuantityDB);

        try {

            mainProductsInput = Files.newInputStream(pathsEncap.getMainProductPath());
            tempProductsInput = Files.newInputStream(pathsEncap.getTempProductPath());

            mainQuantityInput = Files.newInputStream(pathsEncap.getMainQuantityPath());
            tempQuantityInput = Files.newInputStream(pathsEncap.getTempQuantityPath());

            mainProductReader = new BufferedReader(new InputStreamReader(mainProductsInput));
            tempProductsReader = new BufferedReader(new InputStreamReader(tempProductsInput));

            mainQuantityReader = new BufferedReader(new InputStreamReader(mainQuantityInput));
            tempQuantityReader = new BufferedReader(new InputStreamReader(tempQuantityInput));

            System.out.println("==========================================\n");

            while((mainProductReaderFile = mainProductReader.readLine()) != null &&
            (mainQuantityReaderFile = mainQuantityReader.readLine()) != null ) {

            String[] prodNameData = mainProductReaderFile.split(delimeter);
            String[] prodQuantityData = mainQuantityReaderFile.split(delimeter);
                
            prodName = prodNameData[0];
            prodQnt = prodQuantityData[0];

            System.out.println("Product Name: " + prodName + "\nProduct Quantity: " + prodQnt );
            System.out.println();

            }

            //the second loop is for displaying the products from the product temp and quantity temp txt
            while((tempProductReaderFile = tempProductsReader.readLine()) != null && (tempQuantityReaderFile = tempQuantityReader.readLine()) != null) {

                String[] prodNameDataTemp = tempProductReaderFile.split(delimeter);
                String[] prodQuantityDataTemp = tempQuantityReaderFile.split(delimeter);
                    
                prodName = prodNameDataTemp[0];
                prodQnt = prodQuantityDataTemp[0];
    
                // System.out.println("==========================================");
                System.out.println("Product Name: " + prodName + "\nProduct Quantity: " + prodQnt );
                // System.out.println("==========================================");
                System.out.println();

            }

            System.out.println("==========================================");
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

    void viewUpdatedItem() {

        pathsEncap = new BasedPath();

        mainProductsDB = Paths.get("D:\\Capstone Project\\productsDB.txt");
        pathsEncap.setMainProductPath(mainProductsDB);

        tempProductsDB = Paths.get("D:\\Capstone Project\\productTemp.txt");
        pathsEncap.setTempProductPath(tempProductsDB);

        mainQuantityDB = Paths.get("D:\\Capstone Project\\quantityDB.txt");
        pathsEncap.setMainQuantityPath(mainQuantityDB);

        tempQuantityDB = Paths.get("D:\\Capstone Project\\quantityTemp.txt");
        pathsEncap.setTempQuantityPath(tempQuantityDB);
        
        try {

            mainProductsInput = Files.newInputStream(pathsEncap.getMainProductPath());
            tempProductsInput = Files.newInputStream(pathsEncap.getTempProductPath());

            mainQuantityInput = Files.newInputStream(pathsEncap.getMainQuantityPath());
            tempQuantityInput = Files.newInputStream(pathsEncap.getTempQuantityPath());

            mainProductReader = new BufferedReader(new InputStreamReader(mainProductsInput));
            tempProductsReader = new BufferedReader(new InputStreamReader(tempProductsInput));

            mainQuantityReader = new BufferedReader(new InputStreamReader(mainQuantityInput));
            tempQuantityReader = new BufferedReader(new InputStreamReader(tempQuantityInput));

            System.out.println("    ========== List Of Products ==========\n");

            if ((mainProductReaderFile = mainProductReader.readLine()) == null && (mainQuantityReaderFile = mainQuantityReader.readLine()) == null && (tempProductReaderFile = tempProductsReader.readLine()) == null && (tempQuantityReaderFile = tempQuantityReader.readLine()) == null) {

                System.out.println("List is empty!");

            }else if((mainProductReaderFile = mainProductReader.readLine()) == null && (mainQuantityReaderFile = mainQuantityReader.readLine()) == null ) {

               
                viewProductsFromTemp();
                

            }else { 

                readUpdatedDB();
                
            }


        } catch (Exception e) {
            
            e.printStackTrace();
        }

            System.out.println("\nWhat to do now? Available Option(s): 1.Back");
            System.out.print("Choice: ");
            String choices = input.next();
            
            if (choices.equals("1")) {
                RunS();
        } else {
            System.out.println(invMessage.message());
        } RunS();

    }

    private void RunS() {

        boolean isValid = true;
            
                do {

                System.out.println("Choose Option: ");
                System.out.println("+───────────────+─────────────────────────────+────────────────+───────────+");
                System.out.println("|1. Add Item    |2. View Item Existing Item   |3. Remove Item  | 4. Exit   |");
                System.out.println("+───────────────+─────────────────────────────+────────────────+───────────+");
                System.out.print("|Choice: ");
                String choices = input.next();
                System.out.println("+───────────────+─────────────────────────────+────────────────+───────────+");

                if (choices.equals("1")) {

                    isValid = true;
                    sManagement.AddItem();
                }
                else if (choices.equals("2")) {

                    isValid = true;
                    sManagement.viewUpdatedItem();
                }
                else if (choices.equals("3")) {

                    isValid = true;
                    sManagement.removeV2();
                }
                else if (choices.equals("4")) {

                    isValid = true;
                    sManagement.Exit();
                }else { 

                    isValid = false;
                    System.out.println("Invalid Input. Please try again with numbers 1 - 4\n");
                }

                } while (isValid == false);

    }
    
}
