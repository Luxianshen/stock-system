package com.zjht.inventory.extensions.webinterface.ui.entity;

/**
 * Created by Jason on 2016/10/20.
 */
public class QueryStockResponseEntity extends AbstractResponseEntity{
    /**
     * 数量
     */
    private String count;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public QueryStockResponseEntity(String responseCode, String prompt, String count) {
        super(responseCode, prompt);
        this.count = count;
    }

    public QueryStockResponseEntity(String responseCode) {
        super(responseCode);
    }

    public QueryStockResponseEntity(String responseCode, String count) {
        super(responseCode);
        this.count = count;
    }

    @Override
    public String toString() {
        return "QueryStockResponseEntity{" +
                "count='" + count + '\'' +
                ", " + super.toString() +
                '}';
    }
}
