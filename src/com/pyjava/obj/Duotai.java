package com.pyjava.obj;

/**
 * Created by wangheng on 16/10/11.
 */
public class Duotai {
    /*
    子类继承了父类.
    子类就可以重写父类中的公共方法.
    多个子类,就出现了多态性

    用父类的引用,指向不同子类的实例.
    调用的结果是不同子类的结果.
    对于调用者而言,不用关心子类如何实现的.
    这就是多态.

     */
    public static void main(String[] args) {
        Fu fu =new Fu();
        fu.print(5);
        fu=new Add();
        fu.print(5);
        fu=new Sub();
        fu.print(5);
        fu=new Chen();
        fu.print(5);
        fu=new Chu();
        fu.print(5);

    }
}

class Fu
{
    public void print (int num)
    {
        System.out.println(num);
    }
}

class Add extends Fu
{
    @Override
    public void print(int num) {
        super.print(num+num);
    }
}
class Sub extends Fu
{
    @Override
    public void print(int num) {
        super.print(num-num);
    }
}
class Chen extends Fu
{
    @Override
    public void print(int num) {
        super.print(num*num);
    }
}
class Chu extends Fu
{
    @Override
    public void print(int num) {
        super.print(num/num);
    }
}
