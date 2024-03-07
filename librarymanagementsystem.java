import java.util.*;

class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class User {
    private String username;
    private List<Book> borrowedBooks;

    public User(String username) {
        this.username = username;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
}

public class LibraryManagementSystem {
    private Map<String, Book> books;
    private Map<String, User> users;
    private Scanner scanner;

    public LibraryManagementSystem() {
        this.books = new HashMap<>();
        this.users = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void addBook(String title, String author) {
        Book book = new Book(title, author);
        books.put(title, book);
        System.out.println("Book added successfully: " + title + " by " + author);
    }

    public void addUser(String username) {
        User user = new User(username);
        users.put(username, user);
        System.out.println("User added successfully: " + username);
    }

    public void displayBooks() {
        System.out.println("Books in the library:");
        for (Book book : books.values()) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() +
                    " - " + (book.isAvailable() ? "Available" : "Not Available"));
        }
        System.out.println();
    }

    public void displayUsers() {
        System.out.println("Library users:");
        for (User user : users.values()) {
            System.out.println("User: " + user.getUsername());
            System.out.println("Borrowed books:");
            for (Book book : user.getBorrowedBooks()) {
                System.out.println(book.getTitle() + " by " + book.getAuthor());
            }
            System.out.println();
        }
    }

    public void borrowBook(String username, String bookTitle) {
        if (users.containsKey(username) && books.containsKey(bookTitle)) {
            User user = users.get(username);
            Book book = books.get(bookTitle);

            if (book.isAvailable()) {
                user.borrowBook(book);
                book.setAvailable(false);
                System.out.println("Book successfully borrowed by " + username);
            } else {
                System.out.println("Sorry, the book is not available for borrowing");
            }
        } else {
            System.out.println("User or book not found");
        }
    }

    public void returnBook(String username, String bookTitle) {
        if (users.containsKey(username) && books.containsKey(bookTitle)) {
            User user = users.get(username);
            Book book = books.get(bookTitle);

            if (user.getBorrowedBooks().contains(book)) {
                user.returnBook(book);
                book.setAvailable(true);
                System.out.println("Book successfully returned by " + username);
            } else {
                System.out.println("This user did not borrow this book");
            }
        } else {
            System.out.println("User or book not found");
        }
    }

    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();

        while (true) {
            System.out.println("********** Library Management System Menu **********");
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. Display Books");
            System.out.println("4. Display Users");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            Scanner scanner = new Scanner(System.in);
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    library.addBook(title, author);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    library.addUser(username);
                    break;
                case 3:
                    library.displayBooks();
                    break;
                case 4:
                    library.displayUsers();
                    break;
                case 5:
                    System.out.print("Enter username: ");
                    String borrowUsername = scanner.nextLine();
                    System.out.print("Enter book title: ");
                    String borrowBookTitle = scanner.nextLine();
                    library.borrowBook(borrowUsername, borrowBookTitle);
                    break;
                case 6:
                    System.out.print("Enter username: ");
                    String returnUsername = scanner.nextLine();
                    System.out.print("Enter book title: ");
                    String returnBookTitle = scanner.nextLine();
                    library.returnBook(returnUsername, returnBookTitle);
                    break;
                case 0:
                    System.out.println("Exiting the library management system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
