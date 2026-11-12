package librarysystem;

import java.time.LocalDate;
import java.time.LocalTime;

// Inheritance: Loan also inherits from LibraryRecord.
class Loan extends LibraryRecord {
    private String bookId;
    private String borrowerName;
    private LocalDate borrowDate;
    private LocalTime borrowTime;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private LocalTime returnTime;

    public Loan(String bookId, String borrowerName, LocalDate borrowDate,
            LocalTime borrowTime, LocalDate dueDate) {
        this.bookId = bookId;
        this.borrowerName = borrowerName;
        this.borrowDate = borrowDate;
        this.borrowTime = borrowTime;
        this.dueDate = dueDate;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalTime getBorrowTime() {
        return borrowTime;
    }

    public LocalTime getReturnTime() {
        return returnTime;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setReturnTime(LocalTime returnTime) {
        this.returnTime = returnTime;
    }

    // Polymorphism: Loan gives its own version of displayRecord.
    @Override
    public void displayRecord() {
        System.out.println("Book ID: " + bookId
                + " | Borrower: " + borrowerName
                + " | Borrowed: " + borrowDate + " " + borrowTime
                + " | Due: " + dueDate
                + " | Returned: " + returnDate + " " + returnTime);
    }
}