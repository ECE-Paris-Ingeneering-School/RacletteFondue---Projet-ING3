package Modele.Exceptions;

import Modele.Adresse;

public class AdresseException extends Exception {

    public AdresseException() {

        super("Le format de l'adresse est incorrect");
    }
}
