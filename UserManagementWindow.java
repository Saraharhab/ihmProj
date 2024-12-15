package tp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserManagementWindow {

   
    public static void createUserManagementWindow() {
        JFrame userManagementFrame = new JFrame("Gestion des utilisateurs");
        userManagementFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userManagementFrame.setSize(600, 400);
        userManagementFrame.setLayout(new BorderLayout());

         
        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);
        redPanel.setLayout(new GridBagLayout());
        JLabel redLabel = new JLabel("Gestion des utilisateurs :");
        redLabel.setForeground(Color.WHITE);
        redPanel.add(redLabel);

        
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
        SwingUtilities.invokeLater(() -> createUserManagementWindow());
    }
}
