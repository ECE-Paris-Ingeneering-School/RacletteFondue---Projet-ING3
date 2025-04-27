package DAO;
import Modele.*;
import Modele.Exceptions.ConnexionException;
import Modele.Exceptions.EmailExistantException;

import java.sql.*;
import java.util.ArrayList;



/**
 * Implémentation MySQL du stockage dans la base de données des méthodes définies dans l'interface UtilisateurDao.
 */
public class UtilisateurDAOImpl implements UtilisateurDAO {

    private DaoFactory daoFactory;

    public UtilisateurDAOImpl(DaoFactory daoFactory) {

        this.daoFactory = daoFactory;
    }

    public Utilisateur chercherUtilisateur(int id) {

        try {

            // Connexion à la BDD
            Connection connexion = daoFactory.getConnection();

            // Création de plusieurs statements pour effectuer plusieurs requêtes
            Statement[] statements = new Statement[8];
            for (int i=0; i<statements.length; i++) {

                statements[i] = connexion.createStatement();
            }

            // Initialisation des variables
            int utilisateurId = 0;
            String utilisateurNom = null;
            String utilisateurPrenom = null;
            int utilisateurAge = 0;
            String temp = null;
            char utilisateurSexe = 'a';
            String utilisateurMail = null;
            String utilisateurPassword = null;
            String utilisateurTelephone = null;
            String utilisateurImage = null;

            Adresse utilisateurAdresse = null;
            int adresseCodePostal = 0;
            String adresseVille = null;
            String adresseRue = null;
            String adresseNumero = "0";


            // Récupération de l'utilisateur grâce à son identifiant
            ResultSet resultats = statements[0].executeQuery("SELECT * FROM utilisateur WHERE utilisateur.utilisateurId=\'" + id + "\'");

            // On parcourt les résultats de la requête
            while (resultats.next()) {

                // On récupère les données de l'utilisateur
                utilisateurId = resultats.getInt("utilisateurId");
                utilisateurNom = resultats.getString("utilisateurNom");
                utilisateurPrenom = resultats.getString("utilisateurPrenom");
                utilisateurAge = resultats.getInt("utilisateurAge");
                temp = resultats.getString("utilisateurSexe");
                utilisateurSexe = temp.charAt(0);
                utilisateurMail = resultats.getString("utilisateurMail");
                utilisateurPassword = resultats.getString("utilisateurPassword");
                utilisateurTelephone = resultats.getString("utilisateurTel");
                utilisateurImage = resultats.getString("utilisateurImage");

                // On récupère les données de l'adresse
                ResultSet donneesAdresse = statements[1].executeQuery("SELECT * FROM adresse WHERE adresse.adresseId=" + utilisateurId);

                while (donneesAdresse.next()) {

                    adresseCodePostal = donneesAdresse.getInt("adresseCodePostal");
                    adresseVille = donneesAdresse.getString("adresseVille");
                    adresseRue = donneesAdresse.getString("adresseRue");
                    adresseNumero = donneesAdresse.getString("adresseNumero");

                }

                // Instanciation de l'adresse du utilisateur
                utilisateurAdresse = new Adresse(adresseCodePostal, adresseVille, adresseRue, adresseNumero);

            }


            // On récupère le type de compte qui correspond à l'id passé en paramètre
            int[] confirmations = new int[3];

            ResultSet resultatConfirmations;

            resultatConfirmations = statements[2].executeQuery("SELECT count(*) FROM patient WHERE patientId = " + id);
            resultatConfirmations.next();

            confirmations[0] = resultatConfirmations.getInt(1);

            resultatConfirmations = statements[3].executeQuery("SELECT count(*) FROM admin WHERE adminId = " + id);
            resultatConfirmations.next();

            confirmations[1] = resultatConfirmations.getInt(1);

            resultatConfirmations = statements[4].executeQuery("SELECT count(*) FROM specialiste WHERE specialisteId = " + id);
            resultatConfirmations.next();

            confirmations[2] = resultatConfirmations.getInt(1);

            int typeUtilisateur = 0;

            // On détermine alors le type de compte en question
            for (int i = 0; i < confirmations.length; i++) {
                typeUtilisateur = confirmations[i] > confirmations[typeUtilisateur] ? i : typeUtilisateur;
            }

            // On crée l'objet correspondant au type d'utilisateur déterminé précédemment
            switch (typeUtilisateur) {

                // L'utilisateur est un patient
                case 0:

                    return new Patient(utilisateurId, utilisateurNom, utilisateurPrenom, utilisateurAge, utilisateurAdresse, utilisateurSexe, utilisateurMail, utilisateurPassword, utilisateurTelephone, utilisateurImage);

                // L'utilisateur est un admin
                case 1:

                    return new Admin(utilisateurId, utilisateurNom, utilisateurPrenom, utilisateurAge, utilisateurAdresse, utilisateurSexe, utilisateurMail, utilisateurPassword, utilisateurTelephone, utilisateurImage);

                // L'utilisateur est un spécialiste
                case 2:

                    ResultSet resultatSpecialiste;

                    resultatSpecialiste = statements[5].executeQuery("SELECT specialisteSpecialite FROM specialiste WHERE specialisteId = " + id);
                    resultatSpecialiste.next();

                    String specialite = resultatSpecialiste.getString("specialisteSpecialite");

                    resultatSpecialiste = statements[6].executeQuery("SELECT specialisteDescription FROM specialiste WHERE specialisteId=" + id);
                    resultatSpecialiste.next();

                    String description = resultatSpecialiste.getString("specialisteDescription");

                    resultatSpecialiste = statements[7].executeQuery("SELECT specialisteTarif FROM specialiste WHERE specialisteId=" + id);
                    resultatSpecialiste.next();

                    double tarif = resultatSpecialiste.getDouble("specialisteTarif");

                    return new Specialiste(utilisateurId, utilisateurNom, utilisateurPrenom, utilisateurAge, utilisateurAdresse, utilisateurSexe, utilisateurMail, utilisateurPassword, utilisateurTelephone, utilisateurImage, specialite, description, tarif);
            }


        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("Recherche de l'utilisateur impossible");

        }

        // On ne retourne rien si la méthode a rencontré un problème lors de son exécution
        return null;
    }

