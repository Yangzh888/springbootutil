package com.cn.springbootutil;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.concurrent.*;

@RestController()
@SpringBootTest
class SpringbootutilApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	public void testCachedThreadPool(){
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 6, 3,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3)
		);

		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		//ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor();
		ArrayBlockingQueue arrayBlockingQueue=new ArrayBlockingQueue(10);
		Object poll = arrayBlockingQueue.poll();
		arrayBlockingQueue.add(new Runnable() {
			@Override
			public void run() {
				System.out.println("线程arrayBlockingQueue：");
			}
		});
		System.out.println(poll);
		for (int i = 0; i < 5; i++) {
			final int index = i;
			try {
				Thread.sleep(index * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cachedThreadPool.execute(new Runnable() {
				public void run() {
					System.out.println("线程1："+index);
				}
			} );

		}
	}
	@Test
	public void test(){
		long currentTimeMillis = System.currentTimeMillis();

		// 构造一个线程池
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 6, 3,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3)
		);

		for (int i = 1; i <= 10; i++) {
			try {
				String task = "task=" + i;
				System.out.println("创建任务并提交到线程池中：" + task);
				threadPool.execute(new ThreadPoolTask(task));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			//等待所有线程执行完毕当前任务。
			threadPool.shutdown();

			boolean loop = true;
			do {
				//等待所有线程执行完毕当前任务结束
				loop = !threadPool.awaitTermination(2, TimeUnit.SECONDS);//等待2秒
			} while (loop);

			if (loop != true) {
				System.out.println("所有线程执行完毕");
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("耗时：" + (System.currentTimeMillis() - currentTimeMillis));
		}


	}

	}



 class ThreadPoolTask implements Runnable, Serializable {

	 private Object attachData;

	 ThreadPoolTask(Object tasks) {
		 this.attachData = tasks;
	 }

	 public void run() {

		 try {

			 System.out.println("开始执行任务：" + attachData + "任务，使用的线程池，线程名称：" + Thread.currentThread().getName());

			 System.out.println();

		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 attachData = null;
	 }


 }

