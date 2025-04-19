package Vue;

import Modele.Adresse;
import Modele.Specialiste;
import Modele.Utilisateur;

import javax.swing.*;
import java.awt.*;

public class ModifierInfoDocteurAdmin extends JFrame {

    public JButton btnSpecialiste;
    public JButton btnDossierPatients;
    public JButton btnStatistiques;
    public JLabel imageField;
    public JButton btnChargerImage;
    public JTextField nomField;
    public JTextField specialiteField;
    public JTextField descriptionField;
    public JTextField tarifField;
    public JTextField numeroField;
    public JTextField rueField;
    public JTextField codePostalField;
    public JTextField villeField;
    public JButton modifierButton;
    public JButton supprimerButton;
    public JButton annulerButton;
    public JLabel erreurLabel;
    public ImageIcon cheminImage;

    public Specialiste specialiste;

    public ModifierInfoDocteurAdmin() {
        setTitle("Modifier Specialiste");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        Adresse adresse1 = new Adresse(75015, "Paris", "Rue de Javel",  "23");
        specialiste = new Specialiste(1, "Moche","Carbel", 54, adresse1, 'H', "lucie.daix@caca.com", "ZZZZ", "098765432", "", "Psy", "J'aide les gens", 40);


        setContentPane(buildPanel());
    }

    public JPanel buildPanel() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Titre
        JLabel titleLabel = new JLabel("Modifier un spécialiste");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 42));
        titleLabel.setForeground(new Color(45, 104, 196));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);


        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        JPanel menuPanel = new JPanel();

        btnSpecialiste = new JButton("Spécialistes");
        styleMenuButton(btnSpecialiste);

        btnDossierPatients = new JButton("Dossier Patients");
        styleMenuButton(btnDossierPatients);

        btnStatistiques = new JButton("Statistiques");
        styleMenuButton(btnStatistiques);

        menuPanel.add(btnSpecialiste);
        menuPanel.add(btnDossierPatients);
        menuPanel.add(btnStatistiques);

        mainPanel.add(menuPanel);
        mainPanel.add(Box.createVerticalStrut(20));




        // Panel principal avec GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Augmenter les marges pour une meilleure lisibilité
        gbc.fill = GridBagConstraints.HORIZONTAL;


        Font labelFont = new Font("Tahoma", Font.PLAIN, 18); // Définir une police plus grande


        // Affichage image
        if (specialiste.getUtilisateurImage().isEmpty()){

            cheminImage = new ImageIcon("src/Images/pdp_defaut.png");
        }
        else {
            cheminImage = new ImageIcon(specialiste.getUtilisateurImage());
        }

        Image image = cheminImage.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        imageField = new JLabel(new ImageIcon(image));
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(imageField, gbc);

        btnChargerImage = new JButton("Modifier votre photo");
        //btnChangerImage.setPreferredSize(new Dimension(200, 25)); // Augmenter la taille du bouton
        btnChargerImage.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnChargerImage.setBackground(new Color(45, 104, 196)); // Changer la couleur de fond
        btnChargerImage.setForeground(Color.WHITE);
        btnChargerImage.setFocusPainted(false);


        JLabel imageLabel = new JLabel("Photo de profil :");
        imageLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(imageLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(imageField, gbc);

        gbc.gridx = 1;
        gbc.gridy++;
        formPanel.add(btnChargerImage, gbc);


        // Champs
        JLabel nomLabel = new JLabel("Nom :");
        nomLabel.setFont(labelFont);
        nomField = new JTextField(specialiste.getUtilisateurNom(), 20);
        nomField.setFont(labelFont);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(nomLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nomField, gbc);


        JLabel specialiteLabel = new JLabel("Spécialité :");
        specialiteLabel.setFont(labelFont);
        specialiteField = new JTextField(specialiste.getSpecialisteSpecialite(), 20);
        specialiteField.setFont(labelFont);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(specialiteLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(specialiteField, gbc);


        JLabel descriptionLabel = new JLabel("Description :");
        descriptionLabel.setFont(labelFont);
        descriptionField = new JTextField(specialiste.getSpecialisteDescription(), 20);
        descriptionField.setFont(labelFont);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(descriptionLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(descriptionField, gbc);


        JLabel tarifLabel = new JLabel("Tarif (€) :");
        tarifLabel.setFont(labelFont);
        tarifField = new JTextField(String.valueOf(specialiste.getSpecialisteTarif()), 20);
        tarifField.setFont(labelFont);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(tarifLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(tarifField, gbc);


        JLabel adresseLabel = new JLabel("Adresse :");
        adresseLabel.setFont(labelFont);


        numeroField = new JTextField(specialiste.getUtilisateurAdresse().getAdresseNumero(), 5);
        numeroField.setFont(labelFont);

        rueField = new JTextField(specialiste.getUtilisateurAdresse().getAdresseRue(), 10);
        rueField.setFont(labelFont);

        codePostalField = new JTextField(String.valueOf(specialiste.getUtilisateurAdresse().getAdresseCodePostal()), 5);
        codePostalField.setFont(labelFont);

        villeField = new JTextField(specialiste.getUtilisateurAdresse().getAdresseVille(), 10);
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


        erreurLabel = new JLabel("");
        erreurLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        erreurLabel.setForeground(Color.RED);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        formPanel.add(erreurLabel, gbc);


        // Bouton Ajouter
        modifierButton = new JButton("Modifier");
        modifierButton.setPreferredSize(new Dimension(150, 50)); // Augmenter la taille du bouton
        modifierButton.setFont(new Font("Tahoma", Font.BOLD, 20)); // Augmenter la taille de la police
        modifierButton.setBackground(new Color(45, 104, 196)); // Changer la couleur de fond
        modifierButton.setForeground(Color.WHITE); // Changer la couleur du texte
        modifierButton.setFocusPainted(false);

        // Bouton Supprimer
        supprimerButton = new JButton("Supprimer");
        supprimerButton.setPreferredSize(new Dimension(150, 50)); // Augmenter la taille du bouton
        supprimerButton.setFont(new Font("Tahoma", Font.BOLD, 20)); // Augmenter la taille de la police
        supprimerButton.setBackground(new Color(207, 9, 31)); // Changer la couleur de fond
        supprimerButton.setForeground(Color.WHITE); // Changer la couleur du texte
        supprimerButton.setFocusPainted(false);


        // Bouton Annuler
        annulerButton = new JButton("Annuler");
        annulerButton.setPreferredSize(new Dimension(150, 50));
        annulerButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        annulerButton.setBackground(new Color(69, 73, 78));
        annulerButton.setForeground(Color.WHITE);
        annulerButton.setFocusPainted(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(modifierButton);
        buttonPanel.add(supprimerButton);
        buttonPanel.add(annulerButton);

        JPanel panelGeneral = new JPanel();

        panelGeneral.setLayout(new BorderLayout());

        panelGeneral.add(mainPanel, BorderLayout.NORTH);
        panelGeneral.add(formPanel, BorderLayout.CENTER);
        panelGeneral.add(buttonPanel, BorderLayout.SOUTH);

        return panelGeneral;
    }



    private void styleMenuButton(JButton button) {
        button.setPreferredSize(new Dimension(250, 70));
        button.setFont(new Font("Tahoma", Font.BOLD, 20));
        button.setBackground(new Color(221, 235, 247));
        button.setFocusPainted(false);
    }

    public static void main(String[] args) {



        SwingUtilities.invokeLater(() -> {

            new ModifierInfoDocteurAdmin().setVisible(true);
        });
    }
}
