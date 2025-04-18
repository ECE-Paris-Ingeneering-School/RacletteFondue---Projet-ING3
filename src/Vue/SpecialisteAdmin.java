package Vue;

import Modele.Specialiste;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SpecialisteAdmin extends JFrame {

    public JButton btnSpecialiste;
    public JButton btnDossierPatients;
    public JButton btnStatistiques;
    public JTextField searchField;
    public JButton searchButton;
    public JLabel nameLabel;
    public JLabel locationLabel;
    public JLabel availabilityLabel;
    public JLabel imageLabel;

    public ArrayList<Specialiste> listeSpecialistes;
    public Map<JLabel, Specialiste> mapSpecialistesInfo = null;
    public Map<JLabel, Specialiste> mapSpecialistesDispo = null;

    public SpecialisteAdmin() {

        mapSpecialistesInfo = new HashMap<JLabel, Specialiste>();
        mapSpecialistesDispo = new HashMap<JLabel, Specialiste>();

        setTitle("Spécialiste");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setContentPane(buildPanel());
    }

    public JPanel buildPanel() {

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        if (listeSpecialistes == null) {

            return mainPanel;
        }

        // Titre
        JLabel titleLabel = new JLabel("Spécialistes - ADMIN");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 42));
        titleLabel.setForeground(new Color(45, 104, 196));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Menu boutons
        JPanel menuPanel = new JPanel();

        btnSpecialiste = new JButton("Spécialistes");
        styleMenuButton(btnSpecialiste);
        btnSpecialiste.setForeground(Color.WHITE);
        btnSpecialiste.setBackground(new Color(45, 104, 196));

        btnDossierPatients = new JButton("Dossier Patients");
        styleMenuButton(btnDossierPatients);

        btnStatistiques = new JButton("Statistiques");
        styleMenuButton(btnStatistiques);

        menuPanel.add(btnSpecialiste);
        menuPanel.add(btnDossierPatients);
        menuPanel.add(btnStatistiques);
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

        searchField = new JTextField("", 30); // Champ de recherche avec texte
        searchField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        searchField.setPreferredSize(new Dimension(400, 40));
        addPlaceholder(searchField, "Rechercher dans les spécialistes enregistrés... ");

        searchButton = new JButton("🔍");
        searchButton.setPreferredSize(new Dimension(80, 40));
        searchButton.setBackground(new Color(221, 235, 247));
        searchButton.setFocusPainted(false);

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        mainPanel.add(searchPanel);
        mainPanel.add(Box.createVerticalStrut(20)); // Réduire l'espace vertical ici


        // Nombre de résultats
        JLabel listeSpecialisteLabel = new JLabel("Liste des spécialistes enregistrés : " + listeSpecialistes.size());
        listeSpecialisteLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        listeSpecialisteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(listeSpecialisteLabel);
        mainPanel.add(Box.createVerticalStrut(10));

        // Champ de recherche et résultats
        JPanel searchResultPanel = new JPanel();
        searchResultPanel.setLayout(new BoxLayout(searchResultPanel, BoxLayout.Y_AXIS));
        searchResultPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        for (Specialiste specialiste : listeSpecialistes) {

            JPanel doctorPanel = new JPanel(new BorderLayout());
            doctorPanel.setPreferredSize(new Dimension(600, 50));
            doctorPanel.setMaximumSize(new Dimension(600, 50));
            doctorPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            doctorPanel.setBackground(new Color(221, 235, 247));

            String path;

            if (specialiste.getUtilisateurImage().isEmpty()){
                path = "src/Images/pdp_defaut.png";
            }else {
                path = specialiste.getUtilisateurImage();
            }

            //Image du spécialiste
            imageLabel = new JLabel();
            ImageIcon icon;
            Image img = new ImageIcon(path).getImage().getScaledInstance(42, 40, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);

            imageLabel.setIcon(icon);
            imageLabel.setPreferredSize(new Dimension(42, 40));
            doctorPanel.add(imageLabel,BorderLayout.WEST);

            //Description du spécialiste
            nameLabel = new JLabel(" Dr. " + specialiste.getUtilisateurNom() + ", " + specialiste.getSpecialisteSpecialite() + " / "+specialiste.getUtilisateurAdresse().getAdresseVille());
            nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
            nameLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            doctorPanel.add(nameLabel,BorderLayout.CENTER);


            availabilityLabel = new JLabel("Voir les dispos");
            availabilityLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
            availabilityLabel.setForeground(new Color(45, 104, 196));
            availabilityLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

            doctorPanel.add(availabilityLabel, BorderLayout.EAST);

            searchResultPanel.add(doctorPanel);
            searchResultPanel.add(Box.createVerticalStrut(10));

            // On associe l'objet à son label pour le référencer plus tard
            mapSpecialistesInfo.put(nameLabel, specialiste);
            mapSpecialistesDispo.put(availabilityLabel, specialiste);
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
        SwingUtilities.invokeLater(SpecialisteAdmin::new);
    }
}
