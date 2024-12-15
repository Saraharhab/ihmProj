package tp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportingHistoryWindow {

    public static void createReportingHistoryWindow() {
        JFrame reportingHistoryFrame = new JFrame("Reporting et Historique");
        reportingHistoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reportingHistoryFrame.setSize(600, 400);
        reportingHistoryFrame.setLayout(new BorderLayout());

      
        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);
        redPanel.setLayout(new GridBagLayout());
        JLabel redLabel = new JLabel("Reporting et Historique");
        redLabel.setForeground(Color.WHITE);
        redLabel.setFont(new Font("Arial", Font.BOLD, 14));
        redPanel.add(redLabel);

      
        JPanel whitePanel = new JPanel();
        whitePanel.setBackground(Color.WHITE);
        whitePanel.setLayout(new BorderLayout());

        
        String[] columns = {"Date", "Événement", "Description", "Action"};
        String[][] data = {
            {"2024-12-01", "Réservation", "Réservation de Houda", "Confirmée"},
            {"2024-12-02", "Annulation", "Annulation de Hana", "Annulée"},
            {"2024-12-05", "Modification", "Modification de réservation pour Sarah", "Confirmée"},
            {"2024-12-07", "Réservation", "Réservation de Hanane", "Confirmée"},
        };
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        whitePanel.add(scrollPane, BorderLayout.CENTER);

         
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton exportButton = new JButton("Exporter le rapport");
        exportButton.setBackground(Color.RED);
        exportButton.setForeground(Color.WHITE);
        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 
                JOptionPane.showMessageDialog(reportingHistoryFrame, "Rapport exporté avec succès!", "Exportation", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton backButton = new JButton("Retour au tableau de bord");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reportingHistoryFrame.dispose();
              
                new MainDashboard();  
            }
        });

        buttonPanel.add(exportButton);
        buttonPanel.add(backButton);
        whitePanel.add(buttonPanel, BorderLayout.SOUTH);

         
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, redPanel, whitePanel);
        splitPane.setDividerLocation(200);  
        splitPane.setDividerSize(0);  
 
        reportingHistoryFrame.add(splitPane, BorderLayout.CENTER);

        reportingHistoryFrame.setVisible(true);
    }

     
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createReportingHistoryWindow());
    }
}

