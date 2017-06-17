package com.zjht.inventory.stock.dao;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zjht.inventory.stock.dao.mapper.TodoMapper;
import com.zjht.inventory.stock.entity.Todo;
import org.smarabbit.massy.annotation.ExportService;

import java.util.List;

/**
 * Created by lujiasheng on 2016/10/18.
 */
@ExportService(serviceTypes = {TodoDao.class, TodoWriteDao.class})
public class TodoDaoImpl implements TodoWriteDao{

    private TodoMapper todoMapper;

    @Override
    public Page<Todo> FindTodoList(int index, int size) {
        PageHelper.startPage(index,size);
        return todoMapper.FindTodoList();
    }//查询待办事件列表

    @Override
    public Page<Todo> FindHandleList(int index, int size) {
        PageHelper.startPage(index,size);
        return todoMapper.FindHandleList();
    }//查询处理完成事件列表

    @Override
    public void UpdateHandle(Todo todo) {
        todoMapper.UpdateHandle(todo);
    }////更新未处理事件状态的方法 实现方法

    public void setTodoMapper(TodoMapper todoMapper) {
        this.todoMapper = todoMapper;
    }




}
