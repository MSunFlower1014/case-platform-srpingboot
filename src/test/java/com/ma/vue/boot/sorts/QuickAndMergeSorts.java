package com.ma.vue.boot.sorts;

import java.util.Arrays;

/**
 * @author zy_mayantao
 * @date 2020/10/26 14:06
 */
public class QuickAndMergeSorts {

    public static void main(String[] args) {
        int [] m = new int []{3,1,0,6,2,9,7};
        long l = System.currentTimeMillis();
        quickSort(m,0,m.length-1);
        System.out.println(Arrays.toString(m));
        System.out.println("quick cost time : "+(System.currentTimeMillis()-l));
        m = new int []{3,1,0,6,2,9,7};
        l = System.currentTimeMillis();
        m = mergeSort(m);
        System.out.println(Arrays.toString(m));
        System.out.println("quick cost time : "+(System.currentTimeMillis()-l));
    }

    public static void quickSort(int [] source , int left,int right){
        if(left>=right){
            return;
        }

        int change = left;
        int temp;
        for (int i = left+1; i <= right; i++) {
            if(source[i]<= source[left]){
                change++;
                temp = source[change];
                source[change]=source[i];
                source[i]=temp;
            }
        }
        temp = source[change];
        source[change] = source[left];
        source[left] = temp;


        quickSort(source,left,change-1);
        quickSort(source,change+1,right);
    }

    public static int [] mergeSort(int [] source){
        if(source.length<=1){
            return source;
        }

        int mid = source.length/2;

        int [] subLeft = mergeSort(Arrays.copyOfRange(source,0,mid));
        int [] subRight = mergeSort(Arrays.copyOfRange(source,mid,source.length));

        int [] temp = new int [source.length];

        int length = subLeft.length;
        int lengthRight = subRight.length;

        int j =0 ,p =0;
        int index =0;
        while (true){
            if(j == length){
                for (int i = p; i < lengthRight; i++) {
                    temp[index] = subRight[i];
                    index++;
                }
                break;
            }

            if(p == lengthRight){
                for (int i = j; i < length; i++) {
                    temp[index] = subLeft[i];
                    index++;
                }
                break;
            }

            if(subLeft[j]< subRight[p]){
                temp[index] = subLeft[j];
                j++;
                index++;
            }else{
                temp[index] = subRight[p];
                p++;
                index++;
            }
        }
        return temp;
    }
}
