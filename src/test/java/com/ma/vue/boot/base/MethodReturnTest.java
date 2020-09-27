package com.ma.vue.boot.base;

/**
 * 测试返回值是否为引用传递
 * 结果 是的
 * java中对象都是引用传递
 * @author zy_mayantao
 * @date 2020/6/8 17:44
 */
public class MethodReturnTest {
    protected class Man {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    public Man getMan(){
        Man man = new Man();
        System.out.println(man.hashCode());
        return man;
    }

    public static void main(String[] args) {
        MethodReturnTest methodReturnTest = new MethodReturnTest();
        Man man = methodReturnTest.getMan();
        System.out.println(man.hashCode());
    }
}
