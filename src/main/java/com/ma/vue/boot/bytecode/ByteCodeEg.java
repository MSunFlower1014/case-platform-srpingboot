package com.ma.vue.boot.bytecode;

import java.util.List;

/**
 * @author zy_mayantao
 * @date 2020/9/25 14:48
 */
public class ByteCodeEg {
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void dealList4Final(){
        final List<String> list = this.list;
        String s = list.get(0);
        if(list.size()>0){
            s = "123";
        }
    }

    public void dealList(){
        final List<String> list = this.list;
        String s = list.get(0);
        if(list.size()>0){
            s = "123";
        }
    }
}
