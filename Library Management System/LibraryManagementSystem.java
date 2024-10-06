import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class LibraryManagementSystem {
    static Map<String, String> users = new HashMap<>(); // Store user credentials
    static Map<String, Book> books = new HashMap<>(); // Store books
    static Map<String, String> issuedBooks = new HashMap<>(); // Store issued books with PNR as key

    public static void main(String[] args) {
        // Sample admin credentials
        users.put("admin", "admin123");

        // Sample books
        books.put("B001", new Book("B001", "The Great Gatsby", "F. Scott Fitzgerald", true));
        books.put("B002", new Book("B002", "To Kill a Mockingbird", "Harper Lee", true));
        books.put("B003", new Book("B003", "1984", "George Orwell", true));

        // Set the custom green theme
        setGreenTheme();

        // Launch the login window
        SwingUtilities.invokeLater(() -> createLoginWindow());
    }

    public static void setGreenTheme() {
        // Define a green theme for the UIManager
        UIManager.put("Panel.background", new Color(204, 255, 204)); // Light green background
        UIManager.put("Button.background", new Color(102, 204, 102)); // Green buttons
        UIManager.put("Button.foreground", Color.WHITE); // White text on buttons
        UIManager.put("Button.font", new Font("Arial", Font.BOLD, 14));
        UIManager.put("TextField.border", new LineBorder(new Color(34, 139, 34), 2)); // Dark green border for text
                                                                                      // fields
        UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 14)); // Consistent label font
        UIManager.put("TextField.font", new Font("Arial", Font.PLAIN, 14)); // Consistent text field font
    }

    public static void createLoginWindow() {
        JFrame loginFrame = new JFrame("Login - Library Management System");
        loginFrame.setSize(400, 200); // Fixed size
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(new GridLayout(3, 2, 10, 10)); // GridLayout with spacing

        // Center the window on the screen
        loginFrame.setLocationRelativeTo(null);

        JLabel userLabel = new JLabel("Login ID:");
        JTextField userField = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JLabel messageLabel = new JLabel();

        loginButton.addActionListener(e -> {
            String loginId = userField.getText();
            String password = new String(passField.getPassword());

            if (users.containsKey(loginId) && users.get(loginId).equals(password)) {
                loginFrame.dispose();
                createMainMenu();
            } else {
                messageLabel.setText("Invalid credentials!");
            }
        });

        loginFrame.add(userLabel);
        loginFrame.add(userField);
        loginFrame.add(passLabel);
        loginFrame.add(passField);
        loginFrame.add(loginButton);
        loginFrame.add(messageLabel);

        loginFrame.setVisible(true);
    }

    public static void createMainMenu() {
        JFrame mainMenuFrame = new JFrame("Main Menu - Library Management System");
        mainMenuFrame.setSize(400, 200); // Fixed size
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setLayout(new GridLayout(3, 1, 10, 10)); // GridLayout with spacing

        // Center the window on the screen
        mainMenuFrame.setLocationRelativeTo(null);

        JButton adminModuleButton = new JButton("Admin Module");
        JButton userModuleButton = new JButton("User Module");
        JButton exitButton = new JButton("Exit");

        adminModuleButton.addActionListener(e -> createAdminModule());
        userModuleButton.addActionListener(e -> createUserModule());
        exitButton.addActionListener(e -> System.exit(0));

        mainMenuFrame.add(adminModuleButton);
        mainMenuFrame.add(userModuleButton);
        mainMenuFrame.add(exitButton);

        mainMenuFrame.setVisible(true);
    }

    public static void createAdminModule() {
        JFrame adminFrame = new JFrame("Admin Module");
        adminFrame.setSize(400, 300); // Fixed size
        adminFrame.setLayout(new GridLayout(4, 1, 10, 10)); // GridLayout with spacing

        // Center the window on the screen
        adminFrame.setLocationRelativeTo(null);

        JButton addBookButton = new JButton("Add Book");
        JButton removeBookButton = new JButton("Remove Book");
        JButton viewBooksButton = new JButton("View Books");
        JButton exitButton = new JButton("Exit Admin Module");

        addBookButton.addActionListener(e -> addBook());
        removeBookButton.addActionListener(e -> removeBook());
        viewBooksButton.addActionListener(e -> viewBooks());
        exitButton.addActionListener(e -> adminFrame.dispose());

        adminFrame.add(addBookButton);
        adminFrame.add(removeBookButton);
        adminFrame.add(viewBooksButton);
        adminFrame.add(exitButton);

        adminFrame.setVisible(true);
    }

    public static void addBook() {
        JFrame addBookFrame = new JFrame("Add Book");
        addBookFrame.setSize(400, 300); // Fixed size
        addBookFrame.setLayout(new GridLayout(4, 2, 10, 10)); // GridLayout with spacing

        // Center the window on the screen
        addBookFrame.setLocationRelativeTo(null);

        JLabel bookIdLabel = new JLabel("Book ID:");
        JTextField bookIdField = new JTextField();

        JLabel titleLabel = new JLabel("Book Title:");
        JTextField titleField = new JTextField();

        JLabel authorLabel = new JLabel("Author:");
        JTextField authorField = new JTextField();

        JButton submitButton = new JButton("Add Book");
        JLabel messageLabel = new JLabel();

        submitButton.addActionListener(e -> {
            String bookId = bookIdField.getText();
            String title = titleField.getText();
            String author = authorField.getText();

            books.put(bookId, new Book(bookId, title, author, true));
            messageLabel.setText("Book added successfully!");
        });

        addBookFrame.add(bookIdLabel);
        addBookFrame.add(bookIdField);
        addBookFrame.add(titleLabel);
        addBookFrame.add(titleField);
        addBookFrame.add(authorLabel);
        addBookFrame.add(authorField);
        addBookFrame.add(submitButton);
        addBookFrame.add(messageLabel);

        addBookFrame.setVisible(true);
    }

    public static void removeBook() {
        JFrame removeBookFrame = new JFrame("Remove Book");
        removeBookFrame.setSize(400, 200); // Fixed size
        removeBookFrame.setLayout(new GridLayout(2, 2, 10, 10)); // GridLayout with spacing

        // Center the window on the screen
        removeBookFrame.setLocationRelativeTo(null);

        JLabel bookIdLabel = new JLabel("Book ID to Remove:");
        JTextField bookIdField = new JTextField();
        JButton removeButton = new JButton("Remove Book");
        JLabel messageLabel = new JLabel();

        removeButton.addActionListener(e -> {
            String bookId = bookIdField.getText();
            if (books.remove(bookId) != null) {
                messageLabel.setText("Book removed successfully!");
            } else {
                messageLabel.setText("Book not found!");
            }
        });

        removeBookFrame.add(bookIdLabel);
        removeBookFrame.add(bookIdField);
        removeBookFrame.add(removeButton);
        removeBookFrame.add(messageLabel);

        removeBookFrame.setVisible(true);
    }

    public static void viewBooks() {
        JFrame viewBooksFrame = new JFrame("View Books");
        viewBooksFrame.setSize(400, 300); // Fixed size
        viewBooksFrame.setLayout(new GridLayout(books.size() + 1, 1, 10, 10)); // GridLayout with spacing

        // Center the window on the screen
        viewBooksFrame.setLocationRelativeTo(null);

        for (Book book : books.values()) {
            viewBooksFrame.add(new JLabel(book.toString()));
        }

        viewBooksFrame.setVisible(true);
    }

    public static void createUserModule() {
        JFrame userFrame = new JFrame("User Module");
        userFrame.setSize(400, 300); // Fixed size
        userFrame.setLayout(new GridLayout(4, 1, 10, 10)); // GridLayout with spacing

        // Center the window on the screen
        userFrame.setLocationRelativeTo(null);

        JButton viewBooksButton = new JButton("View Books");
        JButton issueBookButton = new JButton("Issue Book");
        JButton returnBookButton = new JButton("Return Book");
        JButton exitButton = new JButton("Exit User Module");

        viewBooksButton.addActionListener(e -> viewBooks());
        issueBookButton.addActionListener(e -> issueBook());
        returnBookButton.addActionListener(e -> returnBook());
        exitButton.addActionListener(e -> userFrame.dispose());

        userFrame.add(viewBooksButton);
        userFrame.add(issueBookButton);
        userFrame.add(returnBookButton);
        userFrame.add(exitButton);

        userFrame.setVisible(true);
    }

    public static void issueBook() {
        JFrame issueBookFrame = new JFrame("Issue Book");
        issueBookFrame.setSize(400, 200); // Fixed size
        issueBookFrame.setLayout(new GridLayout(2, 2, 10, 10)); // GridLayout with spacing

        // Center the window on the screen
        issueBookFrame.setLocationRelativeTo(null);

        JLabel bookIdLabel = new JLabel("Book ID to Issue:");
        JTextField bookIdField = new JTextField();
        JButton issueButton = new JButton("Issue Book");
        JLabel messageLabel = new JLabel();

        issueButton.addActionListener(e -> {
            String bookId = bookIdField.getText();
            Book book = books.get(bookId);
            if (book != null && book.isAvailable()) {
                book.setAvailable(false);
                issuedBooks.put(bookId, bookId);
                messageLabel.setText("Book issued successfully!");
            } else {
                messageLabel.setText("Book not available for issue!");
            }
        });

        issueBookFrame.add(bookIdLabel);
        issueBookFrame.add(bookIdField);
        issueBookFrame.add(issueButton);
        issueBookFrame.add(messageLabel);

        issueBookFrame.setVisible(true);
    }

    public static void returnBook() {
        JFrame returnBookFrame = new JFrame("Return Book");
        returnBookFrame.setSize(400, 200); // Fixed size
        returnBookFrame.setLayout(new GridLayout(2, 2, 10, 10)); // GridLayout with spacing

        // Center the window on the screen
        returnBookFrame.setLocationRelativeTo(null);

        JLabel bookIdLabel = new JLabel("Book ID to Return:");
        JTextField bookIdField = new JTextField();
        JButton returnButton = new JButton("Return Book");
        JLabel messageLabel = new JLabel();

        returnButton.addActionListener(e -> {
            String bookId = bookIdField.getText();
            if (issuedBooks.containsKey(bookId)) {
                Book book = books.get(bookId);
                if (book != null) {
                    book.setAvailable(true);
                    issuedBooks.remove(bookId);
                    messageLabel.setText("Book returned successfully!");
                }
            } else {
                messageLabel.setText("This book was not issued!");
            }
        });

        returnBookFrame.add(bookIdLabel);
        returnBookFrame.add(bookIdField);
        returnBookFrame.add(returnButton);
        returnBookFrame.add(messageLabel);

        returnBookFrame.setVisible(true);
    }
}

// Book class remains the same
class Book {
    String id;
    String title;
    String author;
    boolean available;

    public Book(String id, String title, String author, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Available: " + (available ? "Yes" : "No");
    }
}
