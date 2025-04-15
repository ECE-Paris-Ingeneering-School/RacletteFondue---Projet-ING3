package Vue;

import javax.swing.*;
import java.awt.*;

public class AjouterSpecialisteAdmin extends JFrame {

    public JTextField nomField;
    public JTextField specialiteField;
    public JTextField adresseField;
    public JTextField tarifField;
    public JTextField paiementField;
    public JTextField descriptionField;
    public JButton ajouterButton;
    public JButton annulerButton;
    public JLabel erreurLabel;

    public AjouterSpecialisteAdmin() {
        setTitle("Ajouter Specialiste");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        setContentPane(buildPanel());
    }

    public JPanel buildPanel() {

        // Titre
        JLabel titleLabel = new JLabel("Ajouter un spécialiste");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 42));
        titleLabel.setForeground(new Color(45, 104, 196));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);


        // Panel principal avec GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Augmenter les marges pour une meilleure lisibilité
        gbc.fill = GridBagConstraints.HORIZONTAL;


        // Les champs
        String[] labels = {
                "Nom :", "Spécialité :", "Adresse :",
                "Tarif :", "Moyen de paiement acceptés :", "Description :"
        };

        Font labelFont = new Font("Tahoma", Font.PLAIN, 18); // Définir une police plus grande

        JLabel nomLabel = new JLabel(labels[0]);
        nomLabel.setFont(labelFont);
        nomField = new JTextField(20);
        nomField.setPreferredSize(new Dimension(400, 40)); // Augmenter la taille du champ
        nomField.setFont(new Font("Tahoma", Font.PLAIN, 18));


        JLabel specialiteLabel = new JLabel(labels[1]);
        specialiteLabel.setFont(labelFont);
        specialiteField = new JTextField(20);
        specialiteField.setPreferredSize(new Dimension(400, 40));
        specialiteField.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel adresseLabel = new JLabel(labels[2]);
        adresseLabel.setFont(labelFont);
        adresseField = new JTextField(20);
        adresseField.setPreferredSize(new Dimension(400, 40));
        adresseField.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel tarifLabel = new JLabel(labels[3]);
        tarifLabel.setFont(labelFont);
        tarifField = new JTextField(20);
        tarifField.setPreferredSize(new Dimension(400, 40));
        tarifField.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel paiementLabel = new JLabel(labels[4]);
        paiementLabel.setFont(labelFont);
        paiementField = new JTextField(20);
        paiementField.setPreferredSize(new Dimension(400, 40));
        paiementField.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel descriptionLabel = new JLabel(labels[5]);
        descriptionLabel.setFont(labelFont);
        descriptionField = new JTextField(20);
        descriptionField.setPreferredSize(new Dimension(400, 40));
        descriptionField.setFont(new Font("Tahoma", Font.PLAIN, 18));


        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nomLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nomField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(specialiteLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(specialiteField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(adresseLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(adresseField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(tarifLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(tarifField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(paiementLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(paiementField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(descriptionLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(descriptionField, gbc);



        erreurLabel = new JLabel("");
        erreurLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        erreurLabel.setForeground(Color.RED);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(erreurLabel, gbc);


        // Bouton Ajouter
        ajouterButton = new JButton("Ajouter");
        ajouterButton.setPreferredSize(new Dimension(150, 50)); // Augmenter la taille du bouton
        ajouterButton.setFont(new Font("Tahoma", Font.BOLD, 20)); // Augmenter la taille de la police
        ajouterButton.setBackground(new Color(45, 104, 196)); // Changer la couleur de fond
        ajouterButton.setForeground(Color.WHITE); // Changer la couleur du texte
        ajouterButton.setFocusPainted(false);


        // Bouton Annuler
        annulerButton = new JButton("Annuler");
        annulerButton.setPreferredSize(new Dimension(150, 50));
        annulerButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        annulerButton.setBackground(new Color(255, 88, 88));
        annulerButton.setForeground(Color.WHITE);
        annulerButton.setFocusPainted(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(ajouterButton);
        buttonPanel.add(annulerButton);

        JPanel panelGeneral = new JPanel();

        panelGeneral.setLayout(new BorderLayout());

        panelGeneral.add(titleLabel, BorderLayout.NORTH);
        panelGeneral.add(formPanel, BorderLayout.CENTER);
        panelGeneral.add(buttonPanel, BorderLayout.SOUTH);

        return panelGeneral;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AjouterSpecialisteAdmin().setVisible(true);
        });
    }
}
