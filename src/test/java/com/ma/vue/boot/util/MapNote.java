package com.ma.vue.boot.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class MapNote {
    /**
     * 有序的HashMap
     */
    private final LinkedHashMap<String,Object> linkedHashMap = new LinkedHashMap();

    @Test
    public void getOrDefaultTest(){
        Map<String,List<String>> map = new HashMap<>();
        List<String> key = map.getOrDefault("key", new ArrayList<>());
        key.add("1");
        map.put("key",key);
        String key1 = map.get("key").get(0);
        Assert.assertEquals("1",key1);
    }
}
