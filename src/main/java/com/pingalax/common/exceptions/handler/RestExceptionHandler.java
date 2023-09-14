package com.pingalax.common.exceptions.handler;

import com.pingalax.common.enums.ResultCode;
import com.pingalax.common.exceptions.ArgumentException;

/**
 * @author zhouxiaotao
 * @Description: TODO
 * @date 2023-09-06 22:38
 */
public class RestExceptionHandler {
    public RestExceptionHandler() {
    }

    public Result handleException(BizException e) {
        Result result = new Result();
        result.setCode(e.getCode());
        result.setDesc(e.getDesc());
        return result;
    }

    public Result handleException(ServiceException e) {
        Result result = new Result();
        result.setCode(e.getCode());
        result.setDesc(e.getDesc());
        return result;
    }

    public Result handleException(ArgumentException e) {
        Result result = new Result();
        result.setCode(e.getCode());
        result.setDesc(e.getDesc());
        return result;
    }

    public Result handleException(Exception e) {
        Result result = new Result();
        result.setCode(ResultCode.FAILED_SERVER_ERROR.getCode());
        result.setDesc("服务器端错误");
        return result;
    }
}
