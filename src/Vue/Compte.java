package Vue;

import javax.swing.*;
import java.awt.*;

public class Compte extends JFrame {

    public JButton btnAccueil;
    public JButton btnRendezVous;
    public JButton btnCompte;
    public JButton btnModifierCompte;

    public Compte() {
        setTitle("Compte");
        setSize(1550, 820);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setContentPane(buildPanel());
    }

    public JPanel buildPanel() {

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));


        // Titre

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


        // Contenu de la page
        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.X_AXIS));
        wrapperPanel.add(Box.createHorizontalGlue());
        wrapperPanel.add(Box.createHorizontalGlue());



        // Panel pour les infos personnelles
        JPanel personnelPanel = new JPanel();
        personnelPanel.setLayout(new BoxLayout(personnelPanel, BoxLayout.Y_AXIS));
        personnelPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        personnelPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // marges
        personnelPanel.setMaximumSize(new Dimension(600, Integer.MAX_VALUE));



// Titre informations générales
        JLabel infoPerso = new JLabel("Informations personnelles");
        infoPerso.setFont(new Font("Verdana", Font.BOLD, 24));
        personnelPanel.add(infoPerso);
        personnelPanel.add(Box.createVerticalStrut(20)); // espace après le titre

        wrapperPanel.add(personnelPanel);


// Panel pour "Nom"
        JPanel nomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nomPerso = new JLabel("Nom : ");
        nomPerso.setFont(new Font("Verdana", Font.PLAIN, 18));
        nomPanel.add(nomPerso);
        personnelPanel.add(nomPanel);

// Panel pour "Prénom"
        JPanel prenomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel prenomPerso = new JLabel("Prénom : ");
        prenomPerso.setFont(new Font("Verdana", Font.PLAIN, 18));
        prenomPanel.add(prenomPerso);
        personnelPanel.add(prenomPanel);

// Panel pour "Age"
        JPanel agePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel agePerso = new JLabel("Age : ");
        agePerso.setFont(new Font("Verdana", Font.PLAIN, 18));
        agePanel.add(agePerso);
        personnelPanel.add(agePanel);

// Panel pour "Tel"
        JPanel telPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel telPerso = new JLabel("Tel : ");
        telPerso.setFont(new Font("Verdana", Font.PLAIN, 18));
        telPanel.add(telPerso);
        personnelPanel.add(telPanel);

// Panel pour "Email"
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel emailPerso = new JLabel("Email : ");
        emailPerso.setFont(new Font("Verdana", Font.PLAIN, 18));
        emailPanel.add(emailPerso);
        personnelPanel.add(emailPanel);

// Panel pour "Mot de passe"
        JPanel mdpPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel mdpPerso = new JLabel("Mot de passe : ");
        mdpPerso.setFont(new Font("Verdana", Font.PLAIN, 18));
        mdpPanel.add(mdpPerso);
        personnelPanel.add(mdpPanel);

// Espace + bouton Modifier
        personnelPanel.add(Box.createVerticalStrut(20));

        // Crée le bouton
        btnModifierCompte = new JButton("Modifier");
        btnModifierCompte.setPreferredSize(new Dimension(120, 40));
        btnModifierCompte.setFont(new Font("Verdana", Font.BOLD, 18));
        btnModifierCompte.setBackground(new Color(221, 235, 247));
        btnModifierCompte.setFocusPainted(false);

        personnelPanel.add(btnModifierCompte);
        personnelPanel.setAlignmentX(Component.CENTER_ALIGNMENT);


        mainPanel.add(wrapperPanel);
        mainPanel.add(personnelPanel);
        mainPanel.add(Box.createVerticalStrut(30));


        //add(mainPanel);

        return mainPanel;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Compte().setVisible(true);
        });
    }
}
