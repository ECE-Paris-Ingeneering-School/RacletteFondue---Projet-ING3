package Vue;

import javax.swing.*;
import java.awt.*;

public class ConfirmationRDV extends JFrame {

    JButton confirmerButton;

    JLabel confirmationText;

    public ConfirmationRDV() {
        setTitle("Confirmation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 900);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Titre "Confirmation"
        JLabel titleLabel = new JLabel("Confirmation");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 42));
        titleLabel.setForeground(new Color(45, 104, 196));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Panel principal avec GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Augmenter les marges pour une meilleure lisibilité
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Texte de confirmation
        confirmationText = new JLabel("Voulez-vous confirmer votre RDV avec Dr. ... le ../../.. à ..:.. ?");
        confirmationText.setFont(new Font("Verdana", Font.PLAIN, 24));
        confirmationText.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(confirmationText, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Bouton Confirmer
        confirmerButton = new JButton("Confirmer");
        confirmerButton.setPreferredSize(new Dimension(200, 50)); // Augmenter la taille du bouton
        confirmerButton.setFont(new Font("Verdana", Font.BOLD, 20)); // Augmenter la taille de la police
        confirmerButton.setBackground(new Color(45, 104, 196)); // Changer la couleur de fond
        confirmerButton.setForeground(Color.WHITE); // Changer la couleur du texte
        confirmerButton.setFocusPainted(false);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(confirmerButton, gbc);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ConfirmationRDV().setVisible(true);
        });
    }
}
