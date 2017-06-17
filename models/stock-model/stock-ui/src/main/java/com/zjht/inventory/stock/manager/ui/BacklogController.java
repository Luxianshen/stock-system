package com.zjht.inventory.stock.manager.ui;

import org.smarabbit.massy.annotation.ImportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jason on 2016/10/12.
 */
@Controller
public class BacklogController {


    @RequestMapping("/apply/test")
    public String test() {
        return "apply/test";
    }
}
