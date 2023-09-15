package com.pingalax.common.auth;

import com.pingalax.common.exceptions.ResultExceptionEnum;
import com.pingalax.common.exceptions.handler.ServiceException;

import java.util.Optional;

/**
 * @author zhouxiaotao
 * @Description: 认证session实体
 * @date 2023-09-11 13:40
 */
public class Session {
    /**
     * 用户会话key
     */
    public static final String USER_SESSION_KEY = "userSessionKey";
    /**
     * 当前线程用户会话
     */
    private static final ThreadLocal<UserSession> userSessionThreadLocal = new ThreadLocal<>();

    /**
     * 设置当前线程用户会话
     *
     * @param userSession 用户会话
     */
    public static void set(UserSession userSession) {
        userSessionThreadLocal.set(userSession);
    }

    /**
     * 获取当前线程用户会话
     *
     * @return 用户会话
     */
    public static UserSession get() {
        UserSession userSession = userSessionThreadLocal.get();
        if (userSession == null || userSession.getUserId() == null) {
            // 用户不存在
            throw new ServiceException(ResultExceptionEnum.USER_UNAVAILABLE.getCode(), ResultExceptionEnum.USER_UNAVAILABLE.getCode());
        }
        return userSession;
    }

    /**
     * 获取当前线程用户ID
     *
     * @return 用户ID
     */
    public static Integer getUserId() {
        return Optional.ofNullable(userSessionThreadLocal.get()).map(UserSession::getUserId).orElse(null);
    }

    /**
     * 移除当前线程用户会话
     * 防止内存泄漏
     */
    public static void remove() {
        userSessionThreadLocal.remove();
    }
}
