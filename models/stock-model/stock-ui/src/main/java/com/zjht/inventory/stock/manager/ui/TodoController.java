package com.zjht.inventory.stock.manager.ui;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zjht.inventory.stock.dao.TodoDao;
import com.zjht.inventory.stock.entity.Todo;
import com.zjht.inventory.stock.service.TodoService;
import org.smarabbit.massy.annotation.ImportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * Created by lujiasheng on 2016/10/19.
 */
@Controller
@RequestMapping("/todo")
public class TodoController {
    @ImportService
    private TodoService todoService;
    @ImportService
    private TodoDao todoDao;

    @RequestMapping(value = "listtodo.html", method = RequestMethod.GET)
    public String listTodo(Model model,@RequestParam(value = "index",defaultValue = "1") int index) {

        Page<Todo> todoList=todoDao.FindTodoList(index,20);
        //获得所有待办事件的事件

        model.addAttribute("todoList", PageInfo.from(todoList));
        //转发到页面

        return "todo/listtodo";
    }

    @RequestMapping(value = "listhandle.html", method = RequestMethod.GET)
    public String listHandle(Model model,@RequestParam(value = "index",defaultValue = "1") int index) {

        Page<Todo> todoList=todoDao.FindHandleList(index,20);
        //获取所有已经事件处理记录

        model.addAttribute("todoList", PageInfo.from(todoList));
        //转发到页面

        return "todo/listhandle";
    }

    @RequestMapping(value = "deal.html", method = RequestMethod.POST)
    public String deal(Model model,@RequestParam(value = "index",defaultValue = "1") int index,Todo todo) {

        todo.setHANDLE_TIME(new Date());
        //获取当前时间当做处理时间
        todoService.UpdateHandle(todo);
        //业务处理，传递处理人，处理时间，修改处理状态，添加修改评论

        Page<Todo> todoList=todoDao.FindTodoList(index,20);
        //重新获取所有待办事件的事件

        model.addAttribute("todoList", PageInfo.from(todoList));
        //转发到页面

        return "todo/listtodo";
    }

}
