package Vue;

import Modele.RDV;
import Modele.Specialiste;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class RendezVous extends JFrame {

    public JButton btnAccueil;
    public JButton btnRendezVous;
    public JButton btnCompte;
    public JLabel labelAnnulation;

    public HashMap<JLabel, RDV> mapRDV = null;
    public ArrayList<RDV> listeRDV = null;



    public RendezVous() {

        mapRDV = new HashMap<JLabel, RDV>();

        setTitle("Rendez-vous");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setContentPane(buildPanel());
    }

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

    private JPanel createRdvPanel(RDV rdv) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(221, 235, 247));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setMaximumSize(new Dimension(550, 100));

        // Partie gauche : image
        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = loadProfileImage(rdv.getSpecialiste()); // méthode pour charger l'image
        imageLabel.setIcon(imageIcon);
        imageLabel.setPreferredSize(new Dimension(64, 84));
        panel.add(imageLabel, BorderLayout.WEST);


        // Partie droite : infos RDV
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);

        JLabel nomLabel = new JLabel("Dr." + rdv.getSpecialiste().getUtilisateurNom());
        nomLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

        JLabel speLabel = new JLabel(rdv.getSpecialiste().getSpecialisteSpecialite());
        speLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));

        JLabel dateLabel = new JLabel(new java.text.SimpleDateFormat("dd/MM/yyyy à HH:mm").format(new java.util.Date (rdv.getDate())));
        dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));

        if (rdv.getDate() > Instant.now().toEpochMilli()) {

            labelAnnulation = new JLabel("Annuler");
            labelAnnulation.setFont(new Font("Verdana", Font.BOLD, 15));
            labelAnnulation.setForeground(new Color(255, 0, 0));
            labelAnnulation.setCursor(new Cursor(Cursor.HAND_CURSOR));

            mapRDV.put(labelAnnulation, rdv);

            panel.add(labelAnnulation, BorderLayout.EAST);
        }

        textPanel.add(nomLabel);
        textPanel.add(speLabel);
        textPanel.add(dateLabel);

        panel.add(textPanel, BorderLayout.CENTER);


        return panel;
    }

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

    private void styleMenuButton(JButton button) {
        button.setPreferredSize(new Dimension(250, 70));
        button.setFont(new Font("Tahoma", Font.BOLD, 20));
        button.setBackground(new Color(221, 235, 247));
        button.setFocusPainted(false);
    }

    public JPanel buildPanel() {

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));



        // Titre
        JLabel titleLabel = new JLabel("Rendez-vous");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 42));
        titleLabel.setForeground(new Color(45, 104, 196));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Menu navigation
        JPanel menuPanel = new JPanel();

        btnAccueil = new JButton("Accueil");
        styleMenuButton(btnAccueil);

        btnRendezVous = new JButton("Rendez-vous");
        styleMenuButton(btnRendezVous);
        btnRendezVous.setForeground(Color.WHITE);
        btnRendezVous.setBackground(new Color(45, 104, 196));

        btnCompte = new JButton("Compte");
        styleMenuButton(btnCompte);

        menuPanel.add(btnAccueil);
        menuPanel.add(btnRendezVous);
        menuPanel.add(btnCompte);
        mainPanel.add(menuPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        if (listeRDV == null) {

            return mainPanel;
        }

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

        // Contenu : deux colonnes avec listes déroulantes
        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 30, 0));
        contentPanel.setMaximumSize(new Dimension(1300, 500));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // RDV passés
        contentPanel.add(createScrollList("RDV passés", listeRDVPasses));

        // RDV à venir
        contentPanel.add(createScrollList("RDV à venir", listeRDVFuturs));

        mainPanel.add(contentPanel);
        mainPanel.add(Box.createVerticalStrut(30));

        return mainPanel;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new RendezVous().setVisible(true);
        });
    }
}
