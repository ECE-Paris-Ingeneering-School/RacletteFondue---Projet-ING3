package Modele;

/**
 * Classe Adresse
 */
public class Adresse {


    private int adresseId;
    private int adresseCodePostal;
    private String adresseVille;
    private String adresseRue;
    private int adresseNumero;


    /**
     * Constructeur de l'adresse avec ses paramètres
     */
    public Adresse(int padresseId, int padresseCodePostal, String padresseVille, String padresseRue, int padresseNumero){

        this.adresseId = padresseId;
        this.adresseCodePostal = padresseCodePostal;
        this.adresseVille = padresseVille;
        this.adresseRue = padresseRue;
        this.adresseNumero = padresseNumero;

    }


    /**
     * Getteur retournant l'id de l'adresse
     */
    public int getAdresseId() {
        return adresseId;
    }

    /**
     * Getteur retournant le code postal de l'adresse
     */
    public int getAdresseCodePostal() {
        return adresseCodePostal;
    }

    /**
     * Getteur retournant la ville de l'adresse
     */
    public String getAdresseVille() {
        return adresseVille;
    }

    /**
     * Getteur retournant la rue de l'adresse
     */
    public String getAdresseRue() {
        return adresseRue;
    }

    /**
     * Getteur retournant le numéro de l'adresse
     */
    public int getAdresseNumero() {
        return adresseNumero;
    }

}
