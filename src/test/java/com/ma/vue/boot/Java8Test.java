package com.ma.vue.boot;

import org.apache.shiro.util.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

/**
 * @ClassName Java8Test
 * @Description JDK8一些特性的测试
 * @Author 71013
 * @Date 2020/3/1 21:43
 * @Version 1.0
 */
public class Java8Test {
    private static final int MAX_LIST_SIZE = 10;

    public static void main(String[] args) {
        Stream<String> stringStream = Stream.of("1", "2");
        //map方法一对一的处理Stream中的元素
        stringStream.map(n -> n.concat(".txt")).forEach(System.out::println);

        Stream<String> txtStream = Stream.of("1.txt", "2.txt");
        //flatMap方法将每个元素经过处理后生成一个多个元素的Stream对象，然后将返回的所有Stream对象中的所有元素组合成一个统一的Stream并返回
        txtStream.flatMap(n -> Stream.of(n.split("\\."))).forEach(System.out::println);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < MAX_LIST_SIZE; i++) {
            list.add(String.valueOf(i));
        }
        //parallelStream创建并行流，使用forkJoin提升速度
        list.parallelStream().forEach(item -> System.out.println(Thread.currentThread().getName() + "   item = " + item));
    }

    @Test
    public void randomTest(){
        int asInt = ThreadLocalRandom.current().ints(70, 100)
                .limit(1).findFirst().getAsInt();
        Assert.notNull(asInt);

    }

    @Test
    public void localTimeTest(){
        LocalTime localTime = LocalTime.now();

        LocalTime localTime1 = localTime.plusHours(22);
        System.out.println(localTime);
        System.out.println(localTime1);
        System.out.println(localTime.isBefore(localTime1));

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = localDateTime.plusHours(22);
        System.out.println(localDateTime);
        System.out.println(localDateTime1);
        System.out.println(localDateTime.isBefore(localDateTime1));

    }
}
