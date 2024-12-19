package tp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;



public class UserManagementWindow {
	 private String role;
    public UserManagementWindow(String role) {
    	
    	this.role = role;

        JFrame userManagementFrame = new JFrame("Gestion des utilisateurs");
        userManagementFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userManagementFrame.setSize(600, 400);
        userManagementFrame.setLocationRelativeTo(null);
        userManagementFrame.setLayout(new BorderLayout());

        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);
        redPanel.setLayout(new BorderLayout());
        
        JLabel redLabel = new JLabel("Gestion des utilisateurs :");
        redLabel.setForeground(Color.WHITE);
        redLabel.setFont(new Font("Arial", Font.BOLD, 18));
        redLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        redPanel.add(redLabel, BorderLayout.CENTER);
        
        
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\Administrator\\Desktop\\logo2.png");  
        JLabel logoLabel = new JLabel(logoIcon);
        redPanel.add(logoLabel);  
        userManagementFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
              
                int width = userManagementFrame.getWidth();
                int height = userManagementFrame.getHeight();

   

                int logoWidth = width / 6;
                int logoHeight = height / 6;
                ImageIcon resizedIcon = new ImageIcon(logoIcon.getImage().getScaledInstance(logoWidth, logoHeight, Image.SCALE_SMOOTH));
                logoLabel.setIcon(resizedIcon);

                int fontSize = Math.min(20, Math.max(12, width / 30));
                redLabel.setFont(new Font("Arial", Font.BOLD, fontSize));
            }
        });

        
        JPanel whitePanel = new JPanel();
        whitePanel.setBackground(Color.WHITE);
        whitePanel.setLayout(new BorderLayout());
        
        
        
        String[] columns = {"id_utilisateur", "nom","prenom", "email","role"};
        String[][] data = {
            {"1","Dassa","Houda", "houda@gmail.com", "Admin"},
            {"2","Karasad","Hana", "hana@gmail.com", "Utilisateur"},
            {"3","Arhab","Sarah", "sarah@gmail.com", "Utilisateur"},
            {"4","Hafsaoui","Hassina", "ha@gmail.com", "Admin"},
        };
        
        
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        whitePanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addButton = new JButton("Ajouter un utilisateur");
        addButton.setBackground(Color.RED);
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String id_utilisateur = JOptionPane.showInputDialog(userManagementFrame, "id utilisateur :");
            	String nom = JOptionPane.showInputDialog(userManagementFrame, "Nom de l'utilisateur :");
                String prenom = JOptionPane.showInputDialog(userManagementFrame, "Prenom de l'utilisateur :");
                String email = JOptionPane.showInputDialog(userManagementFrame, "Email de l'utilisateur :");
                String role = JOptionPane.showInputDialog(userManagementFrame, "Rôle de l'utilisateur :");

                String[][] newData = new String[data.length + 1][3];
                System.arraycopy(data, 0, newData, 0, data.length);
                newData[data.length] = new String[]{id_utilisateur,nom,prenom, email, role};
                table.setModel(new javax.swing.table.DefaultTableModel(newData, columns));
            }
        });
        
        JButton searchButton = new JButton("Rechercher");
        searchButton.setBackground(Color.RED);
        searchButton.setForeground(Color.WHITE);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchTerm = JOptionPane.showInputDialog("Entrer un terme de recherche :");
                for (int i = 0; i < data.length; i++) {
                    for (int j = 0; j < data[i].length; j++) {
                        if (data[i][j].toLowerCase().contains(searchTerm.toLowerCase())) {
                            table.setRowSelectionInterval(i, i);  
                            break;
                        }
                    }
                }
            }
        });
       


        JButton backButton = new JButton("Retour au tableau de bord");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                userManagementFrame.dispose(); 
                switch (role) {
                case "admin":
                    new MainDashboard();
                    break;
                case "gestionnaire":
                    new MainDashboard2(); 
                    break;
                case "utilisateur":
                    new MainDashboard1(); 
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Rôle non reconnu !");
            }
        }
    });
        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
        buttonPanel.add(searchButton, BorderLayout.NORTH); 
        whitePanel.add(buttonPanel, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, redPanel, whitePanel);
        splitPane.setDividerLocation(200);
        splitPane.setDividerSize(0);


        userManagementFrame.add(splitPane, BorderLayout.CENTER);

        userManagementFrame.setVisible(true);
        }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UserManagementWindow("utilisateur");  
            }
        });
    }
}
