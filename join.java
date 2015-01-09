
public class join {
	
	private static int i = 0;
	
	public static void main(String[] args) {
		Thread thread1 = new innerThread();
		thread1.setPriority(Thread.MIN_PRIORITY);
		thread1.start();
		
		for (int k = 0; k<50 ; k++) {
			System.out.println("#main: " + k);
			if (k>20) {
				try {
					thread1.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		}
	}
	
	static class innerThread extends Thread {
		
		private final int threadId = i++;
		
		@Override
		public void run() {
			for (int j=0; j<50; j++) {
				System.out.println("#" + threadId + ": " + j);
			}
		}
	}
}
