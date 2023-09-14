package com.pingalax.basic.equipments;

import cn.hutool.core.bean.BeanUtil;
import com.pingalax.basic.equipments.dto.Equipment;
import com.pingalax.basic.equipments.dto.QueryEquipmentPageRequest;
import com.pingalax.biz.equipment.EquipmentBiz;
import com.pingalax.biz.equipment.bo.EquipmentBo;
import com.pingalax.biz.equipment.bo.QueryEquipmentPageBO;
import com.pingalax.common.util.baseresult.ResultData;
import com.pingalax.common.util.page.PageResult;
import com.pingalax.common.util.result.ResultUtil;
import com.pingalax.dao.equipment.entity.EquipmentEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zhouxiaotao
 * @Description: 设备管理
 * @date 2023-08-07 8:58
 */
@Api(value = "设备管理", tags = "设备管理")
@RestController
@RequestMapping("/equipments")
public class EquipmentController {
    @Autowired
    private EquipmentBiz equipmentBiz;

    @ApiOperation("增加设备")
    @PostMapping("/addEquipment")
    public ResultData<EquipmentEntity> addEquipment(@RequestBody @Valid Equipment equipment) {
        EquipmentBo equipmentBo = BeanUtil.copyProperties(equipment, EquipmentBo.class);
        ResultData<EquipmentEntity> entityResultData = equipmentBiz.addEquipment(equipmentBo);
        return entityResultData;
    }

    @ApiOperation("编辑设备")
    @PostMapping("/modifyEquipment")
    public ResultData<EquipmentEntity> modifyEquipment(@Valid Equipment equipment) {
        EquipmentBo equipmentBo = BeanUtil.copyProperties(equipment, EquipmentBo.class);
        EquipmentEntity equipmentEntity = BeanUtil.copyProperties(equipmentBo, EquipmentEntity.class);
        equipmentBiz.modifyEquipment(equipmentBo);
        return ResultUtil.createResultData(equipmentEntity);
    }

    @ApiOperation("删除设备")
    @ResponseBody
    @PostMapping("/deleteEquipment")
    public String deleteEquipment(@Valid String equipmentSncode) {
        String removeEquipment = equipmentBiz.removeEquipment(equipmentSncode);
        return removeEquipment;
    }

    @ApiOperation("通过ID查找设备")
    @ResponseBody
    @PostMapping("/selectEquipment")
    public ResultData<Equipment> selectEquipment(@RequestBody String snCode) {
        EquipmentBo equipmentBo = equipmentBiz.queryEquipment(snCode);
        Equipment equipment = BeanUtil.copyProperties(equipmentBo, Equipment.class);
        return ResultUtil.createResultData(equipment);
    }


    @ApiOperation("通过条件查询设备(分页)")
    @ResponseBody
    @PostMapping("/queryEquipmentByPage")
    public PageResult<Equipment> selectEquipmentByPage(@Valid @RequestBody QueryEquipmentPageRequest queryEquipmentPageRequest) {
        QueryEquipmentPageBO queryEquipmentPageBO = BeanUtil.copyProperties(queryEquipmentPageRequest, QueryEquipmentPageBO.class);
        PageResult<EquipmentBo> equipmentBoPageResult = equipmentBiz.queryEquipmentByPage(queryEquipmentPageBO);
        return ResultUtil.convert(equipmentBoPageResult, Equipment.class);
    }
}
