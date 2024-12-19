package tp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class VehicleManagementWindow {
    private String role;

    public VehicleManagementWindow(String role) {
        this.role = role;
        JFrame vehicleManagementFrame = new JFrame("Gestion des véhicules");
        vehicleManagementFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vehicleManagementFrame.setSize(600, 400);
        vehicleManagementFrame.setLocationRelativeTo(null);
        vehicleManagementFrame.setLayout(new BorderLayout());

        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);
        redPanel.setLayout(new BorderLayout());
        JLabel redLabel = new JLabel("Gestion des véhicules:");
        redLabel.setForeground(Color.WHITE);
        redLabel.setFont(new Font("Arial", Font.BOLD, 18));
        redLabel.setHorizontalAlignment(SwingConstants.CENTER);
        redPanel.add(redLabel, BorderLayout.CENTER);

        ImageIcon logoIcon = new ImageIcon("C:\\Users\\Administrator\\Desktop\\logo2.png");
        JLabel logoLabel = new JLabel(logoIcon);
        redPanel.add(logoLabel, BorderLayout.NORTH);

        vehicleManagementFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                int width = vehicleManagementFrame.getWidth();
                int height = vehicleManagementFrame.getHeight();

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

        // Initialisation du modèle de table avec DefaultTableModel
        String[] columns = {"ID_Véhicule", "Marque", "Modèle", "Année", "Type", "Carburant", "Prix_Location_Jour (DA)", "État"};
        String[][] data = {
            {"1", "Toyota", "Corolla", "2020", "SUV", "Essence", "15000", "Disponible"},
            {"2", "Honda", "Civic", "2019", "Berline", "Essence", "14000", "Disponible"},
            {"3", "Ford", "Focus", "2021", "SUV", "Essence", "16000", "Disponible"},
            {"4", "Tesla", "Model 3", "2022", "Berline", "Essence", "35000", "Disponible"}
        };

        // Utilisation de DefaultTableModel pour faciliter l'ajout, la suppression et la modification des données
        DefaultTableModel tableModel = new DefaultTableModel(data, columns);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        whitePanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addButton = new JButton("Ajouter un véhicule");
        addButton.setBackground(Color.RED);
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id_vehicule = JOptionPane.showInputDialog(vehicleManagementFrame, "ID du véhicule :");
                String marque = JOptionPane.showInputDialog(vehicleManagementFrame, "Marque du véhicule :");
                String modele = JOptionPane.showInputDialog(vehicleManagementFrame, "Modèle du véhicule :");
                String annee = JOptionPane.showInputDialog(vehicleManagementFrame, "Année du véhicule :");
                String type = JOptionPane.showInputDialog(vehicleManagementFrame, "Type du véhicule :");
                String carburant = JOptionPane.showInputDialog(vehicleManagementFrame, "Type de carburant :");
                String prix_location_jour = JOptionPane.showInputDialog(vehicleManagementFrame, "Prix de location par jour (DA) :");
                String etat = JOptionPane.showInputDialog(vehicleManagementFrame, "État du véhicule :");

                // Ajout de la nouvelle ligne dans la table
                tableModel.addRow(new String[]{id_vehicule, marque, modele, annee, type, carburant, prix_location_jour, etat});
            }
        });

        JButton returnButton = new JButton("Retour et Inspection");
        returnButton.setBackground(Color.RED);
        returnButton.setForeground(Color.WHITE);
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String vehicleState = (String) table.getValueAt(selectedRow, 5);

                    String inspectionResult = JOptionPane.showInputDialog(vehicleManagementFrame, "État du véhicule après inspection (Disponible/Maintenance) :");

                    if (inspectionResult != null && !inspectionResult.trim().isEmpty()) {
                        table.setValueAt(inspectionResult, selectedRow, 5);
                        JOptionPane.showMessageDialog(vehicleManagementFrame, "L'état du véhicule a été mis à jour avec succès.");
                    } else {
                        JOptionPane.showMessageDialog(vehicleManagementFrame, "Veuillez entrer un état valide.");
                    }
                } else {
                    JOptionPane.showMessageDialog(vehicleManagementFrame, "Veuillez sélectionner un véhicule.");
                }
            }
        });

        JButton searchButton = new JButton("Rechercher");
        searchButton.setBackground(Color.RED);
        searchButton.setForeground(Color.WHITE);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchTerm = JOptionPane.showInputDialog("Entrer un terme de recherche :");

                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    for (int j = 0; j < tableModel.getColumnCount(); j++) {
                        if (tableModel.getValueAt(i, j).toString().toLowerCase().contains(searchTerm.toLowerCase())) {
                            table.setRowSelectionInterval(i, i);
                            break;
                        }
                    }
                }
            }
        });

        // Bouton Modifier
        JButton modifyButton = new JButton("Modifier le véhicule");
        modifyButton.setBackground(Color.RED);
        modifyButton.setForeground(Color.WHITE);
        modifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Afficher un dialogue pour modifier les informations du véhicule
                	String newMarque = JOptionPane.showInputDialog("Entrez la nouvelle marque  de véhicule :");
                	String newModel = JOptionPane.showInputDialog("Entrez le nouveau model de vehicule :");
                	 String newAnnee = JOptionPane.showInputDialog("Entrez la nouvelle annee de vehicule :");
                	 String newType = JOptionPane.showInputDialog("Entrez le nouveau type de véhicule :");
                     String newCarburant = JOptionPane.showInputDialog("Entrez le nouveau type de carburant :");
                    String newPrixLocationJour = JOptionPane.showInputDialog("Entrez le nouveau prix de location par jour (DA) :");
                    String newEtat = JOptionPane.showInputDialog("Entrez le nouveau etat de vehicule :");
                    
                   
                    // Mettre à jour la ligne dans la table
                    tableModel.setValueAt(newMarque, selectedRow, 1);
                    tableModel.setValueAt(newModel, selectedRow, 2);
                    tableModel.setValueAt(newAnnee, selectedRow, 3);
                    tableModel.setValueAt(newType, selectedRow, 4);
                    tableModel.setValueAt(newCarburant, selectedRow, 5);
                    tableModel.setValueAt(newPrixLocationJour, selectedRow, 6);
                    tableModel.setValueAt(newEtat, selectedRow, 7);

                } else {
                    JOptionPane.showMessageDialog(vehicleManagementFrame, "Veuillez sélectionner un véhicule à modifier.");
                }
            }
        });

        // Bouton Supprimer
        JButton deleteButton = new JButton("Supprimer le véhicule");
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int confirmation = JOptionPane.showConfirmDialog(vehicleManagementFrame, "Êtes-vous sûr de vouloir supprimer ce véhicule ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (confirmation == JOptionPane.YES_OPTION) {
                        // Supprimer la ligne de la table
                        tableModel.removeRow(selectedRow);
                    }
                } else {
                    JOptionPane.showMessageDialog(vehicleManagementFrame, "Veuillez sélectionner un véhicule à supprimer.");
                }
            }
        });

        JButton backButton = new JButton("Retour au tableau de bord");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vehicleManagementFrame.dispose();
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
        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(returnButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(backButton);
        whitePanel.add(buttonPanel, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, redPanel, whitePanel);
        splitPane.setDividerLocation(200);
        splitPane.setDividerSize(0);
        vehicleManagementFrame.add(splitPane);

        vehicleManagementFrame.setVisible(true);
    }


public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            new VehicleManagementWindow("admin");
        }
    });
}
}
