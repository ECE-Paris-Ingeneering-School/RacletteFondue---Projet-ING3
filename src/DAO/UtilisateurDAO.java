package DAO;

import Modele.Exceptions.ConnexionException;
import Modele.Exceptions.EmailExistantException;
import Modele.Patient;
import Modele.Specialiste;
import Modele.Utilisateur;
import java.util.ArrayList;


/**
 * Interface contenant les méthodes d'accès aux données de la table Utilisateur (ainsi que Patient, Spécialiste et Admin), indépendamment de la méthode de stockage
 * **/
public interface UtilisateurDAO {

    /**
     * Méthode permettant de récupérer tous les utilisateurs de la BDD
     * @return Liste des objets utilisateur récupérés
     */
    public ArrayList<Utilisateur> getAllUtilisateur();

    /**
     * Méthode permettant de chercher un utilisateur dans la BDD
     * @param utilisateurId L'ID de l'utilisateur à chercher
     * @return L'objet utilisateur trouvé correspondant à l'ID de l'utilisateur
     * **/
    public Utilisateur chercherUtilisateur(int utilisateurId);


    /**
     * Méthode permettant de chercher des spécialistes avec un mot clé
     * @param motRecherche Le mot clé recherché
     * @return La liste des spécialistes trié en fonction du terme recherché
     * **/
    public ArrayList<Specialiste> rechercheSpecialiste(String motRecherche);

    /**
     * Méthode permettant de chercher des patients avec un mot clé
     * @param motRecherche le mot clé recherché
     * @return La liste des patients trié en fonction du terme recherché
    **/
    public ArrayList<Patient> recherchePatient(String motRecherche);

    /**
     * Méthode permettant d'ajouter un utilisateur dans la base de données
     * @param utilisateur L'objet utilisateur à ajouter
     * @throws EmailExistantException Email déjà présent dans la BDD
     * **/
    public void ajouterUtilisateur(Utilisateur utilisateur) throws EmailExistantException;

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
     * @throws ConnexionException Identifiant ou mot de passe invalide
     * **/
    public int connexionUtilisateur(String mail, String mdp) throws ConnexionException;
}
