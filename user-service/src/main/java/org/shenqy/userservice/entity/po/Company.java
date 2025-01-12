package org.shenqy.userservice.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

@Data
public class Company {
    @TableId(type = IdType.AUTO)
    private Integer companyId;
    private String chineseName;
    private String englishName;
    private Integer parentCompanyId;
    private Integer provinceId;
    private String location;
    @TableLogic //逻辑删除
    private Integer deleted;
}