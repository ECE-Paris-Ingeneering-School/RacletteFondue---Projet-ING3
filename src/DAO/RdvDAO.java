package DAO;

import Modele.RDV;

/**
 * Interface contenant les méthodes d'accès aux données de la table RDV, indépendamment de la méthode de stockage
 * **/
public interface RdvDAO {

    /**
     * Méthode permettant de chercher un rdv
     * @param patientId L'ID du patient
     * @param specialisteID L'ID du spécialiste
     * @return L'objet patient trouvé correspondant à l'ID du patient
     * **/
    public RDV chercherRDV(int patientId, int specialisteID,int rdvDate);

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

    /**
     * Méthode permettant de modifier un RDV dans la base de données
     * @param rdv L'objet patient à modifier
     * @return L'objet modifié
     * **/
    public RDV modifierRDV(RDV rdv);
}
