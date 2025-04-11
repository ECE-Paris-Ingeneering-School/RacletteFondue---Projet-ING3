package Modele.Exceptions;

public class ConfirmationPasswordException extends Exception {

    public ConfirmationPasswordException() {

        super("Les mots de passe ne correspondent pas");
    }
}
