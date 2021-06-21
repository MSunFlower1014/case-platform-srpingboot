package com.ma.vue.boot.util;

public class StringBuilderNote {
    /**
     * 底层通过 char数组维护字符串，自动扩容，默认容量为16
     * toString 会 return new String(value, 0, count); 生成数据拷贝的字符串对象并返回
     */
    public static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 实现与stringBuilder类似，都继承了AbstractStringBuilder，但所有方法都使用synchronized修饰保证并发安全，吞吐量相比较低
     */
    public static StringBuffer stringBuffer = new StringBuffer();

    public static void main(String[] args) {
        stringBuilder.toString();
    }
}
