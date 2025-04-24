package Modele;

/**
 * Classe Adresse
 */
public class Adresse {

    /**Attributs de la classe adresse **/
    private int adresseCodePostal;
    private String adresseVille;
    private String adresseRue;
    private String adresseNumero;


    /**Constructeur de l'adresse avec paramètre **/
    public Adresse(int padresseCodePostal, String padresseVille, String padresseRue, String padresseNumero){

        this.adresseCodePostal = padresseCodePostal;
        this.adresseVille = padresseVille;
        this.adresseRue = padresseRue;
        this.adresseNumero = padresseNumero;

    }


    /**Getteur pour rendre public les attributs de la classe adresse*/

    /**
     * Getteur retournant le code postal de l'adresse
     * @return Le code postal de l'adresse
     */
    public int getAdresseCodePostal() {
        return adresseCodePostal;
    }

    /**
     * Getteur retournant la ville de l'adresse
     * @return La ville de l'adresse
     */
    public String getAdresseVille() {
        return adresseVille;
    }

    /**
     * Getteur retournant la rue de l'adresse
     * @return La rue de l'adresse
     */
    public String getAdresseRue() {
        return adresseRue;
    }

    /**
     * Getteur retournant le numéro de l'adresse
     * @return Le numero de l'adresse
     */
    public String getAdresseNumero() {
        return adresseNumero;
    }
}
