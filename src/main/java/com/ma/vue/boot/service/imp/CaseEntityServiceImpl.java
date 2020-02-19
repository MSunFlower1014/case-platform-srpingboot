package com.ma.vue.boot.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ma.vue.boot.entity.CaseEntity;
import com.ma.vue.boot.mapper.CaseMapper;
import com.ma.vue.boot.service.ICaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@CacheConfig(cacheNames = "caseEntity")
@Service("caseEntityService")
public class CaseEntityServiceImpl extends ServiceImpl<CaseMapper, CaseEntity> implements ICaseEntityService {

    @Autowired
    private CaseMapper mapper;

    @Cacheable
    @Override
    public List<CaseEntity> findAllCase() {
        return mapper.selectList(new QueryWrapper<CaseEntity>());
    }

    @CacheEvict
    @Override
    public void reloadAllCase() {
    }
}
