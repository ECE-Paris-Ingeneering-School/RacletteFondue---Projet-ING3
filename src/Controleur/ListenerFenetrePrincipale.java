package Controleur;

import Modele.*;
import Modele.Exceptions.ChampsVidesException;
import Vue.FenetrePrincipale;
import DAO.*;
import Vue.RechercheDocteur;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Classe du listener de la fenêtre principale
 * **/
public class ListenerFenetrePrincipale implements ActionListener, MouseListener {

    public FenetrePrincipale fenetre;
    public DaoFactory dao;
    public UtilisateurDAOImpl utilisateurDAO;
    public RdvDAOImpl rdvDAO;

    public ListenerFenetrePrincipale(FenetrePrincipale fenetre)  {

        this.fenetre = fenetre;
        this.dao = DaoFactory.getInstance("projetjava", "root", "");
        this.utilisateurDAO = new UtilisateurDAOImpl(dao);
        this.rdvDAO = new RdvDAOImpl(dao);

    }

    /**
     * Méthode implémentée permettant d'écouter l'appui de boutons
     * **/
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        // Bouton de connexion
        if (source == fenetre.connexion.connexionButton) {

            // On récupert les champs de connexion
            String mail = fenetre.connexion.mailField.getText();
            String mdp = fenetre.connexion.passwordField.getText();

            try {

                // On crée l'objet de l'utilisateur actuel et on affiche des pages différentes en fonction de son type
                fenetre.utilisateurActuel = utilisateurDAO.chercherUtilisateur(utilisateurDAO.connexionUtilisateur(mail,mdp));

                if (fenetre.utilisateurActuel instanceof Patient) {

                    fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.ACCUEIL);

                } else if (fenetre.utilisateurActuel instanceof Admin) {

                    // On récupère la liste des utilisateurs
                    ArrayList<Utilisateur> listeUtilisateurs = utilisateurDAO.getAllUtilisateur();
                    ArrayList<Specialiste> listeSpecialistes = new ArrayList<>();

                    // On n'en retient que les Spécialistes
                    for (Utilisateur utilisateur : listeUtilisateurs) {

                        if (utilisateur instanceof Specialiste) {

                            listeSpecialistes.add((Specialiste) utilisateur);
                        }
                    }

                    // On met à jour la fenêtre de liste de spécialistes de l'admin
                    fenetre.updateSpecialistesAdmin(listeSpecialistes);

                    fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.SPECIALISTEADMIN);
                }


            } catch (Exception ex) {

                fenetre.connexion.erreurLabel.setText(ex.getMessage());
            }


        } else if (source == fenetre.ajoutSpeAdmin.ajouterButton) { // Bouton d'ajout de spécialiste

            // On récupère les champs généraux
            String nom = fenetre.ajoutSpeAdmin.nomField.getText();
            String specialite = fenetre.ajoutSpeAdmin.specialiteField.getText();
            String description = fenetre.ajoutSpeAdmin.descriptionField.getText();
            String tarif = fenetre.ajoutSpeAdmin.tarifField.getText();


            // Les champs de l'adresse
            String numero = fenetre.ajoutSpeAdmin.numeroField.getText();
            String rue = fenetre.ajoutSpeAdmin.rueField.getText();
            String codePostal = fenetre.ajoutSpeAdmin.codePostalField.getText();
            String ville = fenetre.ajoutSpeAdmin.villeField.getText();

            try {

                // Vérification des données saisies
                Utilisateur.verifUtilisateur(nom, specialite, description, tarif, numero, rue, codePostal, ville);

                // Création de l'objet et ajout du spécialiste dans la BDD
                Adresse adresseSpecialiste = new Adresse(Integer.parseInt(codePostal), ville, rue, numero);

                Specialiste specialiste = new Specialiste(0,nom,"",0,adresseSpecialiste,'M',"","","","",specialite,description,Double.parseDouble(tarif));

                utilisateurDAO.ajouterUtilisateur(specialiste);

                fenetre.ajoutSpeAdmin.nomField.setText("");
                fenetre.ajoutSpeAdmin.specialiteField.setText("");
                fenetre.ajoutSpeAdmin.descriptionField.setText("");
                fenetre.ajoutSpeAdmin.tarifField.setText("");
                fenetre.ajoutSpeAdmin.numeroField.setText("");
                fenetre.ajoutSpeAdmin.rueField.setText("");
                fenetre.ajoutSpeAdmin.codePostalField.setText("");
                fenetre.ajoutSpeAdmin.villeField.setText("");
                fenetre.ajoutSpeAdmin.erreurLabel.setText("");


                ArrayList<Utilisateur> listeUtilisateurs = utilisateurDAO.getAllUtilisateur();
                ArrayList<Specialiste> listeSpecialistes = new ArrayList<>();


                for (Utilisateur utilisateur : listeUtilisateurs) {

                    if (utilisateur instanceof Specialiste) {

                        listeSpecialistes.add((Specialiste) utilisateur);
                    }
                }

                fenetre.updateSpecialistesAdmin(listeSpecialistes);

                fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.SPECIALISTEADMIN);


            } catch (Exception ex) {

                fenetre.ajoutSpeAdmin.erreurLabel.setText(ex.getMessage());
            }

        } else if (source == fenetre.inscription.inscrireButton) { // Bouton d'inscription

            // Récupération des champs
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

                // Vérification des données saisies
                Utilisateur.verifUtilisateur(mail, password, confirmPassword, nom, prenom, age, telephone, homme, femme, numero, rue, codePostal, ville);

                char sexe;

                if (homme.isSelected()) {

                    sexe = 'H';

                } else {

                    sexe = 'F';
                }

                Adresse adressePatient = new Adresse(Integer.parseInt(codePostal), ville, rue, numero);

                Patient patient = new Patient(0, nom, prenom, Integer.parseInt(age), adressePatient, sexe, mail, password, telephone, "");

                // Ajout de l'utilisateur en BDD
                utilisateurDAO.ajouterUtilisateur(patient);

                fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.CONNEXION);

            } catch (Exception ex) {

                fenetre.inscription.erreurLabel.setText(ex.getMessage());
            }

        } else if (source == fenetre.rendezvous.btnAccueil
                || source == fenetre.compte.btnAccueil
                || source == fenetre.recherche.btnAccueil
                || source == fenetre.info.btnAccueil) { // Bouton de la page d'accueil

            // Affichage de la page d'accueil
            fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.ACCUEIL);

        } else if (source == fenetre.accueil.btnRendezVous
                || source == fenetre.compte.btnRendezVous
                || source == fenetre.recherche.btnRendezVous
                || source == fenetre.info.btnRendezVous) { // Bouton de la page RDV

            // Récupération de la liste de RDV de l'utilisateur actuel
            ArrayList<RDV> listeRDV = rdvDAO.chercherRDV(fenetre.utilisateurActuel.getUtilisateurId());

            // Mise à jour et affichage de la page de RDV
            fenetre.updateRendezvous(listeRDV);

            fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.RENDEZVOUS);

        } else if (source == fenetre.accueil.btnCompte
                || source == fenetre.rendezvous.btnCompte
                || source == fenetre.recherche.btnCompte
                || source == fenetre.info.btnCompte) { // Bouton de la page compte

            fenetre.updateCompte();

            fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.COMPTE);

        } else if (source == fenetre.accueil.searchButton
                || source == fenetre.recherche.searchButton) { // Bouton de recherche

            String recherche;

            // Récupération du contenu de la barre de recherche
            if (source == fenetre.accueil.searchButton) {

                recherche = fenetre.accueil.searchField.getText();

            } else {

                recherche = fenetre.recherche.searchField.getText();
            }

            // Requete du DAO pour rechercher dans la base de données
            ArrayList<Specialiste> listeSpecialistes = utilisateurDAO.rechercheSpecialiste(recherche);

            // Mise à jour et affichage de la page de résultats de recherche
            fenetre.updateRecherche(listeSpecialistes);

            // Print keys and values
            /*for (JLabel i : fenetre.recherche.mapSpecialistes.keySet()) {
                System.out.println("key: " + i.getText() + " value: " + fenetre.recherche.mapSpecialistes.get(i).getUtilisateurNom());
            }*/

            fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.RECHERCHE);

    } else if (source == fenetre.speAdmin.searchButton) { // Bouton de recherche de spécialistes dans la vue admin

            String recherche;

            recherche = fenetre.speAdmin.searchField.getText();

            // Requete du DAO pour rechercher dans la base de données
            ArrayList<Specialiste> listeSpecialistes = utilisateurDAO.rechercheSpecialiste(recherche);

            fenetre.updateSpecialistesAdmin(listeSpecialistes);

            fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.SPECIALISTEADMIN);

    } else if (source == fenetre.info.prendreRDVButton) { // Bouton de prise de RDV

            // Récupération de la liste de RDV du spécialiste concerné
            ArrayList<RDV> listeRDV = rdvDAO.chercherRDV(fenetre.info.specialiste.getUtilisateurId());

            // Mise à jour et affichae de la fenêtre modale de disponibilités du spécialiste
            fenetre.updateDispoRDV(fenetre.utilisateurActuel,fenetre.info.specialiste, listeRDV);

            fenetre.dispordv.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            fenetre.dispordv.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            fenetre.dispordv.setVisible(true);

        } else if (source == fenetre.confrdv.confirmerButton) { // Bouton de confirmation de prise de RDV

            // On crée le rdv dans la BDD
            RDV rdv = new RDV(fenetre.confrdv.specialiste, fenetre.confrdv.utilisateur, fenetre.confrdv.date);
            rdvDAO.ajouterRDV(rdv);

            // On récupère la liste de RDV de l'utilisateur actuel
            ArrayList<RDV> listeRDV = rdvDAO.chercherRDV(fenetre.utilisateurActuel.getUtilisateurId());

            fenetre.updateRendezvous(listeRDV);

            // On affiche la page de RDV
            fenetre.confrdv.dispose();
            fenetre.dispordv.dispose();
            fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.RENDEZVOUS);

        } else if (source == fenetre.confrdv.annulerButton) { // Bouton d'annulation lors de la confirmation de prise de RDV

            fenetre.confrdv.dispose();

        } else if (source == fenetre.compte.btnChangerImage) { // Bouton de changement de photo de profil dans la page compte

            // Initialisation des objets et variables
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(fenetre.compte);

            if (result == JFileChooser.APPROVE_OPTION) {

                File fichierchoisi = fileChooser.getSelectedFile();
                String extention = fichierchoisi.getName().substring(fichierchoisi.getName().lastIndexOf("."));
                File dossier = new File("src/images");
                if (!dossier.exists()) {
                    dossier.mkdirs();
                }

                // Création de l'objet permettant de sauvegarder l'image sélectionnée
                File imageaSauvegarder = new File(dossier, fenetre.utilisateurActuel.getUtilisateurId() + "_profile_image" + extention);

                try {

                    // Stockage de l'image sélectionnée et mise à jour de son affichage dans la fenêtre de compte
                    Files.copy(fichierchoisi.toPath(), imageaSauvegarder.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    fenetre.compte.cheminImage = new ImageIcon(fichierchoisi.getAbsolutePath());
                    String acces = imageaSauvegarder.getPath();
                    fenetre.compte.cheminImage.setDescription("src/Images/" + fenetre.utilisateurActuel.getUtilisateurId() + "_profile_image" + extention);
                    Image newImage = fenetre.compte.cheminImage.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                    fenetre.compte.imageField.setIcon(new ImageIcon(newImage));

                } catch (Exception exeption) {

                    exeption.printStackTrace();
                    JOptionPane.showMessageDialog(fenetre.compte, "Erreur lors de la sauvegarde de l'image.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

                File selectedFile = fileChooser.getSelectedFile();
                ImageIcon newIcon = new ImageIcon(selectedFile.getAbsolutePath());
                Image newImage = newIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                fenetre.compte.imageField.setIcon(new ImageIcon(newImage));

            }

        } else if (source == fenetre.compte.modifierButton) { // Bouton de modification du compte

            // Récupération des champs
            String mail = fenetre.compte.mailField.getText();
            String password = fenetre.compte.passwordField.getText();
            String nom = fenetre.compte.nomField.getText();
            String prenom = fenetre.compte.prenomField.getText();
            String age = fenetre.compte.ageField.getText();
            String telephone = fenetre.compte.telephoneField.getText();
            String numero = fenetre.compte.numeroField.getText();
            String rue = fenetre.compte.rueField.getText();
            String codePostal = fenetre.compte.codePostalField.getText();
            String ville = fenetre.compte.villeField.getText();
            String image = fenetre.compte.cheminImage.getDescription();


            try {

                // Vérification des données saisies
                Utilisateur.verifUtilisateur(mail, password, nom, prenom, age, telephone, numero, rue, codePostal, ville);

                Adresse adressePatient = new Adresse(Integer.parseInt(codePostal), ville, rue, numero);

                Patient patient = new Patient(fenetre.utilisateurActuel.getUtilisateurId(), nom, prenom, Integer.parseInt(age), adressePatient, fenetre.utilisateurActuel.getUtilisateurSexe(), mail, password, telephone, image);

                // Modification de l'utilisateur dans la BDD
                utilisateurDAO.modifierUtilisateur(patient);

                fenetre.utilisateurActuel = utilisateurDAO.chercherUtilisateur(fenetre.utilisateurActuel.getUtilisateurId());

                // Mise à jour et affichage de la page compte
                fenetre.updateCompte();

                fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.COMPTE);

            } catch (Exception ex) {

                fenetre.compte.confirmationLabel.setText(ex.getMessage());
            }

        } else if (source == fenetre.infoDocteurAdmin.btnChargerImage) { // Bouton de changement de photo de profil dans la page d'informations de spécialistes dans la vue admin


            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(fenetre.infoDocteurAdmin);

            if (result == JFileChooser.APPROVE_OPTION) {


                File fichierchoisi = fileChooser.getSelectedFile();
                String extention = fichierchoisi.getName().substring(fichierchoisi.getName().lastIndexOf("."));
                File dossier = new File("src/images");
                if (!dossier.exists()) {
                    dossier.mkdirs();
                }

                File imageaSauvegarder = new File(dossier, fenetre.infoDocteurAdmin.specialiste.getUtilisateurId() + "_profile_image" + extention);

                try {
                    Files.copy(fichierchoisi.toPath(), imageaSauvegarder.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    fenetre.infoDocteurAdmin.cheminImage = new ImageIcon(fichierchoisi.getAbsolutePath());
                    String acces = imageaSauvegarder.getPath();
                    fenetre.infoDocteurAdmin.cheminImage.setDescription("src/Images/" + fenetre.infoDocteurAdmin.specialiste.getUtilisateurId() + "_profile_image" + extention);


                }
                catch (Exception exeption) {
                    exeption.printStackTrace();
                    JOptionPane.showMessageDialog(fenetre.infoDocteurAdmin, "Erreur lors de la sauvegarde de l'image.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

                File selectedFile = fileChooser.getSelectedFile();
                ImageIcon newIcon = new ImageIcon(selectedFile.getAbsolutePath());
                Image newImage = newIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                fenetre.infoDocteurAdmin.imageField.setIcon(new ImageIcon(newImage));

            }

        } else if (source == fenetre.infoDocteurAdmin.modifierButton) { // Bouton de modification du spécialiste dans la vue admin

                // Récupération des informations générales
                String nom = fenetre.infoDocteurAdmin.nomField.getText();
                String specialite = fenetre.infoDocteurAdmin.specialiteField.getText();
                String description = fenetre.infoDocteurAdmin.descriptionField.getText();
                String tarif = fenetre.infoDocteurAdmin.tarifField.getText();
                String image = fenetre.infoDocteurAdmin.cheminImage.getDescription();

                // Récupération de l'adresse
                String numero = fenetre.infoDocteurAdmin.numeroField.getText();
                String rue = fenetre.infoDocteurAdmin.rueField.getText();
                String codePostal = fenetre.infoDocteurAdmin.codePostalField.getText();
                String ville = fenetre.infoDocteurAdmin.villeField.getText();


            try {

                Adresse adresseSpecialiste = new Adresse(Integer.parseInt(codePostal), ville, rue, numero);

                    Specialiste specialiste = new Specialiste(fenetre.infoDocteurAdmin.specialiste.getUtilisateurId(),nom,"",0,adresseSpecialiste,'M',"","","",image,specialite,description,Double.parseDouble(tarif));

                // Modification du spécialite en BDD
                utilisateurDAO.modifierUtilisateur(specialiste);

                ArrayList<Utilisateur> listeUtilisateurs = utilisateurDAO.getAllUtilisateur();
                ArrayList<Specialiste> listeSpecialistes = new ArrayList<>();


                for (Utilisateur utilisateur : listeUtilisateurs) {

                    if (utilisateur instanceof Specialiste) {

                        listeSpecialistes.add((Specialiste) utilisateur);
                    }
                }

                fenetre.updateSpecialistesAdmin(listeSpecialistes);

                fenetre.updateSpecialistesAdmin(fenetre.speAdmin.listeSpecialistes);
                fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.SPECIALISTEADMIN);

            } catch (Exception ex) {

                System.out.println(ex.getMessage());
            }

        } else if (source == fenetre.infoDocteurAdmin.supprimerButton) { // Bouton de suppression d'un spécialiste dans la vue admin


                try {

                    utilisateurDAO.supprimerUtilisateur(fenetre.infoDocteurAdmin.specialiste);

                    ArrayList<Utilisateur> listeUtilisateurs = utilisateurDAO.getAllUtilisateur();
                    ArrayList<Specialiste> listeSpecialistes = new ArrayList<>();



                    for (Utilisateur utilisateur : listeUtilisateurs) {

                        if (utilisateur instanceof Specialiste) {

                            listeSpecialistes.add((Specialiste) utilisateur);
                        }
                    }

                    fenetre.updateSpecialistesAdmin(listeSpecialistes);

                    fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.SPECIALISTEADMIN);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());

                }

        } else if (source == fenetre.statsAdmin.btnSpecialiste
                || source == fenetre.infoDocteurAdmin.btnSpecialiste
                || source == fenetre.dossiersPatientsAdmin.btnSpecialiste
                || source == fenetre.infoPatientAdmin.btnSpecialiste
                || source == fenetre.infoDocteurAdmin.annulerButton
                || source == fenetre.ajoutSpeAdmin.btnSpecialiste
                || source == fenetre.ajoutSpeAdmin.annulerButton) { // Bouton de la page de spécialistes dans la vue admin

            ArrayList<Utilisateur> listeUtilisateurs = utilisateurDAO.getAllUtilisateur();
            ArrayList<Specialiste> listeSpecialistes = new ArrayList<>();

            for (Utilisateur utilisateur : listeUtilisateurs) {

                if (utilisateur instanceof Specialiste) {

                    listeSpecialistes.add((Specialiste) utilisateur);
                }
            }

            fenetre.updateSpecialistesAdmin(listeSpecialistes);

            fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.SPECIALISTEADMIN);

        } else if (source == fenetre.speAdmin.btnDossierPatients
                || source == fenetre.statsAdmin.btnDossierPatients
                || source == fenetre.infoDocteurAdmin.btnDossierPatients
                || source == fenetre.infoPatientAdmin.btnDossierPatients) { // Bouton de la page de dossiers patients dans la vue admin

            // On récupère la liste de patients
            ArrayList<Utilisateur> listeUtilisateurs = utilisateurDAO.getAllUtilisateur();
            ArrayList<Patient> listePatients = new ArrayList<>();

            for (Utilisateur utilisateur : listeUtilisateurs) {

                if (utilisateur instanceof Patient) {

                    listePatients.add((Patient) utilisateur);
                }
            }

            // On met à jour les informations de la page
            fenetre.updateDossierPatientsAdmin(listePatients);

            fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.DOSSIERSPATIENTSADMIN);

        } else if (source == fenetre.dossiersPatientsAdmin.searchButton) { // Bouton de recherche dans la page de dossiers patients dans la vue admin

            String recherche;

            recherche = fenetre.dossiersPatientsAdmin.searchField.getText();

            // Requete du DAO pour rechercher dans la base de données
            ArrayList<Patient> listePatients = utilisateurDAO.recherchePatient(recherche);

            fenetre.updateDossierPatientsAdmin(listePatients);

            fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.DOSSIERSPATIENTSADMIN);

        } else if (source == fenetre.speAdmin.btnStatistiques
                || source == fenetre.infoDocteurAdmin.btnStatistiques
                || source == fenetre.ajoutSpeAdmin.btnStatistiques
                || source == fenetre.infoPatientAdmin.btnStatistiques
                || source == fenetre.dossiersPatientsAdmin.btnStatistiques) { // Bouton de la page statistiques de la vue admin

            ArrayList<Utilisateur> listeUtilisateurs = utilisateurDAO.getAllUtilisateur();
            ArrayList<RDV> listeRDV = new ArrayList<>();
            TreeMap<Long, Integer> mapRDV = new TreeMap<>();
            int nombrePatients = 0;
            int nombreSpecialistes = 0;
            int compteurRDV = 0;

            for (Utilisateur utilisateur : listeUtilisateurs) {

                if (utilisateur instanceof Patient) {

                    nombrePatients++;

                } else if (utilisateur instanceof Specialiste) {

                    nombreSpecialistes++;

                    listeRDV.addAll(rdvDAO.chercherRDV(utilisateur.getUtilisateurId()));
                }
            }

            // On convertir la date actuelle en date lisible
            String temp = new SimpleDateFormat("dd/MM/yyyy").format(new Date (fenetre.dateActuelle));

            long nouveauTimestamp = 0;

            try {

                nouveauTimestamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(temp + " 00:00:00").getTime();

            } catch (Exception ex) {

                // On ne fait rien
            }

            for (int i=0; i<7; i++) { // +86400000 au timestamp pour ajouter un jour

                // On parcourt la liste de rdv
                for (RDV rdv : listeRDV) {

                    // Si le rdv est dans la journée actuelle
                    if (rdv.getDate() > nouveauTimestamp && rdv.getDate() < (nouveauTimestamp+86400000)) {

                        compteurRDV++;
                    }
                }

                // On ajoute le nombre de rdv comptés pour ce jour la
                mapRDV.put(nouveauTimestamp, compteurRDV);

                // On passe au jour suivant
                nouveauTimestamp = nouveauTimestamp + 86400000;

                // On réinitialise le compteur de rdv
                compteurRDV = 0;
            }

            fenetre.updateStatsAdmin(nombrePatients, nombreSpecialistes, mapRDV);

            fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.STATSADMIN);

        }

        // On map les boutons de créneaux disponibles en fonction de leur date
        for (JButton buttonCreneau : fenetre.dispordv.mapCreneaux.keySet()) {

            if (source == buttonCreneau) {

                // Si c'est un patient qui réserve un créneau
                if (fenetre.utilisateurActuel instanceof Patient) {

                    fenetre.updateConfirmationRDV(fenetre.dispordv.specialiste, fenetre.utilisateurActuel, fenetre.dispordv.mapCreneaux.get(buttonCreneau));

                    fenetre.confrdv.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                    fenetre.confrdv.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    fenetre.confrdv.setVisible(true);

                }  else if (fenetre.utilisateurActuel instanceof Admin) { // Si c'est un admin qui réserve un créneau

                    RDV rdv = new RDV(fenetre.dispordv.specialiste, fenetre.utilisateurActuel, fenetre.dispordv.mapCreneaux.get(buttonCreneau));

                    ArrayList<RDV> listRDV;
                    listRDV = rdvDAO.chercherRDV(fenetre.dispordv.specialiste.getUtilisateurId());

                    int temp = 1;

                    for (RDV rdv_i : listRDV) {

                        if (rdv_i.getDate() == rdv.getDate()){
                            rdvDAO.supprimerRDV(rdv_i);
                            temp = 0;
                            break;
                        }

                    }

                    if (temp == 1){

                        rdvDAO.ajouterRDV(rdv);
                    }

                    ArrayList<RDV> listeRDV = rdvDAO.chercherRDV(fenetre.dispordv.specialiste.getUtilisateurId());
                    fenetre.updateDispoRDV(fenetre.utilisateurActuel,fenetre.dispordv.specialiste, listeRDV);

                    break;

                }

            }
        }

    }

    /**
     * Méthode implémentée permettant d'écouter les clicks de souris, notamment sur les labels
     * **/
    public void mouseClicked(MouseEvent e) {

        Object source = e.getSource();

        // Label de création de compte
        if (source == fenetre.connexion.createAccountLabel) {

            fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.INSCRIPTION);

        } else if (source == fenetre.accueil.deconnexionLabel
                || source == fenetre.speAdmin.deconnexionLabel) { // Label de déconnexion

            fenetre.connexion.passwordField.setText("");

            fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.CONNEXION);


        } else if (source == fenetre.speAdmin.addSpecialistLabel) { // Label d'ajout de spécialiste dans la vue admin

            fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.AJOUTERDOCTEURADMIN);

        }

        // On map les noms de spécialistes aux objets qui leurs sont associés pour afficher la page de leurs informations
        for (JLabel nameLabel : fenetre.recherche.mapSpecialistesInfo.keySet()) {

            if (source == nameLabel) {

                fenetre.updateInfoDocteur(fenetre.recherche.mapSpecialistesInfo.get(nameLabel));

                fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.INFODOCTEUR);

            }
        }

        // On map les labels de disponibilités des spécialistes aux objets qui leurs sont associés pour afficher la page de leurs disponibilités
        for (JLabel availabilityLabel : fenetre.recherche.mapSpecialistesDispo.keySet()) {

            if (source == availabilityLabel) {

                ArrayList<RDV> listeRDV = rdvDAO.chercherRDV(fenetre.recherche.mapSpecialistesDispo.get(availabilityLabel).getUtilisateurId());

                fenetre.updateDispoRDV(fenetre.utilisateurActuel,fenetre.recherche.mapSpecialistesDispo.get(availabilityLabel), listeRDV);

                fenetre.dispordv.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                fenetre.dispordv.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                fenetre.dispordv.setVisible(true);

            }
        }

        // On map les labels d'annulations de RDV aux objets qui leurs sont associés
        for (JLabel labelAnnulation : fenetre.rendezvous.mapRDV.keySet()) {

            if (source == labelAnnulation) {

                rdvDAO.supprimerRDV(fenetre.rendezvous.mapRDV.get(labelAnnulation));

                ArrayList<RDV> listeRDV = rdvDAO.chercherRDV(fenetre.utilisateurActuel.getUtilisateurId());

                fenetre.updateRendezvous(listeRDV);

                fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.RENDEZVOUS);

                break;
            }
        }

        // On map les labels des noms des spécialistes aux objets qui leurs sont associés pour afficher la page contenant leurs informations dans la vue admin
        for (JLabel nameLabel : fenetre.speAdmin.mapSpecialistesInfo.keySet()) {

            if (source == nameLabel) {

                fenetre.updateInfoDocteurAdmin(fenetre.speAdmin.mapSpecialistesInfo.get(nameLabel));

                fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.INFODOCTEURADMIN);

            }
        }

        // On map les labels de disponibilités des spécialistes aux objets qui leurs sont associés pour afficher la page de leurs disponibilités dans la vue admin
        for (JLabel availabilityLabel : fenetre.speAdmin.mapSpecialistesDispo.keySet()) {

            if (source == availabilityLabel) {

                ArrayList<RDV> listeRDV = rdvDAO.chercherRDV(fenetre.speAdmin.mapSpecialistesDispo.get(availabilityLabel).getUtilisateurId());

                fenetre.updateDispoRDV(fenetre.utilisateurActuel,fenetre.speAdmin.mapSpecialistesDispo.get(availabilityLabel), listeRDV);

                fenetre.dispordv.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                fenetre.dispordv.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                fenetre.dispordv.setVisible(true);

            }
        }

        // On map les labels de dossier des patients aux objets qui leurs sont associés pour afficher la page de leurs informations dans la vue admin
        for (JLabel dossierLabel : fenetre.dossiersPatientsAdmin.mapPatients.keySet()) {

            if (source == dossierLabel) {

                ArrayList<RDV> listeRDV = rdvDAO.chercherRDV(fenetre.dossiersPatientsAdmin.mapPatients.get(dossierLabel).getUtilisateurId());

                fenetre.updateInfoPatientAdmin(fenetre.dossiersPatientsAdmin.mapPatients.get(dossierLabel), listeRDV);

                fenetre.cl.show(fenetre.conteneurPrincipal, fenetre.INFOPATIENTADMIN);
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
