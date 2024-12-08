import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.Vector;

public class GestionVehicules extends JFrame {
    private JTextField txtModel, txtBrand, txtYear, txtCapacity, txtPrice;
    private JComboBox<String> cmbFuelType, cmbStatus;
    private JButton btnSave, btnEdit, btnDelete, btnReset, btnSearch;
    private JTable tableVehicules;
    private DefaultTableModel modeleTable;
    private final String cheminFichier = "vehicules.txt"; // Fichier pour sauvegarder les données

    public GestionVehicules() {
        setTitle("Gestion de Véhicules");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menu à gauche
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.RED);
        menuPanel.setLayout(new GridLayout(6, 1, 10, 10));

        ImageIcon logoIcon = new ImageIcon("C:/Users/dell/Downloads/projet ihm/logo2.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(scaledImage), SwingConstants.CENTER);
        menuPanel.add(logo, BorderLayout.NORTH);

        JLabel lblMenu1 = createMenuLabel("Client");
        JLabel lblMenu2 = createMenuLabel("Louer une voiture");
        JLabel lblMenu3 = createMenuLabel("Retourner la voiture");
        JButton btnLogout = new JButton("Déconnexion");
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setBackground(Color.DARK_GRAY);

        menuPanel.add(lblMenu1);
        menuPanel.add(lblMenu2);
        menuPanel.add(lblMenu3);
        menuPanel.add(btnLogout);

        add(menuPanel, BorderLayout.WEST);

        // Contenu principal
        JPanel mainContentPanel = new JPanel(new BorderLayout());
        JLabel lblTitre = new JLabel("Gestion Véhicules", SwingConstants.CENTER);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitre.setForeground(Color.RED);
        mainContentPanel.add(lblTitre, BorderLayout.NORTH);

        JPanel panneauCentre = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        panneauCentre.add(createLabelWithField("Modèle:", txtModel = new JTextField(15)));
        panneauCentre.add(createLabelWithField("Marque:", txtBrand = new JTextField(15)));
        panneauCentre.add(createLabelWithField("Année:", txtYear = new JTextField(15)));
        panneauCentre.add(createLabelWithField("Capacité:", txtCapacity = new JTextField(15)));
        panneauCentre.add(createLabelWithField("Carburant:", cmbFuelType = new JComboBox<>(new String[]{"Essence", "Diesel", "Hybride", "Électrique"})));
        panneauCentre.add(createLabelWithField("État:", cmbStatus = new JComboBox<>(new String[]{"Disponible", "Loué", "En maintenance"})));
        panneauCentre.add(createLabelWithField("Prix/Jour:", txtPrice = new JTextField(15)));

        JPanel panneauBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        btnSave = new JButton("Enregistrer");
        btnEdit = new JButton("Modifier");
        btnDelete = new JButton("Supprimer");
        btnReset = new JButton("Réinitialiser");
        btnSearch = new JButton("Rechercher");
        panneauBoutons.add(btnSave);
        panneauBoutons.add(btnEdit);
        panneauBoutons.add(btnDelete);
        panneauBoutons.add(btnReset);
        panneauBoutons.add(btnSearch);

        JPanel panneauHaut = new JPanel(new BorderLayout());
        panneauHaut.add(panneauCentre, BorderLayout.CENTER);
        panneauHaut.add(panneauBoutons, BorderLayout.SOUTH);
        mainContentPanel.add(panneauHaut, BorderLayout.CENTER);

        modeleTable = new DefaultTableModel(new String[]{"Modèle", "Marque", "Année", "Capacité", "Carburant", "État", "Prix/Jour"}, 0);
        tableVehicules = new JTable(modeleTable);
        JScrollPane scrollPane = new JScrollPane(tableVehicules);
        mainContentPanel.add(scrollPane, BorderLayout.SOUTH);

        add(mainContentPanel, BorderLayout.CENTER);

        btnSave.addActionListener(e -> ajouterVehicule());
        btnEdit.addActionListener(e -> modifierVehicule());
        btnDelete.addActionListener(e -> supprimerVehicule());
        btnReset.addActionListener(e -> reinitialiserChamps());
        btnSearch.addActionListener(e -> rechercherVehicule());

        tableVehicules.getSelectionModel().addListSelectionListener(e -> remplirChampsDepuisTable());

        chargerVehicules(); // Charger les données depuis le fichier
    }

