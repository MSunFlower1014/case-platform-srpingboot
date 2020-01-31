package com.ma.vue.boot.controller;


import com.ma.vue.boot.aop.OperationLog;
import com.ma.vue.boot.entity.CaseEntity;
import com.ma.vue.boot.mapper.CaseMapper;
import com.ma.vue.boot.service.ICaseEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    private static  final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private CaseMapper mapper;
    @Autowired
    private ICaseEntityService caseEntityService;

    @RequestMapping("hello")
    @ResponseBody
    @OperationLog(value = "TestController hello")
    public String hello(){
        CaseEntity caseEntity = new CaseEntity();
        caseEntity.setMessage("");
        caseEntity.setName("");
        caseEntity.setType("");
        caseEntityService.save(caseEntity);
       // mapper.insert(caseEntity);
        logger.info("spring Controller Hello");
        return "hello,StringBoot!";
    }


}
