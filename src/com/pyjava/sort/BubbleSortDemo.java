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

    public List<Integer> disOrderList=CreateList.getLists(5);

    public List<Integer> sort1()
    {
        int length = disOrderList.size();
        System.out.println(disOrderList+" size:"+length);
        //out 代表当前需要进行比较的范围,因为最后一个是确定的,因此初始化的时候,只要比较的范围是总个数减1就可以了.
        //第二层从0开始，一直到out为止，这段数据是需要往上冒的，length-i右边已经确定好.
        for(int out = length -1 ;out > 0;out --) //最后一个范围的数据,是不用检查的 .
        {
            System.out.println("第"+(length-out)+"次冒泡排序开始..............需要比较的两个数据的的左指针范围为: 从最初开始,到"+(out-1));
            for (int in=0;in < out;in++)
            {
                int a1=disOrderList.get(in);
                int b1=disOrderList.get(in+1);

                System.out.println("当前处理的两个数据为："+a1+" "+b1);
                if(a1 >= b1)
                {
                    swap(in,in+1);
                }
                System.out.println(disOrderList);
            }
            System.out.println("第"+(length-out)+"次冒泡排序结束..............结果为："+disOrderList);
        }
        System.out.println("最终结果为：");
        System.out.println(disOrderList);
        return  disOrderList;
    }
    public List<Integer> sort()
    {
        int length = disOrderList.size();
        System.out.println(disOrderList+" size:"+length);
        //i代表右边当前需要预留的位置,放置这次排序后的数据,初始化为1,代表当前需要留1个位置,放置一个数据.
        //第二层从0开始，一直到length-i为止，这段数据是需要往上冒的，length-i右边已经确定好.
        for(int i=1;i<length;i++)
        {
            System.out.println("第"+i+"次冒泡排序开始..............需要比较的两个数据的的左指针范围为: 从最初开始,到"+(length-i-1));
            for (int j=0;j<length-i;j++)
            {
                int a=disOrderList.get(j);
                int b=disOrderList.get(j+1);
                System.out.println("当前处理的两个数据为："+a+" "+b);
                if(a>=b) //比较复杂度 n的平方
                {
                    swap(j,j+1);//交换复杂度 n的平方
                }
                 System.out.println(disOrderList);
            }
            System.out.println("第"+i+"次冒泡排序结束..............结果为："+disOrderList);
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
        bubbleSortDemo.sort1();


    }


}
