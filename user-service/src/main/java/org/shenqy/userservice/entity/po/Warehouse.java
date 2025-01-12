package org.shenqy.userservice.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

@Data
public class Warehouse {
    @TableId(type = IdType.AUTO)
    private Integer warehouseId;
    private String chineseName;
    private String englishName;
    private String zipCode;
    private String location;
    private Integer storeId;
    private Integer directorId;
    private Integer parentWarehouseId;
    private Integer companyId;
    @TableLogic //逻辑删除
    private Integer deleted;
}