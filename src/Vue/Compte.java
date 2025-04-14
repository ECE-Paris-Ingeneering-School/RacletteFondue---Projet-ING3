package Vue;

import Modele.Patient;
import Modele.Utilisateur;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Compte extends JFrame {

    public JButton btnAccueil;
    public JButton btnRendezVous;
    public JButton btnCompte;
    public JTextField imageField;
    public JTextField nomField;
    public JTextField prenomField;
    public JTextField ageField;
    public JTextField telephoneField;
    public JTextField mailField;
    public JPasswordField passwordField;
    public JTextField adresseField;
    public JButton modifierButton;
    public JLabel confirmationLabel;

    public Utilisateur utilisateur;

    public Compte() {
        setTitle("Informations du compte");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        setContentPane(buildPanel());
    }

    public JPanel buildPanel() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Titre "Compte"
        JLabel titleLabel = new JLabel("Compte");
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
        btnCompte.setForeground(Color.WHITE);
        btnCompte.setBackground(new Color(45, 104, 196));
        btnCompte.setFocusPainted(false);

        menuPanel.add(btnAccueil);
        menuPanel.add(btnRendezVous);
        menuPanel.add(btnCompte);
        mainPanel.add(menuPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        if (utilisateur == null) {

            return mainPanel;
        }

        // Label "Informations personnelles"
        JLabel infoPerso = new JLabel("Informations personnelles");
        infoPerso.setFont(new Font("Verdana", Font.BOLD, 24));
        infoPerso.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(infoPerso);
        mainPanel.add(Box.createVerticalStrut(20));

        // Panel principal avec GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Augmenter les marges pour une meilleure lisibilité
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Les champs
        String[] labels = {
                "Photo de profil :",
                "Nom :",
                "Prénom :",
                "Age :",
                "Numéro de téléphone :",
                "Mail :",
                "Mot de passe :",
                "Adresse :"
        };

        Font labelFont = new Font("Tahoma", Font.PLAIN, 18); // Définir une police plus grande

        JLabel imageLabel = new JLabel(labels[0]);
        imageLabel.setFont(labelFont);
        imageField = new JTextField(utilisateur.getUtilisateurImage(), 20);
        imageField.setPreferredSize(new Dimension(100, 100)); // Augmenter la taille de l'image
        imageField.setFont(new Font("Tahoma", Font.PLAIN, 18)); //pas nécessaire pour une image mais je code à l'aveugle

        JLabel nomLabel = new JLabel(labels[1]);
        nomLabel.setFont(labelFont);
        nomField = new JTextField(utilisateur.getUtilisateurNom(), 20);
        nomField.setPreferredSize(new Dimension(400, 40)); // Augmenter la taille du champ
        nomField.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel prenomLabel = new JLabel(labels[2]);
        prenomLabel.setFont(labelFont);
        prenomField = new JTextField(utilisateur.getUtilisateurPrenom(), 20);
        prenomField.setPreferredSize(new Dimension(400, 40)); // Augmenter la taille du champ
        prenomField.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel ageLabel = new JLabel(labels[3]);
        ageLabel.setFont(labelFont);
        ageField = new JTextField(String.valueOf(utilisateur.getUtilisateurAge()), 20);
        ageField.setPreferredSize(new Dimension(400, 40)); // Augmenter la taille du champ
        ageField.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel telephoneLabel = new JLabel(labels[4]);
        telephoneLabel.setFont(labelFont);
        telephoneField = new JTextField(utilisateur.getUtilisateurTel(), 20);
        telephoneField.setPreferredSize(new Dimension(400, 40)); // Augmenter la taille du champ
        telephoneField.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel mailLabel = new JLabel(labels[5]);
        mailLabel.setFont(labelFont);
        mailField = new JTextField(utilisateur.getUtilisateurMail(), 20);
        mailField.setPreferredSize(new Dimension(400, 40)); // Augmenter la taille du champ
        mailField.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel passwordLabel = new JLabel(labels[6]);
        passwordLabel.setFont(labelFont);
        passwordField = new JPasswordField(utilisateur.getUtilisateurPassword(), 20);
        passwordField.setPreferredSize(new Dimension(400, 40)); // Augmenter la taille du champ
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel adresseLabel = new JLabel(labels[7]);
        adresseLabel.setFont(labelFont);
        adresseField = new JTextField(String.valueOf(utilisateur.getUtilisateurAdresse()), 20);
        adresseField.setPreferredSize(new Dimension(400, 40)); // Augmenter la taille du champ
        adresseField.setFont(new Font("Tahoma", Font.PLAIN, 18));

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(imageLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(imageField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(nomLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nomField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(prenomLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(prenomField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(ageLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(ageField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(telephoneLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(telephoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(mailLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(mailField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(adresseLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(adresseField, gbc);

        confirmationLabel = new JLabel("");
        confirmationLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        confirmationLabel.setForeground(Color.GREEN);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(confirmationLabel, gbc);

        // Bouton Modifier
        modifierButton = new JButton("Modifier");
        modifierButton.setPreferredSize(new Dimension(150, 50)); // Augmenter la taille du bouton
        modifierButton.setFont(new Font("Verdana", Font.BOLD, 20)); // Augmenter la taille de la police
        modifierButton.setBackground(new Color(45, 104, 196)); // Changer la couleur de fond
        modifierButton.setForeground(Color.WHITE); // Changer la couleur du texte
        modifierButton.setFocusPainted(false);

        /*modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                utilisateur.getUtilisateurNom(nomField.getText());
                utilisateur.getUtilisateurPrenom(prenomField.getText());
                utilisateur.getUtilisateurAge(Integer.parseInt(ageField.getText()));
                utilisateur.getUtilisateurTel(telephoneField.getText());
                utilisateur.getUtilisateurMail(mailField.getText());
                utilisateur.getUtilisateurPassword(new String(passwordField.getPassword()));

                confirmationLabel.setText("Les nouvelles informations ont été enregistrées");
            }
        });*/



        JPanel buttonPanel = new JPanel();
        buttonPanel.add(modifierButton);

        JPanel panelGeneral = new JPanel();
        panelGeneral.setLayout(new BorderLayout());

        panelGeneral.add(mainPanel, BorderLayout.NORTH);
        panelGeneral.add(formPanel, BorderLayout.CENTER);
        panelGeneral.add(buttonPanel, BorderLayout.SOUTH);

        return panelGeneral;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Compte().setVisible(true);
        });
    }
}
