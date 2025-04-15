package Controleur;

import Modele.*;
import Vue.FenetrePrincipale;
import DAO.*;
import Vue.RechercheDocteur;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Objects;

public class ListenerFenetrePrincipale implements ActionListener, MouseListener {

    FenetrePrincipale fenetre;
    DaoFactory dao;
    UtilisateurDAOImpl utilisateurDAO;
    RdvDAOImpl rdvDAO;

    public ListenerFenetrePrincipale(FenetrePrincipale fenetre) {

        this.fenetre = fenetre;
        this.dao = DaoFactory.getInstance("projetjava", "root", "");
        this.utilisateurDAO = new UtilisateurDAOImpl(dao);
        this.rdvDAO = new RdvDAOImpl(dao);
    }

    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        // Bouton de connexion
        if (source == fenetre.connexion.connexionButton) {

            String mail = fenetre.connexion.mailField.getText();
            String mdp = fenetre.connexion.passwordField.getText();

            try {

                fenetre.utilisateurActuel = utilisateurDAO.chercherUtilisateur(utilisateurDAO.connexionUtilisateur(mail,mdp));

                fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.ACCUEIL);

            } catch (Exception ex) {

                fenetre.connexion.erreurLabel.setText(ex.getMessage());
            }


        } else if (source == fenetre.inscription.inscrireButton) {

            String mail = fenetre.inscription.mailField.getText();
            String password = fenetre.inscription.passwordField.getText();
            String confirmPassword = fenetre.inscription.confirmPasswordField.getText();
            String nom = fenetre.inscription.nomField.getText();
            String prenom = fenetre.inscription.prenomField.getText();
            String age = fenetre.inscription.ageField.getText();
            String telephone = fenetre.inscription.telephoneField.getText();
            String numero = fenetre.inscription.numeroField.getText();
            String rue = fenetre.inscription.rueField.getText();
            String codePostal = fenetre.inscription.codePostalField.getText();
            String ville = fenetre.inscription.villeField.getText();


            JRadioButton homme = fenetre.inscription.hommeRadio;
            JRadioButton femme = fenetre.inscription.femmeRadio;

            try {

                Utilisateur.verifUtilisateur(mail, password, confirmPassword, nom, prenom, age, telephone, homme, femme, numero, rue, codePostal, ville);

                char sexe;

                if (homme.isSelected()) {

                    sexe = 'H';

                } else {

                    sexe = 'F';
                }

                Adresse adressePatient = new Adresse(Integer.parseInt(codePostal), ville, rue, numero);

                Patient patient = new Patient(0, nom, prenom, Integer.parseInt(age), adressePatient, sexe, mail, password, telephone, "");

                utilisateurDAO.ajouterUtilisateur(patient);

                fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.CONNEXION);

            } catch (Exception ex) {

                fenetre.inscription.erreurLabel.setText(ex.getMessage());
            }

        } else if (source == fenetre.rendezvous.btnAccueil
                || source == fenetre.compte.btnAccueil
                || source == fenetre.recherche.btnAccueil
                || source == fenetre.info.btnAccueil) {

            fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.ACCUEIL);

        } else if (source == fenetre.accueil.btnRendezVous
                || source == fenetre.compte.btnRendezVous
                || source == fenetre.recherche.btnRendezVous
                || source == fenetre.info.btnRendezVous) {

            ArrayList<RDV> listeRDV = rdvDAO.chercherRDV(fenetre.utilisateurActuel.getUtilisateurId());

            fenetre.updateRendezvous(listeRDV);

            fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.RENDEZVOUS);

        } else if (source == fenetre.accueil.btnCompte
                || source == fenetre.rendezvous.btnCompte
                || source == fenetre.recherche.btnCompte
                || source == fenetre.info.btnCompte) {

            fenetre.updateCompte();

            fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.COMPTE);

        } else if (source == fenetre.accueil.searchButton || source == fenetre.recherche.searchButton) {

            String recherche;

            if (source == fenetre.accueil.searchButton) {

                recherche = fenetre.accueil.searchField.getText();

            } else {

                recherche = fenetre.recherche.searchField.getText();
            }

            // ICI Requete du DAO pour rechercher dans la base de données

            ArrayList<Specialiste> listeSpecialistes = utilisateurDAO.rechercheSpecialiste(recherche);

            fenetre.updateRecherche(listeSpecialistes);

            // Print keys and values
            /*for (JLabel i : fenetre.recherche.mapSpecialistes.keySet()) {
                System.out.println("key: " + i.getText() + " value: " + fenetre.recherche.mapSpecialistes.get(i).getUtilisateurNom());
            }*/

            fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.RECHERCHE);

        } else if (source == fenetre.info.prendreRDVButton) {

            ArrayList<RDV> listeRDV = rdvDAO.chercherRDV(fenetre.info.specialiste.getUtilisateurId());

            fenetre.updateDispoRDV(fenetre.info.specialiste, listeRDV);

            fenetre.dispordv.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            fenetre.dispordv.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            fenetre.dispordv.setVisible(true);

        } else if (source == fenetre.confrdv.confirmerButton) {

            // On crée le rdv dans la BDD
            RDV rdv = new RDV(fenetre.confrdv.specialiste, (Patient) fenetre.confrdv.utilisateur, fenetre.confrdv.date);
            rdvDAO.ajouterRDV(rdv);

            ArrayList<RDV> listeRDV = rdvDAO.chercherRDV(fenetre.utilisateurActuel.getUtilisateurId());

            fenetre.updateRendezvous(listeRDV);

            // On affiche la page de rdv
            fenetre.confrdv.dispose();
            fenetre.dispordv.dispose();
            fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.RENDEZVOUS);

        } else if (source == fenetre.confrdv.annulerButton) {

            fenetre.confrdv.dispose();
        }

        for (JButton buttonCreneau : fenetre.dispordv.mapCreneaux.keySet()) {

            if (source == buttonCreneau) {

                fenetre.updateConfirmationRDV(fenetre.dispordv.specialiste, fenetre.utilisateurActuel, fenetre.dispordv.mapCreneaux.get(buttonCreneau));

                fenetre.confrdv.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                fenetre.confrdv.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                fenetre.confrdv.setVisible(true);

            }
        }

    }

    public void mouseClicked(MouseEvent e) {

        Object source = e.getSource();

        // Création de compte
        if (source == fenetre.connexion.createAccountLabel) {

            fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.INSCRIPTION);

        } else if (source == fenetre.accueil.searchField || source == fenetre.recherche.searchField) { // Nettoyage de la barre de recherche au click de l'utilisateur

            if (Objects.equals(fenetre.accueil.searchField.getText(), "Nom, spécialité, lieu...") && source == fenetre.accueil.searchField) {

                fenetre.accueil.searchField.setText("");

            } else if (Objects.equals(fenetre.recherche.searchField.getText(), "Nom, spécialité, lieu...") && source == fenetre.recherche.searchField) {

                fenetre.recherche.searchField.setText("");
            }

        }

        for (JLabel nameLabel : fenetre.recherche.mapSpecialistesInfo.keySet()) {

            if (source == nameLabel) {

                fenetre.updateInfoDocteur(fenetre.recherche.mapSpecialistesInfo.get(nameLabel));

                fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.INFODOCTEUR);

            }
        }

        for (JLabel availabilityLabel : fenetre.recherche.mapSpecialistesDispo.keySet()) {

            if (source == availabilityLabel) {

                ArrayList<RDV> listeRDV = rdvDAO.chercherRDV(fenetre.recherche.mapSpecialistesDispo.get(availabilityLabel).getUtilisateurId());

                fenetre.updateDispoRDV(fenetre.recherche.mapSpecialistesDispo.get(availabilityLabel), listeRDV);

                fenetre.dispordv.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                fenetre.dispordv.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                fenetre.dispordv.setVisible(true);

            }
        }

        for (JLabel labelAnnulation : fenetre.rendezvous.mapRDV.keySet()) {

            if (source == labelAnnulation) {

                rdvDAO.supprimerRDV(fenetre.rendezvous.mapRDV.get(labelAnnulation));

                ArrayList<RDV> listeRDV = rdvDAO.chercherRDV(fenetre.utilisateurActuel.getUtilisateurId());

                fenetre.updateRendezvous(listeRDV);

                fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.RENDEZVOUS);

                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {


    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
