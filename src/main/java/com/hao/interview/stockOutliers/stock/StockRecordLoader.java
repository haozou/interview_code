package com.hao.interview.stockOutliers.stock;


import com.hao.interview.stockOutliers.list.List;

/**
 * Created by hzou on 10/25/17.
 */
public interface StockRecordLoader {
    /**
     * load the stock into linkedList
     * @param symbol
     * @return
     */
    List<StockData> loadStockToLinkedList(String symbol);
}
