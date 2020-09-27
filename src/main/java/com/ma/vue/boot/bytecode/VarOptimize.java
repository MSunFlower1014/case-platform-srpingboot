package com.ma.vue.boot.bytecode;

/**
 * @author zy_mayantao
 * @date 2020/9/25 15:20
 */
public class VarOptimize {
    volatile char[] myChars = ("Oracle Cloud Infrastructure Low data networking fees and " +
            "automated migration Oracle Cloud Infrastructure platform is built for " +
            "enterprises that are looking for higher performance computing with easy " +
            "migration of their on-premises applications to the Cloud.").toCharArray();

    /**
     * 使用局部变量，使用时不需要每次由堆中获取数据，直接通过堆栈操作
     * @return
     */
    public int globalVarTest() {
        int count = 0;
        for (int i = 0; i < myChars.length; i++) {
            if (myChars[i] == 'c') {
                count++;
            }
        }
        return count;
    }

    public int localityVarTest() {
        char[] localityChars = myChars;
        int count = 0;
        for (int i = 0; i < localityChars.length; i++) {
            if (localityChars[i] == 'c') {
                count++;
            }
        }
        return count;
    }
}
