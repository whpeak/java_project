package com.pyjava.sort;

import java.util.List;
/**
 *
 * 从小到大排序
 * 最大值冒泡到最右边
 * 然后次大值冒泡到次右边
 * 总之，最右边是确定的。
 * Created by wangheng on 2016/10/13.
 */
public class BubbleSortDemo {

    public List<Integer> disOrderList=CreateList.getLists(10);

    public List<Integer> sort()

    {
        int length = disOrderList.size();
        System.out.println(disOrderList+" size:"+length);
        //i代表右边已经有了几个最大的泡
        //第二层从0开始，一直到length-i-1为止，这段数据是需要往上冒的，length-i-1右边已经确定好
        for(int i=0;i<length-1;i++)
        {
            System.out.println("第"+(i+1)+"次冒泡排序开始..............");
            for (int j=0;j<length-i-1;j++)
            {
                int a=disOrderList.get(j);
                int b=disOrderList.get(j+1);
                System.out.println("当前处理的两个数据为："+a+" "+b);
                if(a>=b) //比较复杂度 n的平方
                {
                    swap(j,j+1);//交换复杂度 n的平方
                }
                 System.out.println(disOrderList);
                System.out.println(j+1);
            }
            System.out.println("第"+(i+1)+"次冒泡排序结束..............结果为："+disOrderList);
        }
        System.out.println("最终结果为：");
        System.out.println(disOrderList);
        return  disOrderList;

    }

    private void swap(int i,int j)
    {
        Integer temp=disOrderList.get(i);
        disOrderList.set(i,disOrderList.get(j));
        disOrderList.set(j,temp);
    }

    public static void main(String[] args) {

        BubbleSortDemo bubbleSortDemo=new BubbleSortDemo();
        bubbleSortDemo.sort();


    }


}
