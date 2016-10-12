package com.pyjava.obj;

/**
 * Created by wangheng on 2016/10/12.
 */
public class InnerDemo {


    private int num=5;

    class Inner
    {
        private  int innerNum=5555;
        public void say()
        {
            System.out.println(num);
            System.out.println("i am a inner class");
            say1(121212222);
            getInnerDemo().say1(11111);
        }
    }

    public InnerDemo getInnerDemo()
    {
        InnerDemo a=InnerDemo.this;
        System.out.println(a);
        return a;
    }

    private void say1(int a)
    {
        System.out.println(a);
    }
    public  void say()
    {
        Inner inner = new Inner();
        inner.say();
        System.out.println(inner.innerNum);
    }

    public static void main(String[] args) {
        InnerDemo innerDemo =new InnerDemo();
        System.out.println(innerDemo);
        innerDemo.say();

        // new inner class
        InnerDemo.Inner ii =innerDemo.new Inner();
        ii.say();
    }
}
