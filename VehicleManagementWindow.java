package tp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VehicleManagementWindow {

 
    public static void createVehicleManagementWindow() {
        JFrame vehicleManagementFrame = new JFrame("Gestion des véhicules");
        vehicleManagementFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vehicleManagementFrame.setSize(600, 400);
        vehicleManagementFrame.setLayout(new BorderLayout());
 
        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);
        redPanel.setLayout(new GridBagLayout());
        JLabel redLabel = new JLabel("Gestion des véhicules:");
        redLabel.setForeground(Color.WHITE);
        redLabel.setFont(new Font("Arial", Font.BOLD, 14));
        redPanel.add(redLabel);

 
        JPanel whitePanel = new JPanel();
        whitePanel.setBackground(Color.WHITE);
        whitePanel.setLayout(new BorderLayout());

  
        String[] columns = {"Marque", "Modèle", "Année", "Prix (DA)"};
        String[][] data = {
            {"Toyota", "Corolla", "2020", "15000"},
            {"Honda", "Civic", "2019", "14000"},
            {"Ford", "Focus", "2021", "16000"},
            {"Tesla", "Model 3", "2022", "35000"},
        };
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        whitePanel.add(scrollPane, BorderLayout.CENTER);

    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addButton = new JButton("Ajouter un véhicule");
        addButton.setBackground(Color.RED);
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String brand = JOptionPane.showInputDialog(vehicleManagementFrame, "Marque du véhicule :");
                String model = JOptionPane.showInputDialog(vehicleManagementFrame, "Modèle du véhicule :");
                String year = JOptionPane.showInputDialog(vehicleManagementFrame, "Année du véhicule :");
                String price = JOptionPane.showInputDialog(vehicleManagementFrame, "Prix du véhicule (DA) :");

            
                String[][] newData = new String[data.length + 1][4];
                System.arraycopy(data, 0, newData, 0, data.length);
                newData[data.length] = new String[]{brand, model, year, price};
                table.setModel(new javax.swing.table.DefaultTableModel(newData, columns));
            }
        });

        JButton backButton = new JButton("Retour au tableau de bord");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vehicleManagementFrame.dispose();
       
                new MainDashboard();  
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
        whitePanel.add(buttonPanel, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, redPanel, whitePanel);
        splitPane.setDividerLocation(200); 
        splitPane.setDividerSize(0); 

       
        vehicleManagementFrame.add(splitPane, BorderLayout.CENTER);

        vehicleManagementFrame.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createVehicleManagementWindow());
    }
}

