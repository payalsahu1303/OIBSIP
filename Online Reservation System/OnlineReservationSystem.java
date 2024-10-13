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
        UIManager.put("Panel.background", new Color(240, 240, 240)); // Light gray background
        UIManager.put("Button.background", new Color(70, 130, 180)); // Steel blue buttons
        UIManager.put("Button.foreground", Color.WHITE); // White text on buttons
        UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 16));
        UIManager.put("TextField.border", new LineBorder(Color.GRAY, 2)); // Gray borders on text fields
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14)); // Consistent label font
        UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 14)); // Consistent text field font
    }

    public static void createLoginWindow() {
        JFrame loginFrame = new JFrame("Login - Online Reservation System");
        loginFrame.setSize(400, 250); // Adjusted size for better layout
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(new GridBagLayout()); // Using GridBagLayout for flexible positioning

        // Center the window on the screen
        loginFrame.setLocationRelativeTo(null);

        // Login UI components
        JLabel userLabel = new JLabel("Login ID:");
        JTextField userField = new JTextField(15); // Specify column width

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(15); // Specify column width

        JButton loginButton = new JButton("Login");
        JLabel messageLabel = new JLabel();

        // Set font size for message label
        messageLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));

        // Add login button action listener
        loginButton.addActionListener(e -> {
            String loginId = userField.getText();
            String password = new String(passField.getPassword());

            if (users.containsKey(loginId) && users.get(loginId).equals(password)) {
                loginFrame.dispose();
                createMainMenu();
            } else {
                messageLabel.setText("Invalid credentials!");
                messageLabel.setForeground(Color.RED);
            }
        });

        // GridBagConstraints for positioning
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Add components to the frame with GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginFrame.add(userLabel, gbc);
        gbc.gridx = 1;
        loginFrame.add(userField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginFrame.add(passLabel, gbc);
        gbc.gridx = 1;
        loginFrame.add(passField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginFrame.add(loginButton, gbc);
        gbc.gridx = 1;
        loginFrame.add(messageLabel, gbc);

        // Add some padding around the window
        loginFrame.getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background
        loginFrame.setVisible(true);
    }

    public static void createMainMenu() {
        JFrame mainMenuFrame = new JFrame("Main Menu - Online Reservation System");
        mainMenuFrame.setSize(400, 250); // Adjusted size for better layout
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setLayout(new GridBagLayout());

        // Center the window on the screen
        mainMenuFrame.setLocationRelativeTo(null);

        JButton makeReservationButton = new JButton("Make a Reservation");
        JButton cancelReservationButton = new JButton("Cancel a Reservation");
        JButton exitButton = new JButton("Exit");

        // Custom button styles
        styleButton(makeReservationButton);
        styleButton(cancelReservationButton);
        styleButton(exitButton);

        // Action listeners for buttons
        makeReservationButton.addActionListener(e -> createReservationForm());
        cancelReservationButton.addActionListener(e -> createCancellationForm());
        exitButton.addActionListener(e -> System.exit(0));

        // GridBagConstraints for positioning
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally

        // Add components to frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainMenuFrame.add(makeReservationButton, gbc);
        gbc.gridy = 1;
        mainMenuFrame.add(cancelReservationButton, gbc);
        gbc.gridy = 2;
        mainMenuFrame.add(exitButton, gbc);

        mainMenuFrame.getContentPane().setBackground(new Color(240, 240, 240)); // Background color
        mainMenuFrame.setVisible(true);
    }

    public static void createReservationForm() {
        JFrame reservationFrame = new JFrame("Make a Reservation");
        reservationFrame.setSize(400, 500); // Adjusted size for better layout
        reservationFrame.setLayout(new GridBagLayout());

        // Center the window on the screen
        reservationFrame.setLocationRelativeTo(null);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(15);

        JLabel trainNumberLabel = new JLabel("Train Number:");
        JTextField trainNumberField = new JTextField(15);

        JLabel trainNameLabel = new JLabel("Train Name:");
        JTextField trainNameField = new JTextField(15);

        JLabel classTypeLabel = new JLabel("Class Type:");
        JTextField classTypeField = new JTextField(15);

        JLabel dateOfJourneyLabel = new JLabel("Date of Journey:");
        JTextField dateOfJourneyField = new JTextField(15);

        JLabel fromLabel = new JLabel("From (Place):");
        JTextField fromField = new JTextField(15);

        JLabel toLabel = new JLabel("To (Destination):");
        JTextField toField = new JTextField(15);

        JButton submitButton = new JButton("Submit");
        JLabel messageLabel = new JLabel();

        // Set font size for message label
        messageLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));

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
            messageLabel.setForeground(Color.GREEN);
        });

        // GridBagConstraints for positioning
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Add components to the frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        reservationFrame.add(nameLabel, gbc);
        gbc.gridx = 1;
        reservationFrame.add(nameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        reservationFrame.add(trainNumberLabel, gbc);
        gbc.gridx = 1;
        reservationFrame.add(trainNumberField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        reservationFrame.add(trainNameLabel, gbc);
        gbc.gridx = 1;
        reservationFrame.add(trainNameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        reservationFrame.add(classTypeLabel, gbc);
        gbc.gridx = 1;
        reservationFrame.add(classTypeField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        reservationFrame.add(dateOfJourneyLabel, gbc);
        gbc.gridx = 1;
        reservationFrame.add(dateOfJourneyField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        reservationFrame.add(fromLabel, gbc);
        gbc.gridx = 1;
        reservationFrame.add(fromField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        reservationFrame.add(toLabel, gbc);
        gbc.gridx = 1;
        reservationFrame.add(toField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        reservationFrame.add(submitButton, gbc);
        gbc.gridx = 1;
        reservationFrame.add(messageLabel, gbc);

        reservationFrame.getContentPane().setBackground(new Color(240, 240, 240)); // Background color
        reservationFrame.setVisible(true);
    }

    public static void createCancellationForm() {
        JFrame cancellationFrame = new JFrame("Cancel a Reservation");
        cancellationFrame.setSize(400, 250); // Adjusted size for better layout
        cancellationFrame.setLayout(new GridBagLayout());

        // Center the window on the screen
        cancellationFrame.setLocationRelativeTo(null);

        JLabel pnrLabel = new JLabel("Enter PNR to cancel:");
        JTextField pnrField = new JTextField(15);
        JButton cancelButton = new JButton("Cancel");
        JLabel messageLabel = new JLabel();

        // Set font size for message label
        messageLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));

        // Action listener for cancel button
        cancelButton.addActionListener(e -> {
            String pnr = pnrField.getText();

            if (reservations.containsKey(pnr)) {
                reservations.remove(pnr);
                messageLabel.setText("Reservation cancelled successfully!");
                messageLabel.setForeground(Color.GREEN);
            } else {
                messageLabel.setText("PNR not found!");
                messageLabel.setForeground(Color.RED);
            }
        });

        // GridBagConstraints for positioning
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Add components to the frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        cancellationFrame.add(pnrLabel, gbc);
        gbc.gridx = 1;
        cancellationFrame.add(pnrField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        cancellationFrame.add(cancelButton, gbc);
        gbc.gridx = 1;
        cancellationFrame.add(messageLabel, gbc);

        cancellationFrame.getContentPane().setBackground(new Color(240, 240, 240)); // Background color
        cancellationFrame.setVisible(true);
    }

    // Custom styling for buttons
    private static void styleButton(JButton button) {
        button.setPreferredSize(new Dimension(250, 40)); // Increased button size
        button.setBorder(new LineBorder(Color.DARK_GRAY, 1)); // Border style
        button.setBackground(new Color(70, 130, 180)); // Background color
        button.setForeground(Color.WHITE); // Text color
        button.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Font style
    }

    static class Reservation {
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
}
