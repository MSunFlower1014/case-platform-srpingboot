package com.ma.vue.boot;

import com.ma.vue.boot.entity.CaseEntity;
import com.ma.vue.boot.entity.UserEntity;
import com.ma.vue.boot.mapper.UserMapper;
import com.ma.vue.boot.service.ICaseEntityService;
import com.ma.vue.boot.service.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {
    @Autowired
    private IUserService userService;

    @Autowired
    private ICaseEntityService caseEntityService;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("123456");
        userEntity.setUserName("mayantao");
        userService.save(userEntity);
//        List<UserEntity> userList = userMapper.selectList(null);
//        Assert.assertEquals(5, userList.size());
//        userList.forEach(System.out::println);
    }

    @Test
    public void testSave() {
        System.out.println(("----- selectAll method test ------"));
        CaseEntity byId = caseEntityService.getById("0379d9c452e3344538ef9afe46c494ce");
        byId.setType("test");
        byId.setName("mayao");
        byId.setHospital("3");
        caseEntityService.saveOrUpdate(byId);
        System.out.println(byId);
    }
}
