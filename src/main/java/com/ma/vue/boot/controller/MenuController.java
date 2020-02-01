package com.ma.vue.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ma.vue.boot.aop.OperationLog;
import com.ma.vue.boot.entity.Menu;
import com.ma.vue.boot.mapper.MenuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("api/menu")
public class MenuController {
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 获取菜单
     *
     * @return
     */
    @OperationLog(value = "获取菜单")
    @RequestMapping(value = "/getMenus", method = RequestMethod.GET)
    public List<Menu> getMenus() {
        List<Menu> menuList = new ArrayList<>();

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("MENU_DEL_FLAG","0");
        List<Menu> list = menuMapper.selectList(queryWrapper);
        for(Menu menu:list){
            if(StringUtils.isEmpty(menu.getParentId())){
                for(Menu childMenu:list){
                    if(menu.getId().equals(childMenu.getParentId())){
                        if(menu.getChildMenus()==null){
                            menu.setChildMenus(new ArrayList<Menu>());
                        }
                        menu.getChildMenus().add(childMenu);
                    }
                }
                menuList.add(menu);
            }
        }
        return menuList;
    }
}

