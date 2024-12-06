package ihmMinProj;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;


public class SimpleLoginPage extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public SimpleLoginPage() {
        // Set up the JFrame
        setTitle("Login Page");
        setSize(800, 400);  // Adjust size for larger window with menu
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components for the login form
        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");
        emailField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        // Customize components' appearance
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Set smaller size for the fields and button
        emailField.setPreferredSize(new Dimension(150, 30));
        passwordField.setPreferredSize(new Dimension(150, 30));
        loginButton.setPreferredSize(new Dimension(150, 40));
        
     // Set colors
        loginButton.setBackground(new Color(200, 220, 230)); // Bleu-gris très clair
        loginButton.setForeground(Color.RED);                // Texte rouge
        loginButton.setFocusPainted(false);                  // Retire le contour sur le bouton lorsqu'il est pressé

        
        // Set up the layout for the login form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());  // Use GridBagLayout for responsive components
        GridBagConstraints gbc = new GridBagConstraints();
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // Add margin around the panel

        // Add components to the form panel with responsive layout
        gbc.insets = new Insets(10, 10, 10, 10);  // Add space between components
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 2;
        formPanel.add(loginButton, gbc);

        // Create the menu panel on the left
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.RED);
        menuPanel.setLayout(new GridLayout(3, 1, 10, 10)); // Menu with only 3 labels

        // Welcome message in red panel
        JLabel welcomeLabel = new JLabel("Bienvenue", SwingConstants.CENTER);
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));  // Bold font for "Bienvenue"
       
        JLabel instructionLabel = new JLabel("Connectez-vous pour continuer", SwingConstants.CENTER);
        instructionLabel.setForeground(Color.WHITE);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 16));  // Regular font for instructions
        
     // Load the image
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\DELL\\OneDrive\\Bureau\\ihmproj\\imageGG.jpg"); // Replace with the actual path to your image
        Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Resize image if needed
        ImageIcon resizedIcon = new ImageIcon(image); // Create a new ImageIcon with the resized image

        // Create an image label
        JLabel imageLabel = new JLabel(resizedIcon, SwingConstants.CENTER); // Center the image
     
        // Add the labels to the menu panel
        menuPanel.add(welcomeLabel);
        menuPanel.add(imageLabel);
        menuPanel.add(instructionLabel);

        // Ensure the menu takes up the full height of the window
        add(menuPanel, BorderLayout.WEST);

        // Set up the main layout (adding the form panel to the center)
        add(formPanel, BorderLayout.CENTER);

        // Action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // For now, just show a message with the email and password
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Show a confirmation message
                JOptionPane.showMessageDialog(null, "Email: " + email + "\nPassword: " + password);
            }
        });
    }

    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure thread safety
        SwingUtilities.invokeLater(new Runnable() {public void run() {
            new SimpleLoginPage().setVisible(true);
        }
    });
}
}