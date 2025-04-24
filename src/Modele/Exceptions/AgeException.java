package Modele.Exceptions;

/**
 * Exception levée si l'age saisi est incorrect
 * **/
public class AgeException extends Exception {

    public AgeException() {

        super("L'age saisi est invalide");
    }
}
