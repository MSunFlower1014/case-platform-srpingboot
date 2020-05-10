package com.ma.vue.boot.controller;

import com.ma.vue.boot.aop.OperationLog;
import com.ma.vue.boot.entity.DepartmentEntity;
import com.ma.vue.boot.mapper.CaseMapper;
import com.ma.vue.boot.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/depart")
public class DepartmentController {
    @Autowired
    private DepartmentMapper departmentMapper;

    @RequestMapping("getAllDepart")
    @OperationLog("获取所有科室")
    public List<DepartmentEntity> getAllDepart(){
        return departmentMapper.selectPermissionByPId("0");
    }
}
