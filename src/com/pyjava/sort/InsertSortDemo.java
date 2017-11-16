package com.pyjava.sort;

import java.util.List;

/**
 * Created by wangheng on 2016/10/13.
 */
public class InsertSortDemo {

    private List<Integer> disOrderList=CreateList.getLists(5);

    public  void insertSort()
    {
        int length=disOrderList.size();
        int currentNum;
        int temp;
        //i 代表左边已经排了几个数据了
        System.out.println(disOrderList);
        System.out.println();
        for(int i=1;i<length;i++)
        {
            currentNum=disOrderList.get(i);
            System.out.println("currentNum is: "+currentNum +" the index is: "+i);
            System.out.println(".............第"+(i)+"次插入排序begin......");
            System.out.println(currentNum+" begin to insert sort list before the index: "+(i));
            for(int j=0;j<=i-1;j++)
            {

                if (currentNum<disOrderList.get(j))
                {
                    /*
                    move list 逻辑
                     */
                    System.out.println("_________________"+currentNum+" is insert into: "+j);
                    System.out.println("begin to move list from "+j+" to "+i);
                    for(int k=i;k>j;k--)
                    {
                        disOrderList.set(k,disOrderList.get(k-1));
                    }
                    disOrderList.set(j,currentNum);
                    break;
                }
                else
                {
//                    System.out.println(currentNum +" not move the list and insert at the: "+(j+1));
//                    System.out.println(".............第"+(i)+"次插入排序end......"+disOrderList);
                }
            }
            System.out.println(".............第"+(i)+"次插入排序end......"+disOrderList);

        }
        System.out.println(disOrderList);
    }

    public static void main(String[] args) {
        InsertSortDemo insertSortDemo =new InsertSortDemo();
        insertSortDemo.insertSort();
    }
}
