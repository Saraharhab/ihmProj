package tp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainDashboard3 {
    private String role;
    private List<String[]> rentals;
    private DefaultTableModel rentalsTableModel;

    public MainDashboard3(String role) {
        this.role = role;
        this.rentals = new ArrayList<>(); // List to hold user's rentals

        // Simulating some rental data for testing
        rentals.add(new String[]{"1", "Toyota Corolla", "2023-01-01", "2023-01-07"});
        rentals.add(new String[]{"2", "Honda Civic", "2023-02-01", "2023-02-05"});

        JFrame clientFrame = new JFrame("Réservation de Véhicules");
        clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientFrame.setSize(800, 400);
        clientFrame.setLocationRelativeTo(null);
        clientFrame.setLayout(new BorderLayout());

        // Left Red Panel with sections
        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);
        redPanel.setLayout(new BoxLayout(redPanel, BoxLayout.Y_AXIS));

        // Available Vehicles button
        JButton availableVehiclesButton = new JButton("Véhicules Disponibles");
        availableVehiclesButton.setBackground(Color.RED);
        availableVehiclesButton.setForeground(Color.WHITE);
        availableVehiclesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        availableVehiclesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Display the available vehicles in the main content area
                showAvailableVehicles();
            }
        });

        // My Rentals button
        JButton myRentalsButton = new JButton("Mes Locations");
        myRentalsButton.setBackground(Color.RED);
        myRentalsButton.setForeground(Color.WHITE);
        myRentalsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        myRentalsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Display the user's rental information
                showMyRentals();
            }
        });

        // Add buttons to the left red panel
        redPanel.add(availableVehiclesButton);
        redPanel.add(Box.createVerticalStrut(20));  // Space between buttons
        redPanel.add(myRentalsButton);

        // Main content panel (white area)
        JPanel whitePanel = new JPanel();
        whitePanel.setBackground(Color.WHITE);
        whitePanel.setLayout(new BorderLayout());

        // Table to show available vehicles
        String[] columns = {"ID_Véhicule", "Marque", "Modèle", "Année", "Type", "Carburant", "Prix_Location_Jour (DA)", "État"};
        Object[][] data = {
            {"1", "Toyota", "Corolla", "2020", "SUV", "Essence", "15000", "Disponible"},
            {"2", "Honda", "Civic", "2019", "Berline", "Essence", "14000", "Disponible"},
            {"3", "Ford", "Focus", "2021", "SUV", "Essence", "16000", "Disponible"},
            {"4", "Tesla", "Model 3", "2022", "Berline", "Essence", "35000", "Non Disponible"}
        };

        DefaultTableModel tableModel = new DefaultTableModel(data, columns);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        whitePanel.add(scrollPane, BorderLayout.CENTER);

        // Panel for the "Reserve" button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton reserveButton = new JButton("Réserver");
        reserveButton.setBackground(Color.RED);
        reserveButton.setForeground(Color.WHITE);
        reserveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String vehicleState = (String) table.getValueAt(selectedRow, 7);
                    if (vehicleState.equals("Disponible")) {
                        String vehicleId = (String) table.getValueAt(selectedRow, 0);
                        String vehicleModel = (String) table.getValueAt(selectedRow, 2);
                        String reservationConfirmation = "Vous avez réservé le véhicule : " + vehicleModel + " (ID: " + vehicleId + ").";
                        JOptionPane.showMessageDialog(clientFrame, reservationConfirmation, "Confirmation de réservation", JOptionPane.INFORMATION_MESSAGE);

                        // Mark the car as "Non Disponible"
                        table.setValueAt("Non Disponible", selectedRow, 7);

                        // Add to rentals
                        rentals.add(new String[]{vehicleId, vehicleModel, "2023-12-19", "2023-12-21"});
                        
                        // Update the "Mes Locations" table
                        updateRentalsTable();
                    } else {
                        JOptionPane.showMessageDialog(clientFrame, "Désolé, ce véhicule n'est pas disponible.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(clientFrame, "Veuillez sélectionner un véhicule.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonPanel.add(reserveButton);
        whitePanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add panels to the frame
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, redPanel, whitePanel);
        splitPane.setDividerLocation(200);
        clientFrame.add(splitPane);

        clientFrame.setVisible(true);

        // Create the rentals table (Mes Locations)
        createRentalsTable(clientFrame);
    }

    private void createRentalsTable(JFrame clientFrame) {
        // Create a table to show user's rentals
        String[] rentalsColumns = {"ID", "Véhicule", "Date de début", "Date de fin"};
        rentalsTableModel = new DefaultTableModel(rentalsColumns, 0);
        JTable rentalsTable = new JTable(rentalsTableModel);
        JScrollPane rentalsScrollPane = new JScrollPane(rentalsTable);
        
        // Create a new window for "Mes Locations"
        JFrame rentalsFrame = new JFrame("Mes Locations");
        rentalsFrame.setSize(600, 300);
        rentalsFrame.setLocationRelativeTo(clientFrame);
        rentalsFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        rentalsFrame.add(rentalsScrollPane, BorderLayout.CENTER);
        
        // Add a button to close the "Mes Locations" window
        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rentalsFrame.dispose();
            }
        });
        rentalsFrame.add(closeButton, BorderLayout.SOUTH);

        rentalsFrame.setVisible(false); // Hide it initially
    }

    private void showAvailableVehicles() {
        // This method can be used to refresh the display of available vehicles in the main content area
        JOptionPane.showMessageDialog(null, "Displaying available vehicles.");
    }

    private void showMyRentals() {
        // Show the user's current rentals
        if (rentals.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vous n'avez aucune location.");
        } else {
            // Show the "Mes Locations" window
            JFrame rentalsFrame = new JFrame("Mes Locations");
            rentalsFrame.setSize(600, 300);
            rentalsFrame.setLocationRelativeTo(null);
            rentalsFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

            // Create the table for "Mes Locations"
            String[] rentalsColumns = {"ID", "Véhicule", "Date de début", "Date de fin"};
            DefaultTableModel rentalsTableModel = new DefaultTableModel(rentalsColumns, 0);

            for (String[] rental : rentals) {
                rentalsTableModel.addRow(rental);
            }

            JTable rentalsTable = new JTable(rentalsTableModel);
            JScrollPane rentalsScrollPane = new JScrollPane(rentalsTable);
            rentalsFrame.add(rentalsScrollPane, BorderLayout.CENTER);

            // Add a button to close the "Mes Locations" window
            JButton closeButton = new JButton("Fermer");
            closeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    rentalsFrame.dispose();
                }
            });
            rentalsFrame.add(closeButton, BorderLayout.SOUTH);

            rentalsFrame.setVisible(true);
        }
    }

    private void updateRentalsTable() {
        // Update the "Mes Locations" table when a new reservation is made
        for (String[] rental : rentals) {
            rentalsTableModel.addRow(rental);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainDashboard3("client");
            }
        });
    }
}