package com.ma.vue.boot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ma.vue.boot.aop.OperationLog;
import com.ma.vue.boot.entity.NoticeEntity;
import com.ma.vue.boot.entity.PageCondition;
import com.ma.vue.boot.mapper.NoticeMapper;
import com.ma.vue.boot.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/notice")
public class NoticeController {
    private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private NoticeMapper noticeMapper;

    @RequestMapping("getNoticeById")
    @OperationLog("档案转诊结果-id查询")
    public IPage getNoticeById(@RequestParam String  id,@RequestBody PageCondition pageCondition){
        Page<NoticeEntity> page = new Page<>(pageCondition.getPageNum(),pageCondition.getPageSize());
        return noticeMapper.queryById(page,id, UserUtils.getPrincipal().getHospital());
    }


    @RequestMapping("getNoticePage")
    @OperationLog("档案转诊结果-分页查询")
    public IPage getNoticePage(@RequestBody PageCondition pageCondition){
        Page<NoticeEntity> page = new Page<>(pageCondition.getPageNum(),pageCondition.getPageSize());
        IPage<NoticeEntity> noticeEntityIPage = noticeMapper.listNotice(page,UserUtils.getPrincipal().getHospital());
        return noticeEntityIPage;
    }
}
