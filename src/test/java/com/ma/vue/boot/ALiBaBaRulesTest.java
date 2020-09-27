package com.ma.vue.boot;

/**
 * 阿里巴巴JAVA开发规范手册测试代码
 * switch会抛出空指针异常
 */
public class ALiBaBaRulesTest {

    public static void main(String[] args) {
        switchNullTest(null);
    }

    /**
     *
     * @param param
     */
    public static void switchNullTest(String param) {
        //switch会抛出NPE
        switch (param) {
            // 肯定不是进入这里
            case "sth":
                System.out.println("it's sth");
                break;
            // 也不是进入这里
            case "null":
                System.out.println("it's null");
                break;
            // 也不是进入这里
            default:
                System.out.println("default");
        }
    }
}
