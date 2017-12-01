package com.security.threadLocal;

/**
 *volatile解决的是变量在多个线程之间的可见性;
 * @author Feng
 *
 */
public class TestVolatile {

	private volatile boolean isRunning = true;

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public void printThreadName() {
		while (isRunning) {
			System.out.println("--->" + Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		final TestVolatile tv = new TestVolatile();
		new Thread(new Runnable() {

			@Override
			public void run() {
				tv.printThreadName();
				System.out.println("--->设置isRunning为false");
				tv.setRunning(false);
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("设置isRunning为false");
				tv.setRunning(false);
			}
		}).start();
	}
}
