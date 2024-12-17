package tp;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ClientManagementWindow {

     
    public ClientManagementWindow() {
        JFrame clientManagementFrame = new JFrame("Gestion des clients");
        clientManagementFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientManagementFrame.setSize(600, 400);
        clientManagementFrame.setLocationRelativeTo(null);
        clientManagementFrame.setLayout(new BorderLayout());
 
        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);
        redPanel.setLayout(new BorderLayout());
        
        JLabel redLabel = new JLabel("Gestion des clients");
        redLabel.setForeground(Color.WHITE);
        redLabel.setFont(new Font("Arial", Font.BOLD, 18));
        redLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        redPanel.add(redLabel, BorderLayout.CENTER);
        
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\Administrator\\Desktop\\logo2.png");  
        JLabel logoLabel = new JLabel(logoIcon);
        redPanel.add(logoLabel, BorderLayout.NORTH);  
        
        clientManagementFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
              
                int width = clientManagementFrame.getWidth();
                int height = clientManagementFrame.getHeight();

   

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
        


        String[] columns = {"Nom", "Email", "Téléphone", "Adresse"};
        String[][] data = {
            {"Houda", "houda@gmail.com", "0698765432", "ZERALDA"},
            {"Hana", "hana@gmail.com", "0756439812", "BOUMERDES"},
            {"Sarah", "sarah@gmail.com", "0555413278", "ALGER"},
            {"Hanane", "hanane@gmail.com", "0541786543", "BOUMERDES"},
        };
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        whitePanel.add(scrollPane, BorderLayout.CENTER);

      
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addButton = new JButton("Ajouter un client");
        addButton.setBackground(Color.RED);
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(clientManagementFrame, "Nom du client :");
                String email = JOptionPane.showInputDialog(clientManagementFrame, "Email du client :");
                String phone = JOptionPane.showInputDialog(clientManagementFrame, "Téléphone du client :");
                String address = JOptionPane.showInputDialog(clientManagementFrame, "Adresse du client :");

               
                String[][] newData = new String[data.length + 1][4];
                System.arraycopy(data, 0, newData, 0, data.length);
                newData[data.length] = new String[]{name, email, phone, address};
                table.setModel(new javax.swing.table.DefaultTableModel(newData, columns));
            }
        });

        JButton backButton = new JButton("Retour au tableau de bord");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clientManagementFrame.dispose();
                 
                new MainDashboard();  
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
        whitePanel.add(buttonPanel, BorderLayout.SOUTH);

     
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, redPanel, whitePanel);
        splitPane.setDividerLocation(200);  
        splitPane.setDividerSize(0);  
 
        clientManagementFrame.add(splitPane, BorderLayout.CENTER);

        clientManagementFrame.setVisible(true);
    }

  
    public static void main(String[] args) {
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ClientManagementWindow() ; 
            }
        });
    }
}




