package Vue;

import Modele.Patient;
import Modele.RDV;
import Modele.Specialiste;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;

public class InfoPatientAdmin extends JFrame {

    public JButton btnSpecialiste;
    public JButton btnDossierPatients;
    public JButton btnStatistiques;

    public Patient patient;
    public ArrayList<RDV> listeRDV;

    public InfoPatientAdmin() {

        setTitle("Informations Patient");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setContentPane(buildPanel());
    }

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

        JPanel menuPanel = new JPanel();

        btnSpecialiste = new JButton("Spécialistes");
        styleMenuButton(btnSpecialiste);

        btnDossierPatients = new JButton("Dossier Patients");
        styleMenuButton(btnDossierPatients);
        btnDossierPatients.setForeground(Color.WHITE);
        btnDossierPatients.setBackground(new Color(45, 104, 196));


        btnStatistiques = new JButton("Statistiques");
        styleMenuButton(btnStatistiques);

        menuPanel.add(btnSpecialiste);
        menuPanel.add(btnDossierPatients);
        menuPanel.add(btnStatistiques);

        mainPanel.add(menuPanel);

        // Infos perso
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(6, 2, 20, 10));
        infoPanel.setMaximumSize(new Dimension(600, 200));
        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Font labelFont = new Font("Tahoma", Font.PLAIN, 20);

        infoPanel.add(createInfoLabel("Nom :", labelFont));
        infoPanel.add(createInfoLabel(patient.getUtilisateurNom(), labelFont));

        infoPanel.add(createInfoLabel("Prénom :", labelFont));
        infoPanel.add(createInfoLabel(patient.getUtilisateurPrenom(), labelFont));

        infoPanel.add(createInfoLabel("Age :", labelFont));
        infoPanel.add(createInfoLabel(String.valueOf(patient.getUtilisateurAge()), labelFont));

        infoPanel.add(createInfoLabel("Téléphone :", labelFont));
        infoPanel.add(createInfoLabel(patient.getUtilisateurTel(), labelFont));

        infoPanel.add(createInfoLabel("Email :", labelFont));
        infoPanel.add(createInfoLabel(patient.getUtilisateurMail(), labelFont));

        mainPanel.add(infoPanel);
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

    private JLabel createInfoLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }



    private void styleMenuButton(JButton button) {
        button.setPreferredSize(new Dimension(250, 70));
        button.setFont(new Font("Tahoma", Font.BOLD, 20));
        button.setBackground(new Color(221, 235, 247));
        button.setFocusPainted(false);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InfoPatientAdmin().setVisible(true));
    }
}
