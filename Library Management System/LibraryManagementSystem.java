import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
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
        books.put("B003", new Book("B003", "1984", "George Orwell", false));
        books.put("B004", new Book("B004", "Pride and Prejudice", "Jane Austen", true));
        books.put("B005", new Book("B005", "Moby Dick", "Herman Melville", false));
        books.put("B006", new Book("B006", "War and Peace", "Leo Tolstoy", true));
        books.put("B007", new Book("B007", "The Odyssey", "Homer", true));
        books.put("B008", new Book("B008", "Ulysses", "James Joyce", false));
        books.put("B009", new Book("B009", "The Catcher in the Rye", "J.D. Salinger", true));
        books.put("B010", new Book("B010", "Crime and Punishment", "Fyodor Dostoevsky", false));
        books.put("B011", new Book("B011", "The Brothers Karamazov", "Fyodor Dostoevsky", true));
        books.put("B012", new Book("B012", "Brave New World", "Aldous Huxley", false));
        books.put("B013", new Book("B013", "Anna Karenina", "Leo Tolstoy", true));
        books.put("B014", new Book("B014", "The Divine Comedy", "Dante Alighieri", false));
        books.put("B015", new Book("B015", "Don Quixote", "Miguel de Cervantes", true));

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
        UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 14)); // Consistent label font
        UIManager.put("TextField.font", new Font("Arial", Font.PLAIN, 14)); // Consistent text field font
        UIManager.put("Table.font", new Font("Arial", Font.PLAIN, 14)); // Table font
        UIManager.put("Table.background", new Color(204, 255, 204)); // Light green table background
        UIManager.put("Table.foreground", Color.BLACK); // Table text color
        UIManager.put("Table.gridColor", new Color(102, 204, 102)); // Grid color matching theme
    }

    public static void createLoginWindow() {
        JFrame loginFrame = new JFrame("Login - Library Management System");
        loginFrame.setSize(400, 250); // Adjusted size
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel userLabel = new JLabel("Login ID:");
        JTextField userField = new JTextField(20);

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(20);

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

        // Add components to the frame
        gbc.anchor = GridBagConstraints.EAST;
        loginFrame.add(userLabel, gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.WEST;
        loginFrame.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        loginFrame.add(passLabel, gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.WEST;
        loginFrame.add(passField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginFrame.add(loginButton, gbc);

        gbc.gridy++;
        loginFrame.add(messageLabel, gbc);
        loginFrame.setLocationRelativeTo(null); // Center the window on the screen

        loginFrame.setVisible(true);
    }

    public static void createMainMenu() {
        JFrame mainMenuFrame = new JFrame("Main Menu - Library Management System");
        mainMenuFrame.setSize(400, 300); // Adjusted size
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setLayout(new GridLayout(3, 1, 20, 20)); // More spacing for better visibility

        JButton adminModuleButton = new JButton("Admin Module");
        JButton userModuleButton = new JButton("User Module");
        JButton exitButton = new JButton("Exit");

        adminModuleButton.addActionListener(e -> createAdminModule());
        userModuleButton.addActionListener(e -> createUserModule());
        exitButton.addActionListener(e -> System.exit(0));

        mainMenuFrame.add(adminModuleButton);
        mainMenuFrame.add(userModuleButton);
        mainMenuFrame.add(exitButton);
        mainMenuFrame.setLocationRelativeTo(null); // Center the window on the screen

        mainMenuFrame.setVisible(true);
    }

    public static void createAdminModule() {
        JFrame adminFrame = new JFrame("Admin Module");
        adminFrame.setSize(400, 300); // Adjusted size
        adminFrame.setLayout(new GridLayout(4, 1, 20, 20));

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
        adminFrame.setLocationRelativeTo(null); // Center the window on the screen

        adminFrame.setVisible(true);
    }

    public static void createUserModule() {
        JFrame userFrame = new JFrame("User Module");
        userFrame.setSize(400, 300); // Adjusted size
        userFrame.setLayout(new GridLayout(4, 1, 20, 20));

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
        userFrame.setLocationRelativeTo(null); // Center the window on the screen

        userFrame.setVisible(true);
    }

    public static void addBook() {
        String id = JOptionPane.showInputDialog("Enter Book ID:");
        String title = JOptionPane.showInputDialog("Enter Book Title:");
        String author = JOptionPane.showInputDialog("Enter Author Name:");

        if (id != null && title != null && author != null && !id.isEmpty() && !title.isEmpty() && !author.isEmpty()) {
            books.put(id, new Book(id, title, author, true));
            JOptionPane.showMessageDialog(null, "Book added successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "All fields are required!");
        }
    }

    public static void removeBook() {
        String id = JOptionPane.showInputDialog("Enter Book ID to remove:");

        if (id != null && books.containsKey(id)) {
            books.remove(id);
            JOptionPane.showMessageDialog(null, "Book removed successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Book ID not found!");
        }
    }

    public static void viewBooks() {
        JFrame bookFrame = new JFrame("Books in the Library");
        bookFrame.setSize(600, 400); // Adjusted size for the table

        String[] columnNames = { "Book ID", "Title", "Author", "Available" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Add each book to the table model
        for (Book book : books.values()) {
            String[] row = { book.getId(), book.getTitle(), book.getAuthor(), book.isAvailable() ? "Yes" : "No" };
            tableModel.addRow(row);
        }

        JTable bookTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        bookFrame.add(scrollPane);
        bookFrame.setLocationRelativeTo(null); // Center the window on the screen

        bookFrame.setVisible(true);
    }

    public static void issueBook() {
        String id = JOptionPane.showInputDialog("Enter Book ID to issue:");

        if (id != null && books.containsKey(id) && books.get(id).isAvailable()) {
            String pnr = JOptionPane.showInputDialog("Enter your PNR:");
            if (pnr != null && !pnr.isEmpty()) {
                books.get(id).setAvailable(false);
                issuedBooks.put(pnr, id);
                JOptionPane.showMessageDialog(null, "Book issued successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "PNR cannot be empty!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Book ID not found or not available!");
        }
    }

    public static void returnBook() {
        String pnr = JOptionPane.showInputDialog("Enter your PNR to return the book:");

        if (pnr != null && issuedBooks.containsKey(pnr)) {
            String bookId = issuedBooks.get(pnr);
            books.get(bookId).setAvailable(true);
            issuedBooks.remove(pnr);
            JOptionPane.showMessageDialog(null, "Book returned successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid PNR or no book issued!");
        }
    }
}

// Book class for book details
class Book {
    private String id;
    private String title;
    private String author;
    private boolean available;

    public Book(String id, String title, String author, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = available;
    }

    public String getId() {
        return id;
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

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Available: " + (available ? "Yes" : "No");
    }
}