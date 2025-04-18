package Vue;

import Controleur.ListenerFenetrePrincipale;
import Modele.RDV;
import Modele.Specialiste;
import Modele.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Classe permettant de gérer la fenêtre principale du programme
 * **/
public class FenetrePrincipale {

    // Listener
    public ListenerFenetrePrincipale listener;

    // Utilisateur actuel
    public Utilisateur utilisateurActuel;

    // Date actuelle
    public long dateActuelle;

    // Nom des pages
    public final String CONNEXION = "Connexion";
    public final String INSCRIPTION = "Inscription";
    public final String ACCUEIL = "Accueil";
    public final String RENDEZVOUS = "Rendez-vous";
    public final String COMPTE = "Compte";
    public final String RECHERCHE = "Recherche";
    public final String INFODOCTEUR = "Information spécialiste";
    public final String SPECIALISTEADMIN = "Spécialistes admin";
    public final String INFODOCTEURADMIN = "Modifier infos docteur admin";
    public final String STATSADMIN = "Statistiques admin";
    public final String INSERERDOCTEURADMIN = "Ajouter un Spécialiste admin";

    // Initialisation des objets de chaque page
    public Connexion connexion;
    public Inscription inscription;
    public Accueil accueil;
    public RendezVous rendezvous;
    public Compte compte;
    public RechercheDocteur recherche;
    public InfoDocteur info;
    public DisponibiliteRDV dispordv;
    public ConfirmationRDV confrdv;
    public SpecialisteAdmin speadmin;
    public ModifierInfoDocteurAdmin infodocteuradmin;
    public StatistiqueAdmin statsadmin;
    public AjouterSpecialisteAdmin ajoutSpeAdmin;

    // Initialisation des panels de chaque page
    public CardLayout cl;
    public JPanel conteneurPrincipal;
    public JPanel connexionPanel;
    public JPanel inscriptionPanel;
    public JPanel accueilPanel;
    public JPanel rendezvousPanel;
    public JPanel comptePanel;
    public JPanel recherchePanel;
    public JPanel infoPanel;
    public JPanel speadminPanel;
    public JPanel infodocteuradminPanel;
    public JPanel statsadminPanel;
    public JPanel ajoutSpeAdminPanel;

    /**
     * Constructeur de la fenêtre principale
     * **/
    public FenetrePrincipale() {

        // Récupération de la date du jour
        dateActuelle = Instant.now().toEpochMilli();

        // Instanciation du listener principal
        listener = new ListenerFenetrePrincipale(this);

        // Instanciation des objets de chaque page
        connexion = new Connexion();
        inscription = new Inscription();
        accueil = new Accueil();
        rendezvous = new RendezVous();
        compte = new Compte();
        recherche = new RechercheDocteur();
        info = new InfoDocteur();
        dispordv = new DisponibiliteRDV();
        confrdv = new ConfirmationRDV();
        speadmin = new SpecialisteAdmin();
        infodocteuradmin = new ModifierInfoDocteurAdmin();
        statsadmin = new StatistiqueAdmin();
        ajoutSpeAdmin = new AjouterSpecialisteAdmin();

        // Instanciation des panels
        this.connexionPanel = connexion.buildPanel();
        this.inscriptionPanel = inscription.buildPanel();
        this.accueilPanel = accueil.buildPanel();
        this.rendezvousPanel = rendezvous.buildPanel();
        this.comptePanel = compte.buildPanel();
        this.recherchePanel = recherche.buildPanel();
        this.infoPanel = info.buildPanel();
        this.speadminPanel = speadmin.buildPanel();
        this.infodocteuradminPanel = infodocteuradmin.buildPanel();
        this.statsadminPanel = statsadmin.buildPanel();
        this.ajoutSpeAdminPanel = ajoutSpeAdmin.buildPanel();

        // Ajout des listeners sur les pages
        // Connexion
        connexion.createAccountLabel.addMouseListener(listener);
        connexion.connexionButton.addActionListener(listener);

        // Inscription
        inscription.inscrireButton.addActionListener(listener);

        // Accueil
        accueil.btnRendezVous.addActionListener(listener);
        accueil.btnCompte.addActionListener(listener);
        accueil.searchButton.addActionListener(listener);
        accueil.searchField.addMouseListener(listener);
        accueil.deconnexionLabel.addMouseListener(listener);

        // Création de la fenêtre principale
        JFrame fenetrePrincipale = new JFrame();

        // Création du layout de cards et initialisation du conteneur principal
        cl = new CardLayout();
        conteneurPrincipal = new JPanel(cl);

        conteneurPrincipal.add(connexionPanel, CONNEXION);
        conteneurPrincipal.add(inscriptionPanel, INSCRIPTION);
        conteneurPrincipal.add(accueilPanel, ACCUEIL);
        conteneurPrincipal.add(rendezvousPanel, RENDEZVOUS);
        conteneurPrincipal.add(comptePanel, COMPTE);
        conteneurPrincipal.add(recherchePanel, RECHERCHE);
        conteneurPrincipal.add(infoPanel, INFODOCTEUR);
        conteneurPrincipal.add(speadminPanel, SPECIALISTEADMIN);
        conteneurPrincipal.add(infodocteuradminPanel, INFODOCTEURADMIN);
        conteneurPrincipal.add(statsadminPanel, STATSADMIN);
        conteneurPrincipal.add(ajoutSpeAdminPanel,SPECIALISTEADMIN);

        // Paramétrage de la fenetre principale
        fenetrePrincipale.add(conteneurPrincipal);
        fenetrePrincipale.setTitle("Feurissimo");
        fenetrePrincipale.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fenetrePrincipale.setSize(1920, 900);
        fenetrePrincipale.setLocationRelativeTo(null);
        fenetrePrincipale.setVisible(true);

    }

