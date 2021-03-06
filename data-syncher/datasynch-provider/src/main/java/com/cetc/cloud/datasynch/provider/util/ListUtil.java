package com.cetc.cloud.datasynch.provider.util;

import java.util.*;

/**
 * 扩展List的toString()功能
 * Created by llj on 2018/10/14.
 */
public class ListUtil {
    public static String[] toStringArray(List<String> list){
        String[] strings = new String[list.size()];
        for (int i=0;i<list.size();i++){
            strings[i] = list.get(i);
        }
        return strings;
    }
    /**
     * 输出字段列表字符串，每个字段带双引号且字段用逗号分隔
     * @param list
     * @return
     */
    public static String getSQLColumnsListWithQuotes(List<String> list){
        List resList = new ArrayList<String>();
        for (String t:list){
            resList.add("\""+t+"\"");
        }
        String s = toStringWithoutBracket(resList);
        return s;
    }
    /**
     * 打印List的时候,不显示中括号,每个元素没有双引号
     * @return
     */
    public static String toStringWithoutBracket(List list){

        if (null!=list) {
            String a = list.toString();
            String b = a.substring(1);
            String c = b.substring(0,b.length()-1);
            return c;
        }else {
            return "error";
        }
    }
    /**
     * 打印List的时候,不显示中括号,每个元素用双引号引起来
     * @return
     */
    public static String toStringNoBracketWithDoubleQuot(Collection list){

        ArrayList<String> resList = new ArrayList<String>();
        if (null!=list) {
            Iterator iterator = list.iterator();
            while (iterator.hasNext()){
                String next = (String) iterator.next();
                resList.add("\""+next+"\"");
            }
            return toStringWithoutBracket(resList);
        }else {
            return "";
        }
    }
    /**
     * 打印List的时候,不显示中括号,每个元素用双引号引起来
     * @return
     */
    public static String toStringNoBracketWithSingleQuot(Collection list){

        ArrayList<String> resList = new ArrayList<String>();
        if (null!=list) {
            Iterator iterator = list.iterator();
            while (iterator.hasNext()){
                String next = (String) iterator.next();
                resList.add("'"+next+"'");
            }
            return toStringWithoutBracket(resList);
        }else {
            return "";
        }
    }
//
//    public static String toStringNoBracketWithDoubleQuot(HashSet<String> set) {
//        ArrayList<String> resList = new ArrayList<String>();
//        if (null!=set) {
//            Iterator iterator = set.iterator();
//            while (iterator.hasNext()){
//                String next = (String) iterator.next();
//                resList.add("\""+next+"\"");
//            }
//            return toStringWithoutBracket(resList);
//        }else {
//            return "";
//        }
//    }
}
