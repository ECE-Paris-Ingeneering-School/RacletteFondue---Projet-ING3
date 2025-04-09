package DAO;

import Modele.Utilisateur;
import java.util.ArrayList;


/**
 * Interface contenant les méthodes d'accès aux données de la table Patient, indépendamment de la méthode de stockage
 * **/
public interface UtilisateurDAO {

    /**
     * Récupérer de la base de données tous les objets des utilisateurs dans une liste
     * @return : liste retournée des objets utilisateur récupérés
     */
    public ArrayList<Utilisateur> getAllUtilisateur();

    /**
     * Méthode permettant de chercher un patient
     * @param utilisateurId L'ID de l'utilisateur à chercher
     * @return L'objet utilisateur trouvé correspondant à l'ID du patient
     * **/
    public Utilisateur chercherUtilisateur(int utilisateurId);

    /**
     * Méthode permettant d'ajouter un utilisateur dans la base de données
     * @param utilisateur l'objet utilisateur à ajouter
     * **/
    public void ajouterUtilisateur(Utilisateur utilisateur);

    /**
     * Méthode permettant de supprimer un utilisateur de la base de données
     * @param utilisateur L'objet utilisateur à supprimer
     * **/
    public void supprimerUtilisateur(Utilisateur utilisateur);

    /**
     * Méthode permettant de modifier un utilisateur dans la base de données
     * @param utilisateur L'objet utilisateur à modifier
     * @return L'objet modifié
     * **/
    public Utilisateur modifierUtilisateur(Utilisateur utilisateur);

    /**
     * Méthode permettant à un utilisateur de se connecter en comparant ses informations
     * à celles présentes en base de données
     * @param mail L'adresse email utilisée dans le formulaire de connexion
     * @param mdp Le mot de passe utilisé dans le formulaire de connexion
     * @return L'identifiant de l'utilisateur connecté, ou 0 s'il n'existe pas
     * **/
    public int connexionUtilisateur(String mail, String mdp);
}
