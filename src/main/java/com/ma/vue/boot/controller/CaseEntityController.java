package com.ma.vue.boot.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ma.vue.boot.aop.OperationLog;
import com.ma.vue.boot.entity.CaseCondition;
import com.ma.vue.boot.entity.CaseEntity;
import com.ma.vue.boot.mapper.CaseMapper;
import com.ma.vue.boot.service.ICaseEntityService;
import com.ma.vue.boot.shiro.realm.UserRealm;
import com.ma.vue.boot.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/case")
public class CaseEntityController {
    private static  final Logger logger = LoggerFactory.getLogger(CaseEntityController.class);

    @Autowired
    private CaseMapper mapper;

    @Autowired
    private ICaseEntityService caseEntityService;

    /**
     * 新建医院档案信息
     * @param caseEntityString
     * @return
     */
    @RequestMapping("caseEntityAdd")
    @OperationLog("新建医院档案信息")
    public boolean caseEntityAdd(@RequestParam(value = "caseEntity") String caseEntityString){
        if(StringUtils.isEmpty(caseEntityString)){
            logger.warn("医院档案为空");
            throw new RuntimeException("医院档案为空");
        }
        UserRealm.Principal principal = UserUtils.getPrincipal();
        if(principal==null){
            throw new RuntimeException("请等候后再试");
        }
        CaseEntity caseEntity = JSON.parseObject(caseEntityString,CaseEntity.class);
        caseEntity.setCreateDate(new Date());
        caseEntity.setHospital(principal.getName());
        mapper.insert(caseEntity);
        logger.info("新建医院档案信息 ； {}",caseEntity);
        return true;
    }

    /**
     * 更新医院档案信息
     * @param caseInfo
     * @return
     */
    @RequestMapping("/update/{id}")
    @OperationLog(value = "更新医院档案信息")
    public String update(@RequestParam(value = "caseInfo") String caseInfo)  {
        if(StringUtils.isEmpty(caseInfo)){
            logger.warn("医院档案信息为空");
            throw new RuntimeException("医院档案信息为空");
        }
        CaseEntity caseEntity = JSON.parseObject(caseInfo.replaceAll("&quot;","\""), CaseEntity.class);
        //会对引号转义，需要自己转换
//        couponObj.setCouponGetMsg(couponObj.getCouponGetMsg().replaceAll("&ldquo;","“")
//                .replaceAll("&rdquo;","”"));
        int i = mapper.updateById(caseEntity);

        logger.info("更新医院档案信息 ； {}",caseEntity);
        return "ok";
    }

    /**
     * 获取医院档案信息
     * @return
     */
    @RequestMapping("/{getCaseInfoPage}")
    @OperationLog(value = "获取医院档案信息")
    public IPage getCaseInfoPage(@RequestBody CaseCondition caseCondition)  {
        Page<CaseEntity> page = new Page<>(caseCondition.getPageNum(),caseCondition.getPageSize());
        QueryWrapper entityWrapper = new QueryWrapper();
        entityWrapper.like("CASE_NAME",caseCondition.getCaseName());
        entityWrapper.orderBy(false,false,"CASE_DATE");
        IPage iPage = mapper.selectPage(page, entityWrapper);
        logger.info("获取医院档案信息 ； {}",iPage);
        return iPage;
    }

    /**
     * 根据id获取医院档案信息
     * @return
     */
    @RequestMapping("/get/{id}")
    @OperationLog(value = "根据id获取医院档案信息")
    public CaseEntity getCaseInfoById(@PathVariable String  id)  {
        CaseEntity caseEntity = mapper.selectById(id);
        logger.info("根据id获取医院档案信息 ； {}",caseEntity);
        return caseEntity;
    }
}
