package com.pingalax.dao.employee;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingalax.dao.employee.entity.EmployeeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhouxiaotao
 * @Description: 管理员Dao接口层
 * @date 2023-08-31 21:03
 */
@Repository
@Mapper
public interface EmployeeDao extends BaseMapper<EmployeeEntity> {
}
