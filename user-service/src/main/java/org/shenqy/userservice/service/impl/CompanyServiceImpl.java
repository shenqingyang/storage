package org.shenqy.userservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.shenqy.userservice.entity.po.Company;
import org.shenqy.userservice.mapper.CompanyMapper;
import org.shenqy.userservice.service.ICompanyService;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {
}
