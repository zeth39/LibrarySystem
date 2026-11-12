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
    private static int loanPeriod = 14;

    private static ArrayList<LibraryMaterial> materials =
        new ArrayList<LibraryMaterial>();
    

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

public static Book findBook(String id) {
    // ABUNDO PART - Search the books list with an enhanced for loop.
    for (LibraryMaterial book : books) {
        if (book.getId().equalsIgnoreCase(id)) {
            return (Book) book;
        }
    }
    return null;
}


public static LibraryMaterial findMaterial(String id) {

    for (int i = 0; i < materials.size(); i++) {

        LibraryMaterial material = materials.get(i);

        if (material.getId().equalsIgnoreCase(id)) {
            return material;
        }
    }

    // Also check existing books
    for (int i = 0; i < books.size(); i++) {

        LibraryMaterial book = books.get(i);

        if (book.getId().equalsIgnoreCase(id)) {
            return book;
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

    materials.add(material);

    System.out.println("Material added successfully.");
    return true;
}



public static void searchBook() {
    Book book = findBook(readText("Book ID: "));
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

public static void addBook() { //move to AdminLibrarySystem.java
    String id = readText("Book ID: ");
    String title = readText("Title: ");
    String author = readText("Author: ");
    int quantity = readNumber("Quantity: ");

    // ABUNDO PART - Input validation: a book must have a positive number of copies.
    if (id.length() == 0 || title.length() == 0 || author.length() == 0 || quantity <= 0) {
        System.out.println("Complete all required fields and enter a quantity greater than zero.");
        return;
    }
    if (findBook(id) != null) {
        System.out.println("That book ID is already in use.");
        return;
    }

    Book book = new Book(id, title, author, quantity);
    books.add(book);
    records.add(book);
    //RAGUDO PART - Save the book list to a file after adding a new book.
    saveBooks();
    System.out.println("Book added on " + LocalDate.now() + " at " + LocalTime.now());
}

//RAGUDO PART - Write book records to Book.txt
public static void saveBooks() {
    List<String> lines = new ArrayList<>();
    for (LibraryMaterial book : books) {
        lines.add(book.getId() + "|" + book.getTitle() + "|" + book.getAuthor()
                + "|" + book.getAvailableQuantity());
    }

    try {
        Files.write(BOOKS_FILE, lines, StandardCharsets.UTF_8);
    } catch (IOException error) {
        System.out.println("Could not save books.txt: " + error.getMessage());
    }
}

/* FILE HANDLING: READ BOOK RECORDS FROM BOOKS.TXT */
public static void loadBooks() {
    if (!Files.exists(BOOKS_FILE)) {
        return;
    }

    try {
        for (String line : Files.readAllLines(BOOKS_FILE, StandardCharsets.UTF_8)) {
            try {
                String[] fields = line.split("\\|", -1);
                if (fields.length != 4) {
                    System.out.println("Skipping invalid book record: " + line);
                    continue;
                }

                String id = fields[0];
                String title = fields[1];
                String author = fields[2];
                int quantity = Integer.parseInt(fields[3]);
                books.add(new Book(id, title, author, quantity));
            } catch (RuntimeException error) {
                System.out.println("Skipping invalid book record: " + line);
            }
        }
    } catch (IOException error) {
        System.out.println("Could not read books.txt: " + error.getMessage());
    }
}

public static void deleteBook() {
    Book book = findBook(readText("Book ID: "));
    if (book == null) {
        System.out.println("Book not found.");
        return;
    }

    book.displayRecord();
    if (readText("Confirm deletion? (y/n): ").equalsIgnoreCase("y")) {
        books.remove(book);
        records.remove(book);
        //RAGUDO PART : Save book list after deleting a book.
        saveBooks();
        System.out.println("Book deleted.");
    }
}

public static Loan findLoan(String id, String borrowerName) {
    // ABUNDO PART - Collection Framework: search the loans list with an enhanced for loop.
    for (Loan loan : loans) {
        if (loan.getBookId().equalsIgnoreCase(id)
                && loan.getBorrowerName().equalsIgnoreCase(borrowerName)
                && loan.getReturnDate() == null) {
            return loan;
        }
    }
    return null;
}

public static void borrowBook(String borrowerName) {
    Book book = findBook(readText("Book ID: "));
    if (book == null) {
        System.out.println("Book not found.");
        return;
    }
    if (book.getAvailableQuantity() == 0) {
        System.out.println("No copies are available.");
        return;
    }

    LocalDate borrowDate = LocalDate.now();
    LocalTime borrowTime = LocalTime.now();
    LocalDate dueDate = borrowDate.plusDays(loanPeriod);

    book.setAvailableQuantity(book.getAvailableQuantity() - 1);
    Loan loan = new Loan(book.getId(), borrowerName, borrowDate, borrowTime, dueDate);
    loans.add(loan);
    records.add(loan);

    /* FILE HANDLING: SAVE BOOK AND LOAN LISTS AFTER BORROWING */
    saveBooks();
    saveLoans();

    System.out.println("Book borrowed. Due date: " + dueDate);
    System.out.println("Loan recorded at: " + borrowTime);
}

public static void returnBook(String borrowerName) {
    String bookId = readText("Book ID: ");
    Loan loan = findLoan(bookId, borrowerName);
    if (loan == null) {
        System.out.println("Loan record not found.");
        return;
    }

    LocalDate returnDate = LocalDate.now();
    LocalTime returnTime = LocalTime.now();
    loan.setReturnDate(returnDate);
    loan.setReturnTime(returnTime);

    Book book = findBook(bookId);
    if (book != null) {
        book.setAvailableQuantity(book.getAvailableQuantity() + 1);
    }

    // RAGUDO PART: SAVE BOOK AND LOAN LISTS AFTER RETURNING
    saveBooks();
    saveLoans();

    if (returnDate.isAfter(loan.getDueDate())) {
        System.out.println("Book returned late.");
    } else {
        System.out.println("Book returned on time.");
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

        lines.add(loan.getBookId() + "|" + loan.getBorrowerName() + "|"
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

    if (materials.size() == 0 && books.size() == 0) {
        System.out.println("No library materials available.");
        return;
    }

    System.out.println("\n=== LIBRARY MATERIALS ===");

    for (int i = 0; i < books.size(); i++) {
        books.get(i).displayRecord();
        System.out.println();
    }

    for (int i = 0; i < materials.size(); i++) {
        materials.get(i).displayRecord();
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

public static void showBorrowerHistory(String borrowerName) {
    boolean found = false;
    //ABUNDO PART - Collection Framework: check each loan without using list indexes.
    for (Loan loan : loans) {
        if (loan.getBorrowerName().equalsIgnoreCase(borrowerName)) {
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