package com.security.threadLocal;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountLatchTest {

	public static void main(String[] args) {

		final CountDownLatch countDownLatch = new CountDownLatch(7);
		for (int i = 1; i <= 7; i++) {
			final int index = i;
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println("第" + index + "颗龙珠已收集到！");
						// 模拟收集第i个龙珠,随机模拟不同的寻找时间
						Thread.sleep(new Random().nextInt(3000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// 每收集到一颗龙珠,需要等待的颗数减1
					countDownLatch.countDown();
				}
			}).start();
		}
		// 等待检查，即上述7个线程执行完毕之后，执行await后边的代码
		try {
			countDownLatch.await();
			System.out.println("等待后....");
			System.out.println("集齐七颗龙珠！召唤神龙！");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
