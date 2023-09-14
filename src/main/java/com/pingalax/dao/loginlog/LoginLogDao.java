package com.pingalax.dao.loginlog;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pingalax.dao.loginlog.entity.LoginLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhouxiaotao
 * @Description: TODO
 * @date 2023-09-08 16:15
 */
@Mapper
public interface LoginLogDao extends BaseMapper<LoginLogEntity> {
}
