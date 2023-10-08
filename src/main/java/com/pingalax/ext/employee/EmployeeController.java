package com.pingalax.ext.employee;

import cn.hutool.core.bean.BeanUtil;
import com.pingalax.biz.boot.countdown.CountDownEventHandler;
import com.pingalax.biz.employee.EmployeeBiz;
import com.pingalax.biz.employee.bo.EmployeeBo;
import com.pingalax.biz.employee.bo.EmployeePageBo;
import com.pingalax.common.factory.factorymethod.Coffee;
import com.pingalax.common.factory.factorymethod.factory.CoffeeFactory;
import com.pingalax.common.factory.factorymethod.Cappuccino;
import com.pingalax.common.util.baseresult.ResultData;
import com.pingalax.common.util.page.PageResult;
import com.pingalax.common.util.result.ResultUtil;
import com.pingalax.ext.employee.dto.Employee;
import com.pingalax.ext.employee.dto.EmployeePageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhouxiaotao
 * @Description: 退出接口
 * @date 2023-09-08 16:04
 */
@Api(value = "用户相关", tags = "用户相关")
@Slf4j
@RestController
@RequestMapping("/pingalax/ext/employee")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {
    @Resource
    private EmployeeBiz employeeBiz;

    private final CountDownEventHandler countDownEventHandler;

    @ApiOperation("通过条件查找员工（分页查找）")
    @PostMapping("/queryEmployeeByPage")
    public PageResult<Employee> queryEmployeeByPage(@RequestBody EmployeePageRequest employeePageRequest) {
        EmployeePageBo employeePageBo = new EmployeePageBo();
        BeanUtil.copyProperties(employeePageRequest, employeePageBo);
        PageResult<EmployeeBo> employeeBoPageResult = employeeBiz.queryEmployeeByPage(employeePageBo);
        return ResultUtil.convert(employeeBoPageResult, Employee.class);
    }

    @ApiOperation("通过ID查找员工")
    @PostMapping("/queryEmployeeById")
    public ResultData<Employee> queryEmployeeById(@RequestBody Integer Id) {
        EmployeeBo employeeBo = employeeBiz.queryEmployeeById(Id);
        Employee employee = new Employee();
        BeanUtil.copyProperties(employeeBo, employee);
        return ResultUtil.createResultData(employee);
    }

    @ApiOperation("编辑员工信息")
    @PostMapping("/editEmployeeById")
    public ResultData<Integer> editEmployeeById(@RequestBody Employee employee) {
        EmployeeBo employeeBo = new EmployeeBo();
        BeanUtil.copyProperties(employee, employeeBo);
        Integer integer = employeeBiz.editEmployee(employeeBo);
        return ResultUtil.createResultData(integer);
    }

    @ApiOperation("增加员工信息")
    @PostMapping("/addEmployeeById")
    public ResultData<Integer> addEmployeeById(@RequestBody Employee employee) {
        EmployeeBo employeeBo = new EmployeeBo();
        BeanUtil.copyProperties(employee, employeeBo);
        long id = Thread.currentThread().getId();
        log.info("线程id为：{}",id);
        Integer integer = employeeBiz.addEmployee(employeeBo);
        return ResultUtil.createResultData(integer);
    }

    @ApiOperation("测试工厂方法")
    @PostMapping("/CEmployeeById")
    public String cEmployeeById() {

        countDownEventHandler.process();
        try {
            Coffee Cappuccino = CoffeeFactory.create(Cappuccino.class);
            log.info("{}", Cappuccino.BuyMethod());
            log.info("{}", Cappuccino.Mode());

        } catch (Exception e) {
            log.info("{}", e.toString());
        }
        return null;
    }




}

