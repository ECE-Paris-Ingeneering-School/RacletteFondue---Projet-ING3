package Modele.Exceptions;

public class ConnexionException extends Exception {

    public ConnexionException() {

        super("Identifiant ou mot de passe incorrect");
    }
}
