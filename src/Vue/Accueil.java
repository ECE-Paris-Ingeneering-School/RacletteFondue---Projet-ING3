package Vue;

import javax.swing.*;
import java.awt.*;

public class Accueil extends JFrame {

    public JButton btnAccueil;
    public JButton btnRendezVous;
    public JButton btnCompte;
    public JTextField searchField;
    public JButton searchButton;
    public JLabel deconnexionLabel;


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
        styleMenuButton(btnAccueil);
        btnAccueil.setForeground(Color.WHITE);
        btnAccueil.setBackground(new Color(45, 104, 196));

        btnRendezVous = new JButton("Rendez-vous");
        styleMenuButton(btnRendezVous);

        btnCompte = new JButton("Compte");
        styleMenuButton(btnCompte);

        menuPanel.add(btnAccueil);
        menuPanel.add(btnRendezVous);
        menuPanel.add(btnCompte);
        mainPanel.add(menuPanel);
        mainPanel.add(Box.createVerticalStrut(10)); // Réduction de l'espace ici

        // Panel pour les descriptions
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));

        JLabel descriptionLabel1 = new JLabel("Bienvenue sur Feurissimo.");
        descriptionLabel1.setFont(new Font("Tahoma", Font.PLAIN, 40));
        descriptionLabel1.setForeground(new Color(45, 104, 196));
        descriptionLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descriptionLabel2 = new JLabel("Notre rôle ? Mieux vous accompagner.");
        descriptionLabel2.setFont(new Font("Tahoma", Font.PLAIN, 32));
        descriptionLabel2.setForeground(new Color(45, 104, 196));
        descriptionLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descriptionLabel3 = new JLabel("Prenez RDV en ligne chez un soignant.");
        descriptionLabel3.setFont(new Font("Tahoma", Font.PLAIN, 26));
        descriptionLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);

        descriptionPanel.add(descriptionLabel1);
        descriptionPanel.add(Box.createRigidArea(new Dimension(0, 25))); // Réduction de l'espace entre les labels
        descriptionPanel.add(descriptionLabel2);
        descriptionPanel.add(Box.createRigidArea(new Dimension(0, 80))); // Réduction de l'espace entre les labels
        descriptionPanel.add(descriptionLabel3);

        // Encapsulation pour éviter les problèmes de mise en page
        JPanel wrapperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0)); // Centrage horizontal, sans espaces
        wrapperPanel.add(descriptionPanel);

        mainPanel.add(wrapperPanel);

        //Accueil du site

        // Barre de recherche
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0)); // Centrage sans espace
        searchField = new JTextField("");
        searchField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        searchField.setPreferredSize(new Dimension(400, 40));
        addPlaceholder(searchField, "Nom, spécialité, lieu...");

        searchButton = new JButton("🔍");
        searchButton.setPreferredSize(new Dimension(80, 40));
        searchButton.setBackground(new Color(221, 235, 247));
        searchButton.setFocusPainted(false);

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        mainPanel.add(searchPanel);
        mainPanel.add(Box.createVerticalStrut(30));

        JPanel deconnexionPanel = new JPanel();

        deconnexionLabel = new JLabel("Déconnexion");
        deconnexionLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        deconnexionLabel.setForeground(new Color(255, 0, 0));
        deconnexionLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        deconnexionPanel.add(deconnexionLabel);
        mainPanel.add(deconnexionPanel, BorderLayout.SOUTH);
        mainPanel.add(Box.createVerticalStrut(10));

        return mainPanel;
    }

    private void styleMenuButton(JButton button) {
        button.setPreferredSize(new Dimension(250, 70));
        button.setFont(new Font("Tahoma", Font.BOLD, 20));
        button.setBackground(new Color(221, 235, 247));
        button.setFocusPainted(false);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Accueil().setVisible(true);
        });
    }
}
