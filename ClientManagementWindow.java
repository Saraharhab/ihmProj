package tp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientManagementWindow {

     
    public static void createClientManagementWindow() {
        JFrame clientManagementFrame = new JFrame("Gestion des clients");
        clientManagementFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientManagementFrame.setSize(600, 400);
        clientManagementFrame.setLayout(new BorderLayout());
 
        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);
        redPanel.setLayout(new GridBagLayout());
        JLabel redLabel = new JLabel("Gestion des clients");
        redLabel.setForeground(Color.WHITE);
        redLabel.setFont(new Font("Arial", Font.BOLD, 14));
        redPanel.add(redLabel);

       
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
        SwingUtilities.invokeLater(() -> createClientManagementWindow());
    }
}
