package com.fmf.pdv.chart;

import java.awt.Dimension;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class ChartExample {
    public CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        dataset.addValue(25, "João", "");
        dataset.addValue(28, "Maria", "");
        dataset.addValue(22, "Pedro", "");
        
        return dataset;
    }
    
    public JFreeChart createBarChart(CategoryDataset dataset) {
        JFreeChart jFreeChart = ChartFactory.createBarChart("Titulo Chart", "", "Teste 2", dataset, PlotOrientation.HORIZONTAL, true, true, true);
        
        return jFreeChart;
    }
    
    public ChartPanel createChart() {
        CategoryDataset dataset = createDataset();
        JFreeChart jFreeChart = createBarChart(dataset);
        
        ChartPanel panel = new ChartPanel(jFreeChart);

        panel.setPreferredSize(new Dimension(350, 300));

        return panel;
    }
}
