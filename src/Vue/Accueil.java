package Vue;

import javax.swing.*;
import java.awt.*;

public class Accueil extends JFrame {

    public JButton btnAccueil;
    public JButton btnRendezVous;
    public JButton btnCompte;
    public JTextField searchField;
    public JButton searchButton;

    public Accueil() {
        setTitle("Accueil");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setContentPane(buildPanel());

    }

    public JPanel buildPanel() {

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Titre
        JLabel titleLabel = new JLabel("Accueil");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 42));
        titleLabel.setForeground(new Color(45, 104, 196));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Menu boutons
        JPanel menuPanel = new JPanel();

        btnAccueil = new JButton("Accueil");
        btnAccueil.setPreferredSize(new Dimension(250, 70));
        btnAccueil.setFont(new Font("Verdana", Font.BOLD, 20));
        btnAccueil.setForeground(Color.WHITE);
        btnAccueil.setBackground(new Color(45, 104, 196));
        btnAccueil.setFocusPainted(false);

        btnRendezVous = new JButton("Rendez-vous");
        btnRendezVous.setPreferredSize(new Dimension(250, 70));
        btnRendezVous.setFont(new Font("Verdana", Font.BOLD, 20));
        btnRendezVous.setBackground(new Color(221, 235, 247));
        btnRendezVous.setFocusPainted(false);

        btnCompte = new JButton("Compte");
        btnCompte.setPreferredSize(new Dimension(250, 70));
        btnCompte.setFont(new Font("Verdana", Font.BOLD, 20));
        btnCompte.setBackground(new Color(221, 235, 247));
        btnCompte.setFocusPainted(false);

        menuPanel.add(btnAccueil);
        menuPanel.add(btnRendezVous);
        menuPanel.add(btnCompte);
        mainPanel.add(menuPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Barre de recherche
        JPanel searchPanel = new JPanel();

        searchField = new JTextField("Nom, spécialité, lieu...", 30); // Agrandit la largeur du champ de recherche
        searchField.setFont(new Font("Verdana", Font.PLAIN, 18));
        searchField.setPreferredSize(new Dimension(400, 40));


        searchButton = new JButton("🔍");
        searchButton.setPreferredSize(new Dimension(80, 40));
        searchButton.setBackground(new Color(221, 235, 247));
        searchButton.setFocusPainted(false);

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        mainPanel.add(searchPanel);
        mainPanel.add(Box.createVerticalStrut(30));


        //add(mainPanel);

        return mainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Accueil::new);
    }
}
