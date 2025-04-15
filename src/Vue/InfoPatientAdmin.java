package Vue;

import javax.swing.*;
import java.awt.*;

public class InfoPatientAdmin extends JFrame {

    public InfoPatientAdmin() {
        setTitle("Informations Patient");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(buildPanel());
    }

    public JPanel buildPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Titre
        JLabel titleLabel = new JLabel("Mme Y - ADMIN");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 42));
        titleLabel.setForeground(new Color(45, 104, 196));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));


        // Infos perso
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(6, 2, 20, 10));
        infoPanel.setMaximumSize(new Dimension(600, 200));
        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Font labelFont = new Font("Tahoma", Font.PLAIN, 20);

        infoPanel.add(createInfoLabel("Nom :", labelFont));
        infoPanel.add(createInfoLabel("__________", labelFont));

        infoPanel.add(createInfoLabel("Prénom :", labelFont));
        infoPanel.add(createInfoLabel("__________", labelFont));

        infoPanel.add(createInfoLabel("Date de naissance :", labelFont));
        infoPanel.add(createInfoLabel("__________", labelFont));

        infoPanel.add(createInfoLabel("Téléphone :", labelFont));
        infoPanel.add(createInfoLabel("__________", labelFont));

        infoPanel.add(createInfoLabel("Email :", labelFont));
        infoPanel.add(createInfoLabel("__________", labelFont));

        infoPanel.add(createInfoLabel("Mot de passe :", labelFont));
        infoPanel.add(createInfoLabel("********", labelFont));

        mainPanel.add(infoPanel);
        mainPanel.add(Box.createVerticalStrut(30));

        // RDV passés et à venir
        String[][] rdvPasses = {
                {"Dr. Juif", "Psychologue", "21/02/25", "16h15"},
                {"Dr. Christ", "Dermatologue", "14/08/24", "16h15"},
                {"Dr. Juif", "Psychologue", "09/02/24", "16h15"},
                {"Dr. Juif", "Psychologue", "21/02/25", "16h15"},
                {"Dr. Christ", "Dermatologue", "14/08/24", "16h15"},
                {"Dr. Juif", "Psychologue", "09/02/24", "16h15"},
        };

        String[][] rdvAvenir = {
                {"Dr. Juif", "Psychologue", "10/08/25", "16h15"},
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

        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 30, 0));
        contentPanel.setMaximumSize(new Dimension(1300, 500));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(createScrollList("RDV passés", rdvPasses));
        contentPanel.add(createScrollList("RDV à venir", rdvAvenir));

        mainPanel.add(contentPanel);

        return mainPanel;
    }

    private JPanel createScrollList(String title, String[][] data) {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));

        JLabel sectionTitle = new JLabel(title);
        sectionTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
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

        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(loadProfileImage());
        imageLabel.setPreferredSize(new Dimension(64, 64));
        panel.add(imageLabel, BorderLayout.WEST);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);

        JLabel nomLabel = new JLabel(nom);
        nomLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

        JLabel speLabel = new JLabel(specialite);
        speLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));

        JLabel dateLabel = new JLabel(date + "  " + heure);
        dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));

        textPanel.add(nomLabel);
        textPanel.add(speLabel);
        textPanel.add(dateLabel);

        panel.add(textPanel, BorderLayout.CENTER);

        return panel;
    }

    private JLabel createInfoLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }


    private ImageIcon loadProfileImage() {
        String path = "Images/pdp_defaut.png";

        try {
            Image img = new ImageIcon(path).getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            Image img = new ImageIcon("Images/pdp_defaut.png").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InfoPatientAdmin().setVisible(true));
    }
}
