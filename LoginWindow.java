package tp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class LoginWindow {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Connexion - Gestion de Location de Véhicules");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        // Panneau rouge à gauche
        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);
        redPanel.setLayout(new BorderLayout()); // Ajout d'un layout pour une meilleure gestion du contenu

        // Panneau blanc à droite
        JPanel whitePanel = new JPanel();
        whitePanel.setBackground(Color.WHITE);
        whitePanel.setLayout(new GridBagLayout());

        // Configuration du split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, redPanel, whitePanel);
        splitPane.setDividerLocation(500 / 3); // Position du séparateur
        splitPane.setDividerSize(0); // Taille du séparateur

        // Ajout du logo dans le panneau rouge
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\Administrator\\Desktop\\logo2.png");
        JLabel logoLabel = new JLabel(logoIcon);
        redPanel.add(logoLabel, BorderLayout.CENTER);

        // Redimensionnement dynamique du logo
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = frame.getWidth();
                int height = frame.getHeight();

                int logoWidth = width / 6;
                int logoHeight = height / 6;
                ImageIcon resizedIcon = new ImageIcon(logoIcon.getImage().getScaledInstance(logoWidth, logoHeight, Image.SCALE_SMOOTH));
                logoLabel.setIcon(resizedIcon);
            }
        });

        // Gestion des composants dans le panneau blanc
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Marges entre les composants
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label et champ pour le nom
        JLabel nameLabel = new JLabel("Nom:");
        nameLabel.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END; // Aligner à droite
        whitePanel.add(nameLabel, gbc);

        JTextField nameField = new JTextField(20);
        nameField.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START; // Aligner à gauche
        whitePanel.add(nameField, gbc);

        // Label et champ pour l'email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        whitePanel.add(emailLabel, gbc);

        JTextField emailField = new JTextField(20);
        emailField.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        whitePanel.add(emailField, gbc);

        // Label et champ pour le mot de passe
        JLabel passwordLabel = new JLabel("Mot de passe:");
        passwordLabel.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        whitePanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        whitePanel.add(passwordField, gbc);

        // Bouton de connexion
        JButton loginButton = new JButton("Connexion");
        loginButton.setBackground(Color.RED);
        loginButton.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        whitePanel.add(loginButton, gbc);

        // Bouton quitter
        JButton quitButton = new JButton("Quitter");
        quitButton.setBackground(Color.RED);
        quitButton.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 4;
        whitePanel.add(quitButton, gbc);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String role = ""; // Variable pour stocker le rôle (admin, gestionnaire, utilisateur)

                // Simuler une validation des identifiants
                if (email.equals("admin@gmail.com") && password.equals("admin123")) {
                    role = "admin";
                } else if (email.equals("gestionnaire@gmail.com") && password.equals("gest123")) {
                    role = "gestionnaire";
                } else if (email.equals("user@gmail.com") && password.equals("user123")) {
                    role = "utilisateur";
                } else {
                    JOptionPane.showMessageDialog(frame, "Email ou mot de passe incorrect!", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return; // Quitter la méthode si les identifiants sont invalides
                }

                // Ouvrir le tableau de bord correspondant
                if (role.equals("admin")) {
                    new MainDashboard();
                } else if (role.equals("gestionnaire")) {
                    new MainDashboard2();
                } else if (role.equals("utilisateur")) {
                    new MainDashboard1();
                }
                frame.dispose(); // Fermer la fenêtre de connexion
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.add(splitPane);
        frame.setVisible(true);
    }
}
