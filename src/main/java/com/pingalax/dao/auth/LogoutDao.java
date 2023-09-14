package com.pingalax.dao.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingalax.dao.auth.entity.LogoutEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhouxiaotao
 * @Description: 退出Dao层
 * @date 2023-09-11 13:09
 */
@Mapper
public interface LogoutDao extends BaseMapper<LogoutEntity> {
}
