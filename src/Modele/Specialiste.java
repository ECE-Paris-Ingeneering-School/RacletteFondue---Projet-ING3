package Modele;

/**
 * Classe Spécialistes qui hérite de la classe Utilisateur
 */
public class Specialiste extends Utilisateur {

    private String specialisteSpecialite;
    private String specialisteDescription;
    private double specialisteTarif;


    /**
     * Constructeur de l'admin avec tous les paramètres de sa classe mère et tous ses paramètres
     */
    public Specialiste(int putilisateurId, String putilisateurNom, String putilisateurPrenom, int putilisateurAge, Adresse padresse, char putilisateurSexe, String putilisateurMail, String putilisateurPassword, String putilisateurTel, String pspecialisteSpecialite, String pspecialisteDescription, double pspecialisteTarif) {

        super(putilisateurId, putilisateurNom, putilisateurPrenom, putilisateurAge, padresse, putilisateurSexe, putilisateurMail, putilisateurPassword, putilisateurTel);

        this.specialisteSpecialite = pspecialisteSpecialite;
        this.specialisteDescription = pspecialisteDescription;
        this.specialisteTarif = pspecialisteTarif;

    }


    /**
     * Getteur retournant la spécialité du spécialiste
     */
    public String getSpecialisteSpecialite() {
        return specialisteSpecialite;
    }

    /**
     * Getteur retournant la description du spécialiste
     */
    public String getSpecialisteDescription() {
        return specialisteDescription;
    }

    /**
     * Getteur retournant le tarif du spécialiste
     */
    public double getSpecialisteTarif() {
        return specialisteTarif;
    }

}
