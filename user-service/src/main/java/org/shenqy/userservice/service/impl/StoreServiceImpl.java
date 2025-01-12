package org.shenqy.userservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.shenqy.userservice.entity.po.Store;
import org.shenqy.userservice.mapper.StoreMapper;
import org.shenqy.userservice.service.IStoreService;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements IStoreService {
}
