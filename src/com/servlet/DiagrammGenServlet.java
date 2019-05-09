package com.servlet;

import com.SingleTone;
import com.company.DAO;
import com.company.StatisticDAO;
import com.model.Statistic;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet("/generate")
public class DiagrammGenServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SingleTone singleTone=SingleTone.getInstance("do");

        OutputStream out = resp.getOutputStream();
        resp.setContentType("image/png");

        XYDataset  dataset = getXYDataset(singleTone.getLogin());

        final JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Статистика",             // chart title
                "Дата",                      // x axis label
                "Потребление",                      // y axis label
                dataset,                  // data
                true,                     // include legend
                true,                     // tooltips
                false                     // urls
        );
        XYPlot plot = chart.getXYPlot();
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("dd.MM"));
        if (chart != null) {
            //resp.setContentType("image/png");
            ChartUtilities.writeChartAsPNG(out, chart, 600, 400);
        }
    }
    private DefaultCategoryDataset getCategoryDataset()
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue( 23.0, "Param1", "Object1");
        dataset.addValue( 14.0, "Param1", "Object2");
        dataset.addValue( 18.0, "Param1", "Object3");
        dataset.addValue( 19.0, "Param1", "Object4");
        dataset.addValue( -6.0, "Param2", "Object1");
        dataset.addValue( -9.0, "Param2", "Object2");
        dataset.addValue( 32.0, "Param2", "Object3");
        dataset.addValue( -5.0, "Param2", "Object4");
        dataset.addValue( 16.0, "Param3", "Object1");
        dataset.addValue( 29.0, "Param3", "Object2");
        dataset.addValue( -4.0, "Param3", "Object3");
        dataset.addValue( 14.0, "Param3", "Object4");
        dataset.addValue( 12.0, "Param4", "Object1");
        dataset.addValue( 26.0, "Param4", "Object2");
        dataset.addValue( -9.0, "Param4", "Object3");
        dataset.addValue(  9.0, "Param4", "Object4");
        dataset.addValue( -9.0, "Param5", "Object1");
        dataset.addValue( -4.0, "Param5", "Object2");
        dataset.addValue( 21.0, "Param5", "Object3");
        dataset.addValue( -3.0, "Param5", "Object4");
        dataset.addValue( 13.0, "Param6", "Object1");
        dataset.addValue( 20.0, "Param6", "Object2");
        dataset.addValue( 15.0, "Param6", "Object3");
        dataset.addValue( 17.0, "Param6", "Object4");
        dataset.addValue(-13.0, "Param7", "Object1");
        dataset.addValue( 18.0, "Param7", "Object2");
        dataset.addValue( null, "Param7", "Object3");
        dataset.addValue( 12.0, "Param7", "Object4");
        dataset.addValue( 14.0, "Param8", "Object1");
        dataset.addValue( 19.0, "Param8", "Object2");
        dataset.addValue( -8.0, "Param8", "Object3");
        dataset.addValue(  7.0, "Param8", "Object4");
        dataset.addValue(-12.0, "Param9", "Object1");
        dataset.addValue( 19.0, "Param9", "Object2");
        dataset.addValue( 21.0, "Param9", "Object3");
        dataset.addValue( -2.0, "Param9", "Object4");
        return dataset;
    }
    private XYDataset getXYDataset(String login) {
        Configuration configuration=new Configuration();
        SessionFactory factory=configuration.configure().buildSessionFactory();
        DAO<Statistic, String> statisticStringDAO=new StatisticDAO(factory);
        login="do";
        Map<String, Statistic> map=((StatisticDAO) statisticStringDAO).getData(login);

        final TimeSeries series1 = new TimeSeries("Калории");
        final TimeSeries  series2 = new TimeSeries ("Жиры");
        final TimeSeries  series3 = new TimeSeries ("Белки");
        final TimeSeries  series4 = new TimeSeries ("Углеводы");/*
        final XYSeries series1 = new XYSeries("Калории");
        final XYSeries  series2 = new XYSeries ("Жиры");
        final XYSeries  series3 = new XYSeries ("Белки");
        final XYSeries  series4 = new XYSeries ("Углеводы");*/
        double i=1;
        for(Map.Entry<String, Statistic> entry: map.entrySet()){
            String date=entry.getKey();
            int day=Integer.parseInt(date.substring(8));
            int month=Integer.parseInt(date.substring(5,7));
            int year=Integer.parseInt(date.substring(0,4));
            Day day1=new Day(day, month, year);

            series1.add(new Day(day, month, year), entry.getValue().getCurrCal());
            series2.add(new Day(day, month, year), entry.getValue().getCurrFats());
            series3.add(new Day(day, month, year), entry.getValue().getCurrProteins());
            series4.add(new Day(day, month, year), entry.getValue().getCurrCarbohydrates());
            /*
            series1.add(i, entry.getValue().getCurrCal());
            series2.add(i, entry.getValue().getCurrFats());
            series3.add(i, entry.getValue().getCurrProteins());
            series4.add(i, entry.getValue().getCurrCarbohydrates());*/
            i++;
        }



       // final XYSeriesCollection dataset = new XYSeriesCollection();
        final TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
        dataset.addSeries(series4);

        return dataset;
    }
}
