package com.pyjava.data_struct.tree.treedemo1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by wangheng on 2016/10/18.
 */
public class Tree {
    public Node root;
    public List<Node> nodeLists =new ArrayList<Node>();



    public Node findMin()
    {
        Node cursor=null;
        Node cursor_old=null;
        cursor=root;
        while(null != cursor)
        {
            cursor_old=cursor;
            cursor=cursor.lNode;
        }
        return cursor_old;
    }

    public Node findMax()
    {
        Node cursor=null;
        Node cursor_old=null;
        cursor=root;
        while(null != cursor)
        {
            cursor_old=cursor;
            cursor=cursor.rNode;
        }
        return cursor_old;
    }

    public Node find(int key)
    {
        int level=0;
        Node current =root;
        while(current.iData !=key)
        {
            if (key< current.iData)
            {
                current=current.lNode;
                level+=1;
            }

            else
            {
                current=current.rNode;
                level+=1;
            }
            if(current==null)
            {
                return null;
            }
        }
        current.setLevel(level);
        return current;
    }

    public void insert(int iData,double fData)
    {
        Node newNode =new Node(iData,fData);
        System.out.println("___________________begin to insert node:"+newNode.toString());
        if (root==null)
        {
            System.out.println(newNode+" is root node");
            System.out.println("insert root: "+newNode.iData+" "+newNode.fData);
            root =newNode;
        }
        else
        {
            System.out.println("from root_node to search....");
            Node cursor =root;
            Node cursor_old;
            while(true)
            {
                System.out.println("current cursor point: "+cursor.toString());
                cursor_old=cursor;//目的是当游标移动的时候，可以记录移动前的节点位置（当游标指向为空时，方便获取上一层指向）
                System.out.println("current cursor_old point: "+cursor_old.toString());
                if(fData<cursor.fData)
                {
                    System.out.println("move cursor to cursor.lNode "+cursor.lNode+"...");
                    cursor=cursor.lNode; //更新游标
                    if(cursor==null)
                    {
                        //判断是否为插入点
                        cursor_old.lNode=newNode;
                        System.out.println("insert lNode: "+newNode.iData+" "+newNode.fData+" parent node is: "+cursor_old.iData);
                        return;
                    }
                    System.out.println("after move to "+cursor+" can not insert,begin to move cursor.....");
                }
                else
                {
                    System.out.println("move cursor to cursor.rNode "+cursor.rNode+"...");
                    cursor =cursor.rNode;//更新游标
                    if(cursor==null)
                    {
                        cursor_old.rNode=newNode;
                        System.out.println("insert rNode: "+newNode.iData+" "+newNode.fData+" parent node is: "+cursor_old.iData);
                        return;
                    }
                    System.out.println("after move to "+cursor+" can not insert,begin to move cursor.....");
                }
            }
        }
    }

    public void inOrder(Node localRoot)
    {//左中右
        System.out.println("...........enter inOrder current node is: "+localRoot);
        if(localRoot !=null)
        {
            System.out.println("begin to find left node of "+localRoot);
            inOrder(localRoot.lNode);//先遍历完左边的
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!save "+localRoot);
            nodeLists.add(localRoot);
            System.out.println("begin to find left node of "+localRoot);
            inOrder(localRoot.rNode);//再遍历右边的
        }
        System.out.println("leave this call...");
    }

    public void preOrder(Node localRoot)
    {//中左右
        System.out.println("...........enter inOrder current node is: "+localRoot);
        if(localRoot !=null)
        {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!save "+localRoot);
            nodeLists.add(localRoot);
            System.out.println("begin to find left node of "+localRoot);
            preOrder(localRoot.lNode);//先遍历完左边的
            System.out.println("begin to find left node of "+localRoot);
            preOrder(localRoot.rNode);//再遍历右边的
        }
        System.out.println("leave this call...");
    }

    public void afterOrder(Node localRoot)
    {//左右中
        System.out.println("...........enter inOrder current node is: "+localRoot);
        if(localRoot !=null)
        {
            System.out.println("begin to find left node of "+localRoot);
            afterOrder(localRoot.lNode);//先遍历完左边的
            System.out.println("begin to find left node of "+localRoot);
            afterOrder(localRoot.rNode);//再遍历右边的
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!save "+localRoot);
            nodeLists.add(localRoot);
        }
        System.out.println("leave this call...");
    }


    public void delete(int id)
    {
        System.out.println("aaaaaa");
    }


}
