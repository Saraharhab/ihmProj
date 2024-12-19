package tp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

public class MainDashboard2 {

    public MainDashboard2() {
       
        JFrame frame = new JFrame("Tableau de bord - Gestion de Location de Véhicules");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); 

   
        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);
        
        JPanel whitePanel = new JPanel();
        whitePanel.setBackground(Color.WHITE);
        whitePanel.setLayout(new GridLayout(3, 2, 10, 10));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, redPanel, whitePanel);
        splitPane.setDividerLocation(200);
        splitPane.setDividerSize(0);
        
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\Administrator\\Desktop\\logo2.png");  
        JLabel logoLabel = new JLabel(logoIcon);
        redPanel.add(logoLabel, BorderLayout.NORTH);  
        
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
              
                int width = frame.getWidth();
                int height = frame.getHeight();

   

                int logoWidth = width / 6;
                int logoHeight = height / 6;
                ImageIcon resizedIcon = new ImageIcon(logoIcon.getImage().getScaledInstance(logoWidth, logoHeight, Image.SCALE_SMOOTH));
                logoLabel.setIcon(resizedIcon);

                int fontSize = Math.min(20, Math.max(12, width / 30));
                redPanel.setFont(new Font("Arial", Font.BOLD, fontSize));
            }
        });

        
        JLabel titleLabel = new JLabel("Bienvenue dans le tableau de bord :");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        redPanel.add(titleLabel, BorderLayout.CENTER); 

        
        JButton vehicleManagementButton = new JButton("Gestion des véhicules");
        vehicleManagementButton.setBackground(Color.WHITE);
        vehicleManagementButton.setForeground(Color.RED);
        vehicleManagementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new VehicleManagementWindow("gestionnaire");  
            }
        });

        JButton clientManagementButton = new JButton("Gestion des clients");
        clientManagementButton.setBackground(Color.WHITE);
        clientManagementButton.setForeground(Color.RED);
        clientManagementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new ClientManagementWindow("gestionnaire");
            }
        });

        JButton reservationManagementButton = new JButton("Gestion des réservations");
        reservationManagementButton.setBackground(Color.WHITE);
        reservationManagementButton.setForeground(Color.RED);
        reservationManagementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new ReservationManagementWindow("gestionnaire");  
            }
        });


        JButton logoutButton = new JButton("Se déconnecter");
        logoutButton.setBackground(Color.WHITE);
        logoutButton.setForeground(Color.RED);
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(frame, "Voulez-vous vraiment vous déconnecter ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    frame.dispose();  
                    new LoginWindow().main(null); 
                }
            }
        });
        whitePanel.add(vehicleManagementButton);
        whitePanel.add(clientManagementButton);
        whitePanel.add(reservationManagementButton);
        whitePanel.add(logoutButton);

     
        frame.add(splitPane);
        frame.setVisible(true); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainDashboard2();
            }
        });
    }
}
