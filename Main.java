import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ManageUser userManager = new ManageUser();
        ManageBook bookManager = new ManageBook();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Library Management System!");
        System.out.println();
        while (true) {
            System.out.println("Please select an option:");
            System.out.println("1. Register a new user");
            System.out.println("2. Add a new book");
            System.out.println("3. List all books");
            System.out.println("4. Search for a book");
            System.out.println("5. Lend a book");
            System.out.println("6. Return a book");
            System.out.println("7. Fine overdue users");
            System.out.println("8. Ban a user from borrowing books");
            System.out.println("9. Quit");
            System.out.println();
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter user ID:");
                    String userId = scanner.next();
                    System.out.println("Enter your user name:");
                    String userName = scanner.next();
                    userManager.registerUser(userId, userName);
                    System.out.println("The User has been registered successfully.");
                    System.out.println();
                    break;

                case 2:
                    System.out.println("Enter book ID:");
                    String bookId = scanner.next();
                    System.out.println("Enter the title of the book:");
                    String bookTitle = scanner.next();
                    System.out.println("Enter book author(s):");
                    String authorInput = scanner.next();
                    String[] authorArray = authorInput.split(",");
                    List<String> bookAuthors = List.of(authorArray);
                    bookManager.addBook(bookId, bookTitle, bookAuthors);
                    System.out.println("Book added successfully.");
                    System.out.println();
                    break;

                case 3:
                    bookManager.listBooks();
                    System.out.println();
                    break;

                case 4:
                    System.out.println("Enter book title to search:");
                    String searchTitle = scanner.next();
                    List<Book> searchResults = bookManager.searchBook(searchTitle);
                    if (searchResults.isEmpty()) {
                        System.out.println("No books found matching the search criteria.");
                    } else {
                        System.out.println("Search results:");
                        for (Book book : searchResults) {
                            System.out.println("Book ID: " + book.getBookId());
                            System.out.println("Title: " + book.getBookTitle());
                            System.out.println("Authors: " + book.getBookAuthors());
                            System.out.println("Status: " + book.getStatus());
                            System.out.println();

                        }
                    }
                    break;

                case 5:
                    System.out.println("Enter book ID to lend:");
                    String lendBookId = scanner.next();
                    System.out.println("Enter user ID:");
                    String lendUserId = scanner.next();
                    User lendUser = userManager.getUserById(lendUserId);
                    if (lendUser == null) {
                        System.out.println("User with ID " + lendUserId + " not found.");
                    } else {
                        bookManager.lendBook(lendBookId, lendUser);
                    }
                    System.out.println();
                    break;
                case 6:
                    System.out.println("Enter book ID to return:");
                    String returnBookId = scanner.next();
                    System.out.println("Enter user ID:");
                    String returnUserId = scanner.next();
                    User returnUser = userManager.getUserById(returnUserId);
                    if (returnUser == null) {
                        System.out.println("User with ID " + returnUserId + " not found.");
                    } else {
                        bookManager.returnBook(returnBookId, returnUser);
                    }
                    System.out.println();
                    break;

                case 7:
                    userManager.fineOverdueUsers();
                    break;

                case 8:
                    System.out.println("Enter user ID to ban:");
                    userId = scanner.next();
                    User user = userManager.getUserById(userId);
                    if (user == null) {
                        System.out.println("User with ID " + userId + " not found.");
                    } else {
                        userManager.banUser(user);
                        System.out.println("User " + userId + " has been banned from borrowing books.");
                    }
                    System.out.println();
                    break;
                case 9:
                    System.out.println("Thank you for using the Library Management System. Goodbye!");
                    System.exit(0);
                    System.out.println();
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
                    break;

            }
        }
    }
}
