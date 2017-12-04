package com.security.threadLocal;

public class CustomTask implements Runnable {

	private int count=10;
	
	@Override
	public synchronized void run() {
		count--;
		System.out.println(Thread.currentThread().getName()+";count:"+count);
	}
	
	
	public static void main(String[] args) {
		CustomTask task=new CustomTask();
		for(int i=0;i<9;i++){
			new Thread(task).start();
		}
	}
}
