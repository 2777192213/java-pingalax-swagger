package com.pingalax.dao.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingalax.dao.auth.entity.LoginEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhouxiaotao
 * @Description: 管理员Dao接口层
 * @date 2023-08-31 21:03
 */
@Mapper
public interface LoginDao extends BaseMapper<LoginEntity> {
}
