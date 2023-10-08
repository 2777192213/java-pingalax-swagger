package com.pingalax.dao.setmealdish;

import com.pingalax.dao.setmealdish.entity.SetmealDishEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhouxiaotao
 * @Description: TODO
 * @date 2023-09-22 9:34
 */
@Service
public class SetmealDishService {
    @Autowired
    private SetmealDishDao setmealDishDao;

    public void  batchInsertSetmealDishS(List<SetmealDishEntity> dishEntityList){
        setmealDishDao.batchInsert(dishEntityList);
    }
}
