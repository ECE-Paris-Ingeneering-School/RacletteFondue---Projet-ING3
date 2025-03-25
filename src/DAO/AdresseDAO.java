package DAO;

import Modele.Adresse;

/**
 * Interface contenant les méthodes d'accès aux données de la table Adresse, indépendamment de la méthode de stockage
 * **/
public interface AdresseDAO {

    /**
     * Méthode permettant d'ajouter une adresse dans la base de données
     * @param adresse L'adresse à ajouter
     * **/
    public void ajouterAdresse(Adresse adresse);

    /**
     * Méthode permettant de supprimer une adresse dans la base de données
     * @param adresse L'adresse à supprimer
     * **/
    public void supprimerAdresse(Adresse adresse);

    /**
     * Méthode permettant de modifier une adresse dans la base de données
     * @param adresse L'adresse à modifier
     * @return L'objet modifié
     * **/
    public Adresse modifierAdresse(Adresse adresse);

}
