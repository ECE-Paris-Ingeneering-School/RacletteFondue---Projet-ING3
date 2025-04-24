package Modele.Exceptions;

/**
 * Exception levée si le format de l'adresse saisie est incorrect
 * **/
public class AdresseException extends Exception {

    public AdresseException() {

        super("Le format de l'adresse est incorrect");
    }
}