    public ArrayList<Utilisateur> getAllUtilisateur() {

        // Initialisation des variables
        ArrayList<Utilisateur> utilisateursListe = new ArrayList<>();
        ArrayList<Utilisateur> patientListe = new ArrayList<>();
        ArrayList<Utilisateur> specialisteListe = new ArrayList<>();

        try {

            // Connexion à la BDD
            Connection connexion = daoFactory.getConnection();
            Statement statement = connexion.createStatement();

            // Requete pour récupérer l'identifiant du dernier utilisateur inscrit
            String requete = "SELECT MAX(utilisateurId) FROM utilisateur";
            ResultSet resultat = statement.executeQuery(requete);
            resultat.next();

            int indexMax = resultat.getInt(1);

            // Parcours des utilisateurs dans la table Utilisateurs et tri des utilisateurs en fonction de leur type
            for (int i = 1; i < indexMax+1; i++) {

                if (chercherUtilisateur(i).getUtilisateurId() != 0) {

                    if (chercherUtilisateur(i) instanceof Admin) {

                        utilisateursListe.add(chercherUtilisateur(i));

                    }
                    else if (chercherUtilisateur(i) instanceof Specialiste) {

                        specialisteListe.add(chercherUtilisateur(i));

                    }
                    else {

                        patientListe.add(chercherUtilisateur(i));
                    }
                }
            }

            // Formation de la liste complète d'utilisateurs, contenant d'abord les Admin, puis les Spécialites et enfin les Patients
            utilisateursListe.addAll(specialisteListe);
            utilisateursListe.addAll(patientListe);

        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("Recherche des utilisateurs impossible");
        }

        // On retourne la liste d'utilisateurs formée
        return utilisateursListe;
    }

