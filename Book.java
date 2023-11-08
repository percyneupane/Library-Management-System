import java.util.List;

public class Book {
    public static final int DAYS_ALLOWED = 14;
    private final String bookId;
    private final String bookTitle;
    private final List<String> bookAuthors;
    private String status;
    private String dateBorrowed;
    private String userBorrowed;

    public Book(String bookId, String bookTitle, List<String> bookAuthors, String status, String date_borrowed, String user_borrowed) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthors = bookAuthors;
        this.status = "returned";
        this.dateBorrowed = null;
        this.userBorrowed = null;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public List<String> getBookAuthors() {
        return bookAuthors;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(String dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public String getUserBorrowed() {
        return userBorrowed;
    }

    public void setUserBorrowed(String userBorrowed) {
        this.userBorrowed = userBorrowed;
    }
}
