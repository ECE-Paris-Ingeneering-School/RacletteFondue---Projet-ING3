package DAO;

import Modele.RDV;
import Modele.Utilisateur;

import java.util.ArrayList;

/**
 * Interface contenant les méthodes d'accès aux données de la table RDV, indépendamment de la méthode de stockage
 * **/
public interface RdvDAO {

    /**
     * Méthode permettant de chercher tous les RDV d'un utilisateur
     * @param utilisateurId L'ID du patient ou du spécialiste dont on veut les RDV
     * @return La listre de tous les RDV de l'utilisateur
     * **/
    public ArrayList<RDV> chercherRDV(int utilisateurId);

    /**
     * Méthode permettant d'ajouter un RDV dans la base de données
     * @param rdv L'objet RDV à ajouter
     * **/
    public void ajouterRDV(RDV rdv);

    /**
     * Méthode permettant de supprimer un RDV de la base de données
     * @param rdv L'objet RDV à supprimer
     * **/
    public void supprimerRDV(RDV rdv);

}