    public ArrayList<Specialiste> rechercheSpecialiste(String motRecherche){

        // Initialisation des variables
        ArrayList<Specialiste> listeSpecialisteRecherches = new ArrayList<>();
        int[] pertinence = new int[3];
        int maxIteration = 0;
        int indexMaxIteration = 0;

        Specialiste specialisteTrouve = null;
        int utilisateurId = 0;
        String utilisateurNom = null;
        String utilisateurPrenom = null;
        int utilisateurAge = 0;
        String temp = null;
        char utilisateurSexe = 'a';
        String utilisateurMail = null;
        String utilisateurPassword = null;
        String utilisateurTelephone = null;
        String utilisateurImage = null;

        String specialiteSpecialite = null;
        String specialisteDescription = null;
        double specialisteTarif = 0.0;

        Adresse specialisteAdresse = null;
        int adresseCodePostal = 0;
        String adresseVille = null;
        String adresseRue = null;
        String adresseNumero = "0";


        try {

            // Connexion à la BDD
            Connection connexion = daoFactory.getConnection();

            // Création des statements pour effectuer plusieurs requêtes
            Statement[] statements = new Statement[4];
            for (int i=0; i<statements.length; i++) {

                statements[i] = connexion.createStatement();
            }

            // On cherche dans les différentes catégories d'interet le nombre d'occurence du mot recherché
            ResultSet resultat;
            resultat = statements[0].executeQuery("SELECT COUNT(*) FROM utilisateur,specialiste WHERE utilisateur.utilisateurId = specialiste.specialisteId AND specialiste.specialisteSpecialite LIKE \""+ motRecherche + "\"" );
            resultat.next();
            pertinence[0] = resultat.getInt(1);

            resultat = statements[1].executeQuery("SELECT COUNT(*) FROM utilisateur,specialiste WHERE utilisateur.utilisateurId = specialiste.specialisteId AND utilisateur.utilisateurNom LIKE \""+ motRecherche + "\"");
            resultat.next();
            pertinence[1] = resultat.getInt(1);

            resultat = statements[2].executeQuery("SELECT COUNT(*) FROM utilisateur,specialiste,adresse WHERE utilisateur.utilisateurId = specialiste.specialisteId AND utilisateur.utilisateurId = adresse.adresseId AND adresse.adresseVille LIKE \""+ motRecherche + "\"");
            resultat.next();
            pertinence[2] = resultat.getInt(1);


            // On sélectionne l'element le plus pertinent
            for (int i = 0; i < pertinence.length; i++) {
                if (pertinence[i] > maxIteration) {
                    maxIteration = pertinence[i];
                    indexMaxIteration = i;
                }
            }

            // On trie les spécialistes en fonction de l'element le plus pertinent
            if (indexMaxIteration == 0) {

                resultat = statements[3].executeQuery("SELECT * FROM adresse, utilisateur, specialiste WHERE utilisateur.utilisateurId = specialiste.specialisteId AND utilisateur.utilisateurId = adresse.adresseId ORDER BY (specialiste.specialisteSpecialite = \""+ motRecherche +"\") DESC, specialiste.specialisteSpecialite ASC;");

            }else if(indexMaxIteration == 1) {

                resultat = statements[3].executeQuery("SELECT * FROM adresse, utilisateur, specialiste WHERE utilisateur.utilisateurId = specialiste.specialisteId AND utilisateur.utilisateurId = adresse.adresseId ORDER BY (utilisateur.utilisateurNom = \""+ motRecherche +"\") DESC, utilisateur.utilisateurNom ASC;");

            } else  {

                resultat = statements[3].executeQuery("SELECT * FROM adresse, utilisateur, specialiste WHERE utilisateur.utilisateurId = specialiste.specialisteId AND utilisateur.utilisateurId = adresse.adresseId ORDER BY (adresse.adresseVille = \""+ motRecherche +"\") DESC, adresse.adresseVille ASC;");

            }

            // On parcourt les résultats de la requête
            while (resultat.next()) {

                utilisateurId = resultat.getInt("utilisateurId");
                utilisateurNom = resultat.getString("utilisateurNom");
                utilisateurPrenom = resultat.getString("utilisateurPrenom");
                utilisateurAge = resultat.getInt("utilisateurAge");
                temp = resultat.getString("utilisateurSexe");
                utilisateurSexe = temp.charAt(0);
                utilisateurMail = resultat.getString("utilisateurMail");
                utilisateurPassword = resultat.getString("utilisateurPassword");
                utilisateurTelephone = resultat.getString("utilisateurTel");
                utilisateurImage = resultat.getString("utilisateurImage");

                specialiteSpecialite = resultat.getString("specialisteSpecialite");
                specialisteDescription = resultat.getString("specialisteDescription");
                specialisteTarif = resultat.getDouble("specialisteTarif");

                adresseCodePostal = resultat.getInt("adresseCodePostal");
                adresseVille = resultat.getString("adresseVille");
                adresseRue = resultat.getString("adresseRue");
                adresseNumero = resultat.getString("adresseNumero");

                specialisteAdresse = new Adresse(adresseCodePostal, adresseVille, adresseRue, adresseNumero);

                specialisteTrouve = new Specialiste(utilisateurId,utilisateurNom,utilisateurPrenom,utilisateurAge,specialisteAdresse,utilisateurSexe,utilisateurMail,utilisateurPassword,utilisateurTelephone,utilisateurImage,specialiteSpecialite,specialisteDescription,specialisteTarif);

                // On ajoute le spécialiste à la liste des spécialistes trouvés
                listeSpecialisteRecherches.add(specialisteTrouve);

            }

        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("Recherche des spécialistes impossible");
        }

        // On retourne la liste des spécialistes trouvés
        return listeSpecialisteRecherches;
    }

