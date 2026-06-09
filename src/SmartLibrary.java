

public class SmartLibrary implements LibraryADT {
    
    // Information hiding
    private BookBST catalogue = new BookBST();
    private BorrowStack history = new BorrowStack();
    private double fineBalance = 0.0; // running fine balance for the user
    
    @Override
    public void addBook(int isbn, String title, String author){
        catalogue.insert(isbn, title, author);
        System.out.println("Success: Book added to the catalogue.");
    }
    
    @Override
    public void searchBook(int isbn){
        Book b = catalogue.search(isbn);
        if (b != null) {
            System.out.println("Found -> Title: " + b.getTitle() + " | Author: " + b.getAuthor()
                    + " | Available: " + (b.isAvailable() ? "Yes" : "No"));
        } else {
            System.out.println("Not Found: No book matches that ISBN.");
        }
    }
    
    @Override
    public void borrowBook(int isbn){
        if (fineBalance > 0) {
            System.out.println("Borrow blocked: outstanding fines RM" + String.format("%.2f", fineBalance) + ". Please pay before borrowing.");
            return;
        }

        Book b = catalogue.search(isbn);
        if (b != null) {
            if (!b.isAvailable()) {
                System.out.println("Error: Book is currently not available.");
                return;
            }
            // mark as borrowed
            b.setAvailable(false);
            history.push(b);
            System.out.println("Success: Borrowed '" + b.getTitle() + "'. Added to history.");
        } else {
            System.out.println("Error: Book not found in catalogue. Cannot borrow.");
        }
    }
    
    @Override
    public void viewLatestHistory(){
        history.show();
    }

    // View all books in ascending ISBN
    public void viewAllBooks() {
        catalogue.printInOrder();
    }

    // Flexible search by substring in title or author
    public java.util.List<Book> searchBySubstring(String q) {
        return catalogue.searchBySubstring(q);
    }

    // Return a book and charge fines if overdueDays > 0
    public void returnBook(int isbn, int overdueDays) {
        Book b = catalogue.search(isbn);
        if (b == null) {
            System.out.println("Return failed: No book with that ISBN in catalogue.");
            return;
        }

        if (b.isAvailable()) {
            System.out.println("This book is already marked available in the catalogue.");
        } else {
            b.setAvailable(true);
            System.out.println("Return successful: '" + b.getTitle() + "' is now available.");
        }

        if (overdueDays > 0) {
            double penalty = overdueDays * 0.5; // RM0.50 per day
            fineBalance += penalty;
            System.out.println("Overdue by " + overdueDays + " days. Penalty charged: RM" + String.format("%.2f", penalty));
            System.out.println("Outstanding fines: RM" + String.format("%.2f", fineBalance));
        }
    }

    // Undo most recent borrow (removes from history and marks it available)
    public void undoLastBorrow() {
        Book undone = history.undoBorrow();
        if (undone != null) {
            undone.setAvailable(true);
        }
    }

    public double getFineBalance() { return fineBalance; }

    // Pay fines (amount reduces the outstanding balance). If amount >= balance, clears it.
    public void payFines(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid payment amount.");
            return;
        }
        if (amount >= fineBalance) {
            System.out.println("Payment successful. All fines cleared (RM" + String.format("%.2f", fineBalance) + ").");
            fineBalance = 0.0;
        } else {
            fineBalance -= amount;
            System.out.println("Payment successful. Remaining balance: RM" + String.format("%.2f", fineBalance));
        }
    }
}