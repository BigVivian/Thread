import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;


public class ProdcuceCosumer4 {

	private static final int MAX = 100;
	
	private static Random random = new Random();
	
	public static void main(String[] args) throws Exception {
		Producer produce = new Producer();
		Comsumer consume = new Comsumer(produce.getPipedWriter());
		produce.start();
		consume.start();
	}
	
	static class Producer extends Thread{
		
		private PipedWriter pw = new PipedWriter();
		
		public void run() {
			while(true) {
				for(char c = 'A'; c<='Z'; c++) {
					try {
						pw.write(c);
						System.out.println("produce : " + c);
						Thread.sleep(random.nextInt(500));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		public PipedWriter getPipedWriter() {
			return pw;
		}
	}

	static class Comsumer extends Thread{
	
		private PipedReader pr;
		
		public Comsumer(PipedWriter pw) throws Exception {
			this.pr = new PipedReader(pw);
		}
		
		public void run() {
			while(true) {
				try {
					System.out.println("reader ：" + pr.read());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
