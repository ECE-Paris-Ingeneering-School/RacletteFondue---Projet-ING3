package Vue;

import Modele.Patient;
import Modele.RDV;
import Modele.Specialiste;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;


/**
 * La classe InfoPatientAdmin représente la page où l'admin peut voir
 * les informations du patient.
 */
public class InfoPatientAdmin extends JFrame {

    public JButton btnSpecialiste;
    public JButton btnDossierPatients;
    public JButton btnStatistiques;

    public Patient patient;
    public ArrayList<RDV> listeRDV;


    /**
     * Constructeur de la classe InfoPatientAdmin.
     * Initialise la fenêtre avec ses propriétés et son contenu.
     */
    public InfoPatientAdmin() {

        setTitle("Informations Patient");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setContentPane(buildPanel());
    }


    /**
     * Construit l'interface graphique et
     *
     * @return Le panneau principal contenant les informations du patient.
     */
    public JPanel buildPanel() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        if (patient == null || listeRDV == null) {

            return mainPanel;
        }

        // Titre
        String pronom;

        if (patient.getUtilisateurSexe() == 'H') {

            pronom = "M. ";

        } else {

            pronom = "Mme. ";
        }

        JLabel titleLabel = new JLabel(pronom + patient.getUtilisateurNom() + " - Informations");
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

        btnDossierPatients = new JButton("Dossier Patients");
        styleMenuButton(btnDossierPatients);

        btnStatistiques = new JButton("Statistiques");
        styleMenuButton(btnStatistiques);


        menuPanel.add(btnSpecialiste);
        menuPanel.add(btnDossierPatients);
        menuPanel.add(btnStatistiques);

        mainPanel.add(menuPanel);
        mainPanel.add(Box.createVerticalStrut(10));

        // Infos perso
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());
        infoPanel.setMaximumSize(new Dimension(800, 300));
        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 30, 7, 30); // marges internes
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Tahoma", Font.PLAIN, 20);

// Incrémenter gbc.gridy à chaque ligne
        gbc.gridy = 0;

        addInfoRow(infoPanel, gbc, "Nom :", patient.getUtilisateurNom(), labelFont);
        addInfoRow(infoPanel, gbc, "Prénom :", patient.getUtilisateurPrenom(), labelFont);
        addInfoRow(infoPanel, gbc, "Age :", String.valueOf(patient.getUtilisateurAge()), labelFont);
        addInfoRow(infoPanel, gbc, "Téléphone :", patient.getUtilisateurTel(), labelFont);
        addInfoRow(infoPanel, gbc, "Email :", patient.getUtilisateurMail(), labelFont);

        String adresseComplete = patient.getUtilisateurAdresse().getAdresseNumero() + " "
                + patient.getUtilisateurAdresse().getAdresseRue() + ", "
                + patient.getUtilisateurAdresse().getAdresseCodePostal() + " "
                + patient.getUtilisateurAdresse().getAdresseVille();
        addInfoRow(infoPanel, gbc, "Adresse :", adresseComplete, labelFont);

        mainPanel.add(Box.createVerticalStrut(40));

        JPanel infoWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        infoWrapper.setOpaque(false); // pour garder le fond transparent si besoin
        infoWrapper.add(infoPanel);

        mainPanel.add(infoWrapper);

        mainPanel.add(Box.createVerticalStrut(30));

        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 30, 0));
        contentPanel.setMaximumSize(new Dimension(1300, 400));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Création des deux listes
        ArrayList<RDV> listeRDVPasses = new ArrayList<>();
        ArrayList<RDV> listeRDVFuturs = new ArrayList<>();

        for (RDV rdv : listeRDV) {

            if (rdv.getDate() > Instant.now().toEpochMilli()) {

                listeRDVFuturs.add(rdv);

            } else {

                listeRDVPasses.add(rdv);
            }
        }

        Collections.reverse(listeRDVPasses);

        contentPanel.add(createScrollList("RDV passés", listeRDVPasses));
        contentPanel.add(createScrollList("RDV à venir", listeRDVFuturs));

        mainPanel.add(contentPanel);

        return mainPanel;
    }

    /**
     * Crée un panneau défilant contenant la liste des rendez-vous.
     *
     * @param title    Le titre de la section.
     * @param listeRDV La liste des rendez-vous à afficher.
     * @return Le panneau contenant la liste des rendez-vous.
     */
    private JPanel createScrollList(String title, ArrayList<RDV> listeRDV) {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));

        JLabel sectionTitle = new JLabel(title);
        sectionTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
        sectionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        sectionPanel.add(sectionTitle);
        sectionPanel.add(Box.createVerticalStrut(20));

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        for (RDV rdv : listeRDV) {

            listPanel.add(createRdvPanel(rdv));
            listPanel.add(Box.createVerticalStrut(10));
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        scrollPane.setMaximumSize(new Dimension(600, 400));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        sectionPanel.add(scrollPane);
        return sectionPanel;
    }

    /**
     * Crée un panneau représentant un rendez-vous.
     *
     * @param rdv Le rendez-vous à afficher.
     * @return Le panneau contenant les informations du rendez-vous.
     */
    private JPanel createRdvPanel(RDV rdv) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(221, 235, 247));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setMaximumSize(new Dimension(550, 100));

        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(loadProfileImage(rdv.getSpecialiste()));
        imageLabel.setPreferredSize(new Dimension(64, 64));
        panel.add(imageLabel, BorderLayout.WEST);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);

        JLabel nomLabel = new JLabel(" Dr." + rdv.getSpecialiste().getUtilisateurNom());
        nomLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

        JLabel speLabel = new JLabel(" " + rdv.getSpecialiste().getSpecialisteSpecialite());
        speLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));

        JLabel dateLabel = new JLabel(new java.text.SimpleDateFormat(" dd/MM/yyyy à HH:mm").format(new java.util.Date (rdv.getDate())));
        dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));

        textPanel.add(nomLabel);
        textPanel.add(speLabel);
        textPanel.add(dateLabel);

        panel.add(textPanel, BorderLayout.CENTER);

        return panel;
    }


    /**
     * Ajoute une ligne d'information au panneau.
     *
     * @param panel     Le panneau auquel ajouter la ligne.
     * @param gbc       Les contraintes de mise en page.
     * @param labelText Le texte du label.
     * @param valueText Le texte de la valeur.
     * @param font      La police à utiliser.
     */
    private void addInfoRow(JPanel panel, GridBagConstraints gbc, String labelText, String valueText, Font font) {
        gbc.gridx = 0;
        JLabel label = new JLabel(labelText);
        label.setFont(font);
        panel.add(label, gbc);

        gbc.gridx = 1;
        JLabel value = new JLabel(valueText);
        value.setFont(font);
        panel.add(value, gbc);

        gbc.gridy++;
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


    /**
     * Charge l'image de profil du spécialiste.
     *
     * @param specialiste Le spécialiste dont l'image doit être chargée.
     * @return L'icône de l'image de profil du spécialiste.
     */
    private ImageIcon loadProfileImage(Specialiste specialiste) {

        String path;

        if (specialiste.getUtilisateurImage().isEmpty()){
            path = "src/Images/pdp_defaut.png";
        }else {
            path = specialiste.getUtilisateurImage();
        }

        ImageIcon icon;
        try {
            Image img = new ImageIcon(path).getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
        } catch (Exception e) {
            // Image par défaut si erreur
            Image img = new ImageIcon("Images/pdp_defaut.png").getImage().getScaledInstance(64, 84, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
        }

        return icon;
    }

}
