package com.zjht.inventory.stock.service;


import com.zjht.inventory.stock.dao.TodoWriteDao;
import com.zjht.inventory.stock.entity.Todo;
import org.smarabbit.massy.annotation.ExportService;
import org.smarabbit.massy.annotation.ImportService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lujiasheng on 2016/10/19.
 */
@ExportService(serviceTypes = {TodoService.class})
public class TodoServiceImpl implements TodoService {

    @ImportService
    private TodoWriteDao todoWriteDao;


    @Override
    public void UpdateHandle(Todo todo) {
       todoWriteDao.UpdateHandle(todo);
    }//更新未处理事件状态的方法 实现方法
}
