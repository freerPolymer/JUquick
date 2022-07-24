package com.polymer.ibase;

import java.util.Set;

/*
基本的常量定义
 */
public interface IConstant {
    //统一的字符编码
    public static final String CHAR_SET="UTF-8";
    //空值
    public static final String NO_VALUE="";
    public static final int NO_INT_VALUE=0;
    //常量小写字母数组
    public static final char[] LOWER_ABC_ARRAY={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    //常量大写字母数组
    public static final char[] UPPER_ABC_ARRAY={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    //常量符号数组
    public static final char[] SYMBOL_ARRAY={'+','-','*','/','%','+','!','~',',','.','|','#','@','&','(',')','[',']','{','}','^','`','<','>','?','$'};
    //常量数字数组
    public static final char[] INTEGER_ARRAY=
    {
            '0' , '1' , '2' , '3' , '4' , '5' ,
            '6' , '7' , '8' , '9' , 'a' , 'b' ,
            'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
            'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
            'o' , 'p' , 'q' , 'r' , 's' , 't' ,
            'u' , 'v' , 'w' , 'x' , 'y' , 'z'
    };
    //最小操作数
    public static final int MIN_OPERAND=1;
    //最大操作数
    public static final int MAX_OPERAND=2056;

    //---------------------通用返回结果集定义---------------------------
    enum RESULT{success,fail,error}
    //----------------时间公共定义---------------------
    enum DATE_FORMAT{
        DATE_YMD("yyyy-MM-dd"),
        DATE_YMDHMS("yyyy-MM-dd HH:mm:ss");

        String format ;
        DATE_FORMAT(String format) {this.format=format;}

        public static String getValus(String name){
            for (DATE_FORMAT val:values()){
                if(name.equals(val.name()))
                    return val.format;
            }
            return "";
        }
    }
//    public static final String DATE_FORMAT = "yyyy-MM-dd";
//    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    enum DATE_TYPE {
        YEAR,
        MONTH,
        DATE,
        HOURS,
        MINUTES,
        SECONDS;
    }

    //数的操作类型定义
    enum OPERATE_TYPE{
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE
    }

    //-------------------JSON相关定义--------------------------
    public static final String JSON_OBJECT_EMPTY="{}";
    public static final String JSON_ARRAY_EMPTY="[]";
    //-----------------常见的文件格式-------------------
    public static final String[] FILE_TYPE_VIDEO={
            "MP4","MOV","WMV","FLV","AVI","AVCHD","WebM","MKV","MPEG4","M4V"
    };
    public static final String[] FILE_TYPE_IMG={
            "JPEG","PNG","GIF","SVG","PSD","TIF","TGA","BMP","DDS",
            "HDR","RAW","EXR","AFPHOTO","EPS","ICO","JFF","JIF"
    };
    public static final String[] FILE_TYPE_TEXT={
            "TXT","DOC","DOCX","XLS","XLSX","RTF","PPT","PPTX","PDF",
            "CLASS","JAVA","ZIP","JAR","RAR","HTML","EXE","SQL","JSON",
            "JS","JSP","LOG","MD","PY","PYC","TAZ","XLT"
    };
}