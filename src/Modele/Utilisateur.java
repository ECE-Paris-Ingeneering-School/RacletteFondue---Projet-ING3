package Modele;


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

    public Utilisateur(){

    }

    /**Constructeur de l'utilisateur avec paramètre */
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

    /**Getteur pour rendre public les attributs de la classe utilisateur*/
    public int getUtilisateurId(){
        return utilisateurId;
    }

    public String getUtilisateurNom() {
        return utilisateurNom;
    }

    public String getUtilisateurPrenom() {
        return utilisateurPrenom;
    }

    public int getUtilisateurAge() {
        return utilisateurAge;
    }

    public Adresse getUtilisateurAdresse() {
        return utilisateurAdresse;
    }

    public char getUtilisateurSexe() {
        return utilisateurSexe;
    }

    public String getUtilisateurMail() {
        return utilisateurMail;
    }

    public String getUtilisateurPassword() {
        return utilisateurPassword;
    }

    public String getUtilisateurTel() {
        return utilisateurTel;
    }
}
