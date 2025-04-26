package Vue;

import Modele.RDV;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.print.attribute.HashPrintJobAttributeSet;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;


/**
 * La classe StatistiqueAdmin représente la page d'accès au statistiques du site (pour un admin).
 * La fenêtre propose différents graphiques afin de mieux visualiser les informations.
 */
public class StatistiqueAdmin extends JFrame {

    public JButton btnSpecialiste;
    public JButton btnDossierPatients;
    public JButton btnStatistiques;

    //donnée à changer
    public int nombrePatients;
    public int nombreSpecialistes;
    public TreeMap<Long, Integer> mapRDV;


    /**
     * Constructeur de la classe StatistiqueAdmin.
     * Initialise la fenêtre avec ses propriétés et son contenu.
     */
    public StatistiqueAdmin() {
        setTitle("Graphiques du spécialiste");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setContentPane(buildPanel());
    }


    /**
     * Construit l'interface graphique et
     *
     * @return Le panneau principal contenant les diagrammes statistiques.
     */
    public JPanel buildPanel() {

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        if (mapRDV == null) {

            return mainPanel;
        }

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
        mainPanel.add(Box.createVerticalStrut(30));

        // Ajoute les graphiques côte à côte
        JPanel chartPanel = new JPanel();
        chartPanel.setLayout(new GridLayout(1, 2));
        chartPanel.add(createPieChartPanel());
        chartPanel.add(createBarChartPanel());

        mainPanel.add(chartPanel);

        return mainPanel;
    }


    /**
     * Crée un panneau contenant un graphique camembert (pie chart) représentant la répartition
     * du nombre de patients et du nombre de spécialistes incrits sur la plateforme.
     *
     * @return Le panneau contenant le graphique camembert.
     */
    private JPanel createPieChartPanel() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Spécialistes", nombreSpecialistes);
        dataset.setValue("Patients", nombrePatients);

        JFreeChart chart = ChartFactory.createPieChart(
                "Répartition du nombre de patients et du nombre de spécialistes",
                dataset,
                true, true, false
        );

        return new ChartPanel(chart);
    }


    /**
     * Crée un panneau contenant un histogramme (bar chart) représentant le nombre de RDV
     * disponibles de la semaine.
     *
     * @return Le panneau contenant l'histogramme.
     */
    private JPanel createBarChartPanel() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int maxAbundance = 6 * nombreSpecialistes;

        ArrayList<Integer> nombreRDVdispo = new ArrayList<>();

        for (Long dateRDV : mapRDV.keySet()) {
            int rdvPris = mapRDV.get(dateRDV);
            int rdvDispo = maxAbundance - rdvPris;

            nombreRDVdispo.add(rdvDispo);

            dataset.addValue(rdvDispo, "Disponibilités", new java.text.SimpleDateFormat("dd/MM").format(new java.util.Date(dateRDV)));
        }


        JFreeChart chart = ChartFactory.createBarChart(
                "Nombre de places disponibles de la semaine",
                "Jours de la semaine",
                "Nombre de RDV disponibles",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        CategoryPlot plot = chart.getCategoryPlot();

        BarRenderer renderer = new CustomBarRenderer(maxAbundance, nombreRDVdispo);

        plot.setRenderer(renderer);

        return new ChartPanel(chart);
    }


    /**
     * Applique un style aux boutons du menu.
     *
     * @param button Le bouton à styliser.
     */
    private void styleMenuButton(JButton button) {
        button.setPreferredSize(new Dimension(250, 70));
        button.setFont(new Font("Tahoma", Font.BOLD, 20));
        button.setBackground(new Color(221, 235, 247));
        button.setFocusPainted(false);
    }


    /**
     * Classe interne personnalisée pour le rendu des barres du graphique en barres.
     * Change la couleur des barres par jour en fonction de la disponibilité des rendez-vous.
     */
    private static class CustomBarRenderer extends BarRenderer {

        private final int maxAbundance;
        private final ArrayList<Integer> rdv;

        public CustomBarRenderer(int maxAbundance, ArrayList<Integer> rdv) {
            this.maxAbundance = maxAbundance;
            this.rdv = rdv;
        }

        @Override
        public Paint getItemPaint(int row, int column) {

            int value = rdv.get(column);

            if (value < maxAbundance / 3) {
                return Color.RED;

            } else if (value < 2 * maxAbundance / 3) {
                return Color.ORANGE;

            } else {
                return Color.GREEN;
            }
        }
    }
}
