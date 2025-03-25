package Modele;

public class Patient extends Utilisateur{


    public Patient(int putilisateurId, String putilisateurNom, String putilisateurPrenom, int putilisateurAge, Adresse padresse, char putilisateurSexe, String putilisateurMail, String putilisateurPassword, String putilisateurTel) {

        super(putilisateurId, putilisateurNom, putilisateurPrenom, putilisateurAge, padresse, putilisateurSexe, putilisateurMail, putilisateurPassword, putilisateurTel);

    }
}
