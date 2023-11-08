import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManageBook {
    private final List<Book> books;

    public ManageBook() {
        this.books = new ArrayList<>();
    }


    public void addBook(String bookId, String bookTitle, List<String> bookAuthors) {
        Book book = new Book(bookId, bookTitle, bookAuthors, "returned", null, null);
        this.books.add(book);
    }


    public void listBooks() {
        System.out.println("Books in the library:");
        for (Book book : this.books) {
            System.out.println("Book ID: " + book.getBookId());
            System.out.println("Title: " + book.getBookTitle());
            System.out.println("Authors: " + book.getBookAuthors());
            System.out.println("Status: " + book.getStatus());
            System.out.println("Borrowed date: " + book.getDateBorrowed());
            System.out.println("User borrowed: " + book.getUserBorrowed());
            System.out.println();
        }
    }


    public List<Book> searchBook(String bookTitle) {
        List<Book> searchResults = new ArrayList<>();
        for (Book book : this.books) {
            if (book.getBookTitle().toLowerCase().contains(bookTitle.toLowerCase())) {
                searchResults.add(book);
            }
        }
        return searchResults;
    }


    public void lendBook(String bookId, User user) {
        for (Book book : this.books) {
            if (book.getBookId().equals(bookId) && book.getStatus().equals("returned")) {
                book.setStatus("borrowed");
                book.setDateBorrowed(LocalDate.now().toString());
                book.setUserBorrowed(user.getUserId());
                user.addBorrowedBook(bookId, LocalDate.now().toString());
                System.out.println("Book with ID " + bookId + " is now borrowed by user " + user.getUserName());
                return;
            }
        }
        System.out.println("Sorry, book with ID " + bookId + " is not available for lending.");
    }


    public void returnBook(String bookId, User user) {
        for (Book book : this.books) {
            if (book.getBookId().equals(bookId) && book.getStatus().equals("borrowed") && book.getUserBorrowed().equals(user.getUserId())) {
                book.setStatus("returned");
                book.setDateBorrowed(null);
                book.setUserBorrowed(null);
                user.removeBorrowedBook(bookId);
                System.out.println("Book with ID " + bookId + " has been returned by user " + user.getUserName());
                return;
            }
        }
        System.out.println("Sorry, book with ID " + bookId + " cannot be returned by user " + user.getUserName());
    }
}
