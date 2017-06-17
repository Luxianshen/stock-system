package com.zjht.commons.database.dao;

import java.io.Serializable;

/**
 *
 * DAO模型,DAO基础类, 数据库操作基本方法, 如save, update, delete
 * Created by leaves chen<leaves615@gmail.com> on 16/3/30.
 *
 * @Author leaves chen<leaves615@gmail.com>
 */
public abstract class BasicDao<T> {

    /**
     * 抽象的保存方法
     * @param entity
     * @return
     */
    public abstract int save(T entity);

    /**
     * 抽象的更新方法
     * @param entity
     * @return
     */
    public abstract int update(T entity);

    /**
     * 抽象的删除方法
     * @param primaryKey
     * @return
     */
    public abstract int delete(Serializable primaryKey);
}
