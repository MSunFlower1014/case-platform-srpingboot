package com.ma.vue.boot;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zy_mayantao
 * @date 2020/8/10 10:09
 */
public class RegexTest {

    /**
     * 匹配字符串中${}中的字符子串
     */
    @Test
    public void regexTest(){
        Pattern regex = Pattern.compile("\\$\\{([^}]*)\\}");
        Matcher matcher = regex.matcher("aaaa${bb}aaa${cc}aaa");
        while(matcher.find()) {
            String group = matcher.group();
            System.out.println(matcher.group(1));
            String dd = matcher.replaceAll("dd");
            System.out.println(dd);
        }
        System.out.println(matcher);
    }
}
