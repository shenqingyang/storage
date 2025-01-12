package org.shenqy.userservice.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

@Data
public class Store {
    @TableId(type = IdType.AUTO)
    private Integer storeId;
    private String chineseName;
    private String englishName;
    private Integer cityId;
    private Integer directorId;
    private Integer companyId;
    @TableLogic //逻辑删除
    private Integer deleted;
}