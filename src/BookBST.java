public class BookBST {
    private Book root; // information hiding so it cant be accidentally deleted or overwritten
    
    public BookBST() {
        this.root =  null;
    }
    
    public void insert(int isbn, String title, String author) {
        root = insertRecursive(root, isbn, title, author);
    }
    
    private Book insertRecursive(Book current, int isbn, String title, String author) {
        if (current == null) {
            return new Book(isbn, title, author);
        } else if (isbn < current.isbn) {
            current.left = insertRecursive(current.left, isbn, title, author);
        } else {
            current.right = insertRecursive(current.right, isbn, title, author);
        }
        // smaller isbn go left, larger isbn go right
        return current;
    }

    public Book search(int isbn) {
        return searchRecursive(root, isbn);
    }

    private Book searchRecursive (Book current, int isbn) {
        if (current == null || current.isbn == isbn) {
            return current;
        } else if (isbn < current.isbn) {
            return searchRecursive(current.left, isbn);
        } else {
            return searchRecursive(current.right, isbn);
        }
    }
}