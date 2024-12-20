package GestionLocation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginInterface extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginInterface() {
        setTitle("Login Page");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");
        emailField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));

        emailField.setPreferredSize(new Dimension(150, 30));
        passwordField.setPreferredSize(new Dimension(150, 30));
        loginButton.setPreferredSize(new Dimension(150, 40));

        loginButton.setBackground(new Color(200, 220, 230));
        loginButton.setForeground(Color.RED);
        loginButton.setFocusPainted(false);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(loginButton, gbc);

        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.RED);
        menuPanel.setLayout(new GridLayout(3, 1, 10, 10));

        JLabel welcomeLabel = new JLabel("Bienvenue", SwingConstants.CENTER);
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel instructionLabel = new JLabel("Connectez-vous pour continuer", SwingConstants.CENTER);
        instructionLabel.setForeground(Color.WHITE);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        ImageIcon imageIcon = new ImageIcon("C:\\Users\\DELL\\OneDrive\\Bureau\\ihmproj\\imageGG.jpg");
        Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(image);

        JLabel imageLabel = new JLabel(resizedIcon, SwingConstants.CENTER);

        menuPanel.add(welcomeLabel);
        menuPanel.add(imageLabel);
        menuPanel.add(instructionLabel);

        add(menuPanel, BorderLayout.WEST);
        add(formPanel, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	authenticateUser();
            }
        });
    }

    private void authenticateUser() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT role FROM Utilisateur WHERE email = ? AND mot_de_passe = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String role = resultSet.getString("role");
                redirectToRoleInterface(role);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void redirectToRoleInterface(String role) {
        switch (role.toLowerCase()) {
            case "administrateur":
                new AdministrateurInterface().setVisible(true); // Open Administrateur interface
                this.dispose(); // Close the login window
                break;
            case "gestionnaire":
                new GestionnaireInterface().setVisible(true); // Open Gestionnaire interface
                this.dispose(); // Close the login window
                break;
            case "client":
                new ClientInterface().setVisible(true); // Open Client interface
                this.dispose(); // Close the login window
                break;
            default:
                JOptionPane.showMessageDialog(this, "Unknown role: " + role, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginInterface().setVisible(true));
    }
}
