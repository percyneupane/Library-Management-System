import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class User {
    private static final int DAYS_ALLOWED = 14;
    private final String userId;
    private final String userName;
    private final List<String> booksBorrowed;
    private final List<String> booksReturned;
    private String status;
    private int timesFined;
    private boolean banned;

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.booksBorrowed = new ArrayList<>();
        this.booksReturned = new ArrayList<>();
        this.status = "active";
        this.timesFined = 0;
        this.banned = false;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public List<String> getBooksBorrowed() {
        return booksBorrowed;
    }

    public List<String> getBooksReturned() {
        return booksReturned;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTimesFined() {
        return timesFined;
    }

    public void setTimesFined(int timesFined) {
        this.timesFined = timesFined;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public void addBorrowedBook(String bookId, String borrowedDate) {
        String borrowedBook = bookId + " - " + borrowedDate;
        this.booksBorrowed.add(borrowedBook);
    }

    public void removeBorrowedBook(String bookId) {
        for (String borrowedBook : this.booksBorrowed) {
            if (borrowedBook.startsWith(bookId)) {
                this.booksBorrowed.remove(borrowedBook);
                break;
            }
        }
    }

    public long getDaysOverdue() {
        long daysOverdue = 0;
        for (String borrowedBook : this.booksBorrowed) {
            String[] parts = borrowedBook.split(" - ");
            String bookId = parts[0];
            String borrowedDateString = parts[1];
            LocalDate borrowedDate = LocalDate.parse(borrowedDateString);
            for (String returnedBook : this.booksReturned) {
                if (returnedBook.startsWith(bookId)) {
                    String[] returnedParts = returnedBook.split(" - ");
                    String returnedDateString = returnedParts[1];
                    LocalDate returnedDate = LocalDate.parse(returnedDateString);
                    long daysBetween = ChronoUnit.DAYS.between(borrowedDate, returnedDate);
                    long daysUntilDue = Book.DAYS_ALLOWED - daysBetween;
                    if (daysUntilDue < 0) {
                        daysOverdue += Math.abs(daysUntilDue);
                    }
                }
            }
        }
        return daysOverdue;
    }
}
