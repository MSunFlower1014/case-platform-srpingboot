package com.ma.vue.boot.controller;

import com.ma.vue.boot.aop.OperationLog;
import com.ma.vue.boot.entity.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("api/menu")
public class MenuController {
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
    /**
     * 获取菜单
     *
     * @return
     */
    @OperationLog(value = "获取菜单")
    @RequestMapping(value = "/getMenus", method = RequestMethod.GET)
    public List<Menu> getMenus() {
        List<Menu> menuList = new ArrayList<>();
        List<Menu> menuChildList  = new ArrayList<>();
        Menu menu = new Menu();
        Menu menuParent = new Menu();
        menuParent.setName("医院");
        menuParent.setUrl("coupon");
        menu.setMenuIcon("123");
        menu.setType("1");
        menu.setRemarks("开户");
        menu.setIsshow((short)1);
        menu.setSort(1);
        menu.setUrl("coupon_list");
        menu.setName("百度");
        menu.setId("123455");
        menuChildList.add(menu);
        menuParent.setChildMenus(menuChildList);
        menuList.add(menuParent);
        return menuList;
    }
}

