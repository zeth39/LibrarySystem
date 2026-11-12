
package librarysystem;

import java.time.LocalDate;
import java.time.LocalTime;

class Loan extends LibraryRecord {

    private String bookId;
    private String borrowerId;
    private LocalDate borrowDate;
    private LocalTime borrowTime;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private LocalTime returnTime;

    public Loan(String bookId, String borrowerId,
            LocalDate borrowDate, LocalTime borrowTime,
            LocalDate dueDate) {

        this.bookId = bookId;
        this.borrowerId = borrowerId;
        this.borrowDate = borrowDate;
        this.borrowTime = borrowTime;
        this.dueDate = dueDate;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalTime getBorrowTime() {
        return borrowTime;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public LocalTime getReturnTime() {
        return returnTime;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setReturnTime(LocalTime returnTime) {
        this.returnTime = returnTime;
    }

    @Override
    public void displayRecord() {
        System.out.println("Book ID: " + bookId
                + " | Borrower ID: " + borrowerId
                + " | Borrowed: " + borrowDate + " " + borrowTime
                + " | Due: " + dueDate
                + " | Returned: " + returnDate + " " + returnTime);
    }
}
