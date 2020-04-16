package com.cn.springbootutil.offer;

import java.util.*;

/**
 * @description
 * @author: YZH
 * @create: 2020-04-08 09:46
 **/
public class Day03 {
    public int reverse(int x) {
        /**
         * 输入-542 返回-245
         * 输入-5420 返回-245
         * 输入542 返回254
         */
        StringBuilder result = new StringBuilder();
        boolean isD = false;
        int a = 0;
        if (x > 0) {
            char[] s = String.valueOf(x).toCharArray();
            for (int i = s.length; i > 0; i--) {
                if (s[i - 1] != 0) {
                    result.append(s[i - 1]);
                }
                a++;
            }
        } else if (x < 0) {
            x = x - 2 * x;
            isD = true;
            char[] s = String.valueOf(x).toCharArray();
            for (int i = s.length; i > 0; i--) {
                if (s[i - 1] != 0) {
                    result.append(s[i - 1]);
                }
                a++;
            }
        }else {
            return 0;
        }
        Long integer=0L;
        try{
            integer = Long.valueOf(result.toString());
            if (isD) {
                integer = integer - 2 * integer;
            }
            return Integer.valueOf(integer.toString());
        }catch(Exception e){
            return 0;
        }
    }

    /**
     * 爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 块糖的大小，B[j] 是鲍勃拥有的第 j 块糖的大小。
     *
     * 因为他们是朋友，所以他们想交换一个糖果棒，这样交换后，他们都有相同的糖果总量。（一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）
     *
     * 返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1] 是 Bob 必须交换的糖果棒的大小。
     *
     * 如果有多个答案，你可以返回其中任何一个。保证答案存在。
     * 示例 1：
     *
     * 输入：A = [1,1], B = [2,2]
     * 输出：[1,2]
     * 示例 2：
     *
     * 输入：A = [1,2], B = [2,3]
     * 输出：[1,2]
     * 示例 3：
     *
     * 输入：A = [2], B = [1,3]
     * 输出：[2,3]
     * 示例 4：
     *
     * 输入：A = [1,2,5], B = [2,4]
     * 输出：[5,4]
     * @param A
     * @param B
     * @return
     */
    public int[] fairCandySwap(int[] A, int[] B) {
        int sa = 0, sb = 0;  // sum of A, B respectively
        for (int x: A){ sa += x;}
        for (int x: B){ sb += x;}
        int delta = (sb - sa) / 2;
        // If Alice gives x, she expects to receive x + delta

        Set<Integer> setB = new HashSet();
        for (int x: B) {setB.add(x);}

        for (int x: A) {
            if (setB.contains(x + delta)){
                return new int[]{x, x + delta};
            }
        }
        throw null;
    }

    /**
     *
     给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。

     如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
     示例 1：
     输入：arr = [1,2,2,1,1,3]
     输出：true
     解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
     示例 2：

     输入：arr = [1,2]
     输出：false
     示例 3：

     输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
     输出：true
     * @param arr
     * @return
     */
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer,Integer> map=new HashMap(16);
        HashSet<Integer> set=new HashSet();
        for(int i=0;i<arr.length;i++){
            map.put(arr[i],(map.get(arr[i]))!=null?(map.get(arr[i])+1):1);  //存储每个数字的次数
        }
        for(Integer v:map.values()){
            if(!set.add(v)){            //通过set判断是否有重复
                return false;
            }
        }
        return true;
    }

    /**
     *
     你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

     给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

     示例 1:

     输入: [1,2,3,1]
     输出: 4
     解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。
     示例 2:

     输入: [2,7,9,3,1]
     输出: 12
     解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     * @param num
     * @return
     */
    public int rob(int[] num) {
        int prevMax = 0;
        int currMax = 0;
        for (int x : num) {
            int temp = currMax;
            currMax = Math.max(prevMax + x, currMax);
            prevMax = temp;
        }
        return currMax;
    }


    public static void main(String[] args) {
        Day03 day03 = new Day03();
       // System.err.println(day03.reverse(1534236469));
       // int[] a=new int[]{0,1,2,3,6,2};
        // int[] b=new int[]{1,2,3,6,4};
        // System.out.println(day03.fairCandySwap(a,b)[0]+"  "+day03.fairCandySwap(a,b)[1]);

        int[] c=new int[]{2,2,1};
        //day03.uniqueOccurrences(c);
        int[] d=new int[]{2,7,9,3,1};

    }
}
