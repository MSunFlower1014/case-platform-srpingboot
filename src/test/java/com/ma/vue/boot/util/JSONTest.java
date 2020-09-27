package com.ma.vue.boot.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.junit.Test;

/**
 * @author zy_mayantao
 * @date 2020/9/7 15:50
 */
public class JSONTest {

    @Test
    public void arrayTest(){
        String json = "[\"model_def_type\",\"model_name\",{\"train_args\":[\"batch_size\",\"focal_ctc_loss\",\"steps_per_epoch\",\"filter_train_name\",\"model_path\",\"epochs\",\"gpu_memory\",\"input_format\",\"input_path\"]},{\"define_args\":[\"input_width\",\"pre_weights_path\",\"input_height\",\"input_channels\",\"attention\",\"charset_file\"]}]";
        JSONArray jsonArray = JSON.parseArray(json);
        for (Object s : jsonArray){
            if (s instanceof String){
                System.out.println(s);
            }else{

                System.out.println(s);
            }
        }
    }
}
