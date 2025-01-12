package org.shenqy.userservice.entity.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.shenqy.storagecommon.entity.PageQuery;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "分页查询员工表单")
public class StaffQuery extends PageQuery {
    @Schema(description = "公司编号")
    private Integer companyId;
    @Schema(description = "店面编号")
    private Integer storeId;
    @Schema(description = "仓库编号")
    private Integer WarehouseId;
}
