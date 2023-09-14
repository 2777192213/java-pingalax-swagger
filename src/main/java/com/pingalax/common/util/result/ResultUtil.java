package com.pingalax.common.util.result;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pingalax.common.enums.ResultCode;
import com.pingalax.common.exceptions.ArgumentException;
import com.pingalax.common.exceptions.ResultExceptionEnum;
import com.pingalax.common.util.baseresult.Result;
import com.pingalax.common.util.baseresult.ResultData;
import com.pingalax.common.util.page.PageInfo;
import com.pingalax.common.util.page.PageResult;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author zhouxiaotao
 * @Description: 返回结果
 * @date 2023-08-08 9:35
 */
public class ResultUtil {
    /**
     * 转换为分页返回结果
     *
     * @param page   分页数据
     * @param tClass 对象
     * @param <T>    对象类型
     * @param <E>    数据类型
     * @return 分页结果
     */
    public static <T, E> PageResult<T> convert(IPage<E> page, Class<T> tClass) {
        return ResultUtil.convert(page, tClass, null);
    }

    /**
     * 转换为分页返回结果
     *
     * @param page            分页数据
     * @param tClass          对象
     * @param convertFunction 转换方法
     * @param <T>             对象类型
     * @param <E>             数据类型
     * @return 分页结果
     */
    public static <T, E> PageResult<T> convert(IPage<E> page, Class<T> tClass, ResultConvertFunction<E, T> convertFunction) {
        PageResult<T> pageResult = new PageResult<>();
        // 创建返回数据
        List<T> data = new ArrayList<>();
        if (CollUtil.isNotEmpty(page.getRecords())) {
            for (E record : page.getRecords()) {
                T convertRecord = BeanUtil.copyProperties(record, tClass);
                if (convertFunction != null) {
                    convertFunction.convert(record, convertRecord);
                }
                data.add(convertRecord);
            }
        }
        pageResult.setData(data);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageIndex(page.getCurrent());
        pageInfo.setPageSize(page.getSize());
        pageInfo.setTotalPage(page.getPages());
        pageInfo.setTotalRecord(page.getTotal());
        pageResult.setPageInfo(pageInfo);
        pageResult.setCode(ResultCode.SUCCESS.getCode());
        pageResult.setDesc(ResultCode.SUCCESS.getDesc());
        return pageResult;
    }

    /**
     * 转换列表返回结果
     *
     * @param list   列表数据
     * @param tClass 对象
     * @param <T>    对象类型
     * @param <E>    数据类型
     * @return 列表结果
     */
    public static <T, E> List<T> convert(Collection<E> list, Class<T> tClass) {
        return ResultUtil.convert(list, tClass, null);
    }

    /**
     * 转换列表返回结果
     *
     * @param list            列表数据
     * @param tClass          对象
     * @param convertFunction 转换方法
     * @param <T>             对象类型
     * @param <E>             数据类型
     * @return 列表结果
     */
    public static <T, E> List<T> convert(Collection<E> list, Class<T> tClass, ResultConvertFunction<E, T> convertFunction) {
        List<T> data = new ArrayList<>();
        if (CollUtil.isEmpty(list)) {
            return data;
        }
        for (E record : list) {
            T convertRecord = BeanUtil.copyProperties(record, tClass);
            if (convertFunction != null) {
                convertFunction.convert(record, convertRecord);
            }
            data.add(convertRecord);
        }
        return data;
    }

    /**
     * 数据类型转换（分页）
     *
     * @param dataPage 数据
     * @param tClass   目标类型
     * @param <T>      类型
     * @return 转换后的数据
     */
    public static <T, E> PageResult<T> convert(PageResult<E> dataPage, Class<T> tClass) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setCode(dataPage.getCode());
        pageResult.setDesc(dataPage.getDesc());
        pageResult.setPageInfo(dataPage.getPageInfo());
        pageResult.setData(ResultUtil.convert(dataPage.getData(), tClass));
        return pageResult;
    }

    /**
     * 转换对象返回结果
     *
     * @param record 对象数据
     * @param tClass 对象
     * @param <T>    对象类型
     * @param <E>    数据类型
     * @return 列表结果
     */
    public static <T, E> T convertObject(E record, Class<T> tClass) {
        return ResultUtil.convertObject(record, tClass, null);
    }

    /**
     * 转换对象返回结果
     *
     * @param record          对象数据
     * @param tClass          对象
     * @param convertFunction 转换方法
     * @param <T>             对象类型
     * @param <E>             数据类型
     * @return 列表结果
     */
    public static <T, E> T convertObject(E record, Class<T> tClass, ResultConvertFunction<E, T> convertFunction) {
        if (record == null) {
            return null;
        }
        T convertRecord = BeanUtil.copyProperties(record, tClass);
        if (convertFunction != null) {
            convertFunction.convert(record, convertRecord);
        }
        return convertRecord;
    }

    /**
     * 创建包含参数的返回结果
     *
     * @param resultData 返回结果
     */
    public static <T> ResultData<T> createResultData(T resultData) {
        ResultData<T> result = new ResultData<>();
        result.setData(resultData);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setDesc(ResultCode.SUCCESS.getDesc());
        return result;
    }

    /**
     * 创建不包含参数的返回结果
     */
    public static Result createNoDataResult() {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setDesc(ResultCode.SUCCESS.getDesc());
        return result;
    }

    /**
     * 抛出结果异常
     *
     * @param resultExceptionEnum 结果异常枚举
     */
    public static void throwResultException(ResultExceptionEnum resultExceptionEnum) {
        throw new ArgumentException(resultExceptionEnum.getCode(), resultExceptionEnum.getDesc());
    }
}