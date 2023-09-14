package com.pingalax.common.auth;

import lombok.Data;

/**
 * @author zhouxiaotao
 * @Description: 用户会话
 * @date 2023-09-11 13:37
 */
@Data
public class UserSession {
    private Integer userId;

    private String sessionId;
}