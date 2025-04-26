package Vue;

import Modele.Specialiste;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * La classe RechercheDocteur représente une fenêtre permettant à un patient
 * de rechercher un spécialiste. Les résultats trouvés s'affichent en conséquence.
 */
public class RechercheDocteur extends JFrame {

    public JButton btnAccueil;
    public JButton btnRendezVous;
    public JButton btnCompte;
    public JTextField searchField;
    public JButton searchButton;
    public JLabel nameLabel;
    public JLabel availabilityLabel;
    public JLabel imageLabel;

    public ArrayList<Specialiste> resultatRecherche = null;
    public Map<JLabel, Specialiste> mapSpecialistesInfo = null;
    public Map<JLabel, Specialiste> mapSpecialistesDispo = null;


    /**
     * Constructeur de la classe RechercheDocteur.
     * Initialise la fenêtre avec ses propriétés et son contenu.
     */
    public RechercheDocteur() {

        mapSpecialistesInfo = new HashMap<JLabel, Specialiste>();
        mapSpecialistesDispo = new HashMap<JLabel, Specialiste>();

        setTitle("Recherche");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setContentPane(buildPanel());
    }


    /**
     * Construit l'interface graphique et
     *
     * @return Le panneau principal contenant la section de recherche et les résultats trouvés.
     */
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

        searchField = new JTextField("", 30); // Champ de recherche avec texte
        searchField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        searchField.setPreferredSize(new Dimension(400, 40));
        addPlaceholder(searchField,"Nom, spécialité, lieu..." );


        searchButton = new JButton("🔍");
        searchButton.setPreferredSize(new Dimension(80, 40));
        searchButton.setBackground(new Color(221, 235, 247));
        searchButton.setFocusPainted(false);

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        mainPanel.add(searchPanel);
        mainPanel.add(Box.createVerticalStrut(20)); // Réduire l'espace vertical ici


        JLabel resultCountLabel;

        // Nombre de résultats
        if (resultatRecherche != null) {

            resultCountLabel = new JLabel("Nb de résultat: " + resultatRecherche.size());

        } else {

            resultCountLabel = new JLabel("Aucun résultat");
        }

        resultCountLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        resultCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(resultCountLabel);
        mainPanel.add(Box.createVerticalStrut(10));

        // Champ de recherche et résultats
        JPanel searchResultPanel = new JPanel();
        searchResultPanel.setLayout(new BoxLayout(searchResultPanel, BoxLayout.Y_AXIS));
        searchResultPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        if (resultatRecherche != null) {

            for (Specialiste specialiste : resultatRecherche) {

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


                availabilityLabel = new JLabel("Voir les dispo");
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

    /**
     * Ajoute un texte de substitution (placeholder) à un champ de texte.
     *
     * @param textField   Le champ de texte auquel ajouter le placeholder.
     * @param placeholder Le texte du placeholder.
     */
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


    /**
     * Applique un style aux boutons du menu.
     *
     * @param button Le bouton à styliser.
     */
    private void styleMenuButton(JButton button) {
        button.setPreferredSize(new Dimension(250, 70));
        button.setFont(new Font("Tahoma", Font.BOLD, 20));
        button.setBackground(new Color(221, 235, 247));
        button.setFocusPainted(false);
    }

}
