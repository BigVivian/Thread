
public class threadSamp {

	/**
	 * @param args
	 */
	
	private static int i = 0;
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("Process1 begin");
		for (int k = 0; k < 5; k++) {
			Thread thread = new innerThread2();
			thread.start();
		}
		Thread.currentThread().sleep(5000);
		System.out.println("Process1 end");

		System.out.println("Process2 begin");
		for (int k = 0; k < 5; k++) {
			Thread thread = new Thread(new innerThread());
			thread.start();
		}
		Thread.currentThread().sleep(5000);
		System.out.println("Process2 end");
		
	}
	
	static class innerThread implements Runnable {
		
		private final int threadId = i++;
		
		public void run() {
			for (int j=0; j<5; j++) {
				System.out.println("#" + threadId + ": " + j);
			}
		}
	}
	
	static class innerThread2 extends Thread {
		
		private final int threadId = i++;
		
		@Override
		public void run() {
			for (int j=0; j<5; j++) {
				System.out.println("#" + threadId + ": " + j);
			}
		}
	}

}
