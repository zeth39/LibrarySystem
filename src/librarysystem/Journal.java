package librarysystem;


public class Journal extends LibraryMaterial {

    // ENCAPSULATION: Private attributes
    private String author;
    private String editor;
    private int volume;
    private int issue;
    private String publicationDate;

    // Constructor
    public Journal(String id, String title, String author,
                String editor, int volume, int issue,
                String publicationDate, int availableQuantity) {

        super(id, title, availableQuantity);

        this.author = author;
        this.editor = editor;
        this.volume = volume;
        this.issue = issue;
        this.publicationDate = publicationDate;
    }

    // GETTERS
    public String getAuthor() {
        return author;
    }

    public String getEditor() {
        return editor;
    }

    public int getVolume() {
        return volume;
    }

    public int getIssue() {
        return issue;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    // SETTERS
    public void setAuthor(String author) {
        this.author = author;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public void setVolume(int volume) {
        if (volume > 0) {
            this.volume = volume;
        }
    }

    public void setIssue(int issue) {
        if (issue > 0) {
            this.issue = issue;
        }
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    // ABSTRACTION: Journals have a 3-day borrowing period
    @Override
    public int getBorrowingDays() {
        return 3;
    }

    // POLYMORPHISM: Journal's own display implementation
    @Override
    public void displayRecord() {
        System.out.println("=== JOURNAL INFORMATION ===");
        System.out.println("ID: " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + author);
        System.out.println("Editor: " + editor);
        System.out.println("Volume: " + volume);
        System.out.println("Issue: " + issue);
        System.out.println("Publication Date: " + publicationDate);
        System.out.println("Available Copies: "
                + getAvailableQuantity());
    }
}
