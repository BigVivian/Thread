
public class dameon {
	
	private static int i = 1;
	
	public static void main(String[] args) {
		
		System.out.println("main begin");
		Thread thread1 = new innerThread();
		Thread dameon = new dameonThread();
		dameon.setDaemon(true);
		
		thread1.start();
		dameon.start();
		System.out.println("main end");
		
	}
	
	static class innerThread extends Thread {
		
		private final int threadId = i++;
		
		@Override
		public void run() {
			System.out.println("thread begin");
			for (int j=0; j<50; j++) {
				System.out.println("#" + threadId + ": " + j);
			}
			System.out.println("thread end");
		}
	}
	
	static class dameonThread extends Thread {
		
		
		@Override
		public void run() {
			for (int j=0; j<50; j++) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("#dameon"  + ": " + j);
			}
		}
	}
}
