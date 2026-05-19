import java.util.Scanner;

public class SmartLibrary implements LibraryADT{
    
    //information hiding
    private BookBST catalogue = new BookBST();
    private BorrowStack history = new BorrowStack();
    
    @Override
    public void addBook(int isbn, String title, String author){
        
    }
    
    @Override
    public void searchBook(int isbn){
        
    }
    
    @Override
    public void borrowBook(int isbn){
        
    }
    
    @Override
    public void viewLatestHistory(){
        
    }
    
    public void printMenu(){
        System.out.println("_______________________\nSmartLibrary Menu\n_______________________");
        System.out.println("1. Add book");
        System.out.println("2. Search (BST)");
        System.out.println("3. Borrow (Stack)");
        System.out.println("4. History");
        System.out.println("5. Exit");
    }
    
    public void runMenu(){
        Scanner scan = new Scanner(System.in);
        
        while(true){
            printMenu();
            System.out.println("Enter choice: ");
            
            //check input
            if(!scan.hasNextInt()){
                System.out.println("Invalid input! Please enter an integer (1-5)");
                scan.next();
                continue;
            }
            
            int choice = scan.nextInt();
            
            //choice 5; exit
            if(choice==5){
                System.out.println("Exit. Goodbye!");
                break;
            }
            
            handleChoice(choice, scan);
        }
    }
    
    private void handleChoice(int choice, Scanner scan){
        
        switch(choice){
            
            case 1: 
                System.out.println("Enter Book ISBN: ");
                
                //check input validation
                while(!scan.hasNextInt()){
                    System.out.println("Invalid input! Please enter a valid integer value");
                    System.out.println("Enter Book ISBN: ");
                    scan.next();
                }
                
                int isbn = scan.nextInt();
                scan.nextLine();
                
                System.out.println("Enter Book Title: ");
                String title = scan.nextLine();
                
                System.out.println("Enter Book Author: ");
                String author = scan.nextLine();
                
                addBook(isbn, title, author);
                break;
                
            case 2:
                System.out.println("Enter Book ISBN to search: ");
                
                //check input validation
                while(!scan.hasNextInt()){
                    System.out.println("Invalid input! Please enter a valid integer value");
                    System.out.println("Enter Book ISBN: ");
                    scan.next();
                }
                
                searchBook(scan.nextInt());
                break;
                
            case 3:
                System.out.println("Enter Book ISBN to borrow: ");
                
                //check input validation
                while(!scan.hasNextInt()){
                    System.out.println("Invalid input! Please enter a valid integer value");
                    System.out.println("Enter Book ISBN: ");
                    scan.next();
                }
                
                borrowBook(scan.nextInt());
                break;
                
            case 4:
                System.out.println("Showing Latest History: ");
                viewLatestHistory();
                break;
                
            default:
                System.out.println("Invalid, please select a valid option (1-5)");
        }
    }
}