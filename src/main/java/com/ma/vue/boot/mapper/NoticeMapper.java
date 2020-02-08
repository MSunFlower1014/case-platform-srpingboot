package com.ma.vue.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ma.vue.boot.entity.NoticeEntity;
import org.apache.ibatis.annotations.Param;


public interface NoticeMapper extends BaseMapper<NoticeEntity> {
    IPage<NoticeEntity> queryById(Page page,@Param("id") String id,@Param("hospital")String hospital);

    IPage<NoticeEntity> listNotice(Page page,@Param("hospital")String hospital);

}
