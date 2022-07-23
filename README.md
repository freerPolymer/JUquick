# JUquick
一个快速通用的工具项目
- CRPUtils
--	public  static  char getRandomChar()
获得一个机器内置的随机字符，如 *、X、p等
--	public  static  String getRandomChar(int length)
获取一个指定长度的字符串，要求length取值在1~2056之间
--	public  static  String getRandomChar(String sourceStr,int length)
获取一个字定义字符的指定长度字符串，sourceStr为空时，默认使用机器内置码生成
- NIOStreamUtils
--	public static StringBuilder nioChannelReader(String sourceFilePath) throws Exception
读取文本文件
--	public static List<Map<Object,Object>> nioChannelWriter(StringBuilder builder, String outFilePath) throws Exception
写出文本文件
--	public static boolean transferTo(String frompath,String topath)throws Exception
拷贝有效的文件
- DateTimeUtils
--	public static String getNowFormatTime(IConstant.DATE_FORMAT format)
返回指定格式的当前日期，如2022-07-18 14:38:07
--	public static boolean isLeapYear(String date)
指定日期是否为闰年
--	public static long dayDiff(String startDate, String endDate, IConstant.DATE_TYPE dateType)
获取两个日期之间的日、时、分、秒差、
--	public static String operateDateTime(String dateTime, IConstant.DATE_TYPE dateType, 
int operateCount, IConstant.OPERATE_TYPE operateType)
对时间进行加减计算，返回计算结果日期
- JSONTools
-- 	调用方法举例 String listString = JSONTools.parseJSONString(map,resultFile,true);

--	public static <T> Type<T> parseJAVA(String json, Class<T> clazz) 
将json字符串转为特定的java对象
--	public static Map<String, Object> parseJAVAMap(File jsonSRC) 
将json字符串转为类型，jsonSRC为可读取的(xx.json)文件
--	public static Map<String, Object> parseJAVAMap(String json) 
将json字符串转为类型，jsonSRC为有效的json字符串
--	public static Object parseJAVAObject(String json, File jsonSRC)
从字符串或文件读取JSON，返回一个OBJECT类型的Java对象
--	public static String parseJSONString(Map<Object, Object> source,boolean format)
将MAP对象转为JSON字符串，并提供格式化功能(true|false)
--	public static String parseJSONString( Map<String,Object> source, 
File resultFile,boolean format)
将MAP对象转为JSON字符串,resultFile提供写入本地文件(null或非空)，
format格式化(true|false)
