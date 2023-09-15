package com.pingalax.dao.loginlog.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhouxiaotao
 * @Description: 日志实体类
 * @date 2023-09-08 16:16
 */
@Data
@TableName("gu_login_log")
public class LoginLogEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 用户第三方登录信息ID
     */
    private Integer userOauthId;
    /**
     * 用户登录名
     */
    private String username;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 第三方 uid 、openid等
     */
    private String oauthId;
    /**
     * 授权方式
     */
    private Integer authType;
    /**
     * 登陆结果
     */
    private Integer authResult;
    /**
     * 登陆结果描述
     */
    private String authResultDesc;
    /**
     * 登陆结果返回数
     */
    private Integer result;
    /**
     * 返回结果描述
     */
    private String resultDesc;

    /**
     * 登录IP
     */
    private String clientIp;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime updateTime;
}
