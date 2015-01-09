
public class yield {

	private static int i = 1;
	
	public static void main(String[] args) {
		
		Thread thread1 = new innerThread1();
		Thread thread2 = new innerThread2();
		
//		thread1.setPriority(Thread.MAX_PRIORITY);
//		thread2.setPriority(Thread.MIN_PRIORITY);
		
		thread1.start();
		thread2.start();
		
	}
	
	static class innerThread1 extends Thread {
		
		private final int threadId = i++;
		
		@Override
		public void run() {
			for (int j=0; j<1000; j++) {
				System.out.println("#" + threadId + ": " + j);
				Thread.yield();
			}
		}
	}
	
	static class innerThread2 extends Thread {
		
		private final int threadId = i++;
		
		@Override
		public void run() {
			for (int j=0; j<1000; j++) {
				System.out.println("#" + threadId + ": " + j);
			}
		}
	}
	
	
	

}