    public ArrayList<Patient> recherchePatient(String motRecherche) {

        // Initialisation des variables
        ArrayList<Patient> listePatientRecherches = new ArrayList<>();
        int[] pertinence = new int[3];
        int maxIteration = 0;
        int indexMaxIteration = 0;

        Patient patientTrouve = null;
        int utilisateurId = 0;
        String utilisateurNom = null;
        String utilisateurPrenom = null;
        int utilisateurAge = 0;
        String temp = null;
        char utilisateurSexe = 'a';
        String utilisateurMail = null;
        String utilisateurPassword = null;
        String utilisateurTelephone = null;
        String utilisateurImage = null;

        Adresse specialisteAdresse = null;
        int adresseCodePostal = 0;
        String adresseVille = null;
        String adresseRue = null;
        String adresseNumero = "0";


        try {

            // Connexion à la BDD
            Connection connexion = daoFactory.getConnection();

            // Création des statements pour exécuter plusieurs requêtes
            Statement[] statements = new Statement[3];

            for (int i=0; i<statements.length; i++) {

                statements[i] = connexion.createStatement();
            }

            // On cherche dans les différentes catégories d'interet le nombre d'occurence du mot recherché
            ResultSet resultat;
            resultat = statements[0].executeQuery("SELECT COUNT(*) FROM utilisateur,patient WHERE utilisateur.utilisateurId = patient.patientId AND utilisateur.utilisateurNom LIKE \""+ motRecherche + "\"" );
            resultat.next();
            pertinence[0] = resultat.getInt(1);

            resultat = statements[1].executeQuery("SELECT COUNT(*) FROM utilisateur,patient,adresse WHERE utilisateur.utilisateurId = patient.patientId AND utilisateur.utilisateurId = adresse.adresseId AND adresse.adresseVille LIKE \""+ motRecherche + "\"");
            resultat.next();
            pertinence[1] = resultat.getInt(1);


            // On sélectionne l'element le plus pertinent
            for (int i = 0; i < pertinence.length; i++) {
                if (pertinence[i] > maxIteration) {
                    maxIteration = pertinence[i];
                    indexMaxIteration = i;
                }
            }

            // On trie les patients en fonction de l'element le plus pertinent
            if (indexMaxIteration == 0) {

                resultat = statements[2].executeQuery("SELECT * FROM adresse, utilisateur, patient WHERE utilisateur.utilisateurId = patient.patientId AND utilisateur.utilisateurId = adresse.adresseId ORDER BY (utilisateur.utilisateurNom = \""+ motRecherche +"\") DESC, utilisateur.utilisateurNom ASC;");

            } else  {

                resultat = statements[2].executeQuery("SELECT * FROM adresse, utilisateur, patient WHERE utilisateur.utilisateurId = patient.patientId AND utilisateur.utilisateurId = adresse.adresseId ORDER BY (adresse.adresseVille = \""+ motRecherche +"\") DESC, adresse.adresseVille ASC;");

            }

            // On parcourt les résultats de la requête
            while (resultat.next()) {

                utilisateurId = resultat.getInt("utilisateurId");
                utilisateurNom = resultat.getString("utilisateurNom");
                utilisateurPrenom = resultat.getString("utilisateurPrenom");
                utilisateurAge = resultat.getInt("utilisateurAge");
                temp = resultat.getString("utilisateurSexe");
                utilisateurSexe = temp.charAt(0);
                utilisateurMail = resultat.getString("utilisateurMail");
                utilisateurPassword = resultat.getString("utilisateurPassword");
                utilisateurTelephone = resultat.getString("utilisateurTel");
                utilisateurImage = resultat.getString("utilisateurImage");;

                adresseCodePostal = resultat.getInt("adresseCodePostal");
                adresseVille = resultat.getString("adresseVille");
                adresseRue = resultat.getString("adresseRue");
                adresseNumero = resultat.getString("adresseNumero");

                specialisteAdresse = new Adresse(adresseCodePostal, adresseVille, adresseRue, adresseNumero);

                patientTrouve = new Patient(utilisateurId,utilisateurNom,utilisateurPrenom,utilisateurAge,specialisteAdresse,utilisateurSexe,utilisateurMail,utilisateurPassword,utilisateurTelephone,utilisateurImage);

                // On ajoute le patient à la liste des patients trouvés
                listePatientRecherches.add(patientTrouve);
            }



        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("Recherche des spécialistes impossible");
        }

        // On renvoie la liste des patients trouvés
        return listePatientRecherches;
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) throws EmailExistantException {

        try {

            // Connexion à la BDD
            Connection connexion = daoFactory.getConnection();
            Statement statement = connexion.createStatement();

            ResultSet resultat;

            // On vérifie s'il n'existe pas déjà un patient ayant l'adresse email de l'utilisateur à ajouter
            if (utilisateur instanceof Patient) {

                resultat = statement.executeQuery("SELECT count(*) FROM utilisateur WHERE utilisateurMail=\"" + utilisateur.getUtilisateurMail() + "\"");
                resultat.next();

                if (resultat.getInt(1) != 0) {

                    throw new EmailExistantException();
                }
            }

            // Requete SQL permettant d'ajouter l'utilisateur à la BDD
            String requete = String.format("INSERT INTO utilisateur(utilisateurNom,utilisateurPrenom,utilisateurAge,utilisateurSexe,utilisateurMail,utilisateurPassword,utilisateurTel,utilisateurImage) VALUES (\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\")",
                    utilisateur.getUtilisateurNom(),
                    utilisateur.getUtilisateurPrenom(),
                    utilisateur.getUtilisateurAge(),
                    utilisateur.getUtilisateurSexe(),
                    utilisateur.getUtilisateurMail(),
                    utilisateur.getUtilisateurPassword(),
                    utilisateur.getUtilisateurTel(),
                    utilisateur.getUtilisateurImage()
                    );


            PreparedStatement preparedStatement = connexion.prepareStatement(requete);
            preparedStatement.executeUpdate();

            // On récupère l'identifiant de l'utilisateur nouvellement ajouté
            String requete2 = "SELECT MAX(utilisateurId) FROM utilisateur";
            resultat = statement.executeQuery(requete2);
            resultat.next();

            int utilisateurId = resultat.getInt(1);

            // On ajoute son identificant ou ses autres informations aux tables de la BDD appropriées en fonction du type d'utilisateur
            if (utilisateur instanceof Patient) {

                requete = "INSERT INTO patient(patientId) VALUES ("+ utilisateurId + ")";

            } else if (utilisateur instanceof Specialiste) {

                String ajoutDescription = ((Specialiste) utilisateur).getSpecialisteDescription();
                String ajoutSpecialite = ((Specialiste) utilisateur).getSpecialisteSpecialite();
                double ajoutTarif = ((Specialiste) utilisateur).getSpecialisteTarif();

                requete = "INSERT INTO specialiste(specialisteId,specialisteSpecialite,specialisteDescription,specialisteTarif) VALUES (\""+ utilisateurId + "\",\"" +ajoutSpecialite + "\",\""+ajoutDescription+"\",\""+ajoutTarif+"\")";
            }

            preparedStatement = connexion.prepareStatement(requete);
            preparedStatement.executeUpdate();

            // Ajout de l'adresse
            requete = String.format("INSERT INTO adresse(adresseId, adresseCodePostal, adresseVille, adresseRue, adresseNumero) VALUES (\"%s\", \"%s\", \"%s\", \"%s\", \"%s\")",
                    utilisateurId,
                    utilisateur.getUtilisateurAdresse().getAdresseCodePostal(),
                    utilisateur.getUtilisateurAdresse().getAdresseVille(),
                    utilisateur.getUtilisateurAdresse().getAdresseRue(),
                    Integer.parseInt(utilisateur.getUtilisateurAdresse().getAdresseNumero())
                    );

            preparedStatement = connexion.prepareStatement(requete);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("Impossible d'ajouter l'utilisateur à la base de données");
        }
    }

