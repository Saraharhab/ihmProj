package tp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.*;
import java.util.Vector;


public class UserManagementWindow {

    private DefaultTableModel tableModel;  
    private JTable table;

    private Connection connectToDatabase() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/your_database_name";  // Remplace avec ton nom de base
            String user = "root";  // Ton utilisateur MySQL
            String password = "";  // Ton mot de passe MySQL (laisse vide si aucun)
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données");
        }
        return conn;
    }

    private void loadUsersFromDB() {
        try (Connection conn = connectToDatabase()) {
            String query = "SELECT name, email, role FROM users";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            tableModel.setRowCount(0); 
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("name"));
                row.add(rs.getString("email"));
                row.add(rs.getString("role"));
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addUserToDB(String name, String email, String role) {
        try (Connection conn = connectToDatabase()) {
            String query = "INSERT INTO users (name, email, role) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, role);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de l'insertion dans la base de données");
        }
    }

    public UserManagementWindow() {
   
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
        
        
        
        String[] columns = {"Nom", "Email", "Rôle"};
        String[][] data = {
            {"Houda", "houda@gmail.com", "Admin"},
            {"Hana", "hana@gmail.com", "Utilisateur"},
            {"Sarah", "sarah@gmail.com", "Utilisateur"},
            {"Hanane", "hanane@gmail.com", "Admin"},
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
                String name = JOptionPane.showInputDialog(userManagementFrame, "Nom de l'utilisateur :");
                String email = JOptionPane.showInputDialog(userManagementFrame, "Email de l'utilisateur :");
                String role = JOptionPane.showInputDialog(userManagementFrame, "Rôle de l'utilisateur :");

                String[][] newData = new String[data.length + 1][3];
                System.arraycopy(data, 0, newData, 0, data.length);
                newData[data.length] = new String[]{name, email, role};
                table.setModel(new javax.swing.table.DefaultTableModel(newData, columns));
            }
        });

        JButton backButton = new JButton("Retour au tableau de bord");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userManagementFrame.dispose(); 
                new MainDashboard();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
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
                new UserManagementWindow();  
            }
        });
    }
}
