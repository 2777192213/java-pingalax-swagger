package com.pingalax.ext.dish;

import cn.hutool.core.bean.BeanUtil;
import com.pingalax.biz.dish.DishBiz;
import com.pingalax.biz.dish.bo.DishBo;
import com.pingalax.biz.dish.bo.DishPageRequestBo;
import com.pingalax.biz.dish.bo.DishPageResponseBo;
import com.pingalax.biz.dish.bo.DishRequestBo;
import com.pingalax.common.util.baseresult.ResultData;
import com.pingalax.common.util.page.PageResult;
import com.pingalax.common.util.result.ResultUtil;
import com.pingalax.ext.dish.dto.Dish;
import com.pingalax.ext.dish.dto.DishPageRequest;
import com.pingalax.ext.dish.dto.DishPageResponse;
import com.pingalax.ext.dish.dto.DishRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhouxiaotao
 * @Description: 菜品接口
 * @date 2023-09-20 13:32
 */
@Api(value = "菜品相关", tags = "菜品相关")
@RequestMapping("/pingalax/dish")
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DishController {
    private final DishBiz dishBiz;

    @ApiOperation("添加菜品")
    @PostMapping("/addDish")
    public Object addDish(@RequestBody DishRequest dishRequest) {
        DishRequestBo dishRequestBo = new DishRequestBo();
        BeanUtil.copyProperties(dishRequest, dishRequestBo);
        if (dishBiz.addDish(dishRequestBo)) {
            return ResultUtil.createNoDataResult();
        }
        return ResultUtil.createNoDataFailedResult();
    }

    @ApiOperation("通过条件分页查找菜品")
    @PostMapping("/queryDishByPageInfo")
    public PageResult<DishPageResponse> queryDishByPageInfo(@RequestBody DishPageRequest dishPageRequest) {
        DishPageRequestBo dishPageRequestBo = new DishPageRequestBo();
        BeanUtil.copyProperties(dishPageRequest, dishPageRequestBo);
        PageResult<DishPageResponseBo> dishPageResponseBoPageResult = dishBiz.queryDishByPage(dishPageRequestBo);
        return ResultUtil.convert(dishPageResponseBoPageResult, DishPageResponse.class);
    }

    @ApiOperation("通过ID查找菜品")
    @PostMapping("/queryDishByID")
    public ResultData<DishRequest> queryDishByID(@RequestBody Long id) {
        DishRequest dishRequest = new DishRequest();
        DishRequestBo dishRequestBo = dishBiz.queryDishById(id);
        BeanUtils.copyProperties(dishRequestBo, dishRequest);
        return ResultUtil.createResultData(dishRequest);
    }

    @ApiOperation("编辑菜品")
    @PostMapping("/editDish")
    public Object editDish(@RequestBody DishRequest dishRequest) {
        DishRequestBo dishRequestBo = new DishRequestBo();
        BeanUtil.copyProperties(dishRequest, dishRequestBo);
        dishBiz.editDish(dishRequestBo);
        if (dishBiz.addDish(dishRequestBo)) {
            return ResultUtil.createNoDataResult();
        }
        return ResultUtil.createNoDataFailedResult();
    }

    @ApiOperation("通过分类ID查询菜品")
    @PostMapping("/queryDishByCategoryId")
    public ResultData<List<Dish>> queryDishByCategoryId(@RequestBody Long categoryId) {
        List<DishBo> dishBos = dishBiz.queryDishByCategoryId(categoryId);
        return ResultUtil.createResultData(ResultUtil.convert(dishBos, Dish.class));
    }


}
