import java.util.Stack;

// =====================================
//          STACK HISTORY (LIFO)
// =====================================

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
            System.out.println("ISBN: " + b.getIsbn() + " | Title: " + b.getTitle() + " | Author: " + b.getAuthor());
        }
    }

    /**
     * Undo the most recent borrow by popping from the history stack.
     * Returns the Book that was undone, or null if history is empty.
     */
    public Book undoBorrow() {
        if (history.isEmpty()) {
            System.out.println("Nothing to undo: history is empty.");
            return null;
        }
        Book undone = history.pop();
        System.out.println("Undo successful. Removed from history: ISBN " + undone.getIsbn() + " | " + undone.getTitle());
        return undone;
    }
}