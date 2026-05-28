import java.util.Scanner;

public class Main {
    private static SmartLibrary librarySystem;
    
    public static void main(String[] args) {
        // Instantiate the SmartLibrary system
        librarySystem = new SmartLibrary();
        
        // Trigger the menu to start the program
        runMenu();
    }
    
    public static void printMenu() {
        System.out.println("_______________________\nSmartLibrary Menu\n_______________________");
        System.out.println("1. Add book");
        System.out.println("2. Search Book");
        System.out.println("3. Borrow Book");
        System.out.println("4. View History");
        System.out.println("5. Exit");
    }
    
    public static void runMenu() {
        Scanner scan = new Scanner(System.in);
        
        while(true) {
            printMenu();
            System.out.print("Enter choice: ");
            
            //check input
            if(!scan.hasNextInt()) {
                System.out.println("Invalid input! Please enter an integer (1-5)");
                scan.next();
                continue;
            }
            
            int choice = scan.nextInt();
            
            //choice 5; exit
            if(choice==5) {
                System.out.println("Exit. Goodbye!");
                break;
            }
            
            handleChoice(choice, scan);
        }
    }
    
    private static void handleChoice(int choice, Scanner scan) {
        
        switch(choice) {
            
            case 1: 
                System.out.print("Enter Book ISBN: ");
                
                //check input validation
                while(!scan.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a valid integer value");
                    System.out.print("Enter Book ISBN: ");
                    scan.next();
                }
                
                int isbn = scan.nextInt();
                scan.nextLine();
                
                System.out.print("Enter Book Title: ");
                String title = scan.nextLine();
                
                System.out.print("Enter Book Author: ");
                String author = scan.nextLine();
                
                librarySystem.addBook(isbn, title, author);
                break;
                
            case 2:
                System.out.print("Enter Book ISBN to search: ");
                
                //check input validation
                while(!scan.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a valid integer value");
                    System.out.print("Enter Book ISBN: ");
                    scan.next();
                }
                
                librarySystem.searchBook(scan.nextInt());
                break;
                
            case 3:
                System.out.print("Enter Book ISBN to borrow: ");
                
                //check input validation
                while(!scan.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a valid integer value");
                    System.out.println("Enter Book ISBN: ");
                    scan.next();
                }
                
                librarySystem.borrowBook(scan.nextInt());
                break;
                
            case 4:
                System.out.println("Showing Latest History: ");
                librarySystem.viewLatestHistory();
                break;
                
            default:
                System.out.println("Invalid, please select a valid option (1-5)");
        }
    }
}