package librarysystem;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BorrowerManagement {

    static ArrayList<Borrower> borrowers = new ArrayList<>();
    private static final Path BORROWERS_FILE =
        Path.of("borrowers.txt");

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

    public static String readBorrowerName(String message, boolean required) {
        while (true) {
            String name = readText(message);

            if (name.isEmpty()) {
                if (!required) {
                    return "";
                }
                System.out.println("This field cannot be empty.");
                continue;
            }

            if (name.contains("|")) {
                System.out.println("The | character is not allowed.");
                continue;
            }

            boolean valid = true;
            for (int i = 0; i < name.length(); i++) {
                char c = name.charAt(i);
                if (!Character.isLetter(c) && c != ' ' && c != '-' && c != '\'') {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                return name;
            } else {
                System.out.println("Invalid name! Only letters, spaces, hyphens, and apostrophes are allowed.");
            }
        }
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


    public static void addViolation(String borrowerId) {

        Borrower borrower = findBorrower(borrowerId);

        if (borrower == null) {
            System.out.println("Borrower not found.");
            return;
        }

        int violations = borrower.getViolations();

        borrower.setViolations(violations + 1);

        saveBorrowers();

        System.out.println("One violation added!");
        System.out.println("Total violations: "
                + borrower.getViolations());
    }


    public static boolean hasThreeStrikes(String borrowerId) {

        Borrower borrower = findBorrower(borrowerId);

        if (borrower == null) {
            return false;
        }

        if (borrower.getViolations() >= 3) {
            return true;
        }

        return false;
    }

    public static boolean isRegistered(String borrowerId) {

        Borrower borrower = findBorrower(borrowerId);

        if (borrower != null) {
            return true;
        } else {
            return false;
        }
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



    public static String readEmail() {

        while (true) {

            String email = requiredText("Email: ");

            int at = email.indexOf("@");
            int dot = email.lastIndexOf(".");

            if (at > 0 && dot > at + 1
                    && dot < email.length() - 1
                    && !email.contains(" ")) {

                return email;
            }
            System.out.println("Invalid email address.");
        }
    }


    public static String readBirthday() {

        while (true) {

            String birthday = requiredText(
                    "Birthday (YYYY-MM-DD): ");

            try {
                LocalDate date = LocalDate.parse(birthday);

                if (date.isAfter(LocalDate.now())) {
                    System.out.println(
                            "Birthday cannot be in the future.");
                } else {
                    return birthday;
                }
            } catch (DateTimeParseException error) {
                System.out.println(
                        "Invalid date. Use YYYY-MM-DD.");
            }
        }
    }


    public static String readContactNumber() {

        while (true) {

            String contact = requiredText("Contact Number: ");

            if (contact.length() == 11
                    && contact.startsWith("09")) {

                boolean valid = true;

                for (int i = 0; i < contact.length(); i++) {

                    if (!Character.isDigit(contact.charAt(i))) {
                        valid = false;
                        break;
                    }
                }

                if (valid) {
                    return contact;
                }
            }

            System.out.println(
                    "Enter a valid 11-digit mobile number starting with 09.");
        }
    }



    public static void addBorrower() {
        System.out.println("\n=== ADD BORROWER ===");

        String id = requiredText("Borrower ID: ");

        if (findBorrower(id) != null) {
            System.out.println("Borrower ID already exists!");
            return;
        }

        String first = readBorrowerName("First Name: ", true);
        String middle = readBorrowerName("Middle Name: ", false);
        String last = readBorrowerName("Last Name: ", true);

        if (duplicateName(first, middle, last, "")) {
            System.out.println(
                    "Borrower is already registered!");
            return;
        }

        String gender = requiredText("Gender: ");
        String birthday = readBirthday();
        String contact = readContactNumber();
        String email = readEmail();
        String address = requiredText("Address: ");
        int violations = readViolations();

        Borrower borrower = new Borrower(
                id, first, middle, last,
                gender, birthday, contact,
                email, address, violations);

        borrowers.add(borrower);
        saveBorrowers();

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

        String first = readBorrowerName("First Name: ", true);
        String middle = readBorrowerName("Middle Name: ", false);
        String last = readBorrowerName("Last Name: ", true);

        if (duplicateName(first, middle, last, id)) {
            System.out.println(
                    "Another borrower has this name!");
            return;
        }

        String gender = requiredText("Gender: ");
        String birthday = readBirthday();
        String contact = readContactNumber();
        String email = readEmail();
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
        saveBorrowers();

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
        
        if (LibrarySystem.hasActiveLoan(id)) {
            System.out.println("\n=== DELETION DENIED ===");
            System.out.println("This borrower has active loans and cannot be deleted.");
            return;
        }

        borrower.displayDetails();

        String confirm = readText(
                "Are you sure you want to delete? (Y/N): ");

        if (confirm.equalsIgnoreCase("Y")) {
            borrowers.remove(borrower);
            saveBorrowers();
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


public static void saveBorrowers() {

    List<String> lines = new ArrayList<>();

    for (Borrower borrower : borrowers) {
        String line = borrower.getId() + "|"
                + borrower.getFirstName() + "|"
                + borrower.getMiddleName() + "|"
                + borrower.getLastName() + "|"
                + borrower.getGender() + "|"
                + borrower.getBirthday() + "|"
                + borrower.getContactNumber() + "|"
                + borrower.getEmail() + "|"
                + borrower.getAddress() + "|"
                + borrower.getViolations();

        lines.add(line);
    }

    try {
        Files.write(BORROWERS_FILE, lines,
                StandardCharsets.UTF_8);

    } catch (IOException error) {
        System.out.println("Could not save borrowers.txt: "
                + error.getMessage());
    }
}


public static void loadBorrowers() {

    if (!Files.exists(BORROWERS_FILE)) {
        return;
    }

    try {
        List<String> lines = Files.readAllLines(
                BORROWERS_FILE, StandardCharsets.UTF_8);

        ArrayList<Borrower> loadedBorrowers =
                new ArrayList<>();

        for (String line : lines) {

            String[] fields = line.split("\\|", -1);

            if (fields.length != 10) {
                System.out.println("Invalid borrower record.");
                return;
            }

            try {
                int violations = Integer.parseInt(fields[9]);

                Borrower borrower = new Borrower(
                        fields[0],
                        fields[1],
                        fields[2],
                        fields[3],
                        fields[4],
                        fields[5],
                        fields[6],
                        fields[7],
                        fields[8],
                        violations
                );

                loadedBorrowers.add(borrower);

            } catch (NumberFormatException error) {
                System.out.println("Invalid violation count.");
                return;
            }
        }

        borrowers.clear();
        borrowers.addAll(loadedBorrowers);

    } catch (IOException error) {
        System.out.println("Could not load borrowers.txt: "
                + error.getMessage());
    }
}

}