package Modele.Exceptions;

public class TelephoneException extends Exception {

    public TelephoneException() {

        super("Le numero de téléphone saisi est invalide");
    }
}
