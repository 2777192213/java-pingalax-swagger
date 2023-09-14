package com.pingalax.basic.information;

import cn.hutool.core.bean.BeanUtil;
import com.pingalax.basic.information.dto.CollectData;
import com.pingalax.biz.information.CollectDataBiz;
import com.pingalax.dao.information.entity.CollectDataEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhouxiaotao
 * @Description: 数据管理接口层
 * @date 2023-08-10 14:01
 */
@Slf4j
@RestController
@Api(value = "数据管理", tags = "数据管理")
@RequestMapping("/pingalax/basic/collect")
public class CollectDataController {
    @Autowired
    private CollectDataBiz collectDataBiz;

    @ApiOperation("上传数据")
    @PostMapping("/addCollect")
    public String addCollect(@RequestBody @Valid CollectData collectData) {
        CollectDataEntity collectDataEntity = BeanUtil.copyProperties(collectData, CollectDataEntity.class);
        collectDataEntity.setCreateTime(LocalDateTime.now());
        String saveData = collectDataBiz.saveData(collectDataEntity);
        return saveData;
    }

    @ApiOperation("查找全部数据")
    @PostMapping("/queryCollect")
    public List<CollectDataEntity> queryCollect() {
        List<CollectDataEntity> collectDataEntities = collectDataBiz.queryDataAll();
        return collectDataEntities;
    }


}
