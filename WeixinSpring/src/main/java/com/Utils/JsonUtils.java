package com.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Wudi
 * @Description: 数据转化为json格式
 * @date 18:07  2018/1/8
 */
public class JsonUtils {

    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        return gson.toJson(object);
    }
}
