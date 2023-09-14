package com.pingalax.common.exceptions.handler;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.pingalax.common.enums.ResultCode;
import com.pingalax.common.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * {@code @Description:} 全局异常统一捕获处理
 * {@code @Author:} zhouxiaotao
 * {@code @Date:} 2023-02-28
 */
@ControllerAdvice
@Slf4j
@Order(9999)
public class GlobleExceptionHandler extends RestExceptionHandler {
    @Autowired
    private HttpServletResponse response;

    /**
     * 业务相关的异常
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    @Override
    public Result handleException(BizException e) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.error("出错了：", e);
        Result result = super.handleException(e);
        return process(result);
    }

    /**
     * 服务相关的异常
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    @Override
    public Result handleException(ServiceException e) {
        response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        log.error("出错了：", e);
        Result result = super.handleException(e);
        return process(result);
    }

    /**
     * 全部相关的异常
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @Override
    public Result handleException(Exception e) {
        response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        // 特殊异常全局处理
        if (e instanceof DuplicateKeyException) {
            // 数据库操作异常
            DuplicateKeyException duplicateKeyException = (DuplicateKeyException) e;
            for (ResultExceptionEnum resultExceptionEnum : ResultExceptionEnum.values()) {
                if (StrUtil.isBlank(resultExceptionEnum.getIdx())) {
                    continue;
                }
                if (Objects.requireNonNull(duplicateKeyException.getMessage()).contains(resultExceptionEnum.getIdx())) {
                    return this.handleException(new BizException(resultExceptionEnum.getCode(), resultExceptionEnum.getDesc()));
                }
            }
        }

        log.error("出错了：", e);
        Result result = super.handleException(e);
        return process(result);
    }

    /**
     * 参数相关的异常
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public Result handleHttpMessageNotReadableException(Exception e) {
        response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        log.error("出错了：", e);
        ArgumentException ex = new ArgumentException("400", "参数转换错误，请检查输入参数是否符合接口文档要求");
        Result result = super.handleException(ex);
        return process(result);
    }

    /**
     * 参数校验相关的异常
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class, ArgumentException.class})
    @ResponseBody
    public Result handleValidationExceptions(Exception e) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        FieldError fieldError;
        if (e instanceof ArgumentException) {
            Result result = super.handleException((ArgumentException) e);
            return process(result);
        }

        if (e instanceof BindException) {
            fieldError = ((BindException) e).getBindingResult().getFieldError();
        } else {
            fieldError = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError();
        }

        assert fieldError != null;
        ArgumentException ex = new ArgumentException(ResultCode.FAILED_PARAM_ERROR.getCode(), String.format("参数:%s校验错误：%s", fieldError.getField(), fieldError.getDefaultMessage()));
        Result result = super.handleException(ex);
        return process(result);
    }

    /**
     * 异常信息打印日志
     *
     * @param result 异常
     * @return 结果
     */
    private Result process(Result result) {
        log.warn("msg-Response:[{}]", JSONUtil.toJsonStr(result));
        return result;
    }
}