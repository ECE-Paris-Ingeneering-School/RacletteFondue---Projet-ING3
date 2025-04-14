package Vue;

import Modele.Specialiste;

import javax.swing.*;
import java.awt.*;

public class InfoDocteur extends JFrame {

    public JLabel titleLabel;

    public JButton btnAccueil;
    public JButton btnRendezVous;
    public JButton btnCompte;

    public JLabel doctorNameLabel;
    public JTextArea presentationValueLabel;
    public JLabel tarifValueLabel;
    public JLabel paiementValueLabel;
    public JLabel expertiseValueLabel;
    public JLabel accessValueLabel;
    public JButton prendreRDVButton;

    public Specialiste specialiste;

    public InfoDocteur() {
        setTitle("Information spécialiste");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setContentPane(buildPanel());
    }

    public JPanel buildPanel() {

        // Panel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Titre
        titleLabel = new JLabel("Information spécialiste");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 42));
        titleLabel.setForeground(new Color(45, 104, 196));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        // Menu boutons
        JPanel menuPanel = new JPanel();

        btnAccueil = new JButton("Accueil");
        styleMenuButton(btnAccueil);

        btnRendezVous = new JButton("Rendez-vous");
        styleMenuButton(btnRendezVous);

        btnCompte = new JButton("Compte");
        styleMenuButton(btnCompte);

        menuPanel.add(btnAccueil);
        menuPanel.add(btnRendezVous);
        menuPanel.add(btnCompte);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(menuPanel, gbc);

        if (specialiste == null) {

            return mainPanel;
        }

        // Nom du docteur
        doctorNameLabel = new JLabel("Dr. " + specialiste.getUtilisateurNom());
        doctorNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
        doctorNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        mainPanel.add(doctorNameLabel, gbc);

        // Présentation
        presentationValueLabel = new JTextArea(specialiste.getSpecialisteDescription());
        presentationValueLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        presentationValueLabel.setEditable(false);
        presentationValueLabel.setBackground(null);
        presentationValueLabel.setBorder(null);
        presentationValueLabel.setLineWrap(true);
        presentationValueLabel.setWrapStyleWord(true);
        presentationValueLabel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        mainPanel.add(presentationValueLabel, gbc);

        // Panel pour les catégories
        JPanel categoriesPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcCategories = new GridBagConstraints();
        gbcCategories.insets = new Insets(50, 100, 10, 100);
        gbcCategories.fill = GridBagConstraints.HORIZONTAL;

        // Colonne de gauche
        JPanel leftColumn = new JPanel();
        leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));

        JLabel tarifLabel = new JLabel("Tarif & remboursement");
        tarifLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        leftColumn.add(tarifLabel);
        leftColumn.add(Box.createVerticalStrut(20));

        tarifValueLabel = new JLabel(String.valueOf(specialiste.getSpecialisteTarif()) + " €");
        tarifValueLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        leftColumn.add(tarifValueLabel);
        leftColumn.add(Box.createVerticalStrut(20));

        JLabel paiementLabel = new JLabel("Moyen de paiement");
        paiementLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        leftColumn.add(paiementLabel);
        leftColumn.add(Box.createVerticalStrut(20));

        paiementValueLabel = new JLabel("Carte");
        paiementValueLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        leftColumn.add(paiementValueLabel);
        leftColumn.add(Box.createVerticalStrut(20));

        gbcCategories.gridx = 0;
        gbcCategories.gridy = 0;
        categoriesPanel.add(leftColumn, gbcCategories);

        // Colonne de droite
        JPanel rightColumn = new JPanel();
        rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));

        JLabel expertiseLabel = new JLabel("Expertise");
        expertiseLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        rightColumn.add(expertiseLabel);
        rightColumn.add(Box.createVerticalStrut(20));

        expertiseValueLabel = new JLabel(specialiste.getSpecialisteSpecialite());
        expertiseValueLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        rightColumn.add(expertiseValueLabel);
        rightColumn.add(Box.createVerticalStrut(20));

        JLabel accessLabel = new JLabel("Information d'accès");
        accessLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        rightColumn.add(accessLabel);
        rightColumn.add(Box.createVerticalStrut(20));

        String adresse = specialiste.getUtilisateurAdresse().getAdresseNumero() + " " + specialiste.getUtilisateurAdresse().getAdresseRue();

        accessValueLabel = new JLabel(adresse);
        accessValueLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        accessValueLabel.setBackground(null);
        accessValueLabel.setBorder(null);
        rightColumn.add(accessValueLabel);
        rightColumn.add(Box.createVerticalStrut(20));

        gbcCategories.gridx = 1;
        gbcCategories.gridy = 0;
        categoriesPanel.add(rightColumn, gbcCategories);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        mainPanel.add(categoriesPanel, gbc);

        // Bouton Prendre un rendez-vous
        prendreRDVButton = new JButton("Prendre un rendez-vous");
        prendreRDVButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        prendreRDVButton.setForeground(Color.WHITE);
        prendreRDVButton.setBackground(new Color(45, 104, 196));
        prendreRDVButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        mainPanel.add(prendreRDVButton, gbc);

        return mainPanel;
    }


    private void styleMenuButton(JButton button) {
        button.setPreferredSize(new Dimension(250, 70));
        button.setFont(new Font("Verdana", Font.BOLD, 20));
        button.setBackground(new Color(221, 235, 247));
        button.setFocusPainted(false);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new InfoDocteur().setVisible(true);
        });
    }
}
