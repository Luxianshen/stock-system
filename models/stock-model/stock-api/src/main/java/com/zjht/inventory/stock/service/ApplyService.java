package com.zjht.inventory.stock.service;

import com.zjht.inventory.stock.entity.Apply;

/**
 * Created by Jason on 2016/10/18.
 */
public interface ApplyService {
    Apply createApply(Apply apply);

    void unlock(Long applyId, String userName, String handleRemark);

    void nullify(Long applyId, Long applyType, String username);

    void convertApply(Apply apply, Long convertType, String remark);

    void changeStatus(Long applyId);
}
