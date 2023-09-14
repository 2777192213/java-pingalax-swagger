package com.pingalax.ext.auth;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.pingalax.biz.employee.EmployeeBiz;
import com.pingalax.biz.employee.bo.EmployeeBo;
import com.pingalax.biz.loginlog.LoginLogBiz;
import com.pingalax.biz.loginlog.bo.LoginLogBo;
import com.pingalax.common.auth.AuthResult;
import com.pingalax.common.auth.Session;
import com.pingalax.common.auth.UserSession;
import com.pingalax.common.exceptions.ResultExceptionEnum;
import com.pingalax.common.util.baseresult.ResultData;
import com.pingalax.common.util.result.ResultUtil;
import com.pingalax.ext.employee.dto.Employee;
import com.pingalax.ext.employee.dto.EmployeeRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * @author zhouxiaotao
 * @Description: 登录接口
 * @date 2023-08-31 20:32
 */
@Api(value = "认证相关", tags = "认证相关")
@RestController
@Slf4j
@RequestMapping("/pingalax/ext/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginController {
    @Resource
    private EmployeeBiz employeeBiz;
    /**
     * Could not autowire. No beans of 'EmployeeBiz' type found.
     * 将@Autowired改为@Resource
     * 两个注解的区别是一个是@Autowired是Spring，@Resource是J2EE的
     * 使用@Resource能减少Spring耦合度
     * @AutoWried按bytype自动注入，而@Resource默认按byName自动注入。
     * @Resource的查询注入顺序是，去Bean中查找Name，如果查不到就去查Class，其次再从属性去查找，如果我们定义的类中有相同的Name可能会报错，因为查询到了多个。
     */

    private final LoginLogBiz loginLogBiz;
    private final HttpServletRequest request;

    @ApiOperation("用户密码登录")
    @PostMapping("/userByPasswordLogin")
    public ResultData<Employee> Login(@RequestBody @Valid EmployeeRequest employeeRequest) {
        String password = employeeRequest.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        Employee employee = new Employee();
        EmployeeBo employeeBoResultData = employeeBiz.selectName(employeeRequest);
        if (!password.equals(employeeBoResultData.getPassword())) {
            LoginLogBo loginLogBo = new LoginLogBo();
            loginLogBo.setResultDesc(ResultExceptionEnum.PASSWORD_NOT_EXIST.getDesc());
            updateLoginLog(employeeBoResultData, loginLogBo);
            ResultUtil.throwResultException(ResultExceptionEnum.PASSWORD_NOT_EXIST);
        }
        if (employeeBoResultData.getStatus() == 0) {
            LoginLogBo loginLogBo = new LoginLogBo();
            loginLogBo.setResultDesc(ResultExceptionEnum.USER_UNAVAILABLE.getDesc());
            updateLoginLog(employeeBoResultData, loginLogBo);
            ResultUtil.throwResultException(ResultExceptionEnum.USER_UNAVAILABLE);
        }
        LoginLogBo loginLogBo = new LoginLogBo();
        loginLogBo.setResultDesc(ResultExceptionEnum.LOGIN_SUCCESS.getDesc());
        updateLoginLog(employeeBoResultData, loginLogBo);
        BeanUtil.copyProperties(employeeBoResultData, employee);
        AuthResult authResult = new AuthResult();
        authResult.setSessionId(employeeBoResultData.getIdNumber());
        authResult.setUserId(employeeBoResultData.getId());
        authResult.setOauthId(null);
        try {
            this.AuthCheck(authResult);
        } catch (Exception e) {
            ResultUtil.throwResultException(ResultExceptionEnum.USER_UNAVAILABLE);
        }
        return ResultUtil.createResultData(employee);

    }

    @ApiOperation("退出")
    @PostMapping("/logout")
    public String Logout(Integer id) {

        return null;
    }

    /**
     * 修改日志类的数据
     *
     * @param employeeBoResultData 请求参数
     * @param loginLogBo           登录日志实体类
     */
    private void updateLoginLog(EmployeeBo employeeBoResultData, LoginLogBo loginLogBo) {
        loginLogBo.setPhone(employeeBoResultData.getPhone());
        loginLogBo.setUserId(employeeBoResultData.getId());
        loginLogBo.setUsername(employeeBoResultData.getUsername());

        loginLogBo.setCreateTime(LocalDateTime.now());
        loginLogBo.setUpdateTime(LocalDateTime.now());
        loginLogBo.setClientIp(this.getIpFromRequest(request));
        log.info("{}", this.getIpFromRequest(request));
        loginLogBo.setAuthType(employeeBoResultData.getStatus());
        loginLogBiz.addLoginLog(loginLogBo);
    }

    /**
     * 获取登录IP
     *
     * @param httpServletRequest Http请求内容
     * @return IP
     */
    private String getIpFromRequest(HttpServletRequest httpServletRequest) {
        String ip = null;
        String ipAddress = httpServletRequest.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = httpServletRequest.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = httpServletRequest.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = httpServletRequest.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = httpServletRequest.getHeader("X-Real-IP");
        }
        if (ipAddress != null && ipAddress.length() != 0) {
            ip = ipAddress.split(",")[0];
        }
        // 还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ip = httpServletRequest.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 登录校验
     *
     * @return 返回校验结果
     */
    private void AuthCheck(AuthResult authResult) {
        //登录成功，http会话中保存登录信息
        HttpSession httpSession = request.getSession();
        log.debug("用户登录成功，httpSessionId：{}，authResult：{}", httpSession.getId(), JSONUtil.toJsonStr(authResult));
        UserSession userSession = new UserSession();
        userSession.setUserId(authResult.getUserId());
        userSession.setSessionId(authResult.getSessionId());
        log.info("{}",authResult.getSessionId());
        httpSession.setAttribute(Session.USER_SESSION_KEY, userSession);
    }

}
