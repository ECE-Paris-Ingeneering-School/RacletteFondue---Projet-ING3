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

    private JPanel aVenirPanel(String nom, String specialite, String date, String heure) {
        JPanel aVenir = new JPanel();
        aVenir.setLayout(new BoxLayout(aVenir, BoxLayout.Y_AXIS));
        aVenir.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        aVenir.setPreferredSize(new Dimension(400, 100));
        aVenir.setMaximumSize(new Dimension(500, 100));
        aVenir.setBackground(Color.WHITE);
        aVenir.setAlignmentX(Component.LEFT_ALIGNMENT);
        aVenir.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30)); // marges

        JLabel nomLabel = new JLabel("👤 " + nom);
        nomLabel.setFont(new Font("Verdana", Font.BOLD, 18));

        JLabel speLabel = new JLabel(specialite);
        speLabel.setFont(new Font("Verdana", Font.PLAIN, 16));

        JLabel dateLabel = new JLabel(date + "  " + heure);
        dateLabel.setFont(new Font("Verdana", Font.PLAIN, 16));

        aVenir.add(nomLabel);
        aVenir.add(speLabel);
        aVenir.add(dateLabel);

        return aVenir;
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
        btnAccueil.setPreferredSize(new Dimension(250, 70));
        btnAccueil.setFont(new Font("Verdana", Font.BOLD, 20));
        btnAccueil.setBackground(new Color(221, 235, 247));
        btnAccueil.setFocusPainted(false);

        btnRendezVous = new JButton("Rendez-vous");
        btnRendezVous.setPreferredSize(new Dimension(250, 70));
        btnRendezVous.setFont(new Font("Verdana", Font.BOLD, 20));
        btnRendezVous.setForeground(Color.WHITE);
        btnRendezVous.setBackground(new Color(45, 104, 196));
        btnRendezVous.setFocusPainted(false);

        btnCompte = new JButton("Compte");
        btnCompte.setPreferredSize(new Dimension(250, 70));
        btnCompte.setFont(new Font("Verdana", Font.BOLD, 20));
        btnAccueil.setBackground(new Color(221, 235, 247));
        btnCompte.setFocusPainted(false);

        menuPanel.add(btnAccueil);
        menuPanel.add(btnRendezVous);
        menuPanel.add(btnCompte);
        mainPanel.add(menuPanel);
        mainPanel.add(Box.createVerticalStrut(30));

        // Contenu : deux colonnes
        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 30, 0)); // Deux colonnes avec un espace

        // Colonne gauche : RDV passés
        JPanel pastPanel = new JPanel();
        pastPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30)); // marges
        pastPanel.setLayout(new BoxLayout(pastPanel, BoxLayout.Y_AXIS));

        JLabel pastLabel = new JLabel("RDV passés");
        pastLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        pastPanel.add(pastLabel);
        pastPanel.add(Box.createVerticalStrut(20));

        pastPanel.add(aVenirPanel("Dr. Juif", "Psychologue", "21/02/25", "16h15"));
        pastPanel.add(Box.createVerticalStrut(15));
        pastPanel.add(aVenirPanel("Dr. Christ", "Dermatologue", "14/08/24", "16h15"));
        pastPanel.add(Box.createVerticalStrut(15));
        pastPanel.add(aVenirPanel("Dr. Juif", "Psychologue", "09/02/24", "16h15"));

        // Colonne droite : RDV à venir
        JPanel futurePanel = new JPanel();
        futurePanel.setLayout(new BoxLayout(futurePanel, BoxLayout.Y_AXIS));
        JLabel futureLabel = new JLabel("RDV à venir");
        futureLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        futurePanel.add(futureLabel);
        futurePanel.add(Box.createVerticalStrut(20));

        futurePanel.add(aVenirPanel("Dr. Juif", "Psychologue", "10/08/25", "16h15"));

        contentPanel.add(pastPanel);
        contentPanel.add(futurePanel);

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
