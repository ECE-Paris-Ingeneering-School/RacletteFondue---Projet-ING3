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
    public RDV(Specialiste pspecialiste, Utilisateur putilisteur, long pdate) {
        this.specialiste = pspecialiste;
        this.utilisateur= putilisteur;
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
