package librarysystem;

public class Magazine extends LibraryMaterial {
    public Magazine(String id, String title, String author, int quantity) {
        super(id, title, author, quantity);
    }

    @Override
    public int getBorrowingDays() {
        return 0;
    }

    @Override
    public String getMaterialType() {
        return "Magazine";
    }
}