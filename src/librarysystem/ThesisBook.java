package librarysystem;

public class ThesisBook extends LibraryMaterial {
    public ThesisBook(String id, String title, String author, int quantity) {
        super(id, title, author, quantity);
    }

    @Override
    public int getBorrowingDays() {
        return 2;
    }

    @Override
    public String getMaterialType() {
        return "ThesisBook";
    }
}