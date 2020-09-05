package com.qf.utils;

import java.util.UUID;

public class FileUtils {

    public static  String getNewFileName(String fileName){
        return UUID.randomUUID().toString().replaceAll("-","")+"_"+fileName;
    }
}
