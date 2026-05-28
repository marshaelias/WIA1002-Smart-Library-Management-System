public class SmartLibrary implements LibraryADT {
    
    // Information hiding
    private BookBST catalogue = new BookBST();
    private BorrowStack history = new BorrowStack();
    
    @Override
    public void addBook(int isbn, String title, String author){
        
        catalogue.insert(isbn, title, author);
        System.out.println("Success: Book added to the catalogue.");
    }
    
    @Override
    public void searchBook(int isbn){
        
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
            // 2. If it exists, push to user's stack
            history.push(b);
            System.out.println("Success: Borrowed '" + b.title + "'. Added to history.");
        } else {
            // 3. If it doesn't exist, handle the error gracefully
            System.out.println("Error: Book not found in catalogue. Cannot borrow.");
        }
    }
    
    @Override
    public void viewLatestHistory(){
        // Trigger user's history display
        history.show();
    }
}