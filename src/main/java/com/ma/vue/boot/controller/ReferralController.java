package com.ma.vue.boot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ma.vue.boot.aop.OperationLog;
import com.ma.vue.boot.entity.*;
import com.ma.vue.boot.mapper.CaseMapper;
import com.ma.vue.boot.mapper.NoticeMapper;
import com.ma.vue.boot.mapper.ReferralMapper;
import com.ma.vue.boot.mapper.UserMapper;
import com.ma.vue.boot.shiro.realm.UserRealm;
import com.ma.vue.boot.utils.ReturnMessageUtils;
import com.ma.vue.boot.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("api/referral")
public class ReferralController {
    private static final Logger logger = LoggerFactory.getLogger(ReferralController.class);

    @Autowired
    private ReferralMapper referralMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CaseMapper caseMapper;

    @Autowired
    private NoticeMapper noticeMapper;
    /**
     * 新建档案转派流程
     * @param referralEntityString
     * @return
     */
    @RequestMapping("referralEntityAdd")
    @OperationLog("新建档案转派流程")
    public Map caseEntityAdd(@RequestParam(value = "referralEntity") String referralEntityString){
        if(StringUtils.isEmpty(referralEntityString)){
            logger.warn("转派流程为空");
            return ReturnMessageUtils.returnErrorMessage(-1,"转派流程为空");
        }
        UserRealm.Principal principal = UserUtils.getPrincipal();
        if(principal==null){
            return ReturnMessageUtils.returnErrorMessage(-1,"请登录后再试");
        }

        ReferralEntity referralEntity = JSON.parseObject(referralEntityString, ReferralEntity.class);
        CaseEntity caseEntity = caseMapper.selectById(referralEntity.getCaseId());
        if(!"1".equals(caseEntity.getStatus())){
            return ReturnMessageUtils.returnErrorMessage(-1,"该病例正在转诊中或已结档，无法转派");
        }
        UserEntity userEntity = userMapper.selectById(referralEntity.getNewOwnId());
        if(principal.getId().equals(userEntity.getId())){
            return ReturnMessageUtils.returnErrorMessage(-1,"同一医院无法转派");
        }
        referralEntity.setCreateDate(new Date());
        referralEntity.setOldOwnId(principal.getId());
        referralEntity.setOldOwnName(principal.getHospital());
        if(userEntity.getLevle()>principal.getLevel()){
            referralEntity.setType("1");//转诊类型 1 上转转诊 2 下转转诊
        }else{
            referralEntity.setType("2");
        }
        referralEntity.setNewOwnName(userEntity.getHospital());
        referralEntity.setStatus(1);//1 接受中 2 已接受
        referralMapper.insert(referralEntity);
        caseEntity.setStatus("2");
        caseMapper.updateById(caseEntity);
        return ReturnMessageUtils.returnErrorMessage(0,"成功");
    }

    /**
     * 获取转诊信息
     * @return
     */
    @RequestMapping("/{getReferralInfo}")
    @OperationLog(value = "获取转诊信息")
    public IPage getReferralInfo(@RequestBody ReferralCondition referralCondition)  {
        Page<ReferralEntity> page = new Page<>(referralCondition.getPageNum(),referralCondition.getPageSize());
        QueryWrapper entityWrapper = new QueryWrapper();
        entityWrapper.eq("NEW_OWN_NAME",UserUtils.getPrincipal().getHospital());
        entityWrapper.like("CASE_ID",referralCondition.getCaseId());
        entityWrapper.orderBy(false,false,"CREATE_DATE");
        IPage iPage = referralMapper.selectPage(page, entityWrapper);
        logger.info("获取医院档案信息 ； {}",iPage);
        return iPage;
    }

    /**
     * 操作转诊信息
     * @return
     */
    @RequestMapping("/checkReferral")
    @OperationLog(value = "操作转诊信息")
    public Map checkReferral(@RequestBody JSONObject jsonObject)  {
        if(jsonObject.isEmpty()){
            return ReturnMessageUtils.returnErrorMessage(-1,"所要操作的转诊信息为空");
        }
        ReferralEntity referralEntity = referralMapper.selectById(jsonObject.getString("id"));
        CaseEntity caseEntity = caseMapper.selectById(referralEntity.getCaseId());

        if ("1".equals(jsonObject.getString("flag"))) {
            caseEntity.setHospital(referralEntity.getNewOwnName()); //接受修改对应医院
            caseEntity.setStatus("1");
            referralEntity.setStatus(2);    //已接受
        }else{
            caseEntity.setStatus("1");  //病例置为正常状态，可重新转诊
            referralEntity.setStatus(3);    //已拒绝
        }
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setReferralId(referralEntity.getId());
        noticeEntity.setStatus("0");
        noticeEntity.setCreateDate(new Date());
        noticeMapper.insert(noticeEntity);
        referralMapper.updateById(referralEntity);
        caseMapper.updateById(caseEntity);
        logger.info("操作转诊信息 ； {}",jsonObject);
        return ReturnMessageUtils.returnErrorMessage(0,"成功");
    }
}
