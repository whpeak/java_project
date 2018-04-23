package com.pyjava.data_struct.array.binarysearch;

/**
 * 关键点:
 *当lowIndex 大于 highIndex 的时候,停止遍历.return 查无此数.
 *
 * Created by wangheng on 2017/11/3.
 */
public class BinarySearch {
    private static BinarySearch ourInstance = new BinarySearch();

    public static BinarySearch getInstance() {
        return ourInstance;
    }

    private BinarySearch() {
    }
    public int binarySearch(int [] soredArray,int target)
    {

        int lowIndex = 0;
        int highIndex = soredArray.length-1;
        int currentI;
        int currentV ;
        while(true)
        {
            currentI = this.getMIdd(lowIndex,highIndex);
            currentV = soredArray[currentI];
            if(currentV == target)
            {
                return currentI;
            }
            else if(lowIndex>highIndex)
            {
                return -1;
            }
            else
            {
               if(currentV < target) //当前值小于目标值,在大空间查找,移动小指针.
               {
                   lowIndex = currentI+1;
               }
                else
               {
                   highIndex = currentI-1;
               }
            }

        }


    }

    public int getMIdd(int a, int b)
    {
        return (a + b)/2;

    }

    public static void main(String[] args) {
        int [] a = new int[]{1,2,3,4,5};
        int b = BinarySearch.getInstance().binarySearch(a,3);
        System.out.println("the index is: "+b);
    }

}
