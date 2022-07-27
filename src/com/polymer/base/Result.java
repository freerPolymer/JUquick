package com.polymer.base;

import com.polymer.ibase.IConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公共的返回结果集
 * @since 2022年7月17日
 * @author polymer
 */
public class Result {

    private static boolean result=true;//
    private static String message= IConstant.RESULT.success.name();
    private static String exception="";
    private static List<Map<Object,Object>> data=new ArrayList<>();

    /**
     * 返回成功结果
     * @return success
     */
    public static List<Map<Object,Object>> successful(){
        Map<Object,Object> mp =new HashMap<>();
        mp.put("result",result);
        mp.put("message",message);
        mp.put("exception",exception);
        mp.put("data",data);
        data.add(mp);
        return data;
    }
}
