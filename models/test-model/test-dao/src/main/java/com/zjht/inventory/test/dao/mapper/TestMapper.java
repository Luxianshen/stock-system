package com.zjht.inventory.test.dao.mapper;

import com.zjht.inventory.test.entity.Test;
import org.apache.ibatis.annotations.Param;

/**
 * Created by leaves chen<leaves615@gmail.com> on 16/3/30.
 *
 * @Author leaves chen<leaves615@gmail.com>
 */
public interface TestMapper {

    int save(@Param("test") Test test);

    Test findById(@Param("id") Long id);
}
