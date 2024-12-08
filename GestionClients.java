import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.nio.file.*;

public class GestionClients extends JFrame { // Class name changed to GestionClients
    private JTextField txtNom, txtPrenom, txtPermis, txtAdresse, txtTelephone;
    private JButton btnEnregistrer, btnRechercher, btnSupprimer;
    private JTable tableClients;
    private DefaultTableModel modeleTable;

    private final String cheminFichier = Paths.get(System.getProperty("user.home"), "Documents", "clients.txt").toString();

    public GestionClients() { // Constructor name changed to GestionClients
        setTitle("Gestion des Clients");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panneau gauche : Menu latéral avec logo et options
        JPanel panneauMenu = new JPanel();
        panneauMenu.setLayout(new BorderLayout());
        panneauMenu.setBackground(Color.RED);

        // Logo
        ImageIcon logoIcon = new ImageIcon("C:/Users/dell/Downloads/projet ihm/logo2.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(scaledImage), SwingConstants.CENTER);
        panneauMenu.add(lblLogo, BorderLayout.NORTH);

        // Menu des boutons
        JPanel menuBoutons = new JPanel(new GridLayout(6, 1, 10, 10));
        menuBoutons.setBackground(Color.RED);

        JLabel lblClients = createMenuLabel("Clients");
        JLabel lblLouer = createMenuLabel("Louer une voiture");
        JLabel lblRetourner = createMenuLabel("Retourner une voiture");
        JButton btnDeconnexion = new JButton("Déconnexion");
        btnDeconnexion.setForeground(Color.WHITE);
        btnDeconnexion.setBackground(Color.DARK_GRAY);

        menuBoutons.add(lblClients);
        menuBoutons.add(lblLouer);
        menuBoutons.add(lblRetourner);
        menuBoutons.add(btnDeconnexion);

        panneauMenu.add(menuBoutons, BorderLayout.CENTER);
        add(panneauMenu, BorderLayout.WEST);

        // Contenu principal
        JPanel mainContentPanel = new JPanel(new BorderLayout());
        JLabel lblTitre = new JLabel("Gestion Clients", SwingConstants.CENTER);
        lblTitre.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitre.setForeground(Color.RED);
        mainContentPanel.add(lblTitre, BorderLayout.NORTH);

        // Panneau pour les champs
        JPanel panneauChamps = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        
        // Champs avec JLabels en rose et JTextFields avec la même couleur de fond
        JLabel lblNom = new JLabel("Nom :");
        lblNom.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNom.setForeground(Color.RED); // Couleur rose
        txtNom = new JTextField(10);
        txtNom.setFont(new Font("Arial", Font.PLAIN, 16));
        txtNom.setBackground(Color.WHITE); // Couleur rose de fond

        JLabel lblPrenom = new JLabel("Prénom :");
        lblPrenom.setFont(new Font("Arial", Font.PLAIN, 16));
        lblPrenom.setForeground(Color.RED);
        txtPrenom = new JTextField(10);
        txtPrenom.setFont(new Font("Arial", Font.PLAIN, 16));
        txtPrenom.setBackground(Color.WHITE);

        JLabel lblPermis = new JLabel("N° Permis :");
        lblPermis.setFont(new Font("Arial", Font.PLAIN, 16));
        lblPermis.setForeground(Color.RED);
        txtPermis = new JTextField(10);
        txtPermis.setFont(new Font("Arial", Font.PLAIN, 16));
        txtPermis.setBackground(Color.WHITE);

        JLabel lblAdresse = new JLabel("Adresse :");
        lblAdresse.setFont(new Font("Arial", Font.PLAIN, 16));
        lblAdresse.setForeground(Color.RED);
        txtAdresse = new JTextField(15);
        txtAdresse.setFont(new Font("Arial", Font.PLAIN, 16));
        txtAdresse.setBackground(Color.WHITE);

        JLabel lblTelephone = new JLabel("Téléphone :");
        lblTelephone.setFont(new Font("Arial", Font.PLAIN, 16));
        lblTelephone.setForeground(Color.RED);
        txtTelephone = new JTextField(10);
        txtTelephone.setFont(new Font("Arial", Font.PLAIN, 16));
        txtTelephone.setBackground(Color.WHITE);

        // Ajouter les labels et les champs de texte au panneau
        panneauChamps.add(lblNom);
        panneauChamps.add(txtNom);
        panneauChamps.add(lblPrenom);
        panneauChamps.add(txtPrenom);
        panneauChamps.add(lblPermis);
        panneauChamps.add(txtPermis);
        panneauChamps.add(lblAdresse);
        panneauChamps.add(txtAdresse);
        panneauChamps.add(lblTelephone);
        panneauChamps.add(txtTelephone);

        // Boutons sous les champs
        JPanel panneauBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        btnEnregistrer = new JButton("Enregistrer");
        panneauBoutons.add(btnEnregistrer);

        btnRechercher = new JButton("Rechercher");
        panneauBoutons.add(btnRechercher);

        btnSupprimer = new JButton("Supprimer");
        panneauBoutons.add(btnSupprimer);

        panneauChamps.add(panneauBoutons);
        mainContentPanel.add(panneauChamps, BorderLayout.CENTER);

        // Tableau pour afficher les clients
        modeleTable = new DefaultTableModel(new String[]{"Nom", "Prénom", "Permis", "Adresse", "Téléphone"}, 0);
        tableClients = new JTable(modeleTable);
        mainContentPanel.add(new JScrollPane(tableClients), BorderLayout.SOUTH);

        add(mainContentPanel, BorderLayout.CENTER);

        // Actions des boutons
        btnEnregistrer.addActionListener(e -> enregistrerClient());
        btnRechercher.addActionListener(e -> rechercherClient());
        btnSupprimer.addActionListener(e -> supprimerClient());
        btnDeconnexion.addActionListener(e -> System.exit(0)); // Fermer l'application

        // Charger les données existantes
        chargerDonnees();
    }

    private JLabel createMenuLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private void enregistrerClient() {
        String nom = txtNom.getText();
        String prenom = txtPrenom.getText();
        String permis = txtPermis.getText();
        String adresse = txtAdresse.getText();
        String telephone = txtTelephone.getText();

        if (nom.isEmpty() || prenom.isEmpty() || permis.isEmpty() || adresse.isEmpty() || telephone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
            return;
        }

        String ligne = nom + "," + prenom + "," + permis + "," + adresse + "," + telephone;

        // Ajouter au tableau
        modeleTable.addRow(new String[]{nom, prenom, permis, adresse, telephone});

        // Ajouter au fichier
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier, true))) {
            writer.write(ligne);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Client enregistré avec succès !");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement : " + ex.getMessage());
        }

        // Réinitialiser les champs
        txtNom.setText("");
        txtPrenom.setText("");
        txtPermis.setText("");
        txtAdresse.setText("");
        txtTelephone.setText("");
    }

    private void rechercherClient() {
        String recherche = JOptionPane.showInputDialog(this, "Entrez le nom, prénom ou le permis à rechercher :");

        if (recherche == null || recherche.isEmpty()) {
            return;
        }

        boolean trouve = false;
        for (int i = 0; i < modeleTable.getRowCount(); i++) {
            String nom = (String) modeleTable.getValueAt(i, 0);
            String prenom = (String) modeleTable.getValueAt(i, 1);
            String permis = (String) modeleTable.getValueAt(i, 2);

            if (nom.equalsIgnoreCase(recherche) || prenom.equalsIgnoreCase(recherche) || permis.equalsIgnoreCase(recherche)) {
                tableClients.setRowSelectionInterval(i, i);
                trouve = true;
                break;
            }
        }

        if (!trouve) {
            JOptionPane.showMessageDialog(this, "Aucun client trouvé avec cette recherche.");
        }
    }

    private void supprimerClient() {
        int ligneSelectionnee = tableClients.getSelectedRow();
        if (ligneSelectionnee == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un client à supprimer.");
            return;
        }

        modeleTable.removeRow(ligneSelectionnee);
        JOptionPane.showMessageDialog(this, "Client supprimé avec succès.");
    }

    private void chargerDonnees() {
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] donnees = ligne.split(",");
                modeleTable.addRow(donnees);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des données : " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GestionClients().setVisible(true); // Updated to new class name
        });
    }
}
