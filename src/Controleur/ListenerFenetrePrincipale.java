package Controleur;

import Modele.Adresse;
import Modele.Patient;
import Modele.Utilisateur;
import Vue.FenetrePrincipale;
import DAO.*;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ListenerFenetrePrincipale implements ActionListener, MouseListener {

    FenetrePrincipale fenetre;
    DaoFactory dao;
    UtilisateurDAOImpl utilisateurDAO;

    public ListenerFenetrePrincipale(FenetrePrincipale fenetre) {

        this.fenetre = fenetre;
        this.dao = DaoFactory.getInstance("projetjava", "root", "");
        this.utilisateurDAO = new UtilisateurDAOImpl(dao);
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
            String adresse = fenetre.inscription.adresseField.getText();
            JRadioButton homme = fenetre.inscription.hommeRadio;
            JRadioButton femme = fenetre.inscription.femmeRadio;

            try {

                Utilisateur.verifUtilisateur(mail, password, confirmPassword, nom, prenom, age, telephone, homme, femme);

                char sexe;

                if (homme.isSelected()) {

                    sexe = 'H';

                } else {

                    sexe = 'F';
                }

                Adresse adressePatient = new Adresse(0, "", "", "0");

                Patient patient = new Patient(0, nom, prenom, Integer.parseInt(age), adressePatient, sexe, mail, password, telephone, "");

                utilisateurDAO.ajouterUtilisateur(patient);

                fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.CONNEXION);

            } catch (Exception ex) {

                fenetre.inscription.erreurLabel.setText(ex.getMessage());
            }

        } else if (source == fenetre.rendezvous.btnAccueil || source == fenetre.compte.btnAccueil) {

            fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.ACCUEIL);

        } else if (source == fenetre.accueil.btnRendezVous || source == fenetre.compte.btnRendezVous) {

            fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.RENDEZVOUS);

        } else if (source == fenetre.accueil.btnCompte || source == fenetre.rendezvous.btnCompte) {

            fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.COMPTE);

        } else if (source == fenetre.accueil.searchButton) {

            String recherche = fenetre.accueil.searchField.getText();

            // ICI Requete du DAO pour rechercher dans la base de données

            //fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.)
        }
    }

    public void mouseClicked(MouseEvent e) {

        Object source = e.getSource();

        // Création de compte
        if (source == fenetre.connexion.createAccountLabel) {

            fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.INSCRIPTION);
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
