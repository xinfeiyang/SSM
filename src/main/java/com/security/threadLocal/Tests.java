package com.security.threadLocal;

public class Tests {
	
	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
		protected Integer initialValue() {
			return 0;
		};
	};
	
	public static int getNextNum(){
		threadLocal.set(threadLocal.get()+1);
		return threadLocal.get();
		
	}

	public static void main(String[] args) {
		for(int i=0;i<3;i++){
			new Thread(){
				public void run() {
					 for (int i = 0; i < 3; i++) {
		                    System.out.println(Thread.currentThread().getName()+":"+ getNextNum());
		              }
				};
			}.start();
		}
		String name="fenglang";
		String str=name.intern();
		System.out.println(Thread.currentThread().getName()+"--------->"+str);
	}
}
