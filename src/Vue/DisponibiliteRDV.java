package Vue;

import Modele.*;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * La classe DisoonibiliteRDV représente la page où le patinet peut voir
 * le tableau des disponibilités du spécialiste choisi.
 * La classe DisoonibiliteRDV hérite de JDialog.
 */
public class DisponibiliteRDV extends JDialog {

    public JButton buttonCreneau;
    public long dateActuelle;
    public long datePage;
    public Specialiste specialiste;
    public Utilisateur utilisateur;

    public ArrayList<RDV> listeRDV = null;
    public Map<JButton, Long> mapCreneaux = null;


    /**
     * Constructeur de la classe DisponibiliteRDV.
     * Initialise la fenêtre avec ses propriétés et son contenu.
     */
    public DisponibiliteRDV() {

        mapCreneaux = new HashMap<JButton, Long>();

        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        dateActuelle = Instant.now().toEpochMilli();
        //System.out.println(dateActuelle);

        setContentPane(buildPanel());
    }


    /**
     * Construit l'interface graphique et
     *
     * @return Le panneau principal contenant le tableau des disponibilités du spécialiste.
     */
    public JPanel buildPanel() {


        // Panel principal avec GridBagLayout
        JPanel tablePanel = new JPanel(new GridLayout(7, 7));
        tablePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        if (specialiste == null || listeRDV == null) {

            return tablePanel;
        }

        ArrayList<Long> listeDates = new ArrayList<>();

        for (RDV rdv : listeRDV) {
            listeDates.add(rdv.getDate());
        }


        setTitle("Disponibilités de Dr. " + specialiste.getUtilisateurNom() + " - " + specialiste.getSpecialisteSpecialite());

        // Initialisation de la première page
        String temp = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date (dateActuelle));

        long nouveauTimestamp = 0;

        try {

            nouveauTimestamp = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(temp + " 08:00:00").getTime();

        } catch (Exception e) {

            // feur
        }

        datePage = nouveauTimestamp;

        //System.out.println(datePage);

        // Jours de la semaine
        String[] jours = {"LUN", "MAR", "MER", "JEU", "VEN", "SAM", "DIM"};

        for (int i=0; i<jours.length; i++) { // +86400000 pour ajouter un jour

            jours[i] = new java.text.SimpleDateFormat("dd/MM").format(new java.util.Date (datePage+(i*86400000)));
        }

        for (String jour : jours) {
            JLabel jourLabel = new JLabel(jour, SwingConstants.CENTER);
            jourLabel.setFont(new Font("Verdana", Font.BOLD, 18));
            jourLabel.setForeground(new Color(45, 104, 196));
            tablePanel.add(jourLabel);
        }


        // Horaires et boutons
        long timeStamp = datePage;

        String[] horaires = {"8:00", "10:00", "12:00", "14:00", "16:00", "18:00"};

        for (String horaire : horaires) {

            for (int i = 0; i < 7; i++) {

                buttonCreneau = new JButton(horaire);
                buttonCreneau.setFont(new Font("Verdana", Font.PLAIN, 16));

                mapCreneaux.put(buttonCreneau, timeStamp+(i*86400000));

                if (utilisateur instanceof Patient){

                    if (dateActuelle > timeStamp+(i*86400000) || listeDates.contains(timeStamp+(i*86400000))) {

                        buttonCreneau.setBackground(Color.LIGHT_GRAY); // Gris clair
                        buttonCreneau.setForeground(Color.DARK_GRAY);
                        buttonCreneau.setEnabled(false);

                    } else {

                        buttonCreneau.setBackground(new Color(45, 104, 196)); // Bleu
                        buttonCreneau.setForeground(Color.WHITE);
                        buttonCreneau.setEnabled(true);
                    }
                }else if (utilisateur instanceof Admin){

                    if (dateActuelle > timeStamp+(i*86400000)) {

                        buttonCreneau.setBackground(Color.LIGHT_GRAY); // Gris clair
                        buttonCreneau.setForeground(Color.DARK_GRAY);
                        buttonCreneau.setEnabled(false);

                    } else if (listeDates.contains(timeStamp+(i*86400000))) {

                        for (RDV RDV_i : listeRDV){
                            if (RDV_i.getDate()==timeStamp+(i*86400000)){
                                if (RDV_i.getUtilisateur().getUtilisateurId()==utilisateur.getUtilisateurId()){
                                    buttonCreneau.setBackground(new Color(207, 9, 31));
                                    buttonCreneau.setForeground(Color.WHITE);
                                    buttonCreneau.setEnabled(true);
                                }
                                else {
                                    buttonCreneau.setBackground(new Color(64, 67, 73));
                                    buttonCreneau.setForeground(Color.WHITE);
                                    buttonCreneau.setEnabled(true);
                                }

                                break;
                            }
                        }



                    }else {

                        buttonCreneau.setBackground(new Color(45, 104, 196)); // Bleu
                        buttonCreneau.setForeground(Color.WHITE);
                        buttonCreneau.setEnabled(true);
                    }

                }


                tablePanel.add(buttonCreneau);
            }

            timeStamp = timeStamp + 7200000;
        }


        return tablePanel;
    }

}
