package com.ma.vue.boot.task;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ma.vue.boot.entity.Menu;
import com.ma.vue.boot.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Lazy(false)
public class MenuCacheTask {

    @Autowired
    private MenuMapper menuMapper;

    private static final String MENU_CACHE_KEY = "CASE_MENU_CACHE";

    private ConcurrentHashMap<String, List> MENU_CACHE = new ConcurrentHashMap<>();

    public void reLoadMenuCache(){

        List list = menuMapper.selectList(new QueryWrapper<Menu>());
        if(list.isEmpty()) {
            return;
        }
        MENU_CACHE.clear();
        MENU_CACHE.put("MENU_CACHE_KEY",list);

    }
}
