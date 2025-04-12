package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Connexion extends JFrame {

    public JTextField mailField;
    public JPasswordField passwordField;
    public JButton connexionButton;
    public JLabel createAccountLabel;
    public JLabel erreurLabel;

    public Connexion() {

        setTitle("Connexion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 900);
        setLocationRelativeTo(null);

        setContentPane(buildPanel());
    }

    public JPanel buildPanel() {

        // Titre "Connexion"
        JLabel titleLabel = new JLabel("Connexion");
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
                "Mail :", "Mot de passe :"
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

        erreurLabel = new JLabel("");
        erreurLabel.setFont(labelFont);
        erreurLabel.setForeground(Color.RED);


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
        gbc.gridy = 2;
        formPanel.add(erreurLabel, gbc);

        //add(formPanel, BorderLayout.CENTER);

        // Bouton Connexion
        connexionButton = new JButton("Connexion");
        connexionButton.setPreferredSize(new Dimension(200, 50)); // Augmenter la taille du bouton
        connexionButton.setFont(new Font("Verdana", Font.BOLD, 20)); // Augmenter la taille de la police
        connexionButton.setBackground(new Color(45, 104, 196)); // Changer la couleur de fond
        connexionButton.setForeground(Color.WHITE); // Changer la couleur du texte
        connexionButton.setFocusPainted(false);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(connexionButton, gbc);

        // Texte cliquable
        createAccountLabel = new JLabel("Pas de compte ? Créez-en un !");
        createAccountLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        createAccountLabel.setForeground(new Color(45, 104, 196));
        createAccountLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy++;

        buttonPanel.add(createAccountLabel, gbc);


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
            new Connexion().setVisible(true);
        });
    }
}
