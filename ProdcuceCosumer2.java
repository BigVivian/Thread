import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock & Condition
 * 1.Lock.lock();    Lock.unlock();
 * 2.Condition.await();  Condition.signal();
 */
public class ProdcuceCosumer2 {

	private static final int MAX = 100;
	
	private static final Random random = new Random();
	
	private static List<Object> queue = new LinkedList<Object>(); 
	
	private static Lock lock = new ReentrantLock();
	
	private static Condition notFullCondition = lock.newCondition();
	private static Condition notEmptyCondition = lock.newCondition();
	
	
	
	public static void main(String[] args) {
		new Producer().start();
		new Comsumer().start();
	}
	
	static class Producer extends Thread{
		@Override
		public void run() {
			while(true) {
				lock.lock();
				try {
					if (queue.size() == MAX){
						notFullCondition.await();
					} else {
						Object o = new Object();
						if (queue.add(o)) {
							System.out.println("Producer produce 1 object , the size of the queue is : " + queue.size());
						}
						notEmptyCondition.signal();
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}
	}

	static class Comsumer extends Thread{
		@Override
		public void run() {
			while(true) {
				lock.lock();
				try {
					if (queue.size() == 0){
						notEmptyCondition.await();
					} else {
						queue.remove(0);
						System.out.println("Consumer comsume 1 object , the size of the queue is : " + queue.size());
						notFullCondition.signal();
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}
	}
	
}
