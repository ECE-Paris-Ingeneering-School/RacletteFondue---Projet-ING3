package Vue;

import javax.swing.*;
import java.awt.*;

public class RendezVous extends JFrame {

    public JButton btnAccueil;
    public JButton btnRendezVous;
    public JButton btnCompte;

    public RendezVous() {
        setTitle("Rendez-vous");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setContentPane(buildPanel());
    }

    private JPanel createScrollList(String title, String[][] data) {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));

        JLabel sectionTitle = new JLabel(title);
        sectionTitle.setFont(new Font("Verdana", Font.BOLD, 22));
        sectionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        sectionPanel.add(sectionTitle);
        sectionPanel.add(Box.createVerticalStrut(20));

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        for (String[] rdv : data) {
            listPanel.add(createRdvPanel(rdv[0], rdv[1], rdv[2], rdv[3]));
            listPanel.add(Box.createVerticalStrut(10));
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        scrollPane.setMaximumSize(new Dimension(600, 400));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        sectionPanel.add(scrollPane);
        return sectionPanel;
    }

    private JPanel createRdvPanel(String nom, String specialite, String date, String heure) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(221, 235, 247));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setMaximumSize(new Dimension(550, 100));

        // Partie gauche : image
        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = loadProfileImage(); // méthode pour charger l'image
        imageLabel.setIcon(imageIcon);
        imageLabel.setPreferredSize(new Dimension(64, 64));
        panel.add(imageLabel, BorderLayout.WEST);

        // Partie droite : infos RDV
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);

        JLabel nomLabel = new JLabel(nom);
        nomLabel.setFont(new Font("Verdana", Font.BOLD, 16));

        JLabel speLabel = new JLabel(specialite);
        speLabel.setFont(new Font("Verdana", Font.PLAIN, 15));

        JLabel dateLabel = new JLabel(date + "  " + heure);
        dateLabel.setFont(new Font("Verdana", Font.PLAIN, 15));

        textPanel.add(nomLabel);
        textPanel.add(speLabel);
        textPanel.add(dateLabel);

        panel.add(textPanel, BorderLayout.CENTER);

        return panel;
    }

    private ImageIcon loadProfileImage() {

        String path = "Images/pdp_defaut.png";

        ImageIcon icon;
        try {
            Image img = new ImageIcon(path).getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
        } catch (Exception e) {
            // Image par défaut si erreur
            Image img = new ImageIcon("Images/pdp_defaut.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
        }

        return icon;
    }

    private void styleMenuButton(JButton button) {
        button.setPreferredSize(new Dimension(250, 70));
        button.setFont(new Font("Verdana", Font.BOLD, 20));
        button.setBackground(new Color(221, 235, 247));
        button.setFocusPainted(false);
    }

    public JPanel buildPanel() {

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Titre
        JLabel titleLabel = new JLabel("Rendez-vous");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 42));
        titleLabel.setForeground(new Color(45, 104, 196));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Menu navigation
        JPanel menuPanel = new JPanel();

        btnAccueil = new JButton("Accueil");
        styleMenuButton(btnAccueil);

        btnRendezVous = new JButton("Rendez-vous");
        styleMenuButton(btnRendezVous);
        btnRendezVous.setForeground(Color.WHITE);
        btnRendezVous.setBackground(new Color(45, 104, 196));

        btnCompte = new JButton("Compte");
        styleMenuButton(btnCompte);

        menuPanel.add(btnAccueil);
        menuPanel.add(btnRendezVous);
        menuPanel.add(btnCompte);
        mainPanel.add(menuPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        // RDV passés et à venir
        String[][] rdvPasses = {
                {"Dr. Juif", "Psychologue", "21/02/25", "16h15"},
                {"Dr. Christ", "Dermatologue", "14/08/24", "16h15"},
                {"Dr. Juif", "Psychologue", "09/02/24", "16h15"},
                {"Dr. Juif", "Psychologue", "21/02/25", "16h15"},
                {"Dr. Christ", "Dermatologue", "14/08/24", "16h15"},
                {"Dr. Juif", "Psychologue", "09/02/24", "16h15"},
                {"Dr. Juif", "Psychologue", "21/02/25", "16h15"},
                {"Dr. Christ", "Dermatologue", "14/08/24", "16h15"},
                {"Dr. Juif", "Psychologue", "09/02/24", "16h15"},
                {"Dr. Juif", "Psychologue", "21/02/25", "16h15"},
                {"Dr. Christ", "Dermatologue", "14/08/24", "16h15"},
                {"Dr. Juif", "Psychologue", "09/02/24", "16h15"},
        };

        String[][] rdvAvenir = {
                {"Dr. Juif", "Psychologue", "10/08/25", "16h15"},
                {"Dr. Christ", "Dermatologue", "05/11/25", "09h30"},
                {"Dr. Leclair", "Généraliste", "12/12/25", "11h00"},
                {"Dr. Juif", "Psychologue", "21/02/25", "16h15"},
                {"Dr. Christ", "Dermatologue", "14/08/24", "16h15"},
                {"Dr. Juif", "Psychologue", "09/02/24", "16h15"},
                {"Dr. Juif", "Psychologue", "21/02/25", "16h15"},
                {"Dr. Christ", "Dermatologue", "14/08/24", "16h15"},
                {"Dr. Juif", "Psychologue", "09/02/24", "16h15"},
                {"Dr. Juif", "Psychologue", "21/02/25", "16h15"},
                {"Dr. Christ", "Dermatologue", "14/08/24", "16h15"},
                {"Dr. Juif", "Psychologue", "09/02/24", "16h15"},
                {"Dr. Juif", "Psychologue", "21/02/25", "16h15"},
                {"Dr. Christ", "Dermatologue", "14/08/24", "16h15"},
                {"Dr. Juif", "Psychologue", "09/02/24", "16h15"},
        };

        // Contenu : deux colonnes avec listes déroulantes
        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 30, 0));
        contentPanel.setMaximumSize(new Dimension(1300, 500));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // RDV passés
        contentPanel.add(createScrollList("RDV passés", rdvPasses));

        // RDV à venir
        contentPanel.add(createScrollList("RDV à venir", rdvAvenir));

        mainPanel.add(contentPanel);
        mainPanel.add(Box.createVerticalStrut(30));

        return mainPanel;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new RendezVous().setVisible(true);
        });
    }
}
