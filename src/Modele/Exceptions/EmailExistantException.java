package Modele.Exceptions;

/**
 * Exception levée si le mail utilisé lors de l'inscription est déjà existant dans la BDD
 * **/
public class EmailExistantException extends Exception {

    public EmailExistantException() {

        super("Cet email est déjà associé à un compte existant");
    }
}
