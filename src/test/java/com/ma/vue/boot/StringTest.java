package com.ma.vue.boot;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author zy_mayantao
 * @date 2020/7/29 10:30
 */
public class StringTest {
    @Test
    public void stringTest(){
        Assert.assertEquals("123null","123"+null);
    }
}
