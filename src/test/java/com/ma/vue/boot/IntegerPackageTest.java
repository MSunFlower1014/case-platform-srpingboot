package com.ma.vue.boot;

public class IntegerPackageTest {
    public static void main(String[] args) {
        Integer a = new Integer(3);
        Integer b = 3;
        Integer c = 3;
        Integer d = new Integer(3);
        System.out.println(a==b);
        System.out.println(a==d);
        System.out.println(c==b);
    }
}
