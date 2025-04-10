package Modele;


/**
 * Classe Patient qui hérite de  la classe Utilisateur
 */
public class Patient extends Utilisateur{


    /**
     * Constructeur du patient avec tous les paramètres de sa classe mère
     */
    public Patient(int putilisateurId, String putilisateurNom, String putilisateurPrenom, int putilisateurAge, Adresse padresse, char putilisateurSexe, String putilisateurMail, String putilisateurPassword, String putilisateurTel, String putilisateurImage) {

        super(putilisateurId, putilisateurNom, putilisateurPrenom, putilisateurAge, padresse, putilisateurSexe, putilisateurMail, putilisateurPassword, putilisateurTel, putilisateurImage);

    }
}
