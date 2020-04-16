package com.cn.springbootutil.offer;

/**
 * @description
 * @author: YZH
 * @create: 2020-04-07 14:11
 **/
public class Day02 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    /**
     *  给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     *
     *     如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     *
     *     您可以假设除了数字 0 之外，这两个数都不会以 0 开头
     *     输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     *     输出： 7 -> 0 -> 8
     *     原因：342 + 465 = 807
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy=new ListNode(0);
        ListNode p=l1,q=l2,result=dummy;
        int carry=0;
        while (p!=null || q !=null){
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum=carry+x+y;
            carry=sum/10;
            result.next=new ListNode(sum % 10);
            result=result.next;
            if(p!=null){
                p=p.next;
            }
            if(q!=null){
                q=q.next;
            }
        }
        if(carry>0){
            result.next=new ListNode(carry);
        }
        return dummy.next;
    }
    public void testAddTwoNumbers() {
        Day02 day02=new Day02();
        ListNode listNode=new ListNode(2);
        listNode.next=new ListNode(4);
        listNode.next.next=new ListNode(3);
        ListNode listNode2=new ListNode(5);
        listNode2.next=new ListNode(6);
        listNode2.next.next=new ListNode(4);
        ListNode listNode1 = day02.addTwoNumbers(listNode, listNode2);
    }

    /**
     * 假设你是一位顺风车司机，车上最初有 capacity 个空座位可以用来载客。由于道路的限制，车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向，你可以将其想象为一个向量）。
     *
     * 这儿有一份行程计划表 trips[][]，其中 trips[i] = [num_passengers, start_location, end_location] 包含了你的第 i 次行程信息：
     *
     * 必须接送的乘客数量；
     * 乘客的上车地点；
     * 以及乘客的下车地点。
     * 这些给出的地点位置是从你的 初始 出发位置向前行驶到这些地点所需的距离（它们一定在你的行驶方向上）。
     *
     * 请你根据给出的行程计划表和车子的座位数，来判断你的车是否可以顺利完成接送所用乘客的任务（当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false）。
     *示例 1：
     *
     * 输入：trips = [[2,1,5],[3,3,7]], capacity = 4
     * 输出：false
     * 示例 2：
     *
     * 输入：trips = [[2,1,5],[3,3,7]], capacity = 5
     * 输出：true
     * 示例 3：
     *
     * 输入：trips = [[2,1,5],[3,5,7]], capacity = 3
     * 输出：true
     * 示例 4：
     *
     * 输入：trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
     * 输出：true
     *
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPooling(int[][] trips, int capacity) {
        int[] arr = new int[1001];
        for(int i=0;i<trips.length;i++){
            int num = trips[i][0];
            int start = trips[i][1];
            int end = trips[i][2];
            arr[start] -= num; //上车地点减去对应容量
            arr[end] += num; //下车地点恢复对应容量
        }
        for(int i=0;i<arr.length;i++){
            capacity += arr[i]; //加上每个地点的容量（可能为正，可能为负）
            if(capacity<0) // 容量不够
            { return false;}
        }
        return true;
    }
    public Boolean testCarPooling(){
        Day02 day02=new Day02();
        int[][] a=new int[][]{{7,5,6},{6,7,8},{10,1,6}};
        return day02.carPooling(a,16);
    }

    public static void main(String[] args) {
        Day02 day02=new Day02();
        //day02.testAddTwoNumbers();
        day02.testCarPooling();
    }
}
