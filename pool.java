import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class pool {
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Thread thread1 = new executor("a");
		Thread thread2 = new executor("b");
		Thread thread3 = new executor("c");
		Thread thread4 = new executor("d");
		Thread thread5 = new executor("e");
		
		ExecutorService pool = Executors.newFixedThreadPool(5);
		pool.execute(thread1);
		pool.execute(thread2);
		pool.execute(thread3);
		pool.execute(thread4);
		pool.execute(thread5);
		
		pool.shutdown();
	}
	
	static class executor extends Thread {
		
		private final String name;
		
		public executor(String name) {
			this.name = name;
		}
		
		@Override
		public void run() {
			System.out.println("i am executed in executor pool : " + name);
		}
	}

}
