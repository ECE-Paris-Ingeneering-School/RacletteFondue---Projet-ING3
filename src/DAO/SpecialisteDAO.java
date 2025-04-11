package DAO;

import Modele.Specialiste;

/**
 * Interface contenant les méthodes d'accès aux données de la table Specialiste, indépendamment de la méthode de stockage
 * **/
public interface SpecialisteDAO {

    /**
     * Méthode permettant de chercher un spécialiste
     * @param specialisteId L'ID du spécialiste à chercher
     * @return L'objet spécialiste trouvé correspondant à l'ID du spécialiste
     * **/
    public Specialiste chercherSpecialiste(int specialisteId);

    /**
     * Méthode permettant d'ajouter un spécialiste dans la base de données
     * @param specialiste L'objet spécialiste à ajouter
     * **/
    public void ajouterSpecialiste(Specialiste specialiste);

    /**
     * Méthode permettant de supprimer un spécialiste de la base de données
     * @param specialiste L'objet spécialiste à supprimer
     * **/
    public void supprimerSpecialiste(Specialiste specialiste);

    /**
     * Méthode permettant de modifier un spécialiste dans la base de données
     * @param specialiste L'objet spécialiste à modifier
     * @return L'objet modifié
     * **/
    public Specialiste modifierSpecialiste(Specialiste specialiste);

}
