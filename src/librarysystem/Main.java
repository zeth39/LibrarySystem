package librarysystem;
//import java.time.LocalDate;
//import java.time.LocalTime;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);
    public static void main(String[] args){

        LibrarySystem.loadData();
        BorrowerManagement.loadBorrowers();

        System.out.println("---===== Welcome to the JK.YS Library System =====---");
        System.out.println("Developed by Team J.K.Y.S Tech");
        
        //Priviledge
        Information activeUser = TypeMenu.call();
        
        if (activeUser.privilege == 3){
            adminMenu();
        } else if (activeUser.privilege == 2){
            studentMenu(activeUser.name);
        } else {
            guestMenu();
        }

    }

public static void adminMenu() {
    int choice = -1;

    while (choice != 0) {
        System.out.println("\n=== ADMIN MENU ===");
        System.out.println("1. Borrowers management");
        System.out.println("2. Asset management");
        System.out.println("3. Book history");
        System.out.println("4. Book details");
        System.out.println("5. Show book catalog");
        System.out.println("6. Show all library materials");
        System.out.println("7. Material borrowing history");
        System.out.println("0. Exit");

        choice = LibrarySystem.readNumber("Enter your choice: ");

        if (choice == 1) {
            BorrowerManagement.menu();
        } else if (choice == 2) {
            bookRecordsMenu();
        } else if (choice == 3) {
            LibrarySystem.showAllHistory();
        } else if (choice == 4) {
            LibrarySystem.searchBook();
        } else if (choice == 5) {
            LibrarySystem.showCatalog();
        } else if (choice == 6) {
            LibrarySystem.showAllMaterials();
        } else if (choice == 7) {
            LibrarySystem.showMaterialHistory();
        } else if (choice != 0) {
            System.out.println("Invalid menu choice! Select the available only");
        }
    }

    exitMessage();
} 
public static void studentMenu(String borrowerName) {
    int choice = -1;

    System.out.println("\n=== STUDENT VERIFICATION ===");
    System.out.println("Logged in as: " + borrowerName);
    String borrowerId = LibrarySystem.readText("Enter your assigned Borrower ID to confirm identity: ");

    Borrower borrower = BorrowerManagement.findBorrower(borrowerId);
    if (borrower == null) {
        System.out.println("Borrower ID not found! Access denied.");
        return;
    }
    String registeredFullName = (borrower.getFirstName() + " " + borrower.getLastName()).trim();
    if (!registeredFullName.equalsIgnoreCase(borrowerName) && !borrower.getFirstName().equalsIgnoreCase(borrowerName)) {
        System.out.println("Security Warning: The Borrower ID entered does not match your login name!");
        return;
    }

    System.out.println("Identity verified successfully!");

    while (choice != 0) {
        System.out.println("\n=== STUDENT MENU ===");
        System.out.println("1. Borrow book");
        System.out.println("2. Return book");
        System.out.println("3. Show book catalog");
        System.out.println("4. Borrower history");
        System.out.println("5. Show all library materials");
        System.out.println("0. Exit");

        choice = LibrarySystem.readNumber("Enter your choice: ");

        if (choice == 1) {
            LibrarySystem.borrowBook(borrowerId);
        } else if (choice == 2) {
            LibrarySystem.returnBook(borrowerId);
        } else if (choice == 3) {
            LibrarySystem.showCatalog();
        } else if (choice == 4) {
            LibrarySystem.showBorrowerHistory(borrowerId);
        } else if (choice == 5) {
            LibrarySystem.showAllMaterials();
        } else if (choice != 0) {
            System.out.println("Invalid menu choice! Select the available only");
        }
    }

    exitMessage();
}

public static void guestMenu() {
    int choice = -1;

    while (choice != 0) {
        System.out.println("\n=== GUEST MENU ===");
        System.out.println("1. Show book catalog");
        System.out.println("0. Exit");

        choice = LibrarySystem.readNumber("Enter your choice: ");

        if (choice == 1) {
            LibrarySystem.showCatalog();
        } else if (choice != 0) {
            System.out.println("Invalid menu choice! Select the available only");
        }
    }
    exitMessage();
}

public static void bookRecordsMenu() {
    int choice = -1;

    while (choice != 0) {
        System.out.println("\n=== BOOK RECORDS ===");
        System.out.println("1. Add book");
        System.out.println("2. Search book");
        System.out.println("3. Delete book");
        System.out.println("4. Show book list");
        System.out.println("5. Borrow book");
        System.out.println("6. Return book");
        System.out.println("7. Edit Material");
        System.out.println("0. Back to main menu");

        choice = LibrarySystem.readNumber("Enter your choice: ");

        if (choice == 1) {
            LibrarySystem.addBook();
        } else if (choice == 2) {
            LibrarySystem.searchBook();
        } else if (choice == 3) {
            LibrarySystem.deleteBook();
        } else if (choice == 4) {
            LibrarySystem.showCatalog();
        } else if (choice == 5) {
            LibrarySystem.borrowBook(LibrarySystem.readText("Borrower ID: "));
        } else if (choice == 6) {
            LibrarySystem.returnBook(
                LibrarySystem.readText("Borrower ID: "));
        } else if (choice == 7) {
            LibrarySystem.editMaterial();
        } else if (choice != 0) {
            System.out.println("Invalid menu choice! Select the available only");
        }
    }
}

public static void exitMessage() {
    System.out.println("\nThank you for using our Innovative and Aspiring JK.YS Library System!");
    System.out.println("Developed by Team J.K.Y.S Tech");
    System.out.println("Leader: Kurt Angelo C. Rodulfo");
    System.out.println("Members: Gabriel Moses C. Abundo");
    System.out.println("Seth Ashton D. Orteza");
    System.out.println("Simoun Justin C. Ragudo");
    
    System.out.println("We hope you enjoyed your experience with our system.");
    System.out.println("If you have any feedback or suggestions, please let us know.");
    System.out.println("Hope to see you again soon!");
}

}
