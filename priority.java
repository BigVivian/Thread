
public class priority {

	private static int i = 0;
	
	public static void main(String[] args) throws Exception {
		Thread thread1 = new innerThread2();
//		thread1.setPriority(1);
		thread1.start();
		
		Thread thread2 = new innerThread2();
		thread2.start();

		Thread thread3 = new innerThread2();
//		thread3.setPriority(Thread.MAX_PRIORITY);
		thread3.start();
	}
	
	static class innerThread2 extends Thread {
		
		private final int threadId = i++;
		
		@Override
		public void run() {
			for (int j=0; j<10; j++) {
				System.out.println("#" + threadId + ": " + j);
				try {
					Thread.currentThread().sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
