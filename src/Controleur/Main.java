package Controleur;

import Vue.*;

import javax.swing.*;

/**
 * Classe principale
 * **/
public class Main {

    public static void main(String[] args) {

        // Création du thread Swing
        SwingUtilities.invokeLater(new Runnable() {

            // Méthode d'execution des processus de Swing
            public void run() {

                FenetrePrincipale fenetre = new FenetrePrincipale(); // Instaciation de la fenêtre principale

            }
        });


    }
}