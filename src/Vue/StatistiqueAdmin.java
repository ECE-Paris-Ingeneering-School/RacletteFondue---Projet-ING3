package Vue;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

public class StatistiqueAdmin extends JFrame {

    public JButton btnSpecialiste;
    public JButton btnDossierPatients;
    public JButton btnStatistiques;

    public StatistiqueAdmin() {
        setTitle("Graphiques du spécialiste");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Titre
        JLabel titleLabel = new JLabel("Statistique - ADMIN");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 42));
        titleLabel.setForeground(new Color(45, 104, 196));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Menu boutons
        JPanel menuPanel = new JPanel();

        btnSpecialiste = new JButton("Spécialistes");
        styleMenuButton(btnSpecialiste);

        btnDossierPatients = new JButton("Dossier Patients");
        styleMenuButton(btnDossierPatients);

        btnStatistiques = new JButton("Statistiques");
        styleMenuButton(btnStatistiques);
        btnStatistiques.setForeground(Color.WHITE);
        btnStatistiques.setBackground(new Color(45, 104, 196));

        menuPanel.add(btnSpecialiste);
        menuPanel.add(btnDossierPatients);
        menuPanel.add(btnStatistiques);

        mainPanel.add(menuPanel);
        mainPanel.add(Box.createVerticalStrut(0)); // Réduire l'espace vertical ici

        // Ajoute les graphiques côte à côte
        JPanel chartPanel = new JPanel();
        chartPanel.setLayout(new GridLayout(1, 2));
        chartPanel.add(createPieChartPanel());
        chartPanel.add(createBarChartPanel());

        mainPanel.add(chartPanel);

        // Add margins
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(mainPanel);
    }

    //donnée à changer
    String[] patients = {"a", "b", "c", "d", "e", "f", "g"};
    String[] specialistes = {"Dupont", "Petit", "Grand", "Moche"};
    int[] rdv = {1, 4, 10, 54, 24, 36, 18};

    private JPanel createPieChartPanel() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Spécialistes", specialistes.length);
        dataset.setValue("Patients", patients.length);

        JFreeChart chart = ChartFactory.createPieChart(
                "Répartition du nombre de patients et du nombre de spécialistes",
                dataset,
                true, true, false
        );

        return new ChartPanel(chart);
    }

    private JPanel createBarChartPanel() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int maxAbundance = 6 * specialistes.length;

        for (int i = 0; i < rdv.length; i++) {
            dataset.addValue(rdv[i], "RDV", getDayOfWeek(i));
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Mesure de l'abondance du nombre de RDV sur la semaine",
                "Jours de la semaine",
                "Nombre de RDV",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = new CustomBarRenderer(maxAbundance, rdv);

        plot.setRenderer(renderer);

        return new ChartPanel(chart);
    }

    private String getDayOfWeek(int index) {
        return switch (index) {
            case 0 -> "Lun";
            case 1 -> "Mar";
            case 2 -> "Mer";
            case 3 -> "Jeu";
            case 4 -> "Ven";
            case 5 -> "Sam";
            case 6 -> "Dim";
            default -> "";
        };
    }

    private void styleMenuButton(JButton button) {
        button.setPreferredSize(new Dimension(250, 70));
        button.setFont(new Font("Tahoma", Font.BOLD, 20));
        button.setBackground(new Color(221, 235, 247));
        button.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StatistiqueAdmin().setVisible(true);
        });
    }

    private static class CustomBarRenderer extends BarRenderer {
        private final int maxAbundance;
        private final int[] rdv;

        public CustomBarRenderer(int maxAbundance, int[] rdv) {
            this.maxAbundance = maxAbundance;
            this.rdv = rdv;
        }

        @Override
        public Paint getItemPaint(int row, int column) {
            int value = rdv[column];
            if (value < maxAbundance / 3) {
                return Color.GREEN;
            } else if (value < 2 * maxAbundance / 3) {
                return Color.ORANGE;
            } else {
                return Color.RED;
            }
        }
    }
}
