package com.fmf.pdv.chart;

import com.fmf.pdv.model.ChartValue;
import java.awt.Dimension;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class ChartSalesMonths {
    public CategoryDataset createDataset(List<ChartValue> values) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (ChartValue o: values) {
            dataset.addValue(o.getTotal(), "", o.getName());
        }

        return dataset;
    }

    public JFreeChart createBarChart(CategoryDataset dataset) {
        JFreeChart jFreeChart = ChartFactory.createLineChart("Faturamento Mensal", "Mês", "Valor", dataset, PlotOrientation.VERTICAL, false, true, false);

        return jFreeChart;
    }

    public ChartPanel createChart(List<ChartValue> values) {
        CategoryDataset dataset = createDataset(values);
        JFreeChart jFreeChart = createBarChart(dataset);

        ChartPanel panel = new ChartPanel(jFreeChart);

        panel.setPreferredSize(new Dimension(350, 300));

        return panel;
    }
}
