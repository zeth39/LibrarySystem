
package librarysystem;

public abstract class LibraryMaterial extends LibraryRecord {
    private String id;
    private String title;
    private String author;
    private int availableQuantity;

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

    public void setAvailableQuantity(int quantity) {
        availableQuantity = quantity;
    }

    public abstract int getBorrowingDays();
    public abstract String getMaterialType();

    @Override
    public void displayRecord() {
        System.out.println("ID: " + id + " | Type: " + getMaterialType()
                + " | Title: " + title + " | Author: " + author
                + " | Available: " + availableQuantity
                + " | Borrowing days: " + getBorrowingDays());
    }
}
