package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Connexion extends JDialog {
    private JPanel connexionPanel;
    private JButton btnConnexion;
    private JTextField tfMail;
    private JTextField tfNom;
    private JTextField tfPrenom;
    private JTextField tfAge;
    private JTextField tfNumero;
    private JPasswordField pfMdp;
    private JPasswordField pfConfirmeMdp;
    private JTextField tfAdressePostale;
    private JButton sInscrireButton;


    public Connexion (JFrame parent){
        super(parent);
        setTitle("Créer un compte");
        setContentPane(connexionPanel);
        setMinimumSize(new Dimension(650, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setVisible(true);
        btnConnexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                utilisateurConnexion();
            }
        });
    }

    private void utilisateurConnexion() {
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
        Connexion monFormulaire = new Connexion(null);
        monFormulaire.utilisateurConnexion();
        System.exit(0);

    }
}
