import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class OnlineReservationSystem {
    static Map<String, String> users = new HashMap<>(); // Store user credentials
    static Map<String, Reservation> reservations = new HashMap<>(); // Store reservations

    public static void main(String[] args) {
        // Sample users
        users.put("user1", "password1");
        users.put("user2", "password2");

        // Set a custom theme using UIManager
        setCustomTheme();

        // Initialize GUI
        SwingUtilities.invokeLater(() -> createLoginWindow());
    }

    public static void setCustomTheme() {
        // Set UIManager properties for the look-and-feel of the theme
        UIManager.put("Panel.background", new Color(230, 240, 255)); // Light blue background
        UIManager.put("Button.background", new Color(100, 150, 255)); // Blue buttons
        UIManager.put("Button.foreground", Color.WHITE); // White text on buttons
        UIManager.put("Button.font", new Font("Arial", Font.BOLD, 14));
        UIManager.put("TextField.border", new LineBorder(Color.GRAY, 2)); // Gray borders on text fields
        UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 14)); // Consistent label font
        UIManager.put("TextField.font", new Font("Arial", Font.PLAIN, 14)); // Consistent text field font
    }

    public static void createLoginWindow() {
        JFrame loginFrame = new JFrame("Login - Online Reservation System");
        loginFrame.setSize(400, 200); // Fixed size
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(new GridLayout(3, 2, 10, 10)); // Spacing between elements

        // Center the window on the screen
        loginFrame.setLocationRelativeTo(null);

        // Login UI components
        JLabel userLabel = new JLabel("Login ID:");
        JTextField userField = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JLabel messageLabel = new JLabel();

        // Add login button action listener
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
        loginFrame.add(userLabel);
        loginFrame.add(userField);
        loginFrame.add(passLabel);
        loginFrame.add(passField);
        loginFrame.add(loginButton);
        loginFrame.add(messageLabel);

        // Add some padding around the window
        loginFrame.getContentPane().setBackground(new Color(230, 240, 255)); // Light background color
        loginFrame.setVisible(true);
    }

    public static void createMainMenu() {
        JFrame mainMenuFrame = new JFrame("Main Menu - Online Reservation System");
        mainMenuFrame.setSize(400, 200); // Fixed size
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setLayout(new GridLayout(3, 1, 10, 10)); // GridLayout with spacing

        // Center the window on the screen
        mainMenuFrame.setLocationRelativeTo(null);

        JButton makeReservationButton = new JButton("Make a Reservation");
        JButton cancelReservationButton = new JButton("Cancel a Reservation");
        JButton exitButton = new JButton("Exit");

        makeReservationButton.setBorder(new LineBorder(new Color(80, 120, 255), 2)); // Custom button border
        cancelReservationButton.setBorder(new LineBorder(new Color(80, 120, 255), 2));
        exitButton.setBorder(new LineBorder(new Color(80, 120, 255), 2));

        // Action listeners for buttons
        makeReservationButton.addActionListener(e -> createReservationForm());
        cancelReservationButton.addActionListener(e -> createCancellationForm());
        exitButton.addActionListener(e -> System.exit(0));

        // Add components to frame
        mainMenuFrame.add(makeReservationButton);
        mainMenuFrame.add(cancelReservationButton);
        mainMenuFrame.add(exitButton);

        mainMenuFrame.getContentPane().setBackground(new Color(230, 240, 255)); // Background color
        mainMenuFrame.setVisible(true);
    }

    public static void createReservationForm() {
        JFrame reservationFrame = new JFrame("Make a Reservation");
        reservationFrame.setSize(400, 400); // Fixed size
        reservationFrame.setLayout(new GridLayout(9, 2, 10, 10)); // GridLayout with spacing

        // Center the window on the screen
        reservationFrame.setLocationRelativeTo(null);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel trainNumberLabel = new JLabel("Train Number:");
        JTextField trainNumberField = new JTextField();

        JLabel trainNameLabel = new JLabel("Train Name:");
        JTextField trainNameField = new JTextField();

        JLabel classTypeLabel = new JLabel("Class Type:");
        JTextField classTypeField = new JTextField();

        JLabel dateOfJourneyLabel = new JLabel("Date of Journey:");
        JTextField dateOfJourneyField = new JTextField();

        JLabel fromLabel = new JLabel("From (Place):");
        JTextField fromField = new JTextField();

        JLabel toLabel = new JLabel("To (Destination):");
        JTextField toField = new JTextField();

        JButton submitButton = new JButton("Submit");
        JLabel messageLabel = new JLabel();

        // Action listener for submit button
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String trainNumber = trainNumberField.getText();
            String trainName = trainNameField.getText();
            String classType = classTypeField.getText();
            String dateOfJourney = dateOfJourneyField.getText();
            String from = fromField.getText();
            String to = toField.getText();

            // Generate a unique PNR number
            String pnr = String.valueOf(System.currentTimeMillis());

            Reservation reservation = new Reservation(name, trainNumber, trainName, classType, dateOfJourney, from, to,
                    pnr);
            reservations.put(pnr, reservation);

            messageLabel.setText("Reservation successful! PNR: " + pnr);
        });

        // Add components to the frame
        reservationFrame.add(nameLabel);
        reservationFrame.add(nameField);
        reservationFrame.add(trainNumberLabel);
        reservationFrame.add(trainNumberField);
        reservationFrame.add(trainNameLabel);
        reservationFrame.add(trainNameField);
        reservationFrame.add(classTypeLabel);
        reservationFrame.add(classTypeField);
        reservationFrame.add(dateOfJourneyLabel);
        reservationFrame.add(dateOfJourneyField);
        reservationFrame.add(fromLabel);
        reservationFrame.add(fromField);
        reservationFrame.add(toLabel);
        reservationFrame.add(toField);
        reservationFrame.add(submitButton);
        reservationFrame.add(messageLabel);

        reservationFrame.getContentPane().setBackground(new Color(230, 240, 255)); // Background color
        reservationFrame.setVisible(true);
    }

    public static void createCancellationForm() {
        JFrame cancellationFrame = new JFrame("Cancel a Reservation");
        cancellationFrame.setSize(400, 200); // Fixed size
        cancellationFrame.setLayout(new GridLayout(3, 2, 10, 10)); // GridLayout with spacing

        // Center the window on the screen
        cancellationFrame.setLocationRelativeTo(null);

        JLabel pnrLabel = new JLabel("PNR Number:");
        JTextField pnrField = new JTextField();
        JButton cancelButton = new JButton("Cancel");
        JLabel messageLabel = new JLabel();

        cancelButton.addActionListener(e -> {
            String pnr = pnrField.getText();

            if (reservations.containsKey(pnr)) {
                reservations.remove(pnr);
                messageLabel.setText("Reservation canceled.");
            } else {
                messageLabel.setText("Invalid PNR number!");
            }
        });

        cancellationFrame.add(pnrLabel);
        cancellationFrame.add(pnrField);
        cancellationFrame.add(cancelButton);
        cancellationFrame.add(messageLabel);

        cancellationFrame.getContentPane().setBackground(new Color(230, 240, 255)); // Background color
        cancellationFrame.setVisible(true);
    }
}

class Reservation {
    String name;
    String trainNumber;
    String trainName;
    String classType;
    String dateOfJourney;
    String from;
    String to;
    String pnr;

    public Reservation(String name, String trainNumber, String trainName, String classType, String dateOfJourney,
            String from, String to, String pnr) {
        this.name = name;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.to = to;
        this.pnr = pnr;
    }
}