    public void supprimerUtilisateur(Utilisateur utilisateur) {

        try {

            // Connexion à la BDD
            Connection connexion = daoFactory.getConnection();
            Statement statement = connexion.createStatement();

            // Suppression des RDV de l'utilisateur à supprimer
            statement.executeUpdate("DELETE FROM rdv WHERE rdvPatient=" + utilisateur.getUtilisateurId()+" OR rdvSpecialiste=" + utilisateur.getUtilisateurId());

            // Suppression du type d'utilisateur
            if (utilisateur instanceof Patient) {

                statement.executeUpdate("DELETE FROM patient WHERE patientId=" + utilisateur.getUtilisateurId());

            } else if (utilisateur instanceof Specialiste) {

                statement.executeUpdate("DELETE FROM specialiste WHERE specialisteId=" + utilisateur.getUtilisateurId());

            }

            // Suppression de l'adresse
            statement.executeUpdate("DELETE FROM adresse WHERE adresseId=" + utilisateur.getUtilisateurId());

            // Suppression de l'utilisateur
            statement.executeUpdate("DELETE FROM utilisateur WHERE utilisateurId=" + utilisateur.getUtilisateurId());

        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("Suppression de l'utilisateur impossible");
        }
    }

    public Utilisateur modifierUtilisateur(Utilisateur utilisateur) {

        try {

            // Connexion à la BDD
            Connection connexion = daoFactory.getConnection();

            // Modification des informations principales
            String requete = String.format("UPDATE utilisateur SET utilisateurNom=\"%s\",utilisateurPrenom=\"%s\",utilisateurAge=\"%s\",utilisateurSexe=\"%s\",utilisateurMail=\"%s\",utilisateurPassword=\"%s\",utilisateurTel=\"%s\",utilisateurImage=\"%s\" WHERE utilisateurId=\"%s\"",
                    utilisateur.getUtilisateurNom(),
                    utilisateur.getUtilisateurPrenom(),
                    utilisateur.getUtilisateurAge(),
                    utilisateur.getUtilisateurSexe(),
                    utilisateur.getUtilisateurMail(),
                    utilisateur.getUtilisateurPassword(),
                    utilisateur.getUtilisateurTel(),
                    utilisateur.getUtilisateurImage(),
                    utilisateur.getUtilisateurId()
            );

            PreparedStatement preparedStatement = connexion.prepareStatement(requete);
            preparedStatement.executeUpdate();


            // Modification de l'adresse
            requete = String.format("UPDATE adresse SET adresseCodePostal = \"%s\", adresseVille = \"%s\", adresseRue = \"%s\", adresseNumero=\"%s\" WHERE adresseId=\"%s\"",
                    utilisateur.getUtilisateurAdresse().getAdresseCodePostal(),
                    utilisateur.getUtilisateurAdresse().getAdresseVille(),
                    utilisateur.getUtilisateurAdresse().getAdresseRue(),
                    utilisateur.getUtilisateurAdresse().getAdresseNumero(),
                    utilisateur.getUtilisateurId()
            );

            preparedStatement = connexion.prepareStatement(requete);
            preparedStatement.executeUpdate();


            // Modification des informations de spécialiste
            if (utilisateur instanceof Specialiste) {

                requete = String.format("UPDATE specialiste SET specialisteSpecialite = \"%s\",specialisteDescription = \"%s\",specialisteTarif= \"%s\" WHERE specialisteId=\"%s\"",
                        ((Specialiste) utilisateur).getSpecialisteSpecialite(),
                        ((Specialiste) utilisateur).getSpecialisteDescription(),
                        ((Specialiste) utilisateur).getSpecialisteTarif(),
                        utilisateur.getUtilisateurId()
                );


                preparedStatement  = connexion.prepareStatement(requete);
                preparedStatement.executeUpdate();

            }


        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("Impossible de modifier l'utilisateur dans la base de données");
        }

        // On renvoie l'utilisateur modifié
        return utilisateur;

    }

