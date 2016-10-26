package com.pyjava.data_struct.tree.treedemo1;

/**
 * Created by wangheng on 2016/10/18.
 */
public class Node {
    int iData;   //节点代号
    double fData; // the realValue
    Node lNode=null;
    Node rNode=null;
    int level=0;

    public Node(int iData, double fData) {
        this.iData = iData;
        this.fData = fData;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getIData() {
        return iData;
    }

    public void setIData(int iData) {
        this.iData = iData;
    }

    public double getFData() {
        return fData;
    }

    public void setFData(double fData) {
        this.fData = fData;
    }

    public Node getLNode() {
        return lNode;
    }

    public void setLNode(Node lNode) {
        this.lNode = lNode;
    }

    public Node getRNode() {
        return rNode;
    }

    public void setRNode(Node rNode) {
        this.rNode = rNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "iData=" + iData +
                ", fData=" + fData +
                '}';
    }
}
