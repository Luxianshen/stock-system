package com.zjht.inventory.stock.dao.mapper;



import com.github.pagehelper.Page;
import com.zjht.inventory.stock.entity.Todo;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * Created by lujiasheng on 2016/10/18.
 */

public interface TodoMapper {

    Page<Todo> FindTodoList();//mapper定义抽象接口

    Page<Todo> FindHandleList();//mapper定义抽象接口

    void UpdateHandle(@Param("todo")Todo todo);//mapper定义抽象接口

}
