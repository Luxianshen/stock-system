package com.zjht.inventory.stock.dao;



import com.github.pagehelper.Page;
import com.zjht.inventory.stock.entity.Todo;

import java.util.List;

/**
 * Created by lujiasheng on 2016/10/18.
 */
public interface TodoDao {

    Page<Todo> FindTodoList(int index, int size);//查询待办事件列表

    Page<Todo> FindHandleList(int index, int size);//查询处理完成事件列表
}
