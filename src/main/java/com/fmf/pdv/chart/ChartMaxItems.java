package com.fmf.pdv.chart;

import com.fmf.pdv.model.ChartValue;
import java.awt.Dimension;
import java.util.List;
import java.util.Locale;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class ChartMaxItems {
    public PieDataset createDataset(List<ChartValue> values) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        for (ChartValue o: values) {
            dataset.setValue(o.getName(), o.getTotal());
        }

        return dataset;
    }

    public JFreeChart createPieChart(PieDataset dataset) {
        JFreeChart jFreeChart = ChartFactory.createPieChart("Produtos mais vendidos", dataset, false, true, Locale.US);

        return jFreeChart;
    }

    public ChartPanel createChart(List<ChartValue> orderItems) {
        PieDataset dataset = createDataset(orderItems);
        JFreeChart jFreeChart = createPieChart(dataset);

        ChartPanel panel = new ChartPanel(jFreeChart);

        panel.setPreferredSize(new Dimension(300, 300));

        return panel;
    }
}
