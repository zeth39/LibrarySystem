
package librarysystem;

import java.util.ArrayList;

public class BorrowerManagement {

    static ArrayList<Borrower> borrowers = new ArrayList<>();

    public static void menu() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n=== BORROWERS MANAGEMENT ===");
            System.out.println("1. Add Borrower");
            System.out.println("2. Edit Borrower");
            System.out.println("3. Delete Borrower");
            System.out.println("4. View Borrower");
            System.out.println("5. View All Borrowers");
            System.out.println("0. Back to Admin Menu");

            choice = readNumber("Enter your choice: ");

            if (choice == 1) {
                addBorrower();
            } else if (choice == 2) {
                editBorrower();
            } else if (choice == 3) {
                deleteBorrower();
            } else if (choice == 4) {
                viewBorrower();
            } else if (choice == 5) {
                viewAllBorrowers();
            } else if (choice != 0) {
                System.out.println("Invalid choice!");
            }
        }
    }

    public static String readText(String message) {
        System.out.print(message);
        return Main.input.nextLine().trim();
    }

    public static String requiredText(String message) {
        String value = "";

        while (value.isEmpty()) {
            value = readText(message);

            if (value.isEmpty()) {
                System.out.println("This field is required!");
            }
        }

        return value;
    }

    public static int readNumber(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(
                        Main.input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    public static Borrower findBorrower(String id) {
        for (Borrower borrower : borrowers) {
            if (borrower.getId().equalsIgnoreCase(id)) {
                return borrower;
            }
        }

        return null;
    }

    public static boolean duplicateName(
            String first, String middle,
            String last, String excludedId) {

        for (Borrower borrower : borrowers) {
            if (!borrower.getId().equalsIgnoreCase(excludedId)
                    && borrower.getFirstName().equalsIgnoreCase(first)
                    && borrower.getMiddleName().equalsIgnoreCase(middle)
                    && borrower.getLastName().equalsIgnoreCase(last)) {
                return true;
            }
        }

        return false;
    }

    public static int readViolations() {
        int violations = -1;

        while (violations < 0) {
            violations = readNumber("Number of violations: ");

            if (violations < 0) {
                System.out.println(
                        "Violations cannot be negative!");
            }
        }

        return violations;
    }

    public static void addBorrower() {
        System.out.println("\n=== ADD BORROWER ===");

        String id = requiredText("Borrower ID: ");

        if (findBorrower(id) != null) {
            System.out.println("Borrower ID already exists!");
            return;
        }

        String first = requiredText("First Name: ");
        String middle = readText("Middle Name: ");
        String last = requiredText("Last Name: ");

        if (duplicateName(first, middle, last, "")) {
            System.out.println(
                    "Borrower is already registered!");
            return;
        }

        String gender = requiredText("Gender: ");
        String birthday = requiredText("Birthday (YYYY-MM-DD): ");
        String contact = requiredText("Contact Number: ");
        String email = requiredText("Email: ");
        String address = requiredText("Address: ");
        int violations = readViolations();

        Borrower borrower = new Borrower(
                id, first, middle, last,
                gender, birthday, contact,
                email, address, violations);

        borrowers.add(borrower);

        System.out.println("Borrower added successfully!");
    }

    public static void editBorrower() {
        System.out.println("\n=== EDIT BORROWER ===");

        String id = requiredText("Enter Borrower ID: ");
        Borrower borrower = findBorrower(id);

        if (borrower == null) {
            System.out.println("Borrower not found!");
            return;
        }

        borrower.displayDetails();

        System.out.println("\nEnter new information:");

        String first = requiredText("First Name: ");
        String middle = readText("Middle Name: ");
        String last = requiredText("Last Name: ");

        if (duplicateName(first, middle, last, id)) {
            System.out.println(
                    "Another borrower has this name!");
            return;
        }

        String gender = requiredText("Gender: ");
        String birthday = requiredText("Birthday (YYYY-MM-DD): ");
        String contact = requiredText("Contact Number: ");
        String email = requiredText("Email: ");
        String address = requiredText("Address: ");
        int violations = readViolations();

        borrower.setFirstName(first);
        borrower.setMiddleName(middle);
        borrower.setLastName(last);
        borrower.setGender(gender);
        borrower.setBirthday(birthday);
        borrower.setContactNumber(contact);
        borrower.setEmail(email);
        borrower.setAddress(address);
        borrower.setViolations(violations);

        System.out.println("Borrower updated successfully!");
    }

    public static void deleteBorrower() {
        System.out.println("\n=== DELETE BORROWER ===");

        String id = requiredText("Enter Borrower ID: ");
        Borrower borrower = findBorrower(id);

        if (borrower == null) {
            System.out.println("Borrower not found!");
            return;
        }

        borrower.displayDetails();

        String confirm = readText(
                "Are you sure you want to delete? (Y/N): ");

        if (confirm.equalsIgnoreCase("Y")) {
            borrowers.remove(borrower);
            System.out.println("Borrower deleted successfully!");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    public static void viewBorrower() {
        System.out.println("\n=== VIEW BORROWER ===");

        String id = requiredText("Enter Borrower ID: ");
        Borrower borrower = findBorrower(id);

        if (borrower == null) {
            System.out.println("Borrower not found!");
        } else {
            borrower.displayDetails();
        }
    }

    public static void viewAllBorrowers() {
        System.out.println("\n=== ALL BORROWERS ===");

        if (borrowers.isEmpty()) {
            System.out.println("No registered borrowers.");
            return;
        }

        for (Borrower borrower : borrowers) {
            borrower.displayDetails();
            System.out.println("----------------------");
        }

        System.out.println(
                "Total Borrowers: " + borrowers.size());
    }
}
