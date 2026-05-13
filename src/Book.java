public class Book {
    int isbn;
    String title;
    String author;
    Book left;
    Book right;
    
    public Book(int isbn, String title, String author){
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.left = null;
        this.right = null;
    }
}