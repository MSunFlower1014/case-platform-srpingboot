package com.ma.vue.boot.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author zy_mayantao
 * @date 2020/8/28 16:40
 */
public class SortTest {
    public void sortTest() {
        List list = new ArrayList<>();
        list.add("");
        list.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        });

    }

    class People implements Comparable {
        private int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public int compareTo(Object o) {
            return this.age - ((People) o).getAge();
        }
    }
}