    public void updateRecherche(ArrayList<Specialiste> listeSpecialistes) {

        recherche.resultatRecherche = listeSpecialistes;

        conteneurPrincipal.remove(recherchePanel);
        recherche.mapSpecialistesInfo.clear();
        recherche.mapSpecialistesDispo.clear();

        recherchePanel = recherche.buildPanel();
        recherche.btnAccueil.addActionListener(listener);
        recherche.btnRendezVous.addActionListener(listener);
        recherche.btnCompte.addActionListener(listener);
        recherche.searchButton.addActionListener(listener);
        recherche.searchField.addMouseListener(listener);

        for (JLabel nameLabel : recherche.mapSpecialistesInfo.keySet()) {

            if (nameLabel != null) nameLabel.addMouseListener(listener);
        }

        for (JLabel availabilityLabel : recherche.mapSpecialistesDispo.keySet()) {

            if (availabilityLabel != null) availabilityLabel.addMouseListener(listener);
        }

        conteneurPrincipal.add(recherchePanel, RECHERCHE);

        recherche.revalidate();
        recherche.repaint();
    }

    public void updateRendezvous(ArrayList<RDV> listeRDV) {

        rendezvous.listeRDV = listeRDV;

        conteneurPrincipal.remove(rendezvousPanel);
        rendezvous.mapRDV.clear();

        rendezvousPanel = rendezvous.buildPanel();
        rendezvous.btnAccueil.addActionListener(listener);
        rendezvous.btnCompte.addActionListener(listener);

        for (JLabel labelAnnulation : rendezvous.mapRDV.keySet()) {

            if (labelAnnulation != null) labelAnnulation.addMouseListener(listener);
        }

        conteneurPrincipal.add(rendezvousPanel, RENDEZVOUS);

        rendezvous.revalidate();
        rendezvous.repaint();
    }

    public void updateInfoDocteur(Specialiste specialiste) {

        info.specialiste = specialiste;

        conteneurPrincipal.remove(infoPanel);

        infoPanel = info.buildPanel();
        info.btnAccueil.addActionListener(listener);
        info.btnRendezVous.addActionListener(listener);
        info.btnCompte.addActionListener(listener);
        info.prendreRDVButton.addActionListener(listener);

        conteneurPrincipal.add(infoPanel, INFODOCTEUR);

        info.revalidate();
        info.repaint();
    }

