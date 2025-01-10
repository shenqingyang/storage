package org.shenqy.userservice.entity.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "登录表单实体")
public class LoginFormDto {
    @Schema(description = "登陆账户", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "用户名不能为空")
    private String loginCount;
    @NotNull(message = "密码不能为空")
    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}