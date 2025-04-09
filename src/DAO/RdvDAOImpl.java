package DAO;

import Modele.*;
import DAO.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * implémentation MySQL du stockage dans la base de données des méthodes définies dans l'interface RDVDao.
 */
public class RdvDAOImpl implements RdvDAO {

    private DaoFactory daoFactory;

    public RdvDAOImpl(DaoFactory daoFactory) {

        this.daoFactory = daoFactory;
    }

    public RDV chercherRDV(int patientId, int specialisteId, int rdvDate) {

        int recherchePatientId = 0;
        int rechercheSpecialisteId = 0;
        int rechercheRdvDate = 0;

        try {
            // connexion
            Connection connexion = daoFactory.getConnection();

            Statement statements = connexion.createStatement();

            //Recupération des informations associées
            ResultSet resultats = statements.executeQuery("SELECT * FROM rdv WHERE rdv.rdvSpecialiste=\'" + specialisteId + "\' AND rdv.rdvPatient=\'"+ patientId + "\' AND rdv.rdvDate=\'"+ rdvDate + "\'");

            while (resultats.next()) {

                rechercheSpecialisteId = resultats.getInt("specialisteId");
                recherchePatientId = resultats.getInt("patientId");
                rechercheRdvDate = resultats.getInt("rdvDate");

            }

            return new RDV(((Specialiste) daoFactory.getUtilisateurDAO().chercherUtilisateur(rechercheSpecialisteId), (Patient) daoFactory.getUtilisateurDAO().chercherUtilisateur(recherchePatientId), rechercheRdvDate);



        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("Recherche de l'utilisateur impossible");

            }


    };

}
