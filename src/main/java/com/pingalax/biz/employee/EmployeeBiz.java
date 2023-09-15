package com.pingalax.biz.employee;

import com.pingalax.biz.employee.bo.EmployeeBo;
import com.pingalax.biz.employee.bo.EmployeePageBo;
import com.pingalax.common.util.baseresult.ResultData;
import com.pingalax.common.util.page.PageResult;
import com.pingalax.ext.employee.dto.Employee;
import com.pingalax.ext.employee.dto.EmployeeRequest;

/**
 * @author zhouxiaotao
 * @Description: 登录业务实现层接口
 * @date 2023-08-31 21:11
 */
public interface EmployeeBiz {

    /**
     * 登录相关的查询信息
     *
     * @param employeeRequest 请求用户名与密码实体
     * @return employee对象
     */
    EmployeeBo selectName(EmployeeRequest employeeRequest);

    /**
     * 分页查找Employee
     *
     * @param employeePageBo 分页实体类
     * @return PageResult对象
     */
    PageResult<EmployeeBo> queryEmployeeByPage(EmployeePageBo employeePageBo);

    /**
     * 通过Id查询Employee对象
     *
     * @return EmployeeBo
     */
    EmployeeBo queryEmployeeById(Integer Id);

    /**
     * 编辑员工信息
     * @param EmployeeBo 员工实体
     * @return 员工ID
     */
    Integer editEmployee(EmployeeBo EmployeeBo);

    /**
     * 增加员工
     * @param EmployeeBo 员工实体
     * @return 员工ID
     */
    Integer addEmployee(EmployeeBo EmployeeBo);


}
