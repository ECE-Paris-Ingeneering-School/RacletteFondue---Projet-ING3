package Modele.Exceptions;

/**
 * Exception levée si le numéro de téléphone saisi est incorrect
 * **/
public class TelephoneException extends Exception {

    public TelephoneException() {

        super("Le numero de téléphone saisi est invalide");
    }
}
