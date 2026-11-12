package librarysystem;

public class Book extends LibraryMaterial {
    public Book(String id, String title, String author, int quantity) {
        super(id, title, author, quantity);
    }

    @Override
    public int getBorrowingDays() {
        return 7;
    }

    @Override
    public String getMaterialType() {
        return "Book";
    }
}