    private JPanel createLabelWithField(String labelText, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.RED);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(label, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    private JLabel createMenuLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private void rechercherVehicule() {
        String critere = JOptionPane.showInputDialog(this,
                "Entrez le modèle, la marque ou l'état du véhicule à rechercher :",
                "Recherche de véhicule", JOptionPane.QUESTION_MESSAGE);

        if (critere == null || critere.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un critère de recherche valide.",
                    "Aucun critère", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Rendre tout désélectionné d'abord
        tableVehicules.clearSelection();

        boolean trouve = false;
        for (int i = 0; i < modeleTable.getRowCount(); i++) {
            String modele = modeleTable.getValueAt(i, 0).toString();
            String marque = modeleTable.getValueAt(i, 1).toString();
            String etat = modeleTable.getValueAt(i, 5).toString();

            if (modele.equalsIgnoreCase(critere) ||
                    marque.equalsIgnoreCase(critere) ||
                    etat.equalsIgnoreCase(critere)) {
                tableVehicules.addRowSelectionInterval(i, i);
                trouve = true;
            }
        }

        if (!trouve) {
            JOptionPane.showMessageDialog(this, "Aucun véhicule trouvé correspondant au critère : " + critere,
                    "Non trouvé", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // (Toutes les autres méthodes restent inchangées, comme ajouterVehicule, modifierVehicule, etc.)

    private void ajouterVehicule() {
        if (!validerChamps()) return;
    
        modeleTable.addRow(new Object[]{
                txtModel.getText(),
                txtBrand.getText(),
                txtYear.getText(),
                txtCapacity.getText(),
                cmbFuelType.getSelectedItem(),
                cmbStatus.getSelectedItem(),
                txtPrice.getText()
        });
        sauvegarderVehicules();
        JOptionPane.showMessageDialog(this, "Véhicule ajouté avec succès !");
        reinitialiserChamps();
    }
    
    private void modifierVehicule() {
        int ligneSelectionnee = tableVehicules.getSelectedRow();
        if (ligneSelectionnee == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un véhicule à modifier.");
            return;
        }
        if (!validerChamps()) return;
    
        modeleTable.setValueAt(txtModel.getText(), ligneSelectionnee, 0);
        modeleTable.setValueAt(txtBrand.getText(), ligneSelectionnee, 1);
        modeleTable.setValueAt(txtYear.getText(), ligneSelectionnee, 2);
        modeleTable.setValueAt(txtCapacity.getText(), ligneSelectionnee, 3);
        modeleTable.setValueAt(cmbFuelType.getSelectedItem(), ligneSelectionnee, 4);
        modeleTable.setValueAt(cmbStatus.getSelectedItem(), ligneSelectionnee, 5);
        modeleTable.setValueAt(txtPrice.getText(), ligneSelectionnee, 6);
    
        sauvegarderVehicules();
        JOptionPane.showMessageDialog(this, "Véhicule modifié avec succès !");
    }
    
    private void supprimerVehicule() {
        int ligneSelectionnee = tableVehicules.getSelectedRow();
        if (ligneSelectionnee == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un véhicule à supprimer.");
            return;
        }
    
        modeleTable.removeRow(ligneSelectionnee);
        sauvegarderVehicules();
        JOptionPane.showMessageDialog(this, "Véhicule supprimé avec succès !");
    }
    
    private void remplirChampsDepuisTable() {
        int ligneSelectionnee = tableVehicules.getSelectedRow();
        if (ligneSelectionnee != -1) {
            txtModel.setText((String) modeleTable.getValueAt(ligneSelectionnee, 0));
            txtBrand.setText((String) modeleTable.getValueAt(ligneSelectionnee, 1));
            txtYear.setText((String) modeleTable.getValueAt(ligneSelectionnee, 2));
            txtCapacity.setText((String) modeleTable.getValueAt(ligneSelectionnee, 3));
            cmbFuelType.setSelectedItem(modeleTable.getValueAt(ligneSelectionnee, 4));
            cmbStatus.setSelectedItem(modeleTable.getValueAt(ligneSelectionnee, 5));
            txtPrice.setText((String) modeleTable.getValueAt(ligneSelectionnee, 6));
        }
    }
    
    private void reinitialiserChamps() {
        txtModel.setText("");
        txtBrand.setText("");
        txtYear.setText("");
        txtCapacity.setText("");
        txtPrice.setText("");
        cmbFuelType.setSelectedIndex(0);
        cmbStatus.setSelectedIndex(0);
    }
    
    private boolean validerChamps() {
        if (txtModel.getText().isEmpty() || txtBrand.getText().isEmpty() ||
                txtYear.getText().isEmpty() || txtCapacity.getText().isEmpty() || txtPrice.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
            return false;
        }
        return true;
    }
    
    private void chargerVehicules() {
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] donnees = ligne.split(",");
                modeleTable.addRow(donnees);
            }
        } catch (IOException e) {
            System.out.println("Fichier introuvable. Aucun véhicule chargé.");
        }
    }
    //foncion pour sauvegarder vehicule dans un fichier 
    private void sauvegarderVehicules() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) {
            for (int i = 0; i < modeleTable.getRowCount(); i++) {
                Vector<Object> ligne = modeleTable.getDataVector().elementAt(i);
                writer.write(String.join(",", ligne.toArray(new String[0])));
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la sauvegarde des véhicules.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestionVehicules frame = new GestionVehicules();
            frame.setVisible(true);
        });
    }
}
