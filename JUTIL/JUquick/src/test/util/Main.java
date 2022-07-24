package util;

import com.polymer.base.Type;
import com.polymer.util.CRPUtils;
import com.polymer.util.JSONTools;
import com.polymer.util.NIOStreamUtils;

import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {

/*
        List<Integer> ls=new LinkedList<>();
        for (int i = 1; i <=800 ; i++) {
            int num = Math.abs(new Random(i).nextInt());
            ls.add(num);
            System.out.println(num);
        }
        for (Integer i:ls) {
            System.out.println(ls.contains(i));
        }*/
        String frompath="D:\\MySystem\\显卡驱动/igfx_win_101.3113.zip";
        String topath="C:\\Users\\Administrator\\Desktop/"+ CRPUtils.getRandomChar("abce536",5)+".rar";
        try {
            System.out.println(NIOStreamUtils.transferTo(frompath,topath));
            System.out.println();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}