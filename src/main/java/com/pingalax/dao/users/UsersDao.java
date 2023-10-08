package com.pingalax.dao.users;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingalax.dao.users.entity.UsersEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhouxiaotao
 * @Description: 短信登录相关接口
 * @date 2023-09-22 14:58
 */

@Mapper
public interface UsersDao extends BaseMapper<UsersEntity> {
}
