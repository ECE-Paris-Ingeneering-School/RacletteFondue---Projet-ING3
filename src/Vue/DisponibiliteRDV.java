package Vue;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DisponibiliteRDV extends JFrame {

    public JButton button;

    public DisponibiliteRDV() {
        setTitle("Disponibilités");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Titre "Disponibilité"
        JLabel titleLabel = new JLabel("Disponibilité");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 42));
        titleLabel.setForeground(new Color(45, 104, 196));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Panel principal avec GridBagLayout
        JPanel tablePanel = new JPanel(new GridLayout(7, 7));
        tablePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Jours de la semaine
        String[] jours = {"LUN", "MAR", "MER", "JEU", "VEN", "SAM", "DIM"};
        for (String jour : jours) {
            JLabel jourLabel = new JLabel(jour, SwingConstants.CENTER);
            jourLabel.setFont(new Font("Verdana", Font.BOLD, 18));
            jourLabel.setForeground(new Color(45, 104, 196));
            tablePanel.add(jourLabel);
        }

        // Horaires et boutons
        String[] horaires = {"8:00", "10:00", "12:00", "14:00", "16:00", "18:00"};
        Random random = new Random();

        for (String horaire : horaires) {
            for (int i = 0; i < 7; i++) {
                button = new JButton(horaire);
                button.setFont(new Font("Verdana", Font.PLAIN, 16));
                if (random.nextBoolean()) {
                    button.setBackground(new Color(45, 104, 196)); // Bleu
                    button.setForeground(Color.WHITE);
                    button.setEnabled(true);
                } else {
                    button.setBackground(Color.LIGHT_GRAY); // Gris clair
                    button.setForeground(Color.DARK_GRAY);
                    button.setEnabled(false);
                }
                tablePanel.add(button);
            }
        }

        add(tablePanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DisponibiliteRDV().setVisible(true);
        });
    }
}
