package Vue;

import javax.swing.*;
import java.awt.*;

public class Connexion extends JFrame {

    public JTextField mailField;
    public JPasswordField passwordField;
    JButton connexionButton;

    public Connexion() {
        setTitle("Connexion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 900);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Titre "Connexion"
        JLabel titleLabel = new JLabel("Connexion");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 42));
        titleLabel.setForeground(new Color(45, 104, 196));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

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

        add(formPanel, BorderLayout.CENTER);

        // Bouton Connexion
        connexionButton = new JButton("Connexion");
        connexionButton.setPreferredSize(new Dimension(200, 50)); // Augmenter la taille du bouton
        connexionButton.setFont(new Font("Verdana", Font.BOLD, 20)); // Augmenter la taille de la police
        connexionButton.setBackground(new Color(45, 104, 196)); // Changer la couleur de fond
        connexionButton.setForeground(Color.WHITE); // Changer la couleur du texte
        connexionButton.setFocusPainted(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(connexionButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Connexion().setVisible(true);
        });
    }
}
