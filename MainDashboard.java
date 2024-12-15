package tp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainDashboard {

    public MainDashboard() {
        
        JFrame frame = new JFrame("Tableau de bord - Gestion de Location de Véhicules");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); 

        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        
        JLabel titleLabel = new JLabel("Bienvenue dans le tableau de bord :", JLabel.CENTER);
        titleLabel.setForeground(Color.RED);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 10, 10)); 

       
        JButton userManagementButton = new JButton("Gestion des utilisateurs");
        userManagementButton.setBackground(Color.RED);
        userManagementButton.setForeground(Color.WHITE);
        userManagementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UserManagementWindow(); 
                frame.setVisible(false);
            }
        });

        JButton vehicleManagementButton = new JButton("Gestion des véhicules");
        vehicleManagementButton.setBackground(Color.RED);
        vehicleManagementButton.setForeground(Color.WHITE);
        vehicleManagementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VehicleManagementWindow(); 
                frame.setVisible(false); 
            }
        });

        JButton clientManagementButton = new JButton("Gestion des clients");
        clientManagementButton.setBackground(Color.RED);
        clientManagementButton.setForeground(Color.WHITE);
        clientManagementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ClientManagementWindow(); 
                frame.setVisible(false); 
            }
        });

        JButton reservationManagementButton = new JButton("Gestion des réservations");
        reservationManagementButton.setBackground(Color.RED);
        reservationManagementButton.setForeground(Color.WHITE);
        reservationManagementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ReservationManagementWindow(); 
                frame.setVisible(false); 
            }
        });

        JButton reportingButton = new JButton("Reporting et historique");
        reportingButton.setBackground(Color.RED);
        reportingButton.setForeground(Color.WHITE);
        reportingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ReportingHistoryWindow(); 
                frame.setVisible(false);
            }
        });

        JButton logoutButton = new JButton("Se déconnecter");
        logoutButton.setBackground(Color.RED);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(frame, "Voulez-vous vraiment vous déconnecter ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    frame.dispose(); 
                    new LoginWindow().main(null); 
                }
            }
        });

      
        buttonPanel.add(userManagementButton);
        buttonPanel.add(vehicleManagementButton);
        buttonPanel.add(clientManagementButton);
        buttonPanel.add(reservationManagementButton);
        buttonPanel.add(reportingButton);
        buttonPanel.add(logoutButton);


        panel.add(buttonPanel, BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainDashboard();
            }
        });
    }
}
