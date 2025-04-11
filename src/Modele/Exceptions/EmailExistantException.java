package Modele.Exceptions;

public class EmailExistantException extends Exception {

    public EmailExistantException() {

        super("Cet email est déjà associé à un compte existant");
    }
}
