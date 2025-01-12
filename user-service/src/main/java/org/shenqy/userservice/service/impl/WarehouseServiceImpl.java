package org.shenqy.userservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.shenqy.userservice.entity.po.Warehouse;
import org.shenqy.userservice.mapper.WarehouseMapper;
import org.shenqy.userservice.service.IWarehouseService;
import org.springframework.stereotype.Service;

@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements IWarehouseService {
}
