package Modele;

/**
 * Classe Utilisateur
 */
abstract class Utilisateur {

    private int utilisateurId;
    private String utilisateurNom;
    private String utilisateurPrenom;
    private int utilisateurAge;
    private Adresse utilisateurAdresse;
    private char utilisateurSexe;
    private String utilisateurMail;
    private String utilisateurPassword;
    private String utilisateurTel; //Si jamais l'utilisateur met des espaces ou des points

    /**
     * Constructeur de l'utilisateur avec sans paramètre
     */
    public Utilisateur(){

    }

    /**
     * Constructeur de l'utilisateur avec tous les paramètres
     */
    public Utilisateur (int putilisateurId, String putilisateurNom, String putilisateurPrenom,  int putilisateurAge, Adresse padresse, char putilisateurSexe, String putilisateurMail, String putilisateurPassword, String putilisateurTel){

        this.utilisateurId = putilisateurId;
        this.utilisateurNom = putilisateurNom;
        this.utilisateurPrenom = putilisateurPrenom;
        this.utilisateurAge = putilisateurAge;
        this.utilisateurAdresse = padresse;
        this.utilisateurSexe = putilisateurSexe;
        this.utilisateurMail = putilisateurMail;
        this.utilisateurPassword = putilisateurPassword;
        this.utilisateurTel = putilisateurTel;
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
}
