package com.hao.interview.stockOutliers;

import com.hao.interview.stockOutliers.list.List;
import com.hao.interview.stockOutliers.stock.StockData;
import com.hao.interview.stockOutliers.stock.StockRecordFileLoader;
import com.hao.interview.stockOutliers.stock.StockRecordLoader;
import com.hao.interview.stockOutliers.stock.StockDataOutlierDetector;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by hzou on 10/24/17.
 */
public class Driver {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Stock Symbol: ");
        String symbol = scanner.next();
        System.out.print("Starting date: ");
        Date startDate = StockData.simpleDateFormat.parse(scanner.next());

        //step one: initialize stockRecordLoader and load stock into linkedList
        StockRecordLoader stockRecordLoader = new StockRecordFileLoader("./");
        List<StockData> linkedList = stockRecordLoader.loadStockToLinkedList(symbol);
        if (linkedList == null) throw new RuntimeException(String.format("No stock Found Named %s", symbol));

        //step two: initialize stockDataOutlierDetector and calculateOutliers.
        StockDataOutlierDetector stockDataOutlierDetector = new StockDataOutlierDetector();
        List<StockData> outliers = stockDataOutlierDetector.process(startDate, linkedList);

        // step three: output the outliers
        outputOutliers(startDate, symbol, outliers);

    }

    public static void outputOutliers(Date startDate, String symbol, List<StockData> outliers) {
        System.out.println(String.format("Outliers for %s %s:", symbol,  StockData.simpleDateFormat.format(startDate)));
        for (StockData stockData : outliers) {
            System.out.println(
                    String.format(
                            "%s: %f",
                            StockData.simpleDateFormat.format(stockData.getDate()),
                            stockData.getClose()));
        }
    }


}
