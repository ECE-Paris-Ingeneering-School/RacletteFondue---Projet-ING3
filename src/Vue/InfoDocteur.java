package Vue;

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
    public JTextArea accessValueLabel;
    public JButton prendreRDVButton;


    public InfoDocteur() {
        setTitle("Information spécialiste");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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
        btnAccueil.setPreferredSize(new Dimension(250, 70));
        btnAccueil.setFont(new Font("Verdana", Font.BOLD, 20));
        btnAccueil.setBackground(new Color(221, 235, 247));
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
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(menuPanel, gbc);

        // Nom du docteur
        doctorNameLabel = new JLabel("Dr. Juiph");
        doctorNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
        doctorNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        mainPanel.add(doctorNameLabel, gbc);

        // Présentation
        presentationValueLabel = new JTextArea("Le chirurgien-dentiste, aussi appelé dentiste, prend en charge les problèmes bucco-dentaires. Ce spécialiste de la dentition s'occupe aussi bien des dents, des gencives et des nerfs que des maxillaires. Les patients peuvent notamment le consulter pour un détartrage, le soin d'une carie, le soulagement de gencives irritées ou encore pour la réparation d'une dent abîmée.");
        presentationValueLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
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
        gbcCategories.insets = new Insets(10, 10, 10, 10);
        gbcCategories.fill = GridBagConstraints.HORIZONTAL;

        // Colonne de gauche
        JPanel leftColumn = new JPanel();
        leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));

        JLabel tarifLabel = new JLabel("Tarif & remboursement");
        tarifLabel.setFont(new Font("Verdana", Font.PLAIN, 22));
        leftColumn.add(tarifLabel);
        leftColumn.add(Box.createVerticalStrut(10));

        tarifValueLabel = new JLabel("60€");
        tarifValueLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        leftColumn.add(tarifValueLabel);
        leftColumn.add(Box.createVerticalStrut(20));

        JLabel paiementLabel = new JLabel("Moyen de paiement");
        paiementLabel.setFont(new Font("Verdana", Font.PLAIN, 22));
        leftColumn.add(paiementLabel);
        leftColumn.add(Box.createVerticalStrut(20));

        paiementValueLabel = new JLabel("Carte");
        paiementValueLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        leftColumn.add(paiementValueLabel);
        leftColumn.add(Box.createVerticalStrut(20));

        gbcCategories.gridx = 0;
        gbcCategories.gridy = 0;
        categoriesPanel.add(leftColumn, gbcCategories);

        // Colonne de droite
        JPanel rightColumn = new JPanel();
        rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));

        JLabel expertiseLabel = new JLabel("Expertise");
        expertiseLabel.setFont(new Font("Verdana", Font.PLAIN, 22));
        rightColumn.add(expertiseLabel);
        rightColumn.add(Box.createVerticalStrut(20));

        expertiseValueLabel = new JLabel("chirurgien-dentiste ");
        expertiseValueLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        rightColumn.add(expertiseValueLabel);
        rightColumn.add(Box.createVerticalStrut(20));

        JLabel accessLabel = new JLabel("Information d'accès");
        accessLabel.setFont(new Font("Verdana", Font.PLAIN, 22));
        rightColumn.add(accessLabel);
        rightColumn.add(Box.createVerticalStrut(20));

        accessValueLabel = new JTextArea("189 rue de Javel, code : 92B4, 6e étage avec ascenseur");
        accessValueLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
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
        prendreRDVButton.setFont(new Font("Verdana", Font.BOLD, 20));
        prendreRDVButton.setForeground(Color.WHITE);
        prendreRDVButton.setBackground(new Color(45, 104, 196));
        prendreRDVButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        mainPanel.add(prendreRDVButton, gbc);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InfoDocteur::new);
    }
}
