package com.opendog.opendogserver.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import netscape.javascript.JSObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * @className: IOUtils
 * @description: TODO 类描述
 * @author: opendog
 * @date: 2022/4/8
 **/
public class IOUtils {



    public static String inputStream2String(InputStream stream){



        return new BufferedReader(new InputStreamReader(stream))
                .lines().collect(Collectors.joining(System.lineSeparator()));
    }

    public static JSONObject stringConvert2Json(String s){
        return JSON.parseObject(s);
    }

    public static void array(JSONArray array,Object [] dst){
        array.toArray(new Object[array.size()]);
    }
}
