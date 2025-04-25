package Vue;

import Modele.Specialiste;
import Modele.Utilisateur;

import javax.swing.*;
import java.awt.*;


/**
 * La classe ConfirmationRDV représente la page où le patient
 * a le récapitulatif des informations de son RDV sélectionné avant de confirmer.
 * Classe héritant de JDialog.
 */
public class ConfirmationRDV extends JDialog {

    public JButton confirmerButton;
    public JButton annulerButton;
    public JLabel confirmationText;
    public Specialiste specialiste;
    public Utilisateur utilisateur;
    public long date;


    /**
     * Constructeur de la classe ConfirmationRDV.
     * Initialise la fenêtre avec ses propriétés et son contenu.
     */
    public ConfirmationRDV() {
        setTitle("Confirmation");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);

        setContentPane(buildPanel());
    }

    /**
     * Construit l'interface graphique et
     *
     * @return Le panneau principal contenant le récapitulatif des informations du RDV sélectionné.
     */
    public JPanel buildPanel() {

        JPanel panelGeneral = new JPanel();
        panelGeneral.setLayout(new BorderLayout());

        if (specialiste == null || utilisateur == null || date == 0) {

            return panelGeneral;
        }

        // Titre "Confirmation"
        JLabel titleLabel = new JLabel("Confirmation");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setForeground(new Color(45, 104, 196));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panelGeneral.add(titleLabel, BorderLayout.NORTH);

        // Panel principal avec GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Augmenter les marges pour une meilleure lisibilité
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Texte de confirmation
        String message = "Confirmer le RDV avec le Dr. " + specialiste.getUtilisateurNom() + " le " + new java.text.SimpleDateFormat("dd/MM/yyyy à HH:mm").format(new java.util.Date (date));
        confirmationText = new JLabel(message);
        confirmationText.setFont(new Font("Tahoma", Font.PLAIN, 16));
        confirmationText.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(confirmationText, gbc);

        panelGeneral.add(formPanel, BorderLayout.CENTER);

        // Bouton Confirmer
        confirmerButton = new JButton("Confirmer");
        confirmerButton.setPreferredSize(new Dimension(200, 50)); // Augmenter la taille du bouton
        confirmerButton.setFont(new Font("Tahoma", Font.BOLD, 18)); // Augmenter la taille de la police
        confirmerButton.setBackground(new Color(45, 104, 196)); // Changer la couleur de fond
        confirmerButton.setForeground(Color.WHITE); // Changer la couleur du texte
        confirmerButton.setFocusPainted(false);

        // Bouton annuler
        annulerButton = new JButton("Annuler");
        annulerButton.setPreferredSize(new Dimension(200, 50)); // Augmenter la taille du bouton
        annulerButton.setFont(new Font("Verdana", Font.BOLD, 18)); // Augmenter la taille de la police
        annulerButton.setBackground(new Color(255, 0, 0)); // Changer la couleur de fond
        annulerButton.setForeground(Color.WHITE); // Changer la couleur du texte
        annulerButton.setFocusPainted(false);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(confirmerButton, gbc);

        gbc.gridx = 10;
        gbc.gridy = 0;
        buttonPanel.add(annulerButton, gbc);

        panelGeneral.add(buttonPanel, BorderLayout.SOUTH);

        return panelGeneral;
    }

}
