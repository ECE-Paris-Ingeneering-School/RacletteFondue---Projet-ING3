package Modele.Exceptions;

public class ChampsVidesException extends Exception {

    public ChampsVidesException() {

        super("Tous les champs n'ont pas été complétés");
    }
}
