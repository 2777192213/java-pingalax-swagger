package com.pingalax.ext.setmeal;

import com.pingalax.biz.setmeal.SetmealBiz;
import com.pingalax.biz.setmeal.bo.SetmealRequestBo;
import com.pingalax.common.util.result.ResultUtil;
import com.pingalax.ext.setmeal.dto.SetmealRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouxiaotao
 * @Description: 套餐接口
 * @date 2023-09-20 13:33
 */
@Api(value = "套餐相关", tags = "套餐相关")
@RestController
@RequestMapping("/pingalax/setmeal")
@EnableTransactionManagement
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SetmealController {
    private final SetmealBiz setmealBiz;

    @ApiOperation("增加套餐")
    @PostMapping("/addSetmeal")
    public Object addSetmeal(@RequestBody SetmealRequest setmealRequest) {
        SetmealRequestBo setmealRequestBo = new SetmealRequestBo();
        BeanUtils.copyProperties(setmealRequest, setmealRequestBo);
        if (setmealBiz.addSetmeal(setmealRequestBo)) {
            return ResultUtil.createNoDataResult();
        }
        return ResultUtil.createNoDataFailedResult();
    }

    @ApiOperation("通过分页条件查询套餐")
    @PostMapping("/querySetmealBypage")
    public Object querySetmealBypage(@RequestBody SetmealRequest setmealRequest) {
        SetmealRequestBo setmealRequestBo = new SetmealRequestBo();
        BeanUtils.copyProperties(setmealRequest, setmealRequestBo);
        if (setmealBiz.addSetmeal(setmealRequestBo)) {
            return ResultUtil.createNoDataResult();
        }
        return ResultUtil.createNoDataFailedResult();
    }

    @ApiOperation("删除套餐")
    @PostMapping("/removeSetmealBypage")
    public Object removeSetmealBypage(@RequestBody SetmealRequest setmealRequest) {
        SetmealRequestBo setmealRequestBo = new SetmealRequestBo();
        BeanUtils.copyProperties(setmealRequest, setmealRequestBo);
        if (setmealBiz.addSetmeal(setmealRequestBo)) {
            return ResultUtil.createNoDataResult();
        }
        return ResultUtil.createNoDataFailedResult();
    }

}
