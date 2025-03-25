package Vue;

import Modele.Patient;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Inscription extends JDialog {
    private JPanel inscriptionPanel;
    private JButton btnInscription;
    private JTextField tfMail;
    private JTextField tfNom;
    private JTextField tfPrenom;
    private JTextField tfAge;
    private JTextField tfNumero;
    private JPasswordField pfMdp;
    private JPasswordField pfConfirmeMdp;
    private JTextField tfAdressePostale;


    public Inscription (JFrame parent){
        super(parent);
        setTitle("Créer un compte");
        setContentPane(inscriptionPanel);
        setMinimumSize(new Dimension(500, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setVisible(true);
        btnInscription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                utilisateurInscription();
            }
        });
    }

    private void utilisateurInscription() {
        String mail = tfMail.getText();
        String mdp = String.valueOf(pfConfirmeMdp.getPassword());
        String confirmeMdp = String.valueOf(pfConfirmeMdp.getPassword());
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String age = tfAge.getText();
        //int age = Integer.parseInt(tfAge.getText());
        String numero = tfNumero.getText();
        String adressePostale = tfAdressePostale.getText();
        //Adresse adressePostale = tfAdressePostale.getText();

        if (mail.isEmpty() || mdp.isEmpty() || confirmeMdp.isEmpty() || nom.isEmpty() || prenom.isEmpty() || age.isEmpty() || numero.isEmpty() || adressePostale.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Veuillez rentrer tous les champ",
                    "Recommencez",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!mdp.equals(confirmeMdp)){
            JOptionPane.showMessageDialog(this,
                    "Les mots de passes ne correspondent pas",
                    "Recommencez",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }


        //Patient patient = new Patient(0, nom, prenom, age, adressePostale, 0, mail, mdp, confirmeMdp);


    }


    public static void main(String[] args) {
        Inscription monFormulaire = new Inscription(null);
        monFormulaire.utilisateurInscription();
        System.exit(0);

    }
}
