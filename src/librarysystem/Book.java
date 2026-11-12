package librarysystem;

public class Book extends LibraryMaterial {

    // ENCAPSULATION: Private attributes
    private String author;
    private String isbn;
    private int publicationYear;

    // Constructor for existing LibrarySystem.java
    public Book(String id, String title, String author,
                int availableQuantity) {

        super(id, title, availableQuantity);
        this.author = author;
        this.isbn = "";
        this.publicationYear = 0;
    }

    // Constructor for complete book information
    public Book(String id, String title, String author,
                String isbn, int publicationYear,
                int availableQuantity) {

        super(id, title, availableQuantity);
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
    }

    // GETTERS
    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    // SETTERS
    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    // ABSTRACTION: Books can be borrowed for 7 days
    @Override
    public int getBorrowingDays() {
        return 7;
    }

    // POLYMORPHISM: Book's own display implementation
    @Override
    public void displayRecord() {
        System.out.println("=== BOOK INFORMATION ===");
        System.out.println("ID: " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Publication Year: " + publicationYear);
        System.out.println("Available Copies: "
                + getAvailableQuantity());
    }
}
