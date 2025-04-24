package Modele.Exceptions;

/**
 * Exception levée si le mot de passe et sa confirmation ne correspondent pas dans le formulaire concerné
 * **/
public class ConfirmationPasswordException extends Exception {

    public ConfirmationPasswordException() {

        super("Les mots de passe ne correspondent pas");
    }
}
