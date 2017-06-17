package com.zjht.web.commons.menu;

import com.zjht.web.commons.template.PageSidebarRootMenu;
import com.zjht.web.commons.template.UserMenuGroup;
import org.apache.commons.collections.functors.ExceptionClosure;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.smarabbit.massy.annotation.ImportService;
import org.smarabbit.massy.web.menu.DefaultMenuService;
import org.smarabbit.massy.web.menu.Menu;
import org.smarabbit.massy.web.menu.SimpleMenu;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by leaves chen<leaves615@gmail.com> on 16/3/31.
 *
 * @Author leaves chen<leaves615@gmail.com>
 */
public class DataBaseMenuService extends DefaultMenuService {
    private static Log logger = LogFactory.getLog(DataBaseMenuService.class);

    @ImportService(alias = "commons-datasource")
    private DataSource dataSource;

    @Override
    protected void init() {
        super.init();
        //初始化用户菜单,和左侧菜单组
        addMenu(new UserMenuGroup());
        addMenu(new PageSidebarRootMenu());
        try {
            initFormDatabase();
        } catch (Exception e) {
            logger.error("init menu failed", e);
        }
    }

    private void initFormDatabase()
            throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException,
                   NoSuchMethodException, InvocationTargetException {
        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM menu");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                instanceMenu(resultSet);
            }
        } catch (SQLException| ClassNotFoundException| IllegalAccessException| InstantiationException|
                NoSuchMethodException| InvocationTargetException e) {
            throw e;
        } finally {
            if (connection != null && !connection.isClosed()) connection.close();
        }
    }

    private void instanceMenu(ResultSet resultSet)
            throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException,
                   NoSuchMethodException, InvocationTargetException {
        String className = resultSet.getString("menuType");
        Class _class = ClassUtils.getClass(className);
        Object menu =  _class.newInstance();
        MethodUtils.invokeMethod(menu, "setId", resultSet.getString("id"));
        MethodUtils.invokeMethod(menu,"setId",resultSet.getString("id"));
        MethodUtils.invokeMethod(menu,"setIcon",resultSet.getString("icon"));
        MethodUtils.invokeMethod(menu,"setName",resultSet.getString("name"));
        MethodUtils.invokeMethod(menu,"setLabel",resultSet.getString("label"));
        MethodUtils.invokeMethod(menu,"setDivided",resultSet.getBoolean("divided"));
        MethodUtils.invokeMethod(menu,"setHref",resultSet.getString("href"));
        MethodUtils.invokeMethod(menu,"setSuperiorName",resultSet.getString("superiorName"));
        MethodUtils.invokeMethod(menu,"setOrded",resultSet.getInt("ordered"));
        addMenu((Menu) menu);
    }
}
