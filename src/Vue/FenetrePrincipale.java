package Vue;

import Controleur.ListenerFenetrePrincipale;
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

    // Initialisation des objets de chaque page
    public Connexion connexion;
    public Inscription inscription;
    public Accueil accueil;
    public RendezVous rendezvous;
    public Compte compte;
    public RechercheDocteur recherche;

    // Initialisation des panels de chaque page
    public CardLayout cl;
    public JPanel conteneurPrincipal;
    public JPanel connexionPanel;
    public JPanel inscriptionPanel;
    public JPanel accueilPanel;
    public JPanel rendezvousPanel;
    public JPanel comptePanel;
    public JPanel recherchePanel;

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

        // Instanciation des panels
        this.connexionPanel = connexion.buildPanel();
        this.inscriptionPanel = inscription.buildPanel();
        this.accueilPanel = accueil.buildPanel();
        this.rendezvousPanel = rendezvous.buildPanel();
        this.comptePanel = compte.buildPanel();
        this.recherchePanel = recherche.buildPanel();

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

        // Recherche
        recherche.btnAccueil.addActionListener(listener);
        recherche.btnRendezVous.addActionListener(listener);
        recherche.btnCompte.addActionListener(listener);
        recherche.searchButton.addActionListener(listener);
        recherche.searchField.addMouseListener(listener);

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

        recherchePanel = recherche.buildPanel();
        recherche.btnAccueil.addActionListener(listener);
        recherche.btnRendezVous.addActionListener(listener);
        recherche.btnCompte.addActionListener(listener);
        recherche.searchButton.addActionListener(listener);
        recherche.searchField.addMouseListener(listener);

        conteneurPrincipal.add(recherchePanel, RECHERCHE);

        recherche.revalidate();
        recherche.repaint();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(FenetrePrincipale::new);
    }


}


