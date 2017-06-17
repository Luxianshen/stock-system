package com.zjht.inventory.stock.dao.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * Created by lujiasheng on 2016/10/18.
 */

public interface GeneratorMapper {

	Long next(@Param("seq") String seq);

	Long nextGeneral();
}
