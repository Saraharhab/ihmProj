package tp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ReservationManagementWindow {
    private String role;

    public ReservationManagementWindow(String role) {
        this.role = role;
        JFrame reservationManagementFrame = new JFrame("Gestion des réservations");
        reservationManagementFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reservationManagementFrame.setSize(600, 400);
        reservationManagementFrame.setLocationRelativeTo(null);
        reservationManagementFrame.setLayout(new BorderLayout());

        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);
        redPanel.setLayout(new BorderLayout());
        JLabel redLabel = new JLabel("Gestion des réservations");
        redLabel.setForeground(Color.WHITE);
        redLabel.setFont(new Font("Arial", Font.BOLD, 18));
        redLabel.setHorizontalAlignment(SwingConstants.CENTER);
        redPanel.add(redLabel, BorderLayout.CENTER);

        ImageIcon logoIcon = new ImageIcon("C:\\Users\\Administrator\\Desktop\\logo2.png");
        JLabel logoLabel = new JLabel(logoIcon);
        redPanel.add(logoLabel, BorderLayout.NORTH);

        reservationManagementFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                int width = reservationManagementFrame.getWidth();
                int height = reservationManagementFrame.getHeight();

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

        String[] columns = {"id_reservation", "id_vehicule", "id_client", "date_debut", "date_fin", "montant_total", "statut"};
        String[][] data = {
                {"1", "1", "Houda", "2024-12-19", "2024-12-20", "15000", "en cours"},
                {"2", "2", "Hana", "2024-07-11", "2024-07-12", "14000", "terminée"},
                {"3", "3", "Sarah", "2024-11-06", "2024-11-07", "16000", "annulée"},
                {"4", "4", "Hassina", "2024-09-27", "2024-09-28", "35000", "en cours"}
        };

        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        whitePanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addButton = new JButton("Ajouter une réservation");
        addButton.setBackground(Color.RED);
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id_vehicule = JOptionPane.showInputDialog(reservationManagementFrame, "ID du véhicule :");
                String id_client = JOptionPane.showInputDialog(reservationManagementFrame, "ID du client :");
                String date_debut = JOptionPane.showInputDialog(reservationManagementFrame, "Date de début (AAAA-MM-JJ) :");
                String date_fin = JOptionPane.showInputDialog(reservationManagementFrame, "Date de fin (AAAA-MM-JJ) :");
                String montant_total = JOptionPane.showInputDialog(reservationManagementFrame, "Montant total de la location :");
                String statut = JOptionPane.showInputDialog(reservationManagementFrame, "Statut de la réservation :");

                String[][] newData = new String[data.length + 1][7];
                System.arraycopy(data, 0, newData, 0, data.length);
                newData[data.length] = new String[]{String.valueOf(data.length + 1), id_vehicule, id_client, date_debut, date_fin, montant_total, statut};
                table.setModel(new javax.swing.table.DefaultTableModel(newData, columns));
            }
        });

       
        JButton modifyButton = new JButton("Modifier la réservation");
        modifyButton.setBackground(Color.RED);
        modifyButton.setForeground(Color.WHITE);
        modifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                 
                    String newDateDebut = JOptionPane.showInputDialog("Entrez la nouvelle date de début (AAAA-MM-JJ) :");
                    String newDateFin = JOptionPane.showInputDialog("Entrez la nouvelle date de fin (AAAA-MM-JJ) :");
                    String newMontantTotal = JOptionPane.showInputDialog("Entrez le nouveau montant total :");

                 
                    data[selectedRow][3] = newDateDebut; 
                    data[selectedRow][4] = newDateFin;    
                    data[selectedRow][5] = newMontantTotal; 
                    table.setModel(new javax.swing.table.DefaultTableModel(data, columns)); 
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une réservation à modifier.");
                }
            }
        });

        JButton cancelButton = new JButton("Annuler la réservation");
        cancelButton.setBackground(Color.RED);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                  
                    int confirmation = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir annuler cette réservation ?", "Annulation", JOptionPane.YES_NO_OPTION);
                    if (confirmation == JOptionPane.YES_OPTION) {
                        data[selectedRow][6] = "Annulée";  
                        table.setModel(new javax.swing.table.DefaultTableModel(data, columns)); 
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une réservation à annuler.");
                }
            }
        });

        JButton backButton = new JButton("Retour au tableau de bord");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reservationManagementFrame.dispose();

                switch (role) {
                    case "admin":
                        new MainDashboard();
                        break;
                    case "gestionnaire":
                        new MainDashboard1();
                        break;
                    case "utilisateur":
                        new MainDashboard2();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Rôle non reconnu !");
                }
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(cancelButton);
        whitePanel.add(buttonPanel, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, redPanel, whitePanel);
        splitPane.setDividerLocation(200);
        splitPane.setDividerSize(0);

        reservationManagementFrame.add(splitPane, BorderLayout.CENTER);

        reservationManagementFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ReservationManagementWindow("admin");
            }
        });
    }
}

