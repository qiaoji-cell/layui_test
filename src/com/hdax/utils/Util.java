package com.hdax.utils;

/**
 * Created by Joe on 2021-07-28 0028
 */
public class Util {
    public static boolean isNull(Object... obj){
        boolean isNull = true;
        for (Object str:obj
             ) {
            if(str!=null){
                isNull = false;
                if(str.toString().trim().length()==0){
                    isNull=true;
                }
            }else{
                isNull = true;

            }
        }
        return isNull;
    }

    public static int beInt(String str){
        return Integer.valueOf(str);
    }
}
