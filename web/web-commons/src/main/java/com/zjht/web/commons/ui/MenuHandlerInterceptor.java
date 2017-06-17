/**
 * 
 */
package com.zjht.web.commons.ui;

import com.zjht.web.commons.directive.PaginateDirective;
import com.zjht.web.commons.template.PageSidebarRootMenu;
import com.zjht.web.commons.template.UserMenuGroup;
import org.smarabbit.massy.annotation.ImportService;
import org.smarabbit.massy.model.useradmin.AnonymousUser;
import org.smarabbit.massy.model.useradmin.User;
import org.smarabbit.massy.web.menu.Menu;
import org.smarabbit.massy.web.menu.MenuService;
import org.smarabbit.massy.web.menu.Visitor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author huangkaihui
 *
 */
public class MenuHandlerInterceptor implements HandlerInterceptor {
    private final PaginateDirective paginateDirective = new PaginateDirective();

	@ImportService
	private MenuService menuService;
	/**
	 * 
	 */
	public MenuHandlerInterceptor() {
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("page",paginateDirective);
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

        if(modelAndView !=null) {
            User current = new AnonymousUser();
            Visitor visitor = new Visitor(current, request);

            Menu menu = menuService.findByName(UserMenuGroup.NAME);
            if (menu != null) {
                modelAndView.addObject("userMenu", menu.toHtmlText(visitor, 4, null));
            } else {
                modelAndView.addObject("userMenu", "");
            }

            menu = menuService.findByName(PageSidebarRootMenu.NAME);
            if (menu != null) {
                modelAndView.addObject("pageSidebarMenu", menu.toHtmlText(visitor, 3, null));
            } else modelAndView.addObject("pageSidebarMenu", "");
        }
    }

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
