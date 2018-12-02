package com.pyjava.thread.threadlocal;


/**
 * @ClassName: ThreadLocalDemo
 * @author wangheng8
 * @date: 2018年10月8日 下午2:42:43

 */

public class ThreadLocalDemo {
	
	ThreadLocal<Persion> threadLocal = new ThreadLocal<Persion>();
	
	static class Persion
	{
		private int age;
		private int name;
		
		public int getAge() {
			return age;
		}


		public void setAge(int age) {
			this.age = age;
		}


		public int getName() {
			return name;
		}


		public void setName(int name) {
			this.name = name;
		}


		@Override
		public String toString() {
			return "Persion [age=" + age + ", name=" + name + "]";
		}
		
	}
	
	class PersionManager
	{
		private ThreadLocal<Persion> threadLocal = new ThreadLocal<Persion>();
		
		public void setAge(int age)
		{
			Persion persion = threadLocal.get();
			if(null != persion)
			{
				persion.setAge(age);
			}
			else
			{
				persion = new Persion();
				persion.setAge(age);
				threadLocal.set(persion);
			}
		}
		
		public int getAge()
		{
			return threadLocal.get().getAge();
		}
	}
	
	
	class mytask implements Runnable
	{
		
		private PersionManager persionManager ;
		
		mytask(PersionManager persionManager )
		{
			this.persionManager = persionManager;
		}
		@Override
		public void run() {
			persionManager.setAge(5);
			
			System.out.println(this.getAge());
		}
		
		public int getAge()
		{
			int age = persionManager.getAge();
			return age;
		}
		
	}
	
	public void test() throws InterruptedException
	{
		final PersionManager persionManager = new PersionManager();	
		
		persionManager.setAge(1);
		System.out.println(persionManager.getAge());
		
		mytask mytask = new mytask(persionManager);
		Thread thread = new Thread(mytask);
		thread.start();
		
		thread.join();
		
		
		System.out.println(persionManager.getAge());
		
		
	}
	
	
	



	public static void main(String[] args) throws InterruptedException {
		
		 new ThreadLocalDemo().test();
	}

}
