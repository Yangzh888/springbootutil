package com.cn.springbootutil.service.thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @author: YZH
 * @create: 2020-03-13 13:46
 **/
public class demo {
    public static void main(String[] args) throws InterruptedException {
        //核心数量5，最大数量10，无界队列，超出核心线程数量的线程存活时间5s，指定拒绝策略得
        //LinkedBlockingDeque是双向链表实现的双向并发阻塞队列。
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(
                5,10,5,
                TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        for(int i=0;i<15;i++){
            int n=i;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        System.out.println("开始执行 " + n);
                        Thread.sleep(300L);
                        System.out.println("执行结束 " + n);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }

            });
            System.out.println("任务执行成功"+n);
        }
        Thread.sleep(300);
        System.out.println("当前线程池数量为："+threadPoolExecutor.getPoolSize());
        System.out.println("当前线程池队列等待数量为："+threadPoolExecutor.getQueue().size());
        System.out.println("当前线程池核心线程数量为："+threadPoolExecutor.getCorePoolSize());
    }
    /**
     * BlockingDeque 队列具有 4 组不同的方法用于插入、移除以及对双端队列中的元素进行检查。
     * 如果请求的操作不能得到立即执行的话，每个方法的表现也不同。这些方法如下：
     *  	抛异常	         特定值	            阻塞	         超时
     * 插入	addFirst(o)	    offerFirst(o)	    putFirst(o)  	    offerFirst(o, timeout, timeunit)
     * 移除	removeFirst(o)	pollFirst(o)	takeFirst(o)	pollFirst(timeout, timeunit)
     * 检查	getFirst(o)	    peekFirst(o)
     *
     *
     *  	抛异常	        特定值	       阻塞	         超时
     * 插入	addLast(o)	    offerLast(o)	putLast(o)	 offerLast(o, timeout, timeunit)
     * 移除	removeLast(o)	pollLast(o)	    takeLast(o)	 pollLast(timeout, timeunit)
     * 检查	getLast(o)	   peekLast(o)
     *
     * 四组不同的行为方式解释：
     *
     * 抛异常：如果试图的操作无法立即执行，抛一个异常。
     * 特定值：如果试图的操作无法立即执行，返回一个特定的值(常常是 true / false)。
     * 阻塞：如果试图的操作无法立即执行，该方法调用将会发生阻塞，直到能够执行。
     * 超时：如果试图的操作无法立即执行，该方法调用将会发生阻塞，直到能够执行，但等待时间不会超过给定值。
     * 返回一个特定值以告知该操作是否成功(典型的是 true / false)。
     */

}