    public void updateCompte() {

        compte.utilisateur = utilisateurActuel;

        conteneurPrincipal.remove(comptePanel);

        comptePanel = compte.buildPanel();
        compte.btnAccueil.addActionListener(listener);
        compte.btnRendezVous.addActionListener(listener);
        compte.modifierButton.addActionListener(listener);
        compte.btnChangerImage.addActionListener(listener);

        conteneurPrincipal.add(comptePanel, COMPTE);

        compte.revalidate();
        compte.repaint();
    }

    public void updateDispoRDV(Utilisateur utilisateurActuel,Specialiste specialiste, ArrayList<RDV> listeRDV) {

        dispordv.specialiste = specialiste;
        dispordv.listeRDV = listeRDV;
        dispordv.utilisateur = utilisateurActuel;

        dispordv.mapCreneaux.clear();

        dispordv.setContentPane(dispordv.buildPanel());

        for (JButton buttonCreneau : dispordv.mapCreneaux.keySet()) {

            if (buttonCreneau != null) buttonCreneau.addActionListener(listener);
        }

        dispordv.revalidate();
        dispordv.repaint();
    }

    public void updateConfirmationRDV(Specialiste specialiste, Utilisateur utilisateur, long date) {

        confrdv.specialiste = specialiste;
        confrdv.utilisateur = utilisateur;
        confrdv.date = date;

        confrdv.setContentPane(confrdv.buildPanel());

        confrdv.confirmerButton.addActionListener(listener);
        confrdv.annulerButton.addActionListener(listener);

        confrdv.revalidate();
        confrdv.repaint();
    }

    public void updateSpecialistesAdmin(ArrayList<Specialiste> listeSpecialistes) {

        speadmin.listeSpecialistes = listeSpecialistes;

        conteneurPrincipal.remove(speadminPanel);

        speadminPanel = speadmin.buildPanel();
        speadmin.btnDossierPatients.addActionListener(listener);
        speadmin.btnStatistiques.addActionListener(listener);
        speadmin.searchButton.addActionListener(listener);
        speadmin.searchField.addMouseListener(listener);

        for (JLabel nameLabel : speadmin.mapSpecialistesInfo.keySet()) {

            if (nameLabel != null) nameLabel.addMouseListener(listener);
        }

        for (JLabel availabilityLabel : speadmin.mapSpecialistesDispo.keySet()) {

            if (availabilityLabel != null) availabilityLabel.addMouseListener(listener);
        }

        conteneurPrincipal.add(speadminPanel, SPECIALISTEADMIN);

        speadmin.revalidate();
        speadmin.repaint();
    }

    public void updateInfoDocteurAdmin(Specialiste specialiste) {

        infodocteuradmin.specialiste = specialiste;

        conteneurPrincipal.remove(infodocteuradminPanel);

        infodocteuradminPanel = infodocteuradmin.buildPanel();
        infodocteuradmin.btnSpecialiste.addActionListener(listener);
        infodocteuradmin.btnDossierPatients.addActionListener(listener);
        infodocteuradmin.btnStatistiques.addActionListener(listener);
        infodocteuradmin.modifierButton.addActionListener(listener);

        conteneurPrincipal.add(infodocteuradminPanel, INFODOCTEURADMIN);

        infodocteuradmin.revalidate();
        infodocteuradmin.repaint();
    }

    public void updateStatsAdmin(int nombrePatients, int nombreSpecialistes, TreeMap<Long, Integer> mapRDV) {

        statsadmin.nombrePatients = nombrePatients;
        statsadmin.nombreSpecialistes = nombreSpecialistes;
        statsadmin.mapRDV = mapRDV;

        conteneurPrincipal.remove(statsadminPanel);

        statsadminPanel = statsadmin.buildPanel();
        statsadmin.btnSpecialiste.addActionListener(listener);
        statsadmin.btnDossierPatients.addActionListener(listener);

        conteneurPrincipal.add(statsadminPanel, STATSADMIN);

        statsadmin.revalidate();
        statsadmin.repaint();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(FenetrePrincipale::new);
    }


}


