package com.ma.vue.boot.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ma.vue.boot.entity.CaseEntity;
import com.ma.vue.boot.mapper.CaseMapper;
import com.ma.vue.boot.service.ICaseEntityService;
import org.springframework.stereotype.Service;

@Service("caseEntityService")
public class CaseEntityServiceImpl extends ServiceImpl<CaseMapper, CaseEntity> implements ICaseEntityService {
}
