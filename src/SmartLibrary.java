import java.util.Scanner;

public class SmartLibrary implements LibraryADT {
    
    // Information hiding
    private BookBST catalogue = new BookBST();
    private BorrowStack history = new BorrowStack();
    
    @Override
    public void addBook(int isbn, String title, String author){
        // Pass data to Alisha's BST
        catalogue.insert(isbn, title, author);
        System.out.println("Success: Book added to the catalogue.");
    }
    
    @Override
    public void searchBook(int isbn){
        // Use Auni's O(log n) search
        Book b = catalogue.search(isbn);
        if (b != null) {
            System.out.println("Found -> Title: " + b.title + " | Author: " + b.author);
        } else {
            System.out.println("Not Found: No book matches that ISBN.");
        }
    }
    
    @Override
    public void borrowBook(int isbn){
        // 1. Verify if it exists first
        Book b = catalogue.search(isbn);
        
        if (b != null) {
            // 2. If it exists, push to Marsha's stack
            history.push(b);
            System.out.println("Success: Borrowed '" + b.title + "'. Added to history.");
        } else {
            // 3. If it doesn't exist, handle the error gracefully
            System.out.println("Error: Book not found in catalogue. Cannot borrow.");
        }
    }
    
    @Override
    public void viewLatestHistory(){
        // Trigger Marsha's history display
        history.show();
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