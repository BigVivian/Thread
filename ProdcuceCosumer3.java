import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * BlockingQueue<Object>
 */
public class ProdcuceCosumer3 {

	private static final int MAX = 100;
	
	private static BlockingQueue<Object> queue = new LinkedBlockingQueue<Object>(MAX);
	
	
	public static void main(String[] args) {
		new Producer().start();
		new Comsumer().start();
	}
	
	static class Producer extends Thread{
		@Override
		public void run() {
			while(true) {
				if (queue.size() == MAX){
					System.out.println("queue full");
				} 
				try {
					queue.put(new Object());
					System.out.println("put one , the queue size is : " + queue.size());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	static class Comsumer extends Thread{
		@Override
		public void run() {
			while(true) {
				if (queue.size() == 0){
					System.out.println("queue empty");
				} 
				try {
					queue.take();
					System.out.println("get one , the queue size is : " + queue.size());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
