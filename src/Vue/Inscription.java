package Vue;

import javax.swing.*;
import java.awt.*;

public class Inscription extends JFrame {

    public JTextField mailField;
    public JPasswordField passwordField;
    public JPasswordField confirmPasswordField;
    public JTextField nomField;
    public JTextField prenomField;
    public JTextField ageField;
    public JTextField telephoneField;
    public JTextField adresseField;
    public JRadioButton hommeRadio;
    public JRadioButton femmeRadio;
    public JButton inscrireButton;
    public JLabel erreurLabel;

    public Inscription() {
        setTitle("Créer un compte");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        setContentPane(buildPanel());
    }

    public JPanel buildPanel() {

        // Titre "Inscription"
        JLabel titleLabel = new JLabel("Inscription");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 42));
        titleLabel.setForeground(new Color(45, 104, 196));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //add(titleLabel, BorderLayout.NORTH);

        // Panel principal avec GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Augmenter les marges pour une meilleure lisibilité
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Les champs
        String[] labels = {
                "Mail :", "Créer un mot de passe :", "Confirmer le mot de passe :",
                "Nom :", "Prénom :", "Âge :", "Numéro de téléphone :", "Sexe :", "Adresse postale :"
        };

        Font labelFont = new Font("Verdana", Font.PLAIN, 18); // Définir une police plus grande

        JLabel mailLabel = new JLabel(labels[0]);
        mailLabel.setFont(labelFont);
        mailField = new JTextField(20);
        mailField.setPreferredSize(new Dimension(400, 40)); // Augmenter la taille du champ
        mailField.setFont(new Font("Verdana", Font.PLAIN, 18));

        JLabel passwordLabel = new JLabel(labels[1]);
        passwordLabel.setFont(labelFont);
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(400, 40)); // Augmenter la taille du champ
        passwordField.setFont(new Font("Verdana", Font.PLAIN, 18));

        JLabel confirmPasswordLabel = new JLabel(labels[2]);
        confirmPasswordLabel.setFont(labelFont);
        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setPreferredSize(new Dimension(400, 40)); // Augmenter la taille du champ
        confirmPasswordField.setFont(new Font("Verdana", Font.PLAIN, 18));

        JLabel nomLabel = new JLabel(labels[3]);
        nomLabel.setFont(labelFont);
        nomField = new JTextField(20);
        nomField.setPreferredSize(new Dimension(400, 40)); // Augmenter la taille du champ
        nomField.setFont(new Font("Verdana", Font.PLAIN, 18));

        JLabel prenomLabel = new JLabel(labels[4]);
        prenomLabel.setFont(labelFont);
        prenomField = new JTextField(20);
        prenomField.setPreferredSize(new Dimension(400, 40)); // Augmenter la taille du champ
        prenomField.setFont(new Font("Verdana", Font.PLAIN, 18));

        JLabel ageLabel = new JLabel(labels[5]);
        ageLabel.setFont(labelFont);
        ageField = new JTextField(20);
        ageField.setPreferredSize(new Dimension(400, 40)); // Augmenter la taille du champ
        ageField.setFont(new Font("Verdana", Font.PLAIN, 18));

        JLabel telephoneLabel = new JLabel(labels[6]);
        telephoneLabel.setFont(labelFont);
        telephoneField = new JTextField(20);
        telephoneField.setPreferredSize(new Dimension(400, 40)); // Augmenter la taille du champ
        telephoneField.setFont(new Font("Verdana", Font.PLAIN, 18));

        // Agrandir les labels Sexe et les boutons radio Homme et Femme
        JLabel sexeLabel = new JLabel(labels[7]);
        sexeLabel.setFont(labelFont);

        hommeRadio = new JRadioButton("Homme");
        hommeRadio.setFont(labelFont);

        femmeRadio = new JRadioButton("Femme");
        femmeRadio.setFont(labelFont);

        ButtonGroup sexeGroup = new ButtonGroup();
        sexeGroup.add(hommeRadio);
        sexeGroup.add(femmeRadio);

        gbc.gridx = 0;
        gbc.gridy = 0;
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
        formPanel.add(confirmPasswordLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(confirmPasswordField, gbc);

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
        formPanel.add(sexeLabel, gbc);
        gbc.gridx = 1;
        JPanel sexePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sexePanel.add(hommeRadio);
        sexePanel.add(femmeRadio);
        formPanel.add(sexePanel, gbc);

        JLabel adresseLabel = new JLabel(labels[8]);
        adresseLabel.setFont(labelFont);
        adresseField = new JTextField(20);
        adresseField.setPreferredSize(new Dimension(400, 40)); // Augmenter la taille du champ
        adresseField.setFont(new Font("Verdana", Font.PLAIN, 18));

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(adresseLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(adresseField, gbc);

        erreurLabel = new JLabel("");
        erreurLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        erreurLabel.setForeground(Color.RED);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(erreurLabel, gbc);

        //add(formPanel, BorderLayout.CENTER);

        // Bouton S'inscrire
        inscrireButton = new JButton("S'inscrire");
        inscrireButton.setPreferredSize(new Dimension(150, 50)); // Augmenter la taille du bouton
        inscrireButton.setFont(new Font("Verdana", Font.BOLD, 20)); // Augmenter la taille de la police
        inscrireButton.setBackground(new Color(45, 104, 196)); // Changer la couleur de fond
        inscrireButton.setForeground(Color.WHITE); // Changer la couleur du texte
        inscrireButton.setFocusPainted(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(inscrireButton);
        //add(buttonPanel, BorderLayout.SOUTH);

        JPanel panelGeneral = new JPanel();

        panelGeneral.setLayout(new BorderLayout());

        panelGeneral.add(titleLabel, BorderLayout.NORTH);
        panelGeneral.add(formPanel, BorderLayout.CENTER);
        panelGeneral.add(buttonPanel, BorderLayout.SOUTH);

        return panelGeneral;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Inscription().setVisible(true);
        });
    }
}
