package com.zjht.commons.utils;

import java.math.BigDecimal;

/**
 * 人民币单位转换工具类.
 *
 * Created by leaves chen<leaves615@gmail.com> on 16/1/19.
 *
 * @Author leaves chen<leaves615@gmail.com>
 */
public class CNYUnitConversionUtil {

    /**
     * 把 long 类型的分转换为 double 类型的元
     * @param value
     * @return
     */
    public static double toYuanDouble(Long value) {
        if(value == null) return 0d;
        BigDecimal _value = new BigDecimal(value);
        _value = _value.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
        return _value.doubleValue();
    }
}
