package Modele.Exceptions;

/**
 * Exception levée si le tarif saisi est incorrect
 * **/
public class TarifException extends Exception {

    public TarifException() {

        super("Le tarif saisi est invalide");
    }
}
