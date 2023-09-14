package com.pingalax.biz.loginlog.bo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhouxiaotao
 * @Description: 日志实体类
 * @date 2023-09-08 16:21
 */
@Data
public class LoginLogBo {
    private Integer id;
    private Integer userId;
    private Integer oauthId;
    private String username;
    private String phone;
    private String clientIp;
    private String clientType;
    private String clientUa;
    private String clientReferer;
    private Integer authType;
    private Integer result;
    private String resultDesc;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}