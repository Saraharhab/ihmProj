package tp;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ReservationManagementWindow {

 
    public ReservationManagementWindow() {
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
      

      
        String[] columns = {"Client", "Véhicule", "Date de réservation", "Heure"};
        String[][] data = {
            {"Houda", "Véhicule A", "2024-12-19", "14:00"},
            {"Hana", "Véhicule B", "2024-07-11", "10:30"},
            {"Sarah", "Véhicule C", "2024-11-06", "16:45"},
            {"Hanane", "Véhicule D", "2024-09-27", "09:00"},
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
                String client = JOptionPane.showInputDialog(reservationManagementFrame, "Nom du client :");
                String vehicle = JOptionPane.showInputDialog(reservationManagementFrame, "Véhicule réservé :");
                String date = JOptionPane.showInputDialog(reservationManagementFrame, "Date de réservation (AAAA-MM-JJ) :");
                String time = JOptionPane.showInputDialog(reservationManagementFrame, "Heure de réservation (HH:MM) :");

                
                String[][] newData = new String[data.length + 1][4];
                System.arraycopy(data, 0, newData, 0, data.length);
                newData[data.length] = new String[]{client, vehicle, date, time};
                table.setModel(new javax.swing.table.DefaultTableModel(newData, columns));
            }
        });

        JButton backButton = new JButton("Retour au tableau de bord");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reservationManagementFrame.dispose();
                 
                new MainDashboard();  
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
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
                new ReservationManagementWindow(); 
            }
        });
    }
}
