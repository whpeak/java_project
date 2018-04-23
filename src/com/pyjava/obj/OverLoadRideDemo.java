package com.pyjava.obj;

/**
 * Created by wangheng on 16/10/11.
 */

interface Override1
{
    void printNum1(int num);
}
 class Override2
{
     void printNum2(int num)
     {
         System.out.println(num+num);
     }
}
public class OverLoadRideDemo extends  Override2 implements Override1{

    /*

    重载的概念：
        ----->在同一个类中，允许存在同名函数，但它们的参数个数或者参数类型不同即可。
    重写的概念:
        ----->@Override
               子类重写父类的方法.




     */
    public void print(int num)
    {
        System.out.println(num);
    }

    public void print(int num1,int num2)
    {
        System.out.println(num1+num2);
    }

    public void print(String num)
    {
        System.out.println(num);
    }

    public void print(byte num)
    {
        System.out.println(num);
    }

    public void print(Object num)
    {
        System.out.println(num);
    }

    public static void main(String[] args) {

        OverLoadRideDemo overLoadDemo=new OverLoadRideDemo();
        overLoadDemo.print(1);
        overLoadDemo.print(1,2);
        overLoadDemo.print("1");
        overLoadDemo.print((byte)1);
        overLoadDemo.print((Object)1);
        overLoadDemo.printNum1(1);
        overLoadDemo.printNum2(1);

        Override2 override2 =overLoadDemo;
        override2.printNum2(2);
        Override2 override22 =new Override2();
        override22.printNum2(2);

    }

    @Override
    public void printNum1(int num)
    {
        System.out.println(num);
    }

    @Override
    void printNum2(int num)
    {
        System.out.println(num);
    }
}
