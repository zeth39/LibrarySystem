package librarysystem;

//additional imports
import java.time.LocalDate;
import java.time.LocalTime;

//necessary imports
import java.util.List;
import java.util.ArrayList;

//FILE HANDLING imports
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


/*
LIBRARY CATALOGUE SYSTEM
- showCatalog() - displays all books in the catalog
- findBook() - searches for a book by ID and returns the book object if found
- searchBook() - allows users to search for a book by ID and displays its information
- readText() - reads a string input from the user
- readNumber() - reads an integer input from the user with error handling for non-integer inputs
*/

public class LibrarySystem {
    private static final List<LibraryMaterial> books = new ArrayList<>();
    private static final List<LibraryRecord> records = new ArrayList<>();
    private static final Path BOOKS_FILE = Path.of("books.txt");

    private static final List<Loan> loans = new ArrayList<>();
    private static final Path LOANS_FILE = Path.of("loans.txt");
    

public static void showCatalog() {
    if (books.size() == 0) {
        System.out.println("No books in the catalog.");
    } else {
        System.out.println("\n=== BOOK CATALOG ===");
        // ABUNDO PART - Collection Framework - For-loop visits each book in the list.
        for (LibraryMaterial book : books) {
            book.displayRecord();
        }
    }
}

public static LibraryMaterial findBook(String id) {
    for (LibraryMaterial book : books) {
        if (book.getId().equalsIgnoreCase(id)) {
            return book;
        }
    }
    return null;
}


public static LibraryMaterial findMaterial(String id) {

    for (int i = 0; i < books.size(); i++) {

        LibraryMaterial material = books.get(i);

        if (material.getId().equalsIgnoreCase(id)) {
            return material;
        }
    }

    return null;
}

public static boolean materialExists(String id) {
    return findMaterial(id) != null;
}


public static boolean registerMaterial(LibraryMaterial material) {

    if (material == null) {
        return false;
    }

    if (materialExists(material.getId())) {
        System.out.println("Material ID already exists.");
        return false;
    }

    books.add(material);
    records.add(material);
    saveBooks();

    System.out.println("Material added successfully.");
    return true;
}



public static void searchBook() {
    LibraryMaterial book = findBook(readText("Book ID: "));
    if (book == null) {
        System.out.println("Book not found.");
    } else {
        book.displayRecord();
    }
}

public static String readText(String message) {
    System.out.print(message);
    return Main.input.nextLine().trim();
}


public static String readRequiredText(String message) {
    String value = "";

    while (value.isEmpty() || value.contains("|")) {
        value = readText(message);
        if (value.isEmpty()) {
            System.out.println("This field cannot be empty.");
        } else if (value.contains("|")) {
            System.out.println("The | character is not allowed.");
        }
    }
    return value;
}


public static int readNumber(String message) {
    while (true) {
        try {
            return Integer.parseInt(readText(message));
        // ABUNDO PART - Exception handling to reject non-integer inputs and repeat.
        } catch (NumberFormatException error) {
            System.out.println("Please enter a valid number.");
        }
    }
}


public static void addBook() {

    System.out.println("\n=== ADD LIBRARY MATERIAL ===");
    System.out.println("1. Book (7 days)");
    System.out.println("2. Journal (3 days)");
    System.out.println("3. Magazine (Same day)");
    System.out.println("4. Thesis Book (2 days)");

    int type = readNumber("Choose material type: ");

    if (type < 1 || type > 4) {
        System.out.println("Invalid material type.");
        return;
    }

    String id = readRequiredText("Material ID: ");
    String title = readRequiredText("Title: ");
    String author = readRequiredText("Author: ");
    String publisher = readRequiredText("Publisher: ");
    int publicationYear = readNumber("Publication Year: ");
    int quantity = readNumber("Quantity: ");

    if (id.isEmpty() || title.isEmpty()
            || author.isEmpty() || publisher.isEmpty()
            || publicationYear <= 0
            || publicationYear > LocalDate.now().getYear()
            || quantity <= 0) {

        System.out.println("Invalid material information.");
        return;
    }

    if (findBook(id) != null) {
        System.out.println("Material ID already exists.");
        return;
    }

    LibraryMaterial book;

    if (type == 1) {
        book = new Book(id, title, author, quantity);
    } else if (type == 2) {
        book = new Journal(id, title, author, quantity);
    } else if (type == 3) {
        book = new Magazine(id, title, author, quantity);
    } else {
        book = new ThesisBook(id, title, author, quantity);
    }

    book.setPublisher(publisher);
    book.setPublicationYear(publicationYear);

    books.add(book);
    records.add(book);
    saveBooks();

    System.out.println("Material added successfully!");
}


public static void editMaterial() {

    System.out.println("\n=== EDIT LIBRARY MATERIAL ===");

    String id = readText("Enter Material ID: ");
    LibraryMaterial material = findBook(id);

    if (material == null) {
        System.out.println("Material not found.");
        return;
    }

    int choice = -1;

    while (choice != 0) {

        System.out.println("\n=== CURRENT MATERIAL ===");
        material.displayRecord();

        System.out.println("\nWhat would you like to edit?");
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.println("3. Publisher");
        System.out.println("4. Publication Year");
        System.out.println("5. Available Quantity");
        System.out.println("0. Finish Editing");

        choice = readNumber("Enter your choice: ");

        if (choice == 1) {

            String title = readText("New Title: ");

            if (title.isEmpty() || title.contains("|")) {
                System.out.println("Invalid title.");
            } else {
                material.setTitle(title);
                saveBooks();
                System.out.println("Title updated!");
            }

        } else if (choice == 2) {

            String author = readText("New Author: ");

            if (author.isEmpty() || author.contains("|")) {
                System.out.println("Invalid author.");
            } else {
                material.setAuthor(author);
                saveBooks();
                System.out.println("Author updated!");
            }

        } else if (choice == 3) {

            String publisher = readText("New Publisher: ");

            if (publisher.isEmpty() || publisher.contains("|")) {
                System.out.println("Invalid publisher.");
            } else {
                material.setPublisher(publisher);
                saveBooks();
                System.out.println("Publisher updated!");
            }

        } else if (choice == 4) {

            int year = readNumber("New Publication Year: ");

            if (year <= 0 || year > LocalDate.now().getYear()) {
                System.out.println("Invalid publication year.");
            } else {
                material.setPublicationYear(year);
                saveBooks();
                System.out.println("Publication year updated!");
            }

        } else if (choice == 5) {

            int quantity = readNumber("New Available Quantity: ");

            if (quantity < 0) {
                System.out.println("Quantity cannot be negative.");
            } else {
                material.setAvailableQuantity(quantity);
                saveBooks();
                System.out.println("Available quantity updated!");
            }

        } else if (choice != 0) {

            System.out.println("Invalid menu choice.");
        }
    }

    System.out.println("Finished editing material.");
}



//RAGUDO PART - Write book records to Book.txt

public static void saveBooks() {
    List<String> lines = new ArrayList<>();

    for (LibraryMaterial book : books) {
        lines.add(book.getId() + "|"
            + book.getTitle() + "|"
            + book.getAuthor() + "|"
            + book.getAvailableQuantity() + "|"
            + book.getMaterialType() + "|"
            + book.getPublisher() + "|"
            + book.getPublicationYear());
    }

    try {
        Files.write(BOOKS_FILE, lines, StandardCharsets.UTF_8);
    } catch (IOException error) {
        System.out.println("Could not save books.txt: "
                + error.getMessage());
    }
}


/* FILE HANDLING: READ BOOK RECORDS FROM BOOKS.TXT */

public static void loadBooks() {
    if (!Files.exists(BOOKS_FILE)) {
        return;
    }

    books.clear();

    try {
        for (String line : Files.readAllLines(
                BOOKS_FILE, StandardCharsets.UTF_8)) {

            try {
                String[] fields = line.split("\\|", -1);

                if (fields.length != 4 && fields.length != 5 && fields.length != 7) {
                    System.out.println("Skipping invalid record.");
                    continue;
                }

                String id = fields[0];
                String title = fields[1];
                String author = fields[2];
                int quantity = Integer.parseInt(fields[3]);

                String type = "Book";

                if (fields.length >= 5) {
                    type = fields[4];
                }

                LibraryMaterial book;

                if (type.equalsIgnoreCase("Journal")) {
                    book = new Journal(id, title, author, quantity);
                } else if (type.equalsIgnoreCase("Magazine")) {
                    book = new Magazine(id, title, author, quantity);
                } else if (type.equalsIgnoreCase("ThesisBook")) {
                    book = new ThesisBook(id, title, author, quantity);
                } else if (type.equalsIgnoreCase("Book")) {
                    book = new Book(id, title, author, quantity);
                } else {
                    System.out.println("Unknown material type: " + type);
                    continue;
                }

                if (fields.length == 7) {
                    book.setPublisher(fields[5]);
                    book.setPublicationYear(
                            Integer.parseInt(fields[6]));
                } else {
                    book.setPublisher("Not specified");
                    book.setPublicationYear(0);
                }

                books.add(book);

            } catch (RuntimeException error) {
                System.out.println("Skipping invalid book record.");
            }
        }

    } catch (IOException error) {
        System.out.println("Could not read books.txt: "
                + error.getMessage());
    }
}

public static void deleteBook() {

    System.out.println("\n=== DELETE LIBRARY MATERIAL ===");

    String id = readText("Material ID: ");

    LibraryMaterial book = findBook(id);

    if (book == null) {
        System.out.println("Material not found.");
        return;
    }

    // Prevent deletion if material is currently borrowed
    if (hasActiveMaterialLoan(id)) {
        System.out.println("\n=== DELETION DENIED ===");
        System.out.println("This material is currently borrowed.");
        System.out.println("Wait until all copies are returned.");
        return;
    }

    // Show material information before deleting
    book.displayRecord();

    String confirm = readText(
            "Are you sure you want to delete this material? (Y/N): ");

    if (confirm.equalsIgnoreCase("Y")) {

        books.remove(book);
        records.remove(book);

        saveBooks();

        System.out.println("Material deleted successfully!");

    } else {
        System.out.println("Deletion cancelled.");
    }
}

public static Loan findLoan(String id, String borrowerId) {

    for (Loan loan : loans) {
        if (loan.getBookId().equalsIgnoreCase(id)
                && loan.getBorrowerId().equalsIgnoreCase(borrowerId)
                && loan.getReturnDate() == null) {
            return loan;
        }
    }

    return null;
}

public static boolean hasActiveMaterialLoan(String materialId) {

    for (Loan loan : loans) {

        if (loan.getBookId().equalsIgnoreCase(materialId)
                && loan.getReturnDate() == null) {

            return true;
        }
    }

    return false;
}


public static boolean hasActiveLoan(String borrowerId) {

    for (Loan loan : loans) {
        if (loan.getBorrowerId().equalsIgnoreCase(borrowerId)
                && loan.getReturnDate() == null) {

            return true;
        }
    }

    return false;
}


public static void borrowBook(String borrowerId) {

    if (!BorrowerManagement.isRegistered(borrowerId)) {
        System.out.println("Borrower is not registered!");
        System.out.println("Please register first.");
        return;
    }

    if (BorrowerManagement.hasThreeStrikes(borrowerId)) {
        System.out.println("\n=== BORROWING DENIED ===");
        System.out.println("Borrower has reached 3 or more violations.");
        System.out.println("Borrowing privileges are suspended.");
        return;
    }

    if (hasActiveLoan(borrowerId)) {
        System.out.println("Borrowing denied!");
        System.out.println("You already have an active loan.");
        System.out.println("Please return your borrowed material first.");
        return;
    }


    LibraryMaterial book = findBook(
            readText("Material ID: "));

    if (book == null) {
        System.out.println("Material not found.");
        return;
    }

    if (book.getAvailableQuantity() <= 0) {
        System.out.println("No copies are available.");
        return;
    }

    LocalDate borrowDate = LocalDate.now();
    LocalTime borrowTime = LocalTime.now();
    LocalDate dueDate = calculateDueDate(book, borrowDate);

    book.setAvailableQuantity(
            book.getAvailableQuantity() - 1);

    Loan loan = new Loan(
            book.getId(),
            borrowerId,
            borrowDate,
            borrowTime,
            dueDate
    );

    loans.add(loan);
    records.add(loan);

    saveBooks();
    saveLoans();

    System.out.println("Material borrowed successfully!");
    System.out.println("Borrower ID: " + borrowerId);
    System.out.println("Due date: " + dueDate);
}


public static void returnBook(String borrowerId) {

    String bookId = readText("Material ID: ");
    Loan loan = findLoan(bookId, borrowerId);

    if (loan == null) {
        System.out.println("Active loan not found.");
        return;
    }

    LocalDate returnDate = LocalDate.now();
    LocalTime returnTime = LocalTime.now();

    loan.setReturnDate(returnDate);
    loan.setReturnTime(returnTime);

    LibraryMaterial book = findBook(bookId);

    if (book != null) {
        book.setAvailableQuantity(
                book.getAvailableQuantity() + 1);
    }

    saveBooks();
    saveLoans();


    if (returnDate.isAfter(loan.getDueDate())) {

        System.out.println("Material returned late!");

        BorrowerManagement.addViolation(borrowerId);

    } else {
        System.out.println("Material returned on time.");
    }

}


//RAGUDO - Write loan records to text file
public static void saveLoans() {
    List<String> lines = new ArrayList<>();
    for (Loan loan : loans) {
        String returnDate = loan.getReturnDate() == null
                ? "" : loan.getReturnDate().toString();
        String returnTime = loan.getReturnTime() == null
                ? "" : loan.getReturnTime().toString();

        lines.add(loan.getBookId() + "|" + loan.getBorrowerId() + "|"
                + loan.getBorrowDate() + "|" + loan.getBorrowTime() + "|"
                + loan.getDueDate() + "|" + returnDate + "|" + returnTime);
    }

    try {
        Files.write(LOANS_FILE, lines, StandardCharsets.UTF_8);
    } catch (IOException error) {
        System.out.println("Could not save loans.txt: " + error.getMessage());
    }
}


//RAGUDO PART - Read loan records from loan.txt 
public static void loadLoans() {
    loans.clear();

    if (!Files.exists(LOANS_FILE)) {
        return;
    }

    try {
        for (String line : Files.readAllLines(LOANS_FILE, StandardCharsets.UTF_8)) {
            try {
                String[] fields = line.split("\\|", -1);
                if (fields.length != 7) {
                    System.out.println("Skipping invalid loan record: " + line);
                    continue;
                }

                Loan loan = new Loan(
                    fields[0],
                    fields[1],
                    LocalDate.parse(fields[2]),
                    LocalTime.parse(fields[3]),
                    LocalDate.parse(fields[4])
                );

                if (!fields[5].isEmpty()) {
                    loan.setReturnDate(LocalDate.parse(fields[5]));
                }
                if (!fields[6].isEmpty()) {
                    loan.setReturnTime(LocalTime.parse(fields[6]));
                }

                loans.add(loan);
            } catch (RuntimeException error) {
                System.out.println("Skipping invalid loan record: " + line);
            }
        }
    } catch (IOException error) {
        System.out.println("Could not read loans.txt: " + error.getMessage());
    }
}

//RAGUDO PART :  Load both files and rebuild record list
public static void loadData() {
    loadBooks();
    loadLoans();

    records.clear();
    records.addAll(books);
    records.addAll(loans);
}


public static void showAllMaterials() {

    if (books.size() == 0) {
        System.out.println("No library materials available.");
        return;
    }

    System.out.println("\n=== LIBRARY MATERIALS ===");

    for (LibraryMaterial material : books) {
        material.displayRecord();
        System.out.println();
    }
}



public static void showAllHistory() {
    if (loans.size() == 0) {
        System.out.println("No loan history.");
    } else {
        //ABUNDO PART - Collection Framework: iterate directly through the loan list.
        for (Loan loan : loans) {
            loan.displayRecord();
        }
    }
}

public static void showMaterialHistory() {
    System.out.println("\n=== MATERIAL BORROWING HISTORY ===");
    String materialId = readText("Enter Material ID: ");
    boolean found = false;
    int count = 0;

    for (Loan loan : loans) {

        if (loan.getBookId().equalsIgnoreCase(materialId)) {

            loan.displayRecord();

            System.out.println("--------------------------");

            found = true;
            count++;
        }
    }
    if (!found) {
        System.out.println("No borrowing history found for this material.");
    } else {
        System.out.println("Total transactions: " + count);
    }
}

public static void showBorrowerHistory(String borrowerId) {

    boolean found = false;

    for (Loan loan : loans) {
        if (loan.getBorrowerId().equalsIgnoreCase(borrowerId)) {
            loan.displayRecord();
            found = true;
        }
    }

    if (!found) {
        System.out.println("No borrower history.");
    }
}


public static void showAllRecords() {
    // ABUNDO - Collection Framework: iterate through the combined record list.
    for (LibraryRecord record : records) {
        record.displayRecord();
    }
}


public static LocalDate calculateDueDate(
        LibraryMaterial material, LocalDate borrowDate) {

    return borrowDate.plusDays(material.getBorrowingDays());
}


}