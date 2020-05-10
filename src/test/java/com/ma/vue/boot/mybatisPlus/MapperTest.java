package com.ma.vue.boot.mybatisPlus;

import com.ma.vue.boot.entity.DepartmentEntity;
import com.ma.vue.boot.mapper.DepartmentMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MapperTest {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Test
    public void departmentTest(){
        List<DepartmentEntity> departmentEntities = departmentMapper.selectPermissionByPId("0");
        Assert.assertNotNull(departmentEntities);
    }

}
