package test.util;

public class Test {
    /* JSONTools调用
     //-- statement object
        String json = "{\"name\":\"123\",\"node\":123569}";
        File jsonSRC = new File("D:/try/HEcustomer.json");
        File resultFile = new File("D:/try/HGcustomer.json");

        Map<String, Object> mp = JSONTools.parseJAVAMap(jsonSRC);
        // ?? 需要封装成一个公共的对象|集合解析方法
        List<Site> sites = new LinkedList<>();
        if (mp.get("sites") != null) {
            Map m = (Map) mp.get("sites");
            Object[] o = (Object[]) m.get("site");
            for (Object t : o) {
                if (t instanceof Map) {
                    Map s = (Map) t;
                    Site site = new Site((String) s.get("id"), (String) s.get("name"), (String) s.get("url"), (String) s.get("dateTime"));
                    sites.add(site);
                }
            }
        }
        Object op= JSONTools.parseJAVAObject(null,jsonSRC);
        System.out.println("Object : "+op);

        Map<String,Object> map = new LinkedHashMap<>();
        Site[] site = {new Site("89","hello",null,"2018-20-31"),new Site("63255","Mody","www.wang.org","")};
        map.put("site",site);
        map.put("save",true);
        String listString = JSONTools.parseJSONString(map,resultFile,true);
        System.out.println(listString);

        //----------------------------------------
        Type<Util> opt=JSONTools.parseJAVA(json,Util.class);
        Util u= (Util)opt.getT();
        System.out.println(u.getName());
     */

//---- java8  Lambda用法
/*
    @Test
    public void test() {
        List<String> strings = Arrays.asList("abc", "def", "gkh", "abc");
        //返回符合条件的stream
        Stream<String> stringStream = strings.stream().filter(s -> "abc".equals(s));
        //计算流符合条件的流的数量
        long count = stringStream.count();

        //forEach遍历->打印元素
        strings.stream().forEach(System.out::println);

        //limit 获取到1个元素的stream
        Stream<String> limit = strings.stream().limit(1);
        //toArray 比如我们想看这个limitStream里面是什么，比如转换成String[],比如循环
        String[] array = limit.toArray(String[]::new);

        //map 对每个元素进行操作返回新流
        Stream<String> map = strings.stream().map(s -> s + "22");

        //sorted 排序并打印
        strings.stream().sorted().forEach(System.out::println);

        //Collectors collect 把abc放入容器中
        List<String> collect = strings.stream().filter(string -> "abc".equals(string)).collect(Collectors.toList());
        //把list转为string，各元素用，号隔开
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(","));

        //对数组的统计，比如用
        List<Integer> number = Arrays.asList(1, 2, 5, 4);

        IntSummaryStatistics statistics = number.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : "+statistics.getMax());
        System.out.println("列表中最小的数 : "+statistics.getMin());
        System.out.println("平均数 : "+statistics.getAverage());
        System.out.println("所有数之和 : "+statistics.getSum());

        //concat 合并流
        List<String> strings2 = Arrays.asList("xyz", "jqx");
        Stream.concat(strings2.stream(),strings.stream()).count();

        //注意 一个Stream只能操作一次，不能断开，否则会报错。
        Stream stream = strings.stream();
        //第一次使用
        stream.limit(2);
        //第二次使用
        stream.forEach(System.out::println);
        //报错 java.lang.IllegalStateException: stream has already been operated upon or closed

        //但是可以这样, 连续使用
        stream.limit(2).forEach(System.out::println);
    }

    */
}
