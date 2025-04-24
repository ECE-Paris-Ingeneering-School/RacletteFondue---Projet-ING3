package Modele.Exceptions;

/**
 * Exception levée si tous les champs du formulaire concerné n'ont pas été remplis
 * **/
public class ChampsVidesException extends Exception {

    public ChampsVidesException() {

        super("Tous les champs n'ont pas été complétés");
    }
}
