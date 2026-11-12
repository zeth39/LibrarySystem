package librarysystem;

public abstract class LibraryMaterial extends LibraryRecord {
    
    private String id;
    private String title;
    private int availableQuantity;

    public LibraryMaterial(String id, String title, int availableQuantity) {
        this.id = id;
        this.title = title;
        this.availableQuantity = availableQuantity;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            this.title = title;
        } 
    }

    public void setAvailableQuantity(int availableQuantity) {
        if (availableQuantity >= 0) {
            this.availableQuantity = availableQuantity;
        } else {
            System.out.println("Quantity cannot be negative.");
        }
    }

    public abstract int getBorrowingDays();

    @Override 
    public abstract void displayRecord();
}
