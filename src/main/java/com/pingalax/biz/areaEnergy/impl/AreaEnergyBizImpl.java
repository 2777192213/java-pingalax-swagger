package com.pingalax.biz.areaEnergy.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import com.pingalax.biz.areaEnergy.AreaEnergyBiz;
import com.pingalax.biz.areaEnergy.bo.*;
import com.pingalax.common.exceptions.ResultExceptionEnum;
import com.pingalax.common.util.result.ResultUtil;
import com.pingalax.dao.areaEnergy.AreaEnergyDao;
import com.pingalax.dao.areaEnergy.entity.AreaEnergyEntity;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author zhouxiaotao
 * {@code @Description:} AreaEnergy实现层
 * {@code @date} 2023-08-17 14:48
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AreaEnergyBizImpl implements AreaEnergyBiz {
    private final AreaEnergyDao areaEnergyDao;

    @Value("${pingalax.energy.measure-calculate.pv-area}")
    private BigDecimal pvArea;

    @Value("${pingalax.energy.measure-calculate.pv-capacity}")
    private BigDecimal pvCapacity;

    @Value("${pingalax.energy.measure-calculate.pv-efficiency}")
    private BigDecimal pvEfficiency;

    @Value("${pingalax.energy.measure-calculate.ess-efficiency}")
    private BigDecimal essEfficiency;

    @Override
    public List<AreaEnergyBo> queryRegionalEnergyList() {
        ArrayList<AreaEnergyBo> areaEnergyBoList = new ArrayList<>();
        List<AreaEnergyEntity> areaEnergyEntityList = areaEnergyDao.selectList(null);
        if (ObjectUtils.isNotNull(areaEnergyEntityList)) {
            for (AreaEnergyEntity areaEnergyEntity : areaEnergyEntityList) {
                AreaEnergyBo areaEnergyBo = new AreaEnergyBo();
                BeanUtil.copyProperties(areaEnergyEntity, areaEnergyBo);
                areaEnergyBoList.add(areaEnergyBo);
            }
        }
        return areaEnergyBoList;
    }

    @Override
    public AreaEnergyResponseBo computeInvestmentIncome(AreaEnergyRequestBo areaEnergyRequestBo) {
        // 实例化返回结果类
        AreaEnergyResponseBo areaEnergyResponseBo = new AreaEnergyResponseBo();
        AreaEnergyEntity areaEnergy = areaEnergyDao.selectById(areaEnergyRequestBo.getId());
        // 校验ID是否存在
        if (areaEnergy == null) {
            ResultUtil.throwResultException(ResultExceptionEnum.FAILED_NOT_EXIST_AREA_ID);
        }
        //首年放电量firstYearDischarge
        BigDecimal firstYearDischarge;
        // 储能基数
        BigDecimal baseYearDischarge;
        // 每年充电成本
        BigDecimal yearChargingCost;
        //每年放电量收益
        BigDecimal yearDischarges;
        // 每年储能收益
        List<BigDecimal> yearDischargesList = new ArrayList<>();
        // 总收益
        BigDecimal totalIncome = new BigDecimal(0);
        // 每年收益
        BigDecimal yearIncome;
        // 投资成本
        BigDecimal investTotal;
        // 总收入
        BigDecimal yearsTotalIncome = new BigDecimal(0);
        //定义常量
        BigDecimal one = new BigDecimal(1);
        BigDecimal zeroPrintZeroTwo = new BigDecimal("0.02");
        BigDecimal zeroPrintZeroOneTwo = new BigDecimal("0.012");
        //彩钢瓦利用率
        BigDecimal cUseRatio = new BigDecimal("0.7");
        //水泥平房利用率
        BigDecimal sUseRatio = new BigDecimal("0.56");
        //定义每年发电量
        BigDecimal yearElectricity;
        //定义每年发电量收入列表
        List<BigDecimal> yearElectricityList = new ArrayList<>();
        // 光伏基数
        BigDecimal baseYearElectricity;
        // 装机容量
        BigDecimal installedCapacity;

        /**
         *  光伏测算
         */
        if (Objects.equals(areaEnergyRequestBo.getType(), AreaEnergyEnum.PHOTOVOLTAIC_MEASUREMENT.getType())) {
            // 校验是否为null
            getPhotovoltaicCheck(areaEnergyRequestBo);

            // 选择房屋类型
            BigDecimal useRation = areaEnergyRequestBo.getRoofType().equals(RoofTypeEnum.COLOR_STEEL_TILE_ROOF.getType())
                    ? cUseRatio : sUseRatio;

            // 装机容量（MW）
            installedCapacity = getInstalledCapacity(areaEnergyRequestBo, useRation);
            areaEnergyResponseBo.setInstalledCapacity(installedCapacity);

            // 选择最佳倾角
            BigDecimal dipAngle = areaEnergyRequestBo.getDipAngle().equals(RadiationTypeEnum.ZERO_TILING.getType())
                    ? areaEnergy.getHorizontalRadiation() : areaEnergy.getBestRadiation();
            // 光伏基数
            baseYearElectricity = getBaseYearElectricity(areaEnergyRequestBo, useRation, dipAngle);

            //平均用电电价 上网电价（元/kWh）
            getGridElectricityPriceAndAverageUsePrice(areaEnergyResponseBo, areaEnergy);

            //首年发电量
            getOneFirstYearElectricity(areaEnergyResponseBo, one, zeroPrintZeroTwo, installedCapacity, baseYearElectricity);

            //投资成本
            investTotal = installedCapacity.multiply(areaEnergyRequestBo.getPvUnitInvest()).multiply(new BigDecimal(1000000));
            areaEnergyResponseBo.setInvestTotal(investTotal);

            for (int i = 0; i < 25; i++) {
                yearElectricity = baseYearElectricity.multiply(one.subtract(zeroPrintZeroTwo)
                        .subtract(new BigDecimal(i).multiply(new BigDecimal("0.0055"))));
                yearIncome = yearElectricity.multiply(areaEnergy.getAverageUsePrice()).multiply(areaEnergyRequestBo.getSelfUseRatio())
                        .divide(new BigDecimal(100), 20, RoundingMode.HALF_UP).add(yearElectricity.multiply(areaEnergy.getGridElectricityPrice())
                                .multiply(one.subtract(areaEnergyRequestBo.getSelfUseRatio()
                                        .divide(new BigDecimal(100), 20, RoundingMode.HALF_UP))));
                yearElectricityList.add(yearIncome);
                totalIncome = totalIncome.add(yearIncome);

            }
            areaEnergyResponseBo.setAverageIncome(totalIncome.divide(new BigDecimal(25), 20, RoundingMode.HALF_UP));

            for (int i = 0; i < yearElectricityList.size(); i++) {
                yearsTotalIncome = yearsTotalIncome.add(yearElectricityList.get(i));
                if (yearsTotalIncome.compareTo(investTotal) >= 0) {
                    BigDecimal year = yearElectricityList.get(i).subtract(yearsTotalIncome.subtract(investTotal))
                            .divide(yearElectricityList.get(i), 20, RoundingMode.HALF_UP);
                    year = year.add(new BigDecimal(i));
                    areaEnergyResponseBo.setPaybackPeriod(year);
                    break;
                }
                getCalibrationPeriod(areaEnergyResponseBo, yearElectricityList, i);
            }

            getReturnRate(areaEnergyResponseBo, totalIncome, investTotal);
        }
        /**
         * 储能测算
         */
        else if (Objects.equals(areaEnergyRequestBo.getType(), AreaEnergyEnum.ENERGY_STORAGE_CALCULATION.getType())) {
            getEnergyStorageCheck(areaEnergyRequestBo);
            // 选择运行策略
            BigDecimal x = new BigDecimal(areaEnergyRequestBo.getWorkingStrategy().equals(ChargeTypeEnum.ONE_CHARGE_AND_ONE_RELEASE.getType())
                    ? 1 : 2);

            baseYearDischarge = getBaseYearDischarge(areaEnergyRequestBo).multiply(x);
            getPeakValleyDifferencePrice(areaEnergyResponseBo, areaEnergy);
            // 选择损耗比
            BigDecimal a = areaEnergyRequestBo.getWorkingStrategy().equals(ChargeTypeEnum.ONE_CHARGE_AND_ONE_RELEASE.getType())
                    ? zeroPrintZeroOneTwo : zeroPrintZeroTwo;
            // 首年放电量
            firstYearDischarge = baseYearDischarge.multiply(one.subtract(a));
            areaEnergyResponseBo.setFirstYearDischarge(firstYearDischarge);

            //通过策略选择使用年限
            int length = areaEnergyRequestBo.getWorkingStrategy().equals(ChargeTypeEnum.ONE_CHARGE_AND_ONE_RELEASE.getType())
                    ? 15 : 10;

            //投资成本（万元）investTotal
            investTotal = areaEnergyRequestBo.getEssCapacity().multiply(areaEnergyRequestBo.getEssUnitInvest())
                    .multiply(new BigDecimal(1000000));
            areaEnergyResponseBo.setInvestTotal(investTotal);

            for (int i = 0; i < length; i++) {
                BigDecimal k = one.subtract((new BigDecimal(i).add(one)).multiply(a));
                yearDischarges = baseYearDischarge.multiply(k).multiply(areaEnergy.getPeakElectricityPrice());


                yearChargingCost = areaEnergyRequestBo.getEssCapacity().multiply(new BigDecimal(1000))
                        .multiply(areaEnergy.getValleyElectricityPrice()).multiply(new BigDecimal(350))
                        .multiply(k).multiply(x);

                yearIncome = yearDischarges.subtract(yearChargingCost);

                yearDischargesList.add(yearIncome);

                totalIncome = totalIncome.add(yearIncome);
            }

            areaEnergyResponseBo.setAverageIncome(totalIncome.divide(new BigDecimal(yearDischargesList.size()), 20, RoundingMode.HALF_UP));

            //全投资静态回收期（年）paybackPeriod
            for (int i = 0; i < yearDischargesList.size(); i++) {
                yearsTotalIncome = yearsTotalIncome.add(yearDischargesList.get(i));
                if (yearsTotalIncome.compareTo(investTotal) >= 0) {
                    BigDecimal year = yearDischargesList.get(i).subtract(yearsTotalIncome.subtract(investTotal))
                            .divide(yearDischargesList.get(i), 20, RoundingMode.HALF_UP);
                    year = year.add(new BigDecimal(i));
                    areaEnergyResponseBo.setPaybackPeriod(year);
                    break;
                }
                getCalibrationPeriod(areaEnergyResponseBo, yearDischargesList, i);
            }

            //全投资收益率（%）returnRate
            getReturnRate(areaEnergyResponseBo, totalIncome, investTotal);
        }
        /**
         *  光伏+储能测算模式
         */
        else if (Objects.equals(areaEnergyRequestBo.getType(), AreaEnergyEnum.PHOTOVOLTAIC_AND_ENERGY_STORAGE_CALCULATION.getType())) {
            // 校验是否为null
            getPhotovoltaicCheck(areaEnergyRequestBo);

            getEnergyStorageCheck(areaEnergyRequestBo);

            // 选择房屋类型
            BigDecimal useRation = areaEnergyRequestBo.getRoofType().equals(RoofTypeEnum.COLOR_STEEL_TILE_ROOF.getType())
                    ? cUseRatio : sUseRatio;

            // 光伏装机容量（MW）
            installedCapacity = getInstalledCapacity(areaEnergyRequestBo, useRation);
            areaEnergyResponseBo.setInstalledCapacity(installedCapacity);

            // 选择最佳倾角
            BigDecimal dipAngle = areaEnergyRequestBo.getDipAngle().equals(RadiationTypeEnum.ZERO_TILING.getType())
                    ? areaEnergy.getHorizontalRadiation() : areaEnergy.getBestRadiation();

            // 光伏基数
            baseYearElectricity = getBaseYearElectricity(areaEnergyRequestBo, useRation, dipAngle);

            //平均用电电价 上网电价（元/kWh）
            getGridElectricityPriceAndAverageUsePrice(areaEnergyResponseBo, areaEnergy);

            //首年发电量
            getOneFirstYearElectricity(areaEnergyResponseBo, one, zeroPrintZeroTwo, installedCapacity, baseYearElectricity);

            // 选择运行策略
            BigDecimal x = new BigDecimal(areaEnergyRequestBo.getWorkingStrategy().equals(ChargeTypeEnum.ONE_CHARGE_AND_ONE_RELEASE.getType())
                    ? 1 : 2);

            //通过策略选择使用年限
            int length = areaEnergyRequestBo.getWorkingStrategy().equals(ChargeTypeEnum.ONE_CHARGE_AND_ONE_RELEASE.getType())
                    ? 15 : 10;

            baseYearDischarge = getBaseYearDischarge(areaEnergyRequestBo).multiply(x);
            getPeakValleyDifferencePrice(areaEnergyResponseBo, areaEnergy);

            // 选择损耗比
            BigDecimal a = areaEnergyRequestBo.getWorkingStrategy().equals(ChargeTypeEnum.ONE_CHARGE_AND_ONE_RELEASE.getType())
                    ? zeroPrintZeroOneTwo : zeroPrintZeroTwo;

            // 首年放电量
            firstYearDischarge = baseYearDischarge.multiply(one.subtract(a));
            areaEnergyResponseBo.setFirstYearDischarge(firstYearDischarge);

            //投资成本（万元）investTotal
            investTotal = installedCapacity.multiply(areaEnergyRequestBo.getPvUnitInvest())
                    .multiply(new BigDecimal(1000000)).add(areaEnergyRequestBo.getEssCapacity()
                            .multiply(areaEnergyRequestBo.getEssUnitInvest()).multiply(new BigDecimal(1000000)));
            areaEnergyResponseBo.setInvestTotal(investTotal);


            for (int i = 0; i < 25; i++) {
                yearElectricity = baseYearElectricity.multiply(one.subtract(zeroPrintZeroTwo)
                        .subtract(new BigDecimal(i).multiply(new BigDecimal("0.0055"))));
                yearIncome = yearElectricity.multiply(areaEnergy.getAverageUsePrice()).multiply(areaEnergyRequestBo.getSelfUseRatio())
                        .divide(new BigDecimal(100), 20, RoundingMode.HALF_UP).add(yearElectricity.multiply(areaEnergy.getGridElectricityPrice())
                                .multiply(one.subtract(areaEnergyRequestBo.getSelfUseRatio()
                                        .divide(new BigDecimal(100), 20, RoundingMode.HALF_UP))));
                yearElectricityList.add(yearIncome);
                totalIncome = totalIncome.add(yearIncome);
            }
            for (int i = 0; i < length; i++) {
                BigDecimal k = one.subtract((new BigDecimal(i).add(one)).multiply(a));
                yearDischarges = baseYearDischarge.multiply(k).multiply(areaEnergy.getPeakElectricityPrice());


                yearChargingCost = areaEnergyRequestBo.getEssCapacity().multiply(new BigDecimal(1000))
                        .multiply(areaEnergy.getValleyElectricityPrice()).multiply(new BigDecimal(350))
                        .multiply(k).multiply(x);
                yearIncome = yearDischarges.subtract(yearChargingCost);

                yearDischargesList.add(yearIncome);

                totalIncome = totalIncome.add(yearIncome);
            }
            areaEnergyResponseBo.setAverageIncome(totalIncome.divide(new BigDecimal(25), 20, RoundingMode.HALF_UP));

            for (int i = 0; i < yearElectricityList.size(); i++) {
                if (i < length) {
                    yearsTotalIncome = yearsTotalIncome.add(yearElectricityList.get(i)).add(yearDischargesList.get(i));
                    if (yearsTotalIncome.compareTo(investTotal) >= 0) {
                        BigDecimal year = yearElectricityList.get(i).add(yearDischargesList.get(i)).subtract(yearsTotalIncome.subtract(investTotal))
                                .divide(yearElectricityList.get(i).add(yearDischargesList.get(i)), 20, RoundingMode.HALF_UP);
                        year = year.add(new BigDecimal(i));
                        areaEnergyResponseBo.setPaybackPeriod(year);
                        break;
                    }
                    getCalibrationPeriod(areaEnergyResponseBo, yearElectricityList, i);
                } else {
                    yearsTotalIncome = yearsTotalIncome.add(yearElectricityList.get(i));
                    if (yearsTotalIncome.compareTo(investTotal) >= 0) {
                        BigDecimal year = yearElectricityList.get(i).subtract(yearsTotalIncome.subtract(investTotal))
                                .divide(yearElectricityList.get(i), 20, RoundingMode.HALF_UP);
                        year = year.add(new BigDecimal(i));
                        areaEnergyResponseBo.setPaybackPeriod(year);
                        break;
                    }
                    getCalibrationPeriod(areaEnergyResponseBo, yearElectricityList, i);
                }
            }
            //全投资收益率（%）returnRate
            getReturnRate(areaEnergyResponseBo, totalIncome, investTotal);

        }
        /**
         *  其他测算
         */
        else {
            ResultUtil.throwResultException(ResultExceptionEnum.FAILED_IS_NULL_TYPE);
        }
        return areaEnergyResponseBo;
    }

    /**
     * @param areaEnergyResponseBo 返回结果响应体
     * @param yearElectricityList  光伏发电列表
     * @param i                    index
     */
    private static void getCalibrationPeriod(AreaEnergyResponseBo areaEnergyResponseBo, List<BigDecimal> yearElectricityList, int i) {
        if (i >= yearElectricityList.size() - 1) {
            areaEnergyResponseBo.setPaybackPeriod(new BigDecimal(-1));
        }
    }

    /**
     * @param areaEnergyRequestBo 请求实体类
     * @param useRation           利用率
     * @return 装机容量
     */
    private BigDecimal getInstalledCapacity(AreaEnergyRequestBo areaEnergyRequestBo, BigDecimal useRation) {
        return areaEnergyRequestBo.getRoofArea().divide(pvArea, 20, RoundingMode.HALF_UP)
                .multiply(useRation).multiply(pvCapacity).divide(new BigDecimal(1000000), 20, RoundingMode.HALF_UP);
    }

    /**
     * @param areaEnergyRequestBo 请求实体类
     * @param useRation           利用率
     * @param dipAngle            最佳倾角
     * @return 光伏基数
     */
    private BigDecimal getBaseYearElectricity(AreaEnergyRequestBo areaEnergyRequestBo, BigDecimal useRation, BigDecimal dipAngle) {
        return dipAngle.multiply(areaEnergyRequestBo.getRoofArea())
                .multiply(useRation).multiply(pvEfficiency).multiply(new BigDecimal("0.8"));
    }

    /**
     * @param areaEnergyRequestBo 请求实体类
     * @return 储能基数
     */
    private BigDecimal getBaseYearDischarge(AreaEnergyRequestBo areaEnergyRequestBo) {
        return areaEnergyRequestBo.getEssCapacity().multiply(new BigDecimal(1000))
                .multiply(essEfficiency).multiply(new BigDecimal("0.9")).multiply(new BigDecimal(350));
    }

    /**
     * @param areaEnergyResponseBo 响应实体类
     * @param areaEnergy           查询返回值
     */
    private static void getPeakValleyDifferencePrice(AreaEnergyResponseBo areaEnergyResponseBo, AreaEnergyEntity areaEnergy) {
        //峰谷电价差（元/KWh）peakValleyDifferencePrice
        BigDecimal peakValleyDifferencePrice;
        peakValleyDifferencePrice = areaEnergy.getPeakElectricityPrice().subtract(areaEnergy.getValleyElectricityPrice());
        areaEnergyResponseBo.setPeakValleyDifferencePrice(peakValleyDifferencePrice);
    }

    /**
     * 首年发电量
     *
     * @param areaEnergyResponseBo 请求实体类
     * @param one                  常量
     * @param zeroPrintZeroTwo     常量
     * @param installedCapacity    装机容量
     * @param baseYearElectricity  基数
     */
    private static void getOneFirstYearElectricity(AreaEnergyResponseBo areaEnergyResponseBo, BigDecimal one, BigDecimal zeroPrintZeroTwo, BigDecimal installedCapacity, BigDecimal baseYearElectricity) {
        BigDecimal firstYearElectricity;
        firstYearElectricity = baseYearElectricity.multiply(one.subtract(zeroPrintZeroTwo));
        BigDecimal oneFirstYearElectricity;
        oneFirstYearElectricity = firstYearElectricity.divide(installedCapacity, 20, RoundingMode.HALF_UP).
                divide(new BigDecimal(1000000), 20, RoundingMode.HALF_UP);
        areaEnergyResponseBo.setOneFirstYearElectricity(oneFirstYearElectricity);
    }

    /**
     * @param areaEnergyResponseBo 响应实体类
     * @param areaEnergy           查询返回值
     */
    private static void getGridElectricityPriceAndAverageUsePrice(AreaEnergyResponseBo areaEnergyResponseBo, AreaEnergyEntity areaEnergy) {
        //上网电价（元/kWh）
        areaEnergyResponseBo.setGridElectricityPrice(areaEnergy.getGridElectricityPrice());
        //平均用电电价（元/kWh
        areaEnergyResponseBo.setAverageUsePrice(areaEnergy.getAverageUsePrice());
    }

    /**
     * 光伏校验类
     *
     * @param areaEnergyRequestBo 请求实体类
     */
    private static void getPhotovoltaicCheck(AreaEnergyRequestBo areaEnergyRequestBo) {
        if (areaEnergyRequestBo.getRoofType() == null) {
            ResultUtil.throwResultException(ResultExceptionEnum.FAILED_IS_NULL_ROOF_TYPE);
        }
        if (Objects.isNull(areaEnergyRequestBo.getDipAngle())) {
            ResultUtil.throwResultException(ResultExceptionEnum.FAILED_IS_NULL_RADIATION);
        }
        if (areaEnergyRequestBo.getRoofArea() == null) {
            ResultUtil.throwResultException(ResultExceptionEnum.FAILED_IS_NULL_ROOF_AREA);
        }
        if (areaEnergyRequestBo.getSelfUseRatio() == null) {
            ResultUtil.throwResultException(ResultExceptionEnum.FAILED_IS_NULL_SELF_USE_RATION);
        }
        if (areaEnergyRequestBo.getPvUnitInvest() == null) {
            ResultUtil.throwResultException(ResultExceptionEnum.FAILED_IS_NULL_PV_UNIT_INVEST);
        }
    }

    /**
     * 储能校验类
     *
     * @param areaEnergyRequestBo 请求实体类
     */
    private static void getEnergyStorageCheck(AreaEnergyRequestBo areaEnergyRequestBo) {
        if (areaEnergyRequestBo.getWorkingStrategy() == null) {
            ResultUtil.throwResultException(ResultExceptionEnum.FAILED_IS_NULL_WORKING_STRATEGY);
        }
        if (areaEnergyRequestBo.getEssCapacity() == null) {
            ResultUtil.throwResultException(ResultExceptionEnum.FAILED_IS_NULL_ESS_CAPACITY);
        }
        if (areaEnergyRequestBo.getEssUnitInvest() == null) {
            ResultUtil.throwResultException(ResultExceptionEnum.FAILED_IS_NULL_ESS_UNIT_INVEST);
        }
    }

    /**
     * 全投资收益
     *
     * @param areaEnergyResponseBo 测算结果响应类
     * @param totalIncome          总收益
     * @param investTotal          投入成本
     */
    private static void getReturnRate(AreaEnergyResponseBo areaEnergyResponseBo, BigDecimal totalIncome, BigDecimal investTotal) {
        BigDecimal returnRate = totalIncome.subtract(investTotal).divide(investTotal, 20, RoundingMode.HALF_UP);
        areaEnergyResponseBo.setReturnRate(returnRate);
    }
}