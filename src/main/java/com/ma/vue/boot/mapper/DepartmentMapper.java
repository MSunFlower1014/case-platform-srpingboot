package com.ma.vue.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ma.vue.boot.entity.DepartmentEntity;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DepartmentMapper extends BaseMapper<DepartmentEntity> {

    @Select("SELECT P_ID,NAME,CREATE_DATE,STATUS FROM TF_MM_DEPARTMENT  WHERE PARENT_ID = #{parentId}")
    @Results({
            @Result(column="P_ID",property="id"),
            @Result(property = "childDepartment",column = "P_ID",many = @Many(select = "com.ma.vue.boot.mapper.DepartmentMapper.selectPermissionById"))
    })
    List<DepartmentEntity> selectPermissionByPId(String parentId);


    @Select("SELECT P_ID,NAME,CREATE_DATE,STATUS FROM TF_MM_DEPARTMENT  WHERE PARENT_ID = #{id}")
    @Results({
            @Result(column="P_ID",property="id"),
    })
    List<DepartmentEntity> selectPermissionById(String id);
}
