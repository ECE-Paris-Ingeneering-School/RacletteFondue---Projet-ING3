package Modele.Exceptions;

/**
 * Exception levée si la combinaison identifiant/mot de passe est introuvable dans la BDD lors de la connexion
 * **/
public class ConnexionException extends Exception {

    public ConnexionException() {

        super("Identifiant ou mot de passe incorrect");
    }
}
