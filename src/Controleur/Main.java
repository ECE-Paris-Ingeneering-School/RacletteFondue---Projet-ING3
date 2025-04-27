package Controleur;

import DAO.DaoFactory;
import Vue.*;

import javax.swing.*;

/**
 * Classe principale
 * **/
public class Main {

    public static void main(String[] args) {

        // On vérifie si la base de données est bien connectée
        try {

            DaoFactory.getInstance("projetjava", "root", "").getConnection();

        } catch (Exception e) {

            System.out.println("La base de données n'est pas connectée !");
            System.exit(10);
        }



        // Création du thread Swing
        SwingUtilities.invokeLater(new Runnable() {

            // Méthode d'execution des processus de Swing
            public void run() {

                FenetrePrincipale fenetre = new FenetrePrincipale(); // Instaciation de la fenêtre principale

            }
        });


    }
}