package Vue;

import Modele.Utilisateur;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.*;


public class Compte extends JFrame {

    public JButton btnAccueil;
    public JButton btnRendezVous;
    public JButton btnCompte;
    public JLabel imageField;
    public JButton btnChangerImage;
    public JTextField nomField;
    public JTextField prenomField;
    public JTextField ageField;
    public JTextField telephoneField;
    public JTextField mailField;
    public JPasswordField passwordField;
    public JTextField numeroField;
    public JTextField rueField;
    public JTextField codePostalField;
    public JTextField villeField;
    public JButton modifierButton;
    public JLabel confirmationLabel;
    public ImageIcon cheminImage;

    public Utilisateur utilisateur;


    public Compte() {
        setTitle("Informations du compte");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        setContentPane(buildPanel());
    }

    private void addPlaceholder(JTextField textField, String placeholder) {
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);

        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }


    public JPanel buildPanel() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Compte");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 42));
        titleLabel.setForeground(new Color(45, 104, 196));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        JPanel menuPanel = new JPanel();

        btnAccueil = new JButton("Accueil");
        btnAccueil.setPreferredSize(new Dimension(250, 70));
        btnAccueil.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnAccueil.setBackground(new Color(221, 235, 247));
        btnAccueil.setFocusPainted(false);

        btnRendezVous = new JButton("Rendez-vous");
        btnRendezVous.setPreferredSize(new Dimension(250, 70));
        btnRendezVous.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnRendezVous.setBackground(new Color(221, 235, 247));
        btnRendezVous.setFocusPainted(false);

        btnCompte = new JButton("Compte");
        btnCompte.setPreferredSize(new Dimension(250, 70));
        btnCompte.setFont(new Font("Tahoma", Font.BOLD, 20));
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


        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Tahoma", Font.PLAIN, 18);

        // Affichage image


        if (utilisateur.getUtilisateurImage().isEmpty()){
            cheminImage=new ImageIcon("src/Images/pdp_defaut.png");
        }
        else {
            cheminImage = new ImageIcon(utilisateur.getUtilisateurImage());
        }
        Image image = cheminImage.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        imageField = new JLabel(new ImageIcon(image));



        btnChangerImage = new JButton("Changer votre photo");
        //btnChangerImage.setPreferredSize(new Dimension(200, 25)); // Augmenter la taille du bouton
        btnChangerImage.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnChangerImage.setBackground(new Color(45, 104, 196)); // Changer la couleur de fond
        btnChangerImage.setForeground(Color.WHITE);
        btnChangerImage.setFocusPainted(false);



        JLabel imageLabel = new JLabel("Photo de profil :");
        imageLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(imageLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(imageField, gbc);

        gbc.gridx = 1;
        gbc.gridy++;
        formPanel.add(btnChangerImage, gbc);


        // Champs
        JLabel nomLabel = new JLabel("Nom :");
        nomLabel.setFont(labelFont);
        //nomField = new JTextField("Jacquier", 20);
        nomField = new JTextField(utilisateur.getUtilisateurNom(), 20);
        nomField.setFont(labelFont);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(nomLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nomField, gbc);

        JLabel prenomLabel = new JLabel("Prénom :");
        prenomLabel.setFont(labelFont);
        //prenomField = new JTextField("Ma meuf", 20);
        prenomField = new JTextField(utilisateur.getUtilisateurPrenom(), 20);
        prenomField.setFont(labelFont);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(prenomLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(prenomField, gbc);

        JLabel ageLabel = new JLabel("Age :");
        ageLabel.setFont(labelFont);
        //ageField = new JTextField(String.valueOf("20"), 20);
        ageField = new JTextField(String.valueOf(utilisateur.getUtilisateurAge()), 20);
        ageField.setFont(labelFont);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(ageLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(ageField, gbc);

        JLabel telephoneLabel = new JLabel("Numéro de téléphone :");
        telephoneLabel.setFont(labelFont);
        //telephoneField = new JTextField("07.68.65.39.45", 20);
        telephoneField = new JTextField(utilisateur.getUtilisateurTel(), 20);
        telephoneField.setFont(labelFont);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(telephoneLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(telephoneField, gbc);

        JLabel mailLabel = new JLabel("Mail :");
        mailLabel.setFont(labelFont);
        //mailField = new JTextField("lucie.daix@orange.fr", 20);
        mailField = new JTextField(utilisateur.getUtilisateurMail(), 20);
        mailField.setFont(labelFont);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(mailLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(mailField, gbc);

        JLabel passwordLabel = new JLabel("Mot de passe :");
        passwordLabel.setFont(labelFont);
        //passwordField = new JPasswordField("caca", 20);
        passwordField = new JPasswordField(utilisateur.getUtilisateurPassword(), 20);
        passwordField.setFont(labelFont);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        JLabel adresseLabel = new JLabel("Adresse :");
        adresseLabel.setFont(labelFont);

        //numeroField = new JTextField(String.valueOf(36), 5);
        numeroField = new JTextField(utilisateur.getUtilisateurAdresse().getAdresseNumero(), 5);
        numeroField.setFont(labelFont);
       

        //rueField = new JTextField("15", 5);
        rueField = new JTextField(utilisateur.getUtilisateurAdresse().getAdresseRue(), 10);
        rueField.setFont(labelFont);


        //codePostalField = new JTextField(String.valueOf(13), 10);
        codePostalField = new JTextField(String.valueOf(utilisateur.getUtilisateurAdresse().getAdresseCodePostal()), 5);
        codePostalField.setFont(labelFont);


        //villeField = new JTextField("14", 10);
        villeField = new JTextField(utilisateur.getUtilisateurAdresse().getAdresseVille(), 10);
        villeField.setFont(labelFont);


        JPanel adressePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        adressePanel.add(numeroField);
        adressePanel.add(rueField);
        adressePanel.add(codePostalField);
        adressePanel.add(villeField);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        formPanel.add(adresseLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(adressePanel, gbc);

        confirmationLabel = new JLabel("");
        confirmationLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        confirmationLabel.setForeground(Color.GREEN);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        formPanel.add(confirmationLabel, gbc);

        modifierButton = new JButton("Modifier");
        modifierButton.setPreferredSize(new Dimension(150, 50));
        modifierButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        modifierButton.setBackground(new Color(45, 104, 196));
        modifierButton.setForeground(Color.WHITE);
        modifierButton.setFocusPainted(false);

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
