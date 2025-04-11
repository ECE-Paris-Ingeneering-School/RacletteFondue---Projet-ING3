package Vue;

import javax.swing.*;
import java.awt.*;

public class RechercheDocteur extends JFrame {

    public JButton btnAccueil;
    public JButton btnRendezVous;
    public JButton btnCompte;
    public JTextField searchField;
    public JButton searchButton;
    public JLabel nameLabel;
    public JLabel locationLabel;
    public JLabel availabilityLabel;


    public RechercheDocteur() {
        setTitle("Recherche");
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
        JLabel titleLabel = new JLabel("Recherche");
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

        btnRendezVous = new JButton("Rendez-vous");
        styleMenuButton(btnRendezVous);

        btnCompte = new JButton("Compte");
        styleMenuButton(btnCompte);

        menuPanel.add(btnAccueil);
        menuPanel.add(btnRendezVous);
        menuPanel.add(btnCompte);
        mainPanel.add(menuPanel);
        mainPanel.add(Box.createVerticalStrut(10)); // Réduire l'espace vertical ici

        // Barre de recherche
        JPanel searchPanel = new JPanel();

        searchField = new JTextField("Dermatologue", 30); // Champ de recherche avec texte
        searchField.setFont(new Font("Verdana", Font.PLAIN, 18));
        searchField.setPreferredSize(new Dimension(400, 40));
        searchField.setBackground(new Color(255, 255, 255));
        searchField.setEditable(false);

        searchButton = new JButton("🔍");
        searchButton.setPreferredSize(new Dimension(80, 40));
        searchButton.setBackground(new Color(221, 235, 247));
        searchButton.setFocusPainted(false);

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        mainPanel.add(searchPanel);
        mainPanel.add(Box.createVerticalStrut(20)); // Réduire l'espace vertical ici

        // Liste des docteurs
        String[][] doctors = {
                {"Dr. Juiph", "Lyon", "voir les dispo"},
                {"Dr. Leroy", "Paris", "voir les dispo"},
                {"Dr. Charbel", "Paris", "voir les dispo"},
                {"Dr. Leclaire", "Paris", "voir les dispo"},
                {"Dr. Daix", "Paris", "voir les dispo"},
                {"Dr. Chibre", "Paris", "voir les dispo"},
                {"Dr. Leclaire", "Paris", "voir les dispo"},
                {"Dr. Daix", "Paris", "voir les dispo"},
                {"Dr. Chibre", "Paris", "voir les dispo"},
        };

        // Nombre de résultats
        JLabel resultCountLabel = new JLabel("Nombre de résultats : " + doctors.length);
        resultCountLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        resultCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(resultCountLabel);
        mainPanel.add(Box.createVerticalStrut(10));

        // Champ de recherche et résultats
        JPanel searchResultPanel = new JPanel();
        searchResultPanel.setLayout(new BoxLayout(searchResultPanel, BoxLayout.Y_AXIS));
        searchResultPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        for (String[] doctor : doctors) {
            JPanel doctorPanel = new JPanel(new BorderLayout());
            doctorPanel.setPreferredSize(new Dimension(600, 50));
            doctorPanel.setMaximumSize(new Dimension(600, 50));
            doctorPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            doctorPanel.setBackground(new Color(221, 235, 247));

            nameLabel = new JLabel(doctor[0] + ", ");
            nameLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
            nameLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            doctorPanel.add(nameLabel, BorderLayout.WEST);


            locationLabel = new JLabel(doctor[1]);
            locationLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
            doctorPanel.add(locationLabel, BorderLayout.CENTER);

            availabilityLabel = new JLabel(doctor[2]);
            availabilityLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
            availabilityLabel.setForeground(new Color(45, 104, 196));
            availabilityLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            doctorPanel.add(availabilityLabel, BorderLayout.EAST);

            searchResultPanel.add(doctorPanel);
            searchResultPanel.add(Box.createVerticalStrut(10));
        }

        // Ajouter le panneau de résultats dans un JScrollPane pour le rendre défilable
        JScrollPane scrollPane = new JScrollPane(searchResultPanel);
        scrollPane.setPreferredSize(new Dimension(600, 300)); // Ajuster la largeur pour correspondre à celle des docteurs
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(238, 238, 238), 1));
        scrollPane.setPreferredSize(new Dimension(650, 450));
        scrollPane.setMaximumSize(new Dimension(650, 450));


        mainPanel.add(scrollPane);

        return mainPanel;
    }

    private void styleMenuButton(JButton button) {
        button.setPreferredSize(new Dimension(250, 70));
        button.setFont(new Font("Verdana", Font.BOLD, 20));
        button.setBackground(new Color(221, 235, 247));
        button.setFocusPainted(false);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new RechercheDocteur().setVisible(true);
        });
    }
}
