package com.pyjava.sort;

import java.util.List;
import java.util.ArrayList;


/**
 *
 * Created by wangheng on 2016/10/12.
 */
public class CreateList {

    private static List<Integer> lists =new ArrayList<Integer>();

    public static List<Integer> getLists(int num)
    {
        for(int i=0;i<num;i++)
        {
            lists.add((int)(Math.random()*100));
        }
        return lists;
    }

    public static List<Integer> getLists()
    {
        lists.add(24);
        lists.add(90);
        lists.add(52);
        lists.add(91);
        lists.add(23);
        return lists;
    }

    public static void main(String[] args) {
        List<Integer> lists=CreateList.getLists();
        System.out.println(lists);
    }
}


