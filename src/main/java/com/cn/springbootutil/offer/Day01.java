package com.cn.springbootutil.offer;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @description
 * @author: YZH
 * @create: 2020-03-30 10:19
 **/

public class Day01 {
    /**
     * Question 1
     * 在一个二维数组中（每个一维数组的长度相同），
     * 每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     */
    public static boolean Find(int target, int [][] array) {
        //最大行
            int row=array.length-1;
        //最大列
            int high=array[0].length-1;
        //当前行
            int nowRow=0;
            while (nowRow<=row && high>=0){
                //当前值（每一行的最右端的值）
                int val=array[nowRow][high];
                if (target>val){
                    nowRow++;
                }else if(target<val){
                    high--;
                }else {
                    return true;
                }
            }
        return false;
    }

    /**
     * Question 2
     * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
     * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     * @param str
     * @return
     */
    public static String replaceSpace(StringBuffer str) {
        StringBuffer s2=str;
        String s = str.toString().replaceAll("\\s", "%20");
        //replaceAll支持转义字符，replace不支持转义字符，都是全局替换，若只要替换一个可以使用
        String s3 = s2.toString().replace(" ", "%20");
        System.out.println(s3);
        s3.toString().replaceFirst("%20","");
        System.out.println(s3);
        return s;
    }


    /**
     * Question 3
     * 链表默认配置
     * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList
     */
    public class ListNode {
        int val;
        ListNode next = null;

                ListNode(int val) {
            this.val = val;
        }
   }
    /**
     * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList
     * @param listNode
     * @return
     */
    ArrayList<Integer> arrayList=new ArrayList<Integer>();
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if(listNode!=null){
            this.printListFromTailToHead(listNode.next);
            arrayList.add(listNode.val);
        }
        return arrayList;
    }

    /**
     * Question 4
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
     * 则重建二叉树并返回。
     * @param
     */
    public class TreeNode2 {
      int val;
      TreeNode2 left;
      TreeNode2 right;
      TreeNode2(int x) { val = x; }
  }
    public TreeNode2 reConstructBinaryTree(int [] pre,int [] in) {
        //TODO  二叉树
        return null;
    }


    /**
     * Question 5
     * 用两个栈来实现一个队列，完成队列的Push和Pop操作。
     * 队列中的元素为int类型
     * @param args
     */
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        while (!stack1.empty()){
            stack2.push(stack1.pop());
        }
        Integer pop = stack2.pop();
        //将拿出来的再放回去
        while (!stack2.empty()){
            stack1.push(stack2.pop());
        }
        return pop;
    }

    /**
     * Question 6
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0
     * @param array
     * @return
     */
    public int minNumberInRotateArray(int [] array) {
        if(array.length==0){
            return 0;
        }
        //TODO 自旋
        return 0;
    }

    /**
     * Question 7
     * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
     * n<=39
     * @param n
     * @return
     */
    public static int Fibonacci(int n) {
        int a=1,b=1,c=0;
        if(n<0){
            return 0;
        }else if(n==1||n==2){
            return 1;
        }else{
            for (int i=3;i<=n;i++){
                c=a+b;
                b=a;
                a=c;
            }
            return c;
        }
    }

    /**
     * Question 8
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
     * @param number
     */
    public int JumpFloor(int number) {
        if (number <= 0) {
            return 0;
        }
        if (number == 1) {
            return 1;
        }
        if (number == 2) {
            return 2;
        }
        int first = 1, second = 2, third = 0;
        for (int i = 3; i <= number; i++) {
            third = first + second;
            first = second;
            second = third;
        }
        return third;
    }

    /**
     * Question 9
     * 变态跳青蛙
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
     * @param target
     */
    public int JumpFloorII(int target) {
        if (target <= 0) {return 0;}
        return (int) Math.pow(2, target - 1);
    }

    public static void main(String[] args) {
        int [][] array={{1,2,3},{4,5,6},{10,12,14}};
        System.out.println(Find(12, array));

        StringBuffer s=new StringBuffer("We are beautiful");
        System.out.println( replaceSpace(s));
        System.out.println(Fibonacci(39));
        System.out.println( Math.pow(2,1));
        byte[] bs1 = {97,98,100};
        System.out.println("bs1: "+new String(bs1));

    }
}
