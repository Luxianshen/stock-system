package com.zjht.commons.utils;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by leaves chen<leaves615@gmail.com> on 15/10/8.
 *
 * @Author leaves chen<leaves615@gmail.com>
 */
public class RedirectHelper {

    /**
     * SPRING MVC 重定向方法，可为重定义添加传递参数，
     * @param message
     * @param url
     * @param redirectAttributes
     * @return
     */
    public static String redirectWithMessage(String message, String url, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("alert", true);
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:" + url;
    }
}
