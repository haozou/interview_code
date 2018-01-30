package com.hao.interview.stockOutliers.stock;

import com.hao.interview.stockOutliers.list.LinkedList;
import com.hao.interview.stockOutliers.list.List;

import java.io.*;
import java.text.ParseException;

/**
 * Created by hzou on 10/25/17.
 */
public class StockRecordFileLoader implements StockRecordLoader {
    private String path;
    public  StockRecordFileLoader(String path) {
        this.path = path;
    }


    @Override
    public List<StockData> loadStockToLinkedList(String symbol) {
        File dir = new File(path);
        for (File file : dir.listFiles()) {
            if (file.isFile() && file.getName().equals(symbol + ".csv")) {
                return readFileToLinkedList(file);
            }
        }
        return null;

    }

    private List<StockData> readFileToLinkedList(File file) {
        List<StockData> linkedList = new LinkedList();

        try {
            InputStream in = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = null;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] record = line.split(",");

                linkedList.add(new StockData(
                        record[0],
                        Double.parseDouble(record[1]),
                        Double.parseDouble(record[2]),
                        Double.parseDouble(record[3]),
                        Double.parseDouble(record[4]),
                        Double.parseDouble(record[5]),
                        Integer.parseInt(record[6])
                ));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return linkedList;
    }
}
