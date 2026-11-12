package librarysystem;


public class ThesisBook extends LibraryMaterial {

    // ENCAPSULATION: Private attributes
    private String researcher;
    private String adviser;
    private String department;
    private int publicationYear;

    // Constructor
    public ThesisBook(String id, String title,
                      String researcher, String adviser,
                      String department, int publicationYear,
                      int availableQuantity) {

        super(id, title, availableQuantity);

        this.researcher = researcher;
        this.adviser = adviser;
        this.department = department;
        this.publicationYear = publicationYear;
    }

    // GETTERS
    public String getResearcher() {
        return researcher;
    }

    public String getAdviser() {
        return adviser;
    }

    public String getDepartment() {
        return department;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    // SETTERS
    public void setResearcher(String researcher) {
        this.researcher = researcher;
    }

    public void setAdviser(String adviser) {
        this.adviser = adviser;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPublicationYear(int publicationYear) {
        if (publicationYear > 0) {
            this.publicationYear = publicationYear;
        }
    }

    // ABSTRACTION: Thesis Books have a 2-day borrowing period
    @Override
    public int getBorrowingDays() {
        return 2;
    }

    // POLYMORPHISM: ThesisBook's own display implementation
    @Override
    public void displayRecord() {
        System.out.println("=== THESIS BOOK INFORMATION ===");
        System.out.println("ID: " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Researcher: " + researcher);
        System.out.println("Adviser: " + adviser);
        System.out.println("Department: " + department);
        System.out.println("Publication Year: " + publicationYear);
        System.out.println("Available Copies: "
                + getAvailableQuantity());
    }
}

