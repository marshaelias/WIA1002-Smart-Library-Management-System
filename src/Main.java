import java.util.Scanner;

// =====================================
//        INTERFACE FUNCTIONALITY
// =====================================

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
        System.out.println("2. Search Book (by ISBN)");
        System.out.println("3. Search (title/author)");
        System.out.println("4. Borrow Book");
        System.out.println("5. Return Book");
        System.out.println("6. View History");
        System.out.println("7. Undo Last Borrow");
        System.out.println("8. View All Books");
        System.out.println("9. Pay Fines");
        System.out.println("10. Exit");
    }
    
    public static void runMenu() {
        Scanner scan = new Scanner(System.in);
        
        while(true) {
            printMenu();
            System.out.print("Enter choice: ");
            
            //check input
            if(!scan.hasNextInt()) {
                System.out.println("Invalid input! Please enter an integer (1-10)");
                scan.next();
                continue;
            }
            
            int choice = scan.nextInt();
            
            //choice 10; exit
            if(choice==10) {
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
                
                //================================
                //      INPUT VALIDATION 
                //================================
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
                while(!scan.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a valid integer value");
                    System.out.print("Enter Book ISBN: ");
                    scan.next();
                }
                librarySystem.searchBook(scan.nextInt());
                break;

            case 3:
                scan.nextLine();
                System.out.print("Enter search substring (title or author): ");
                String q = scan.nextLine();
                java.util.List<Book> results = librarySystem.searchBySubstring(q);
                if (results.isEmpty()) {
                    System.out.println("No matches found.");
                } else {
                    System.out.println("Matches:");
                    for (Book b : results) {
                        System.out.println("ISBN: " + b.getIsbn() + " | Title: " + b.getTitle() + " | Author: " + b.getAuthor() + " | Available: " + (b.isAvailable() ? "Yes" : "No"));
                    }
                }
                break;

            case 4:
                System.out.print("Enter Book ISBN to borrow: ");
                while(!scan.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a valid integer value");
                    System.out.print("Enter Book ISBN: ");
                    scan.next();
                }
                librarySystem.borrowBook(scan.nextInt());
                break;

            case 5:
                System.out.print("Enter Book ISBN to return: ");
                while(!scan.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a valid integer value");
                    System.out.print("Enter Book ISBN: ");
                    scan.next();
                }
                int retIsbn = scan.nextInt();
                System.out.print("Enter overdue days (0 if none): ");
                while(!scan.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a valid integer value");
                    System.out.print("Enter overdue days: ");
                    scan.next();
                }
                int overdue = scan.nextInt();
                librarySystem.returnBook(retIsbn, overdue);
                break;

            case 6:
                System.out.println("Showing Latest History: ");
                librarySystem.viewLatestHistory();
                break;

            case 7:
                System.out.println("Attempting to undo last borrow...");
                librarySystem.undoLastBorrow();
                break;

            case 8:
                librarySystem.viewAllBooks();
                break;

            case 9:
                System.out.println("Outstanding fines: RM" + String.format("%.2f", librarySystem.getFineBalance()));
                System.out.print("Enter payment amount (RM): ");
                while(!scan.hasNextDouble()) {
                    System.out.println("Invalid input! Please enter a valid numeric value");
                    System.out.print("Enter payment amount (RM): ");
                    scan.next();
                }
                double payment = scan.nextDouble();
                librarySystem.payFines(payment);
                break;
                
            default:
                System.out.println("Invalid, please select a valid option (1-10)");
        }
    }
}