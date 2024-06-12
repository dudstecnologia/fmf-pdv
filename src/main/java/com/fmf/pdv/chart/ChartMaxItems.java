package com.fmf.pdv.chart;

import com.fmf.pdv.model.OrderItemChart;
import java.awt.Dimension;
import java.util.List;
import java.util.Locale;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class ChartMaxItems {
    public PieDataset createDataset(List<OrderItemChart> orderItems) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        for (OrderItemChart o: orderItems) {
            dataset.setValue(o.getName(), o.getTotal());
        }

        return dataset;
    }

    public JFreeChart createPieChart(PieDataset dataset) {
        JFreeChart jFreeChart = ChartFactory.createPieChart("Produtos mais vendidos", dataset, true, true, Locale.US);

        return jFreeChart;
    }

    public ChartPanel createChart(List<OrderItemChart> orderItems) {
        PieDataset dataset = createDataset(orderItems);
        JFreeChart jFreeChart = createPieChart(dataset);

        ChartPanel panel = new ChartPanel(jFreeChart);

        panel.setPreferredSize(new Dimension(300, 300));

        return panel;
    }
}
