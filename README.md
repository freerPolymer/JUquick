# JUquick 

**一个快速通用的工具项目**

依赖JAR文件：

- jackson-databind-2.13.3.jar,
- jackson-core-2.13.3.jar, 
- jackson-annotations-2.13.3.jar,
- validation-api-2.0.1.Final.jar


# 基础通用的工具类

* author polymer

#### CRPUtils 随机数生产工具。

#### DateTimeUtils 时间日期处理工具。

#### JSONTools 	JAVA与JSON数据格式的转换。

#### NIOStreamUtils 基于NIO技术的文件处理。

#### CodecsUtil	编码|解码工具类

### CRPUtils
- public  static  char getRandomChar() <br>  
获得一个机器内置的随机字符，如 *、X、p等

- public  static  String getRandomChar(int length)  <br> 
获取一个指定长度的字符串，要求length取值在1~2056之间

- public  static  String getRandomChar(String sourceStr,int length)   <br> 
获取一个字定义字符的指定长度字符串，sourceStr为空时，默认使用机器内置码生成
### NIOStreamUtils
- public static StringBuilder nioChannelReader(String sourceFilePath) throws Exception  <br> 
读取文本文件
     
- public static List<Map<Object,Object>> nioChannelWriter(StringBuilder builder, String outFilePath) throws Exception   <br> 
写出文本文件
      
- public static boolean transferTo(String frompath,String topath)throws Exception   <br> 
拷贝有效的文件
      
### DateTimeUtils
-	public static String getNowFormatTime(IConstant.DATE_FORMAT format)  <br> 
返回指定格式的当前日期，如2022-07-18 14:38:07
      
-	public static boolean isLeapYear(String date)  <br> 
指定日期是否为闰年
     <br>
-	public static long dayDiff(String startDate, String endDate, IConstant.DATE_TYPE dateType)<br>
获取两个日期之间的日、时、分、秒差、
     <br>
-	public static String operateDateTime(String dateTime, IConstant.DATE_TYPE dateType, 
int operateCount, IConstant.OPERATE_TYPE operateType)<br>
对时间进行加减计算，返回计算结果日期
     <br><br>
### JSONTools
- 	调用方法举例 String listString = JSONTools.parseJSONString(map,resultFile,true);
     <br>
-	public static <T> Type<T> parseJAVA(String json, Class<T> clazz)<br> 
将json字符串转为特定的java对象
     <br>
-	public static Map<String, Object> parseJAVAMap(File jsonSRC)<br> 
将json字符串转为类型，jsonSRC为可读取的(xx.json)文件
     <br>
-	public static Map<String, Object> parseJAVAMap(String json)<br> 
将json字符串转为类型，jsonSRC为有效的json字符串
     <br>
-	public static Object parseJAVAObject(String json, File jsonSRC)<br>
从字符串或文件读取JSON，返回一个OBJECT类型的Java对象
     <br>
-	public static String parseJSONString(Map<Object, Object> source,boolean format)<br>
将MAP对象转为JSON字符串，并提供格式化功能(true|false)
     <br>
-	public static String parseJSONString( Map<String,Object> source, File resultFile,boolean format)<br>
将MAP对象转为JSON字符串,resultFile提供写入本地文件(null或非空)，format格式化(true|false)
     <br><br>
### CodecsUtil
-	public static String MD5(@NotNull String res, String key)<br>
MD5加密 - 不可逆加密
     <br>
-	public static String SHA1(@NotNull String res,String key)<br>
SHA1加密 - 不可逆
     <br>
-	public static String DES(@NotNull String res, @NotNull String key,boolean isDecode)<br>
DES 可逆，用于加密解密
     <br>
-	public static String AES(@NotNull String res, @NotNull String key,boolean isDecode)<br>
AES 可逆
     <br>
-	public static String XOR(@NotNull String res, @NotNull String key,boolean isDecode)<br>
XOR 可逆
     <br>
-	public static int XOR(@Min(value = 1) int res, @NotNull String key)<br>
获取异或值
     <br>
-	public static String Base64(@NotNull String res,boolean isDecode)<br>
Base64 可逆
     <br>
### QRCodeUtil 
-    public static String QREncode( String content,int side,File pathFile) <br>
黑白双色的简单二维码 <br>
-	public static String QREncode( String content ,int side,File logoFile,  File pathFile) <br>     
带logo图标的黑白二维码     <br> 
-	public static Result QRScanner( File qrcode)      <br> 
二维码信息扫描，返回值请查看 com.google.zxing.Result 。该方法可能存在logo图标为二维码时无法读取信息的问题。   <br> 
