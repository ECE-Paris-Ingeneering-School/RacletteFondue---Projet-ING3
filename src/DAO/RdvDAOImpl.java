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
 * implémentation MySQL du stockage dans la base de données des méthodes définies dans l'interface RDVDao.
 */
public class RdvDAOImpl implements RdvDAO {

    private DaoFactory daoFactory;

    public RdvDAOImpl(DaoFactory daoFactory) {

        this.daoFactory = daoFactory;
    }

    public ArrayList<RDV> chercherRDV(int utilisateurId ) {

        int rechercheUtilisateurId = 0;
        int rechercheSpecialisteId = 0;
        long rechercheRdvDate = 0;

        ArrayList<RDV> rdvliste = new ArrayList<>();


        try {
            // connexion
            Connection connexion = daoFactory.getConnection();


            Statement[] statements = new Statement[4];
            for (int i=0; i<statements.length; i++) {

                statements[i] = connexion.createStatement();
            }

            // On récupère le type de compte qui correspond à l'id passé en paramètre
            int[] confirmations = new int[2];

            ResultSet resultatConfirmations;

            resultatConfirmations = statements[0].executeQuery("SELECT count(*) FROM patient WHERE patientId = " + utilisateurId);
            resultatConfirmations.next();

            confirmations[0] = resultatConfirmations.getInt(1);


            resultatConfirmations = statements[1].executeQuery("SELECT count(*) FROM specialiste WHERE specialisteId = " + utilisateurId);
            resultatConfirmations.next();

            confirmations[1] = resultatConfirmations.getInt(1);

            int typeUtilisateur = 0;

            for (int i = 0; i < confirmations.length; i++) {
                typeUtilisateur = confirmations[i] > confirmations[typeUtilisateur] ? i : typeUtilisateur;
            }

            ResultSet resultRecherche = null;

            if (typeUtilisateur == 0) {

                resultRecherche = statements[2].executeQuery("SELECT * FROM rdv WHERE rdvPatient = \'"+ utilisateurId + "\' ORDER BY rdvDate ASC");

            } else if (typeUtilisateur == 1) {

                resultRecherche = statements[3].executeQuery("SELECT * FROM rdv WHERE rdvSpecialiste = \'"+ utilisateurId + "\'");

            }


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


        return rdvliste;
    }

    public void ajouterRDV(RDV rdv){
        try {

            // Connexion à la BDD
            Connection connexion = daoFactory.getConnection();
            Statement statement = connexion.createStatement();

            // Requete SQL
            String requete = String.format("INSERT INTO rdv(rdvSpecialiste, rdvPatient, rdvDate) VALUES (\"%s\", \"%s\", \"%s\")",
                    rdv.getSpecialiste().getUtilisateurId(),
                    rdv.getUtilisateur().getUtilisateurId(),
                    rdv.getDate()
            );


            PreparedStatement preparedStatement = connexion.prepareStatement(requete);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("Impossible d'ajouter le RDV à la base de données");
        }
    }

    public void supprimerRDV(RDV rdv){
        try {

            // Connexion
            Connection connexion = daoFactory.getConnection();
            Statement statement = connexion.createStatement();

            // Suppression du rdv
            statement.executeUpdate("DELETE FROM rdv WHERE rdv.rdvSpecialiste  = \""+rdv.getSpecialiste().getUtilisateurId() +"\" AND rdv.rdvPatient = \""+rdv.getUtilisateur().getUtilisateurId()+"\"AND rdv.rdvDate = " + rdv.getDate());

        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("Suppression du RDV impossible");
        }
    }

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
