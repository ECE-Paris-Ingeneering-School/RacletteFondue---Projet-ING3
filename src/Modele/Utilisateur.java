package Modele;

import Modele.Exceptions.*;

import javax.swing.*;

/**
 * Classe Utilisateur
 */
public abstract class Utilisateur {

    private int utilisateurId;
    private String utilisateurNom;
    private String utilisateurPrenom;
    private int utilisateurAge;
    private Adresse utilisateurAdresse;
    private char utilisateurSexe;
    private String utilisateurMail;
    private String utilisateurPassword;
    private String utilisateurTel; //Si jamais l'utilisateur met des espaces ou des points
    private String utilisateurImage;

    /**
     * Constructeur de l'utilisateur avec sans paramètre
     */
    public Utilisateur(){

    }

    /**
     * Constructeur de l'utilisateur avec tous les paramètres
     */
    public Utilisateur (int putilisateurId, String putilisateurNom, String putilisateurPrenom,  int putilisateurAge, Adresse padresse, char putilisateurSexe, String putilisateurMail, String putilisateurPassword, String putilisateurTel, String putilisateurImage) {

        this.utilisateurId = putilisateurId;
        this.utilisateurNom = putilisateurNom;
        this.utilisateurPrenom = putilisateurPrenom;
        this.utilisateurAge = putilisateurAge;
        this.utilisateurAdresse = padresse;
        this.utilisateurSexe = putilisateurSexe;
        this.utilisateurMail = putilisateurMail;
        this.utilisateurPassword = putilisateurPassword;
        this.utilisateurTel = putilisateurTel;
        this.utilisateurImage = putilisateurImage;
    }

    public static void verifUtilisateur(String mailField, String passwordField, String confirmPasswordField, String nomField, String prenomField, String ageField, String telephoneField, JRadioButton hommeRadio, JRadioButton femmeRadio, String numeroField, String rueField, String codePostalField, String villeField) throws AgeException, ChampsVidesException, ConfirmationPasswordException, TelephoneException, AdresseException {

        if (mailField.isEmpty() || passwordField.isEmpty() || confirmPasswordField.isEmpty() || nomField.isEmpty() || prenomField.isEmpty() || ageField.isEmpty() || telephoneField.isEmpty() || (!hommeRadio.isSelected() & !femmeRadio.isSelected()) || numeroField.isEmpty() || rueField.isEmpty() || codePostalField.isEmpty() || villeField.isEmpty() || villeField.equals("Ville")) {

            throw new ChampsVidesException();
        }

        if (!passwordField.equals(confirmPasswordField)) {

            throw new ConfirmationPasswordException();
        }

        try {

            int age = Integer.parseInt(ageField);

            if (age < 1) {

                throw new AgeException();
            }

        } catch (Exception e) {

            throw new AgeException();
        }

        try {

            int telephone = Integer.parseInt(telephoneField);

            if (telephone < 0) {

                throw new TelephoneException();
            }

        } catch (Exception e) {

            throw new TelephoneException();
        }

        try {

            int numero = Integer.parseInt(numeroField);

            if (numero < 1) {

                throw new AdresseException();
            }

        } catch (Exception e) {

            throw new AdresseException();
        }

        try {

            int codePostal = Integer.parseInt(codePostalField);

            if (codePostal < 1) {

                throw new AdresseException();

            }

        } catch (Exception e) {

            throw new AdresseException();
        }
    }

    /**
     * Getteur retournant l'identifiant de l'utilisateur
     */
    public int getUtilisateurId(){
        return utilisateurId;
    }

    /**
     * Getteur retournant le nom de l'utilisateur
     */
    public String getUtilisateurNom() {
        return utilisateurNom;
    }

    /**
     * Getteur retournant le prénom de l'utilisateur
     */
    public String getUtilisateurPrenom() {
        return utilisateurPrenom;
    }

    /**
     * Getteur retournant l'age de l'utilisateur
     */
    public int getUtilisateurAge() {
        return utilisateurAge;
    }

    /**
     * Getteur retournant l'adresse de l'utilisateur
     */
    public Adresse getUtilisateurAdresse() {
        return utilisateurAdresse;
    }

    /**
     * Getteur retournant le sexe de l'utilisateur
     */
    public char getUtilisateurSexe() {
        return utilisateurSexe;
    }

    /**
     * Getteur retournant le mail de l'utilisateur
     */
    public String getUtilisateurMail() {
        return utilisateurMail;
    }

    /**
     * Getteur retournant le mot de passe de l'utilisateur
     */
    public String getUtilisateurPassword() {
        return utilisateurPassword;
    }

    /**
     * Getteur retournant le numéro de téléphone de l'utilisateur
     */
    public String getUtilisateurTel() {
        return utilisateurTel;
    }

    public String getUtilisateurImage() { return utilisateurImage; }

    public void setUtilisateurAdresse(Adresse utilisateurAdresse) {
        this.utilisateurAdresse = utilisateurAdresse;
    }
}
