package com.pingalax.biz.setmeal.impl;

import com.pingalax.biz.setmeal.SetmealBiz;
import com.pingalax.biz.setmeal.bo.SetmealBo;
import com.pingalax.dao.setmeal.SetmealDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhouxiaotao
 * @Description: 套餐业务层实现类
 * @date 2023-09-20 14:21
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SetmealBizImpl implements SetmealBiz {
    private final SetmealDao setmealDao;

    @Override
    public Integer addSetmeal(SetmealBo SetmealBo) {
        return null;
    }

    @Override
    public Integer removeSetmeal(SetmealBo SetmealBo) {
        return null;
    }
}
