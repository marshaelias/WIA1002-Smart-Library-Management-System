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
        } else if (isbn < current.getIsbn()) {
            current.setLeft(insertRecursive(current.getLeft(), isbn, title, author));
        } else {
            current.setRight(insertRecursive(current.getRight(), isbn, title, author));
        }
        // smaller isbn go left, larger isbn go right
        return current;
    }

    // =====================================
    //         BST SEARCH LOGIC
    // =====================================

    public Book search(int isbn) {
        return searchRecursive(root, isbn);
    }

    private Book searchRecursive (Book current, int isbn) {
        if (current == null || current.getIsbn() == isbn) {
            return current;
        } else if (isbn < current.getIsbn()) {
            return searchRecursive(current.getLeft(), isbn);
        } else {
            return searchRecursive(current.getRight(), isbn);
        }
    }

    // =====================================
    //        IN-ORDER TRAVERSAL / LISTING
    // =====================================

    public void printInOrder() {
        if (root == null) {
            System.out.println("No books in catalogue");
            return;
        }
        System.out.println("--- Catalogue (ascending ISBN) ---");
        inOrderPrint(root);
    }

    private void inOrderPrint(Book node) {
        if (node == null) return;
        inOrderPrint(node.getLeft());
        System.out.println("ISBN: " + node.getIsbn()
                + " | Title: " + node.getTitle()
                + " | Author: " + node.getAuthor()
                + " | Available: " + (node.isAvailable() ? "Yes" : "No"));
        inOrderPrint(node.getRight());
    }

    // =====================================
    //   FLEXIBLE SEARCH (title / author)
    // =====================================

    public java.util.List<Book> searchBySubstring(String query) {
        java.util.List<Book> results = new java.util.ArrayList<>();
        if (query == null || query.trim().isEmpty()) return results;
        String q = query.toLowerCase();
        traverseAndCollect(root, q, results);
        return results;
    }

    private void traverseAndCollect(Book node, String q, java.util.List<Book> results) {
        if (node == null) return;
        traverseAndCollect(node.getLeft(), q, results);
        if ((node.getTitle() != null && node.getTitle().toLowerCase().contains(q))
                || (node.getAuthor() != null && node.getAuthor().toLowerCase().contains(q))) {
            results.add(node);
        }
        traverseAndCollect(node.getRight(), q, results);
    }
}