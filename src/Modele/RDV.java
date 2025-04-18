package Modele;

/**
 * Classe des RDV
 */
public class RDV {

    private Specialiste specialiste;
    private Utilisateur utilisateur;
    private long date;


    /**
     * Constructeur des RDV avec tous les paramètres
     */
    public RDV(Specialiste pspecialiste, Utilisateur putilisateur, long pdate) {
        this.specialiste = pspecialiste;
        this.utilisateur = putilisateur;
        this.date = pdate;

    }


    public Specialiste getSpecialiste() {
        return specialiste;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public long getDate() {
        return date;
    }

}
