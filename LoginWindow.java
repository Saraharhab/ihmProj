package tp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class LoginWindow {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Connexion - Gestion de Location de Véhicules");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        
        
        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);
        
        JPanel whitePanel = new JPanel();
        whitePanel.setBackground(Color.WHITE);
        whitePanel.setLayout(new GridBagLayout());
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, redPanel, whitePanel);
        splitPane.setDividerLocation(400 / 3); 
        splitPane.setDividerSize(0); 
        

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\Administrator\\Desktop\\logo2.png");  
        JLabel logoLabel = new JLabel(logoIcon);
        redPanel.add(logoLabel, BorderLayout.WEST);  

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
              
                int width = frame.getWidth();
                int height = frame.getHeight();

   

                int logoWidth = width / 6;
                int logoHeight = height / 6;
                ImageIcon resizedIcon = new ImageIcon(logoIcon.getImage().getScaledInstance(logoWidth, logoHeight, Image.SCALE_SMOOTH));
                logoLabel.setIcon(resizedIcon);

                int fontSize = Math.min(20, Math.max(12, width / 30));
                redPanel.setFont(new Font("Arial", Font.BOLD, fontSize));
            }
        });

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        whitePanel.add(emailLabel, gbc);

        JTextField emailField = new JTextField(20);
        emailField.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        whitePanel.add(emailField, gbc);

        JLabel passwordLabel = new JLabel("Mot de passe:");
        passwordLabel.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        whitePanel.add(passwordLabel, gbc);

      
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        whitePanel.add(passwordField, gbc);

        JButton loginButton = new JButton("Connexion");
        loginButton.setBackground(Color.RED);
        loginButton.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        whitePanel.add(loginButton, gbc);

        
        JButton quitButton = new JButton("Quitter");
        quitButton.setBackground(Color.RED);
        quitButton.setForeground(Color.WHITE);
        gbc.gridy = 3;
        whitePanel.add(quitButton, gbc);

        frame.add(panel);
        frame.add(splitPane);
        frame.setVisible(true);
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                if (email.equals("ihm@gmail.com") && password.equals("12345")) {
                    JOptionPane.showMessageDialog(frame, "Connexion réussie!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose(); 
                    new MainDashboard();
                } else {
                    JOptionPane.showMessageDialog(frame, "Email ou mot de passe incorrect!", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });
    }
}