    public int connexionUtilisateur(String mail, String mdp) throws ConnexionException {

        try {

            // Connexion à la BDD
            Connection connexion = daoFactory.getConnection();
            Statement statement = connexion.createStatement();

            // Requête de connexion
            String requete = String.format("SELECT utilisateurId FROM utilisateur WHERE utilisateurMail=\"%s\" AND utilisateurPassword=\"%s\"",
                    mail,
                    mdp
            );

            ResultSet resultat = statement.executeQuery(requete);

            // Si la requête est positive on renvoie l'identifiant de l'utilisateur connecté, sinon lève une exception
            if (resultat.next()) {

                return resultat.getInt(1);

            } else {

                throw new ConnexionException();
            }

        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("Mot de passe incorrect ou utilisateur inexistant");
        }

        // Si l'utilisateur n'existe pas, on renvoie 0
        return 0;
    }

    // Méthode statique main à vocation de tests
    public static void main(String[] args) {

        DaoFactory dao = DaoFactory.getInstance("projetjava", "root", "");

        UtilisateurDAOImpl daoUtilisateur = new UtilisateurDAOImpl(dao);

        ArrayList<Specialiste> listeSpecialiste = new ArrayList<>();

        listeSpecialiste = daoUtilisateur.rechercheSpecialiste("Conducteur");

        for (Specialiste specialiste_i : listeSpecialiste){
            System.out.println(specialiste_i.getUtilisateurNom()+" "+ specialiste_i.getUtilisateurPrenom()+" "+specialiste_i.getUtilisateurAdresse().getAdresseVille()+" "+specialiste_i.getSpecialisteSpecialite());
        }


        /*Adresse adresse = new Adresse(92000, "FAR", "11 rue des Ormeaux", "2");

        Patient patient = new Patient(6, "KAIROUZ", "Thais", 21, adresse, 'F', "test@gmail.com", "feur", "01", "");
        */

        //System.out.println(daoUtilisateur.connexionUtilisateur("test@gmail.com", "feur"));

    }
}
