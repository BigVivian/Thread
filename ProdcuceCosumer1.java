import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class ProdcuceCosumer1 {

	private static final int MAX = 100;
	
	private static final Random random = new Random();
	
	private static List<Object> queue = new LinkedList<Object>(); 
	
	
	public static void main(String[] args) {
		new Producer().start();
		new Comsumer().start();
	}
	
	static class Producer extends Thread{
		@Override
		public void run() {
			while(true) {
				synchronized (queue) {
					try {
						if (queue.size() == MAX){
							queue.wait();
						} else {
							Object o = new Object();
							if (queue.add(o)) {
								System.out.println("Producer produce 1 object , the size of the queue is : " + queue.size());
							}
							queue.notifyAll();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	static class Comsumer extends Thread{
		@Override
		public void run() {
			while(true) {
				synchronized (queue) {
					try {
						if (queue.size() == 0){
							queue.wait();
						} else {
							queue.remove(0);
							System.out.println("Consumer comsume 1 object , the size of the queue is : " + queue.size());
							queue.notifyAll();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
}
