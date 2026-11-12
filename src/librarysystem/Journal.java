package librarysystem;

public class Journal extends LibraryMaterial {
    public Journal(String id, String title, String author, int quantity) {
        super(id, title, author, quantity);
    }

    @Override
    public int getBorrowingDays() {
        return 3;
    }

    @Override
    public String getMaterialType() {
        return "Journal";
    }
}