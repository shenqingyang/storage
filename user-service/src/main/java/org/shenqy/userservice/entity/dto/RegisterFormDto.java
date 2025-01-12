package org.shenqy.userservice.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "注册表单实体")
public class RegisterFormDto {
    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "用户名不能为空")
    private String userName;
    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "密码不能为空")
    private String password;
    @Schema(description = "登陆账户", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "登陆账户不能为空")
    private String loginAccount;
    @Schema(description = "性别", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "性别不能为空")
    private String gender;
    @Schema(description = "电话")
    private String phone;
    @Schema(description = "头像")
    private String profile;
    @Schema(description = "仓库编号")
    private Integer warehouseId;
    @Schema(description = "商店编号")
    private Integer storeId;
    @Schema(description = "公司编号")
    private Integer companyId;
}
