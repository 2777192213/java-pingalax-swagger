package com.pingalax.common.auth;

import com.pingalax.common.util.baseresult.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhouxiaotao
 * @Description: 认证结果类
 * @date 2023-09-11 13:29
 */
@Data
@NoArgsConstructor
@ApiModel("第三方登录认证返回信息")
public class AuthResult extends Result {
    @ApiModelProperty("sessionId")
    private String sessionId;

    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("oauthId")
    private Integer oauthId;

}
