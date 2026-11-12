package librarysystem;


public class Magazine extends LibraryMaterial {

    // ENCAPSULATION: Private attributes
    private String publisher;
    private int issue;
    private String publicationDate;

    // Constructor
    public Magazine(String id, String title, String publisher,
                    int issue, String publicationDate,
                    int availableQuantity) {

        super(id, title, availableQuantity);

        this.publisher = publisher;
        this.issue = issue;
        this.publicationDate = publicationDate;
    }

    // GETTERS
    public String getPublisher() {
        return publisher;
    }

    public int getIssue() {
        return issue;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    // SETTERS
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setIssue(int issue) {
        if (issue > 0) {
            this.issue = issue;
        }
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    // ABSTRACTION: Magazines must be returned on the same day
    @Override
    public int getBorrowingDays() {
        return 0;
    }

    @Override
    public void displayRecord() {
        System.out.println("=== MAGAZINE INFORMATION ===");
        System.out.println("ID: " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Publisher: " + publisher);
        System.out.println("Issue: " + issue);
        System.out.println("Publication Date: " + publicationDate);
        System.out.println("Available Copies: "
                + getAvailableQuantity());
    }
}

