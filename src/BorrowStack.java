import java.util.Stack;

public class BorrowStack {
    
    private Stack<Book> history;

    public BorrowStack() {
        this.history = new Stack<>();
    }

    
    public void push(Book b) {
        if (b != null) {
            history.push(b);
        }
    }

    
    public void show() {
        if (history.isEmpty()) {
            System.out.println("History is empty."); 
            return;
        }

        System.out.println("--- Borrowing History (Most Recent First) ---");
        
        for (int i = history.size() - 1; i >= 0; i--) {
            Book b = history.get(i);
            System.out.println("ISBN: " + b.isbn + " | Title: " + b.title); 
        }
    }
}