package DAO;

import Modele.*;
import java.sql.*;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Implémentation de l'interface RdvDAO
 */
public class RdvDAOImpl implements RdvDAO {

    private DaoFactory daoFactory;

    public RdvDAOImpl(DaoFactory daoFactory) {

        this.daoFactory = daoFactory;
    }

    public ArrayList<RDV> chercherRDV(int utilisateurId ) {

        // Initialisation des variables
        int rechercheUtilisateurId = 0;
        int rechercheSpecialisteId = 0;
        long rechercheRdvDate = 0;

        ArrayList<RDV> rdvliste = new ArrayList<>();

        try {
            // Connexion à la BDD
            Connection connexion = daoFactory.getConnection();

            // Création des statements permettant d'effectuer plusieurs requêtes imbriquées
            Statement[] statements = new Statement[4];
            for (int i=0; i<statements.length; i++) {

                statements[i] = connexion.createStatement();
            }

            // On récupère le type de compte qui correspond à l'id passé en paramètre (Patient ou Spécialiste)
            int[] confirmations = new int[2];

            ResultSet resultatConfirmations;

            // On cherche si le compte en question est un Patient
            resultatConfirmations = statements[0].executeQuery("SELECT count(*) FROM patient WHERE patientId = " + utilisateurId);
            resultatConfirmations.next();

            confirmations[0] = resultatConfirmations.getInt(1);

            // On cherche si le compte en question est un Spécialiste
            resultatConfirmations = statements[1].executeQuery("SELECT count(*) FROM specialiste WHERE specialisteId = " + utilisateurId);
            resultatConfirmations.next();

            confirmations[1] = resultatConfirmations.getInt(1);

            int typeUtilisateur = 0;

            // On détermine alors le type d'utilisateur (Patient ou Spécialiste)
            for (int i = 0; i < confirmations.length; i++) {
                typeUtilisateur = confirmations[i] > confirmations[typeUtilisateur] ? i : typeUtilisateur;
            }

            ResultSet resultRecherche = null;

            // On cherche la liste de RDV en fonction du type de compte
            if (typeUtilisateur == 0) {

                resultRecherche = statements[2].executeQuery("SELECT * FROM rdv WHERE rdvPatient = \'"+ utilisateurId + "\' ORDER BY rdvDate ASC");

            } else if (typeUtilisateur == 1) {

                resultRecherche = statements[3].executeQuery("SELECT * FROM rdv WHERE rdvSpecialiste = \'"+ utilisateurId + "\'");

            }

            // On récupère chaque RDV que l'on ajoute dans la liste de RDV
            while (resultRecherche.next()) {

                rechercheSpecialisteId = resultRecherche.getInt("rdvSpecialiste");
                rechercheUtilisateurId = resultRecherche.getInt("rdvPatient");
                rechercheRdvDate = resultRecherche.getLong("rdvDate");

                RDV rdvAAjouter = new RDV((Specialiste) daoFactory.getUtilisateurDAO().chercherUtilisateur(rechercheSpecialisteId), daoFactory.getUtilisateurDAO().chercherUtilisateur(rechercheUtilisateurId), rechercheRdvDate);
                rdvliste.add(rdvAAjouter);
            }


        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("Recherche des RDV de l'utilisateur impossible");

        }

        // On retourne la liste de RDV
        return rdvliste;
    }

    public void ajouterRDV(RDV rdv) {

        try {

            // Connexion à la BDD
            Connection connexion = daoFactory.getConnection();
            Statement statement = connexion.createStatement();

            // Requete SQL permettant d'ajouter le rdv passé en paramètre dans la BDD
            String requete = String.format("INSERT INTO rdv(rdvSpecialiste, rdvPatient, rdvDate) VALUES (\"%s\", \"%s\", \"%s\")",
                    rdv.getSpecialiste().getUtilisateurId(),
                    rdv.getUtilisateur().getUtilisateurId(),
                    rdv.getDate()
            );

            // Exécution de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(requete);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("Impossible d'ajouter le RDV à la base de données");
        }
    }

    public void supprimerRDV(RDV rdv) {

        try {

            // Connexion à la BDD
            Connection connexion = daoFactory.getConnection();
            Statement statement = connexion.createStatement();

            // Requête de suppression du RDV passé en paramètre
            statement.executeUpdate("DELETE FROM rdv WHERE rdv.rdvSpecialiste  = \""+rdv.getSpecialiste().getUtilisateurId() +"\" AND rdv.rdvPatient = \""+rdv.getUtilisateur().getUtilisateurId()+"\"AND rdv.rdvDate = " + rdv.getDate());

        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("Suppression du RDV impossible");
        }
    }

    // Méthode statique main à vocation de tests
    public static void main(String[] args) {

        /*DaoFactory dao = DaoFactory.getInstance("projetjava", "root", "");

        RdvDAOImpl rdvDao = new RdvDAOImpl(dao);

        ArrayList<RDV> listeRDV = rdvDao.chercherRDV(16);

        System.out.println(listeRDV);

        Adresse adresse = new Adresse(92260, "FAR", "11 rue des Ormeaux", "2");

        Patient patient = new Patient(6, "KAIROUZ", "Thais", 21, adresse, 'F', "test4@gmail.com", "feur", "01", "");

        Specialiste specialiste = new Specialiste(7,"Shrek","Pierre",69,adresse,'M',"shrek5@com","feur","0","","proctologue"," ",69.69);

        UtilisateurDAOImpl utilisateurDAO = new UtilisateurDAOImpl(dao);

        RdvDAOImpl rdvDao = new RdvDAOImpl(dao);

        try {

            utilisateurDAO.ajouterUtilisateur(specialiste);


        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        RDV rdv = new RDV(specialiste,patient, 33321813);

        rdvDao.ajouterRDV(rdv);



        ArrayList<RDV> listRDV = new ArrayList<>();
        listRDV=rdvDao.chercherRDV(7);

        for(RDV rdvi : listRDV){
            System.out.println(rdvi.getDate());
        }*/


    }

}
