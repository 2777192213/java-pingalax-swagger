package com.pingalax.biz.setmeal;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pingalax.biz.setmeal.bo.SetmealBo;
import com.pingalax.biz.setmeal.bo.SetmealRequestBo;
import com.pingalax.ext.setmeal.dto.Setmeal;

/**
 * @author zhouxiaotao
 * @Description: 套餐接口
 * @date 2023-09-20 14:20
 */
//public interface SetmealBiz  extends IService<SetmealBo>{
public interface SetmealBiz {
    /**
     * 新增菜品
     *
     * @param setmealRequestBo 套餐
     * @return int
     */
    boolean addSetmeal(SetmealRequestBo setmealRequestBo);

    /**
     * 删除菜品
     *
     * @param SetmealBo 套餐
     * @return int
     */
    Integer removeSetmeal(SetmealBo SetmealBo);
}
