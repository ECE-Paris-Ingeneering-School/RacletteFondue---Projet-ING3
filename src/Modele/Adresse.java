package Modele;

public class Adresse {

    /**Attributs de la classe adresse */
    private int adresseId;
    private int adresseCodePostal;
    private String adresseVille;
    private String adresseRue;
    private int adresseNumero;


    /**Constructeur de l'adresse avec paramètre */
    public Adresse(int padresseId, int padresseCodePostal, String padresseVille, String padresseRue, int padresseNumero){

        this.adresseId = padresseId;
        this.adresseCodePostal = padresseCodePostal;
        this.adresseVille = padresseVille;
        this.adresseRue = padresseRue;
        this.adresseNumero = padresseNumero;

    }


    /**Getteur pour rendre public les attributs de la classe adresse*/
    public int getAdresseId() {
        return adresseId;
    }

    public int getAdresseCodePostal() {
        return adresseCodePostal;
    }

    public String getAdresseVille() {
        return adresseVille;
    }

    public String getAdresseRue() {
        return adresseRue;
    }

    public int getAdresseNumero() {
        return adresseNumero;
    }

}
