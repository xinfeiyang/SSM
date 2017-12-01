package com.security.threadLocal;

public class MyThread extends Thread {

	private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
		protected Integer initialValue() {
			return 0;
		};
	};
	
	public int getNextNun(){
		threadLocal.set(threadLocal.get()+1);
		return threadLocal.get();
	}

	@Override
	public void run() {
		super.run();
		for(int i=0;i<3;i++){
			
		}
		System.out.println(Thread.currentThread().getName()+":"+getNextNun());
	}
}
