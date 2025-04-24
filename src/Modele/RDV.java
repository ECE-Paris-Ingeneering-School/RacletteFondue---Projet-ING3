package Modele;

/**
 * Classe des RDV
 */
public class RDV {

    private Specialiste specialiste;
    private Utilisateur utilisateur; // On met un utilisateur plutôt qu'un patient car l'admin peut également "prendre" un rdv pour supprimer ou rajouter des créneaux
    private long date;

    /**
     * Constructeur des RDV avec tous les paramètres
     */
    public RDV(Specialiste pspecialiste, Utilisateur putilisateur, long pdate) {
        this.specialiste = pspecialiste;
        this.utilisateur = putilisateur;
        this.date = pdate;

    }

    /**
     * Getteur retournant le spécialiste
     * @return Le spécialiste concerné par le rdv
     * **/
    public Specialiste getSpecialiste() {

        return specialiste;
    }

    /**
     * Getteur retournant l'utilisateur
     * @return Le patient concerné par le rdv
     * **/
    public Utilisateur getUtilisateur() {

        return utilisateur;
    }

    /**
     * Getteur retournant la date
     * @return La date (sous forme de timestamp) du rdv
     * **/
    public long getDate() {

        return date;
    }

}
