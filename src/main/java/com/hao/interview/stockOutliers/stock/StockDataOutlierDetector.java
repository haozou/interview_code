package com.hao.interview.stockOutliers.stock;

import com.hao.interview.stockOutliers.list.LinkedList;
import com.hao.interview.stockOutliers.list.List;
import com.hao.interview.stockOutliers.machinLearningUtils.MachineLearningUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by hzou on 10/25/17.
 */
public class StockDataOutlierDetector {

    /**
     * process the data to get outliers
     * @param startDate
     * @param stockList
     * @return
     */
    public List<StockData> process(Date startDate, List<StockData> stockList) {
        List<StockData> oneYearStock = getOneYearData(startDate, stockList);

        return calculateOutliers(oneYearStock);
    }

    /**
     * get outliers using liner regression
     * @param oneYearStock
     * @return
     */
    public List<StockData> calculateOutliers(List<StockData> oneYearStock) {

        //oneYearStock.remove(oneYearStock.size() - 1);

        double[] xs = new double[oneYearStock.size()];
        double[] ys = new double[oneYearStock.size()];
        int i = 0;
        for (StockData stockData : oneYearStock) {
            xs[i] = i;
            ys[i] = stockData.getClose();
            i++;
        }
        double[] result = MachineLearningUtils.linerRegression(xs, ys);
        double a = result[0];
        double b = result[1];
        System.out.println(a + "\t" + b);
        double variance = MachineLearningUtils.getVariance(ys);

        List<StockData> outliers = new LinkedList<>();

        i = 0;
        for (StockData stockData : oneYearStock) {
            double yPredict = a + b * xs[i];
            System.out.println(xs[i] + "\t" + ys[i] + "\t" + yPredict);
            if (Math.pow(ys[i] - yPredict, 2) > variance) {
                outliers.add(stockData);
            }
            i++;
        }
        return outliers;
    }


    /**
     * get full year's data
     * @param startDate
     * @param stockList
     * @return
     */
    public List<StockData> getOneYearData(Date startDate, List<StockData> stockList) {
        Date endDate = addOneYear(startDate);
        List<StockData> oneYearStock = new LinkedList<>();
        for (StockData stockData : stockList) {
            if (stockData.getDate().before(startDate)) continue;
            if (stockData.getDate().before(endDate)) {
                oneYearStock.add(stockData);
            } else {
                break;
            }
        }
        if (oneYearStock.size() < 2) {
            System.out.println("There is insufficient data for this period.");
            System.exit(1);
        }
        return oneYearStock;
    }

    private Date addOneYear(Date startDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.YEAR, 1);
        return calendar.getTime();
    }

    private long diffOfTwoDays(Date start, Date end) {
        long diff = Math.abs(end.getTime() - start.getTime());
        long diffDays = diff / (24 * 60 * 60 * 1000);
        return diffDays;
    }
}
