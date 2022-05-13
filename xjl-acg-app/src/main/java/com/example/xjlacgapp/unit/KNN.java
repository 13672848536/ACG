package com.example.xjlacgapp.unit;
import java.util.*;

/**
 * KNN又名临近算法
 * 实现步骤：
 * 1. 首先计算出所有的临近距离值
 * 2. 获取最临近值
 */
public class KNN {

public static double Jaccard(List<Long> a,List<Long> b){
    Set<Long> jiao = new HashSet<>();
    Set<Long> bing = new HashSet<>();
    jiao.addAll(a);
    jiao.retainAll(b);
    bing.addAll(a);
    bing.addAll(b);
    double j = jiao.size();
    double p = bing.size();
    double Jaccard = 1.0-j/p;
    if (Jaccard == 0.0){
        return 1.0;
    }
    return Jaccard;
}
    public static List<Long> Jaccard2(List<Long> a,List<Long> b,double min){
        Set<Long> jiao = new HashSet<>();
        Set<Long> bing = new HashSet<>();
//        Set<Long> t = new HashSet<>();
        jiao.addAll(a);
        jiao.retainAll(b);
        bing.addAll(a);
        bing.addAll(b);
        double j = jiao.size();
        double p = bing.size();
        double Jaccard = 1.0-j/p;
        if(Jaccard==min){
            return b;
        }
        return null;
    }
}
