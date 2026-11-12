
package librarysystem;

public abstract class LibraryMaterial extends LibraryRecord {
    private String id;
    private String title;
    private String author;
    private String publisher;
    private int availableQuantity;
    private int publicationYear;

    public LibraryMaterial(String id, String title, String author, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.availableQuantity = quantity;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getAvailableQuantity() { return availableQuantity; }
    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public int getPublicationYear() { return publicationYear; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAvailableQuantity(int quantity) {
        availableQuantity = quantity;
    }

    public abstract int getBorrowingDays();
    public abstract String getMaterialType();


    @Override
    public void displayRecord() {
        System.out.println("\n=== MATERIAL DETAILS ===");
        System.out.println("ID: " + id);
        System.out.println("Type: " + getMaterialType());
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Publisher: " + publisher);
        System.out.println("Publication Year: " + publicationYear);
        System.out.println("Available Copies: " + availableQuantity);
        System.out.println("Borrowing Days: " + getBorrowingDays());
    }
}
