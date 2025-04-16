package Vue;

import Controleur.ListenerFenetrePrincipale;
import Modele.RDV;
import Modele.Specialiste;
import Modele.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Classe permettant de gérer la fenêtre principale du programme
 * **/
public class FenetrePrincipale {

    // Listener
    public ListenerFenetrePrincipale listener;

    // Utilisateur actuel
    public Utilisateur utilisateurActuel;

    // Nom des pages
    public final String CONNEXION = "Connexion";
    public final String INSCRIPTION = "Inscription";
    public final String ACCUEIL = "Accueil";
    public final String RENDEZVOUS = "Rendez-vous";
    public final String COMPTE = "Compte";
    public final String RECHERCHE = "Recherche";
    public final String INFODOCTEUR = "Information spécialiste";
    public final String DISPORDV = "Disponibilité rdv";
    public final String CONFIRMATIONRDV = "Confirmation rdv";

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

    /**
     * Constructeur de la fenêtre principale
     * **/
    public FenetrePrincipale() {

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

        // Instanciation des panels
        this.connexionPanel = connexion.buildPanel();
        this.inscriptionPanel = inscription.buildPanel();
        this.accueilPanel = accueil.buildPanel();
        this.rendezvousPanel = rendezvous.buildPanel();
        this.comptePanel = compte.buildPanel();
        this.recherchePanel = recherche.buildPanel();
        this.infoPanel = info.buildPanel();

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

        // RendezVous
        rendezvous.btnAccueil.addActionListener(listener);
        rendezvous.btnCompte.addActionListener(listener);

        // Compte
        compte.btnAccueil.addActionListener(listener);
        compte.btnRendezVous.addActionListener(listener);


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

    public void updateDispoRDV(Specialiste specialiste, ArrayList<RDV> listeRDV) {

        dispordv.specialiste = specialiste;
        dispordv.listeRDV = listeRDV;

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

    public static void main(String[] args) {

        SwingUtilities.invokeLater(FenetrePrincipale::new);
    }


}


