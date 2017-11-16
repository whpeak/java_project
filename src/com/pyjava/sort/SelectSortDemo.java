package com.pyjava.sort;

import java.util.List;

/**
 * 从小到达排序
 * 选择排序，就是每一列都跟temp比较。找到最小的，然后和最左边的交换
 * 保证最左边的是最小的
 * 次左边的是次小的。
 * 也就是说左边是确定的
 * Created by wangheng on 2016/10/13.
 */
public class SelectSortDemo {

    private List<Integer> disOrderList=CreateList.getLists(5);

    public void selectSort()
    {
        System.out.println(disOrderList);
        int length=disOrderList.size();
        int temp;
        int index=0;
        boolean isSwap ;
        //i代表左边处理好了几个值，第二层循环从第i+1个开始往右边找,开始的时候,0个处理好
        for (int i=0;i<length;i++)
        {
            System.out.println("选择排序第"+(i+1)+"次：begin.........");
            temp=disOrderList.get(i);
            isSwap = false;
            for(int j=i+1;j<length;j++)
            {
                int currentNum=disOrderList.get(j);
                System.out.println("current temp num is: "+temp+" current  deal num is: "+currentNum+". 开始比较");
                if (temp>currentNum)
                {
                    isSwap = true;
                    index=j;
                    System.out.println(">>>>>>>>>>>>temp num change from: "+temp+" to "+currentNum+".temp num index is :"+index);
                    temp=currentNum;
                }
                else
                {
                    System.out.println(".......temp num need not change");
                }
            }
            if(isSwap)
            {
                swap(i,index);
            }
            System.out.println("选择排序第"+(i+1)+"次：end........."+disOrderList);
        }
        System.out.println("the sort list is:");
        System.out.println(disOrderList);

    }
    private void swap(int i,int j)
    {
        Integer temp=disOrderList.get(i);
        disOrderList.set(i,disOrderList.get(j));
        disOrderList.set(j,temp);
    }

    public static void main(String[] args) {
        SelectSortDemo selectSortDemo=new SelectSortDemo();
        selectSortDemo.selectSort();
    }
}
