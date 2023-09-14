package com.pingalax.biz.loginlog;

import com.pingalax.biz.loginlog.bo.LoginLogBo;

/**
 * @author zhouxiaotao
 * @Description: 登录日志实体类
 * @date 2023-09-08 16:20
 */
public interface LoginLogBiz {
    Integer addLoginLog(LoginLogBo loginLogBo);
}
