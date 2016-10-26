package com.pyjava.data_struct.tree.treedemo1;

import com.pyjava.sort.CreateList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wangheng on 2016/10/18.
 */
public class TreeApp {



    public static void main(String[] args) {
        TreeApp treeApp=new TreeApp();
        Integer [] arrInt={41, 97, 89, 80, 93, 81, 17, 35, 55, 87};
        List<Integer> lists =null;
        Tree tree =new Tree();
//        lists=new CreateList().getLists(10);
        lists= Arrays.asList(arrInt);
        System.out.println("lists is: "+lists);

        for( int i=0;i<lists.size();i++)
        {
            tree.insert(i,lists.get(i)+0.0);
        }
//        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//        treeApp.printTree(tree,lists);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("get min num "+tree.findMin());
        System.out.println("get max num "+tree.findMax());

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ begin to 中序遍历........");
        tree.nodeLists.clear();
        tree.inOrder(tree.root);
        TreeApp.printTree(tree.nodeLists);

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ begin to 前序遍历........");
        tree.nodeLists.clear();
        tree.preOrder(tree.root);
        TreeApp.printTree(tree.nodeLists);

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ begin to 后序遍历........");
        tree.nodeLists.clear();
        tree.afterOrder(tree.root);
        TreeApp.printTree(tree.nodeLists);
    }

//    public void printTree(Tree tree, List<Integer> lists)
//    {
//
//        for (int i=0;i<lists.size();i++)
//        {
//            Node current =tree.find(i);
//            if(null!=current)
//                System.out.println(current.getLevel());
//        }
//    }
    public static void printTree(List<Node> nodeLists)
    {
        for(Node node : nodeLists)
        {
            System.out.print(node.getFData()+",");
        }
        System.out.println();

    }

}
