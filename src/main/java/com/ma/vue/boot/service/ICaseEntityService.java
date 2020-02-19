package com.ma.vue.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ma.vue.boot.entity.CaseEntity;

import java.util.List;


public interface ICaseEntityService extends IService<CaseEntity> {
    List<CaseEntity> findAllCase();
    void reloadAllCase();
}
