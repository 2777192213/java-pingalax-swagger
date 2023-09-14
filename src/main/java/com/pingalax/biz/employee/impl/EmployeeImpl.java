package com.pingalax.biz.employee.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.pingalax.biz.employee.EmployeeBiz;
import com.pingalax.biz.employee.bo.EmployeeBo;
import com.pingalax.biz.employee.bo.EmployeePageBo;
import com.pingalax.biz.loginlog.LoginLogBiz;
import com.pingalax.biz.loginlog.bo.LoginLogBo;
import com.pingalax.common.exceptions.ResultExceptionEnum;
import com.pingalax.common.exceptions.handler.BizException;
import com.pingalax.common.util.page.PageResult;
import com.pingalax.common.util.page.PageUtil;
import com.pingalax.common.util.result.ResultUtil;
import com.pingalax.dao.employee.EmployeeDao;
import com.pingalax.dao.employee.entity.EmployeeEntity;
import com.pingalax.ext.employee.dto.Employee;
import com.pingalax.ext.employee.dto.EmployeePageRequest;
import com.pingalax.ext.employee.dto.EmployeeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

/**
 * @author zhouxiaotao
 * @Description: 登录业务实现层实现接口
 * @date 2023-08-31 21:12
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeImpl implements EmployeeBiz {
    private final EmployeeDao employeeDao;

    /**
     * 登录相关的查询信息
     *
     * @param employeeRequest 请求用户名与密码实体
     * @return employee对象
     */
    @Override
    public EmployeeBo selectName(EmployeeRequest employeeRequest) {
        EmployeeBo employeeBo = new EmployeeBo();
        LambdaQueryWrapper<EmployeeEntity> queryWrapper = new LambdaQueryWrapper<>(EmployeeEntity.class);
        queryWrapper.eq(employeeRequest.getUsername() != null, EmployeeEntity::getUsername, employeeRequest.getUsername());
        EmployeeEntity employeeEntity = employeeDao.selectOne(queryWrapper);
        if (employeeEntity == null) {
            ResultUtil.throwResultException(ResultExceptionEnum.USER_NAME_NOT_EXIST);
        }
        BeanUtil.copyProperties(employeeEntity, employeeBo);
        return employeeBo;
    }

    @Override
    public PageResult<EmployeeBo> queryEmployeeByPage(EmployeePageBo employeePageBo) {
        LambdaQueryWrapper<EmployeeEntity> queryWrapper = new LambdaQueryWrapper<>(EmployeeEntity.class);
        queryWrapper.like(employeePageBo.getName() != null, EmployeeEntity::getName, employeePageBo.getName());
        IPage<EmployeeEntity> employeeEntityIPage = employeeDao.selectPage(PageUtil.createPage(employeePageBo), queryWrapper);
        return ResultUtil.convert(employeeEntityIPage, EmployeeBo.class);
    }

    @Override
    public EmployeeBo queryEmployeeById(Integer id) {
        LambdaQueryWrapper<EmployeeEntity> queryWrapper = new LambdaQueryWrapper<>(EmployeeEntity.class);
        queryWrapper.eq(id != null, EmployeeEntity::getId, id);
        EmployeeEntity employeeEntity = employeeDao.selectById(queryWrapper);
        EmployeeBo employeeBo = new EmployeeBo();
        BeanUtil.copyProperties(employeeEntity, employeeBo);
        return employeeBo;
    }

    @Override
    public Integer editEmployee(EmployeeBo employeeBo) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtil.copyProperties(employeeEntity, employeeBo);
        employeeDao.updateById(employeeEntity);
        return employeeBo.getStatus();
    }

}
