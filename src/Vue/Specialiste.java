package Vue;

import javax.swing.*;
import java.awt.*;


public class Specialiste extends JFrame {

    public JButton btnSpecialiste;
    public JButton btnDossierPatients;
    public JTextField searchField;
    public JButton searchButton;
    public JLabel nameLabel;
    public JLabel locationLabel;
    public JLabel availabilityLabel;



    public Specialiste() {
        setTitle("Spécialiste");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Titre
        JLabel titleLabel = new JLabel("Spécialiste");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 42));
        titleLabel.setForeground(new Color(45, 104, 196));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Menu boutons
        JPanel menuPanel = new JPanel();

        btnSpecialiste = new JButton("Spécialistes");
        btnSpecialiste.setPreferredSize(new Dimension(250, 70));
        btnSpecialiste.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnSpecialiste.setBackground(new Color(45, 104, 196));
        btnSpecialiste.setForeground(Color.WHITE);
        btnSpecialiste.setFocusPainted(false);



        btnDossierPatients = new JButton("Dossier Patients");
        btnDossierPatients.setPreferredSize(new Dimension(250, 70));
        btnDossierPatients.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnDossierPatients.setBackground(new Color(221, 235, 247));
        btnDossierPatients.setFocusPainted(false);


        menuPanel.add(btnSpecialiste);
        menuPanel.add(btnDossierPatients);
        mainPanel.add(menuPanel);
        mainPanel.add(Box.createVerticalStrut(10)); // Réduire l'espace vertical ici


        // Lien pour ajouter un spécialiste
        JLabel addSpecialistLabel = new JLabel("+ Ajouter un spécialiste");
        addSpecialistLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        addSpecialistLabel.setForeground(new Color(45, 104, 196));
        addSpecialistLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addSpecialistLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(addSpecialistLabel);
        mainPanel.add(Box.createVerticalStrut(10));


        // Barre de recherche
        JPanel searchPanel = new JPanel();

        searchField = new JTextField("Rechercher dans les spécialistes enregistrés... ", 30); // Champ de recherche avec texte
        searchField.setFont(new Font("Tahoma", Font.PLAIN, 18));
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
                {"Dr. Charbel", "Paris", "voir les dispo"},
                {"Dr. Charbel", "Paris", "voir les dispo"},
                {"Dr. Charbel", "Paris", "voir les dispo"},
                {"Dr. Charbel", "Paris", "voir les dispo"},
                {"Dr. Charbel", "Paris", "voir les dispo"},
                {"Dr. Charbel", "Paris", "voir les dispo"},
                {"Dr. Charbel", "Paris", "voir les dispo"},
                {"Dr. Charbel", "Paris", "voir les dispo"},
        };

        // Nombre de résultats
        JLabel listeSpecialisteLabel = new JLabel("Liste des spécialistes enregistrés : " + doctors.length);
        listeSpecialisteLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        listeSpecialisteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(listeSpecialisteLabel);
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
            nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
            doctorPanel.add(nameLabel, BorderLayout.WEST);

            locationLabel = new JLabel(doctor[1]);
            locationLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
            doctorPanel.add(locationLabel, BorderLayout.CENTER);

            availabilityLabel = new JLabel(doctor[2]);
            availabilityLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
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
        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Specialiste::new);
    }
}
