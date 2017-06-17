package com.zjht.inventory.test.manager.ui;


import com.zjht.inventory.test.dao.TestDao;
import com.zjht.inventory.test.entity.Test;
import com.zjht.inventory.test.service.TestService;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.smarabbit.massy.annotation.ImportService;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by leaves chen<leaves615@gmail.com> on 16/3/30.
 *
 * @Author leaves chen<leaves615@gmail.com>
 */
@Controller
public class TestController {
    @ImportService
    private TestService testService;
    @ImportService
    private TestDao testDao;

    @RequestMapping("/home")
    public String test(ShiroHttpServletRequest request) {
        Test test = testDao.findById(1L);
        //testService.createTest(new Test(1l,"test"));
        //test = testDao.findById(1L);
        System.out.println(test);
    	System.out.println("进入testController.............");
       /* CasLoginUserInfo casLoginUser = (CasLoginUserInfo)request.getSession().getAttribute(CasShiroFilter.CURRENT_LOGIN_USERINFO);
        System.out.println(casLoginUser);*/
        return "test/test";
    }
}
