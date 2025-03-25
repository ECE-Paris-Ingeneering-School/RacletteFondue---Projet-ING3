package Modele;

public class Specialiste extends Utilisateur {

    private String specialisteSpecialite;
    private String specialisteDescription;
    private double specialisteTarif;


    public Specialiste(int putilisateurId, String putilisateurNom, String putilisateurPrenom, int putilisateurAge, Adresse padresse, char putilisateurSexe, String putilisateurMail, String putilisateurPassword, String putilisateurTel, String pspecialisteSpecialite, String pspecialisteDescription, double pspecialisteTarif) {
        super(putilisateurId, putilisateurNom, putilisateurPrenom, putilisateurAge, padresse, putilisateurSexe, putilisateurMail, putilisateurPassword, putilisateurTel);
        this.specialisteSpecialite = pspecialisteSpecialite;
        this.specialisteDescription = pspecialisteDescription;
        this.specialisteTarif = pspecialisteTarif;
    }


    public String getSpecialisteSpecialite() {
        return specialisteSpecialite;
    }

    public String getSpecialisteDescription() {
        return specialisteDescription;
    }

    public double getSpecialisteTarif() {
        return specialisteTarif;
    }

}
