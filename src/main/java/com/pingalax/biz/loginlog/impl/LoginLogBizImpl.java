package com.pingalax.biz.loginlog.impl;

import cn.hutool.core.bean.BeanUtil;
import com.pingalax.biz.loginlog.LoginLogBiz;
import com.pingalax.biz.loginlog.bo.LoginLogBo;
import com.pingalax.dao.loginlog.LoginLogDao;
import com.pingalax.dao.loginlog.entity.LoginLogEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhouxiaotao
 * @Description: 登录日志实现类
 * @date 2023-09-08 16:22
 */
@Component
@Transactional
@Slf4j
public class LoginLogBizImpl implements LoginLogBiz {
    @Autowired
    private LoginLogDao loginLogDao;

    @Override
    public Integer addLoginLog(LoginLogBo loginLogBo) {
        LoginLogEntity loginLogEntity = new LoginLogEntity();
        BeanUtil.copyProperties(loginLogBo, loginLogEntity);
        loginLogDao.insert(loginLogEntity);
        return loginLogEntity.getId();
    }
}
