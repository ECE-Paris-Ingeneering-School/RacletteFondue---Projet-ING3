package Modele;


/**
 * Classe Admins qui hérite de la classe Utilisateur
 */
public class Admin extends Utilisateur{


    /**
     * Constructeur de l'admin avec tous les paramètres de sa classe mère
     */
    public Admin(int putilisateurId, String putilisateurNom, String putilisateurPrenom, int putilisateurAge, Adresse padresse, char putilisateurSexe, String putilisateurMail, String putilisateurPassword, String putilisateurTel) {

        super(putilisateurId, putilisateurNom, putilisateurPrenom, putilisateurAge, padresse, putilisateurSexe, putilisateurMail, putilisateurPassword, putilisateurTel);

    }
}
