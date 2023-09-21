package com.pingalax.ext.setmeal;

import com.pingalax.biz.setmeal.SetmealBiz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SetmealController {
    private final SetmealBiz setmealBiz;
}
