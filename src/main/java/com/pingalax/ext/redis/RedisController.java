package com.pingalax.ext.redis;

import com.pingalax.common.util.baseresult.ResultData;
import com.pingalax.common.util.result.ResultUtil;
import com.pingalax.dao.employee.EmployeeDao;
import com.pingalax.dao.employee.entity.EmployeeEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouxiaotao
 * @Description: redis接口
 * @date 2023-10-08 9:00
 */
@Api(value = "redis相关接口", tags = "redis相关接口")
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Resource
    private RedisTemplate<String, EmployeeEntity> redisTemplate;

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * 增加Employee
     *
     * @param EmployeeEntity Employee
     * @return Employee
     */
    @ApiOperation("Add Employee")
    @PostMapping("/addEmployee")
    public ResultData<EmployeeEntity> addUser(@RequestBody EmployeeEntity EmployeeEntity) {
        redisTemplate.opsForValue().set(String.valueOf(EmployeeEntity.getId()), EmployeeEntity);
        return ResultUtil.createResultData(redisTemplate.opsForValue().get(String.valueOf(EmployeeEntity.getId())));
    }

    /**
     * 通过ID查找Employee
     *
     * @param userId Employee id.
     * @return EmployeeBo
     */
    @ApiOperation("Query Employee")
    @PostMapping("/queryEmployee/{userId}")
    public ResultData<EmployeeEntity> queryUser(@PathVariable Integer userId) {
        EmployeeEntity entity = redisTemplate.opsForValue().get(String.valueOf(userId));
        if (entity == null) {
            EmployeeEntity employeeEntity = employeeDao.selectById(userId);
            redisTemplate.opsForValue().set(String.valueOf(userId), employeeEntity, 1, TimeUnit.HOURS);
            return ResultUtil.createResultData(employeeEntity);
        }
        return ResultUtil.createResultData(entity);
    }
}
