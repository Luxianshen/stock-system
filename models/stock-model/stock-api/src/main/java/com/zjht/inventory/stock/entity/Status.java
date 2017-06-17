package com.zjht.inventory.stock.entity;

/**
 * Created by Jason on 2016/10/26.
 */
public interface Status {
     public static final long STOCK_OUT_PRODUCE = 1;//生产出库
     public static final long STOCK_OUT_ALLOCATION = 2;//调拨出库
     public static final long STOCK_OUT_REPAIR = 3;//返修出库
     public static final long STOCK_OUT_EXCHANGE = 4;//换机出库
     public static final long STOCK_OUT_BORROW = 5;//借用出库
     public static final long STOCK_LOCK = 8;//锁定
     public static final long STOCK_NORMAL = 9;//正常
     public static final long STOCK_IN_CHECK = 14;//盘点入库
     public static final long STOCK_IN_PURCHASE = 15;//采购入库
     public static final long STOCK_IN_PRODUCE = 16;//生产入库
     public static final long STOCK_IN_ALLOCATION = 17;//调拨入库
     public static final long STOCK_IN_REPAIR = 18;//返修入库
     public static final long STOCK_IN_EXCHANGE = 19;//换机入库
     public static final long STOCK_IN_BORROW = 20;//借用入库
}
