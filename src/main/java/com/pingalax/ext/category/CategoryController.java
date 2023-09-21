package com.pingalax.ext.category;

import com.pingalax.biz.category.CategoryBiz;
import com.pingalax.biz.category.bo.CategoryBo;
import com.pingalax.biz.category.bo.CategoryPageBo;
import com.pingalax.common.util.beancopy.HutoolCopier;
import com.pingalax.common.util.page.PageResult;
import com.pingalax.common.util.result.ResultUtil;
import com.pingalax.ext.category.dto.Category;
import com.pingalax.ext.category.dto.CategoryPageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author zhouxiaotao
 * @Description: 分类接口
 * @date 2023-09-18 11:00
 */
@RestController
@Api(value = "分类相关接口", tags = "分类相关接口")
@RequestMapping("/pingalax/category")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryController {
    private final CategoryBiz categoryBiz;

    /**
     * 新增分类
     */
    @ApiOperation(value = "新增分类")
    @PostMapping("/addCategory")
    public Object addCategory(@RequestBody Category category) {
        CategoryBo categoryBo = new CategoryBo();
        BeanUtils.copyProperties(category, categoryBo);
        if (categoryBiz.addCategory(categoryBo)) {
            return ResultUtil.createNoDataResult();
        }
        return ResultUtil.createNoDataFailedResult();
    }

    /**
     * 删除分类
     */
    @ApiOperation(value = "根据ID删除分类")
    @PostMapping("/removeCategory")
    public Object removeCategory(@RequestBody @NotBlank(message = "分类参数不能为空") Long id) {
        if (categoryBiz.removeCategory(id)) {
            return ResultUtil.createNoDataResult();
        }
        return ResultUtil.createNoDataFailedResult();
    }

    //@ApiOperation(value = "分类分页查询")
    //@PostMapping("/queryCategoryByPage")
    //public PageResult<Category> queryCategoryByPage(@RequestBody CategoryPageRequest categoryPageRequest) throws Exception {
    //    PageResult<CategoryBo> pageResult = categoryBiz.queryCategoryByPage(HutoolCopier.copy(categoryPageRequest, CategoryPageBo.class));
    //    return ResultUtil.convert(pageResult, Category.class);
    //}

    /**
     * 分页查询分类
     *
     * @param categoryPageRequest 请求参数
     * @return 分页结果
     * @throws Exception e
     */
    @ApiOperation(value = "分类分页查询")
    @PostMapping("/queryCategoryByPage")
    public PageResult<Category> queryCategoryByPage(@RequestBody CategoryPageRequest categoryPageRequest) throws Exception {
        PageResult<CategoryBo> pageResult = categoryBiz.queryCategoryByPage(HutoolCopier.copy(categoryPageRequest, CategoryPageBo.class));
        return ResultUtil.convert(pageResult, Category.class);
    }

    /**
     * 通过条件查询分类
     *
     * @param type 分类
     * @return list
     */
    @ApiOperation(value = "通过条件查询分类")
    @PostMapping("/queryCategoryList")
    public List<Category> queryCategoryList(@RequestBody Integer type) {
        List<CategoryBo> categoryBos = categoryBiz.queryCategoryList(type);
        return ResultUtil.convert(categoryBos, Category.class);
    }
}
