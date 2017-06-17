package com.zjht.inventory.stock.dao;

import com.zjht.inventory.stock.entity.Todo;

/**
 * Created by lujiasheng on 2016/10/18.
 */
public interface TodoWriteDao extends TodoDao{


    void UpdateHandle(Todo todo);
    //更新未处理事件状态的方法

}
