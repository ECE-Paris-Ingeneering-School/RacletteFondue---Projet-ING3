package Modele;

/**
 * Classe des RDV
 */
public class RDV {

    private Specialiste specialiste;
    private Patient patient;
    private int date;


    /**
     * Constructeur des RDV avec tous les paramètres
     */
    public RDV(Specialiste pspecialiste, Patient ppatient, int pdate) {
        this.specialiste = pspecialiste;
        this.patient = ppatient;
        this.date = pdate;

    }


    public Specialiste getSpecialiste() {
        return specialiste;
    }

    public Patient getPatient() {
        return patient;
    }

    public int getDate() {
        return date;
    }

}
