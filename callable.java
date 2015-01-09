import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class callable {

	public static void main(String[] args) {
		
		Call c1 = new Call("1");
		Call c2 = new Call("2");
		Call c3 = new Call("3");
		
		ExecutorService exec = Executors.newFixedThreadPool(2);
		Future<String> f1 = exec.submit(c1);
		Future<String> f2 = exec.submit(c2);
		Future<String> f3 = exec.submit(c3);
		
		try {
			System.out.println(f1.get());
			System.out.println(f2.get());
			System.out.println(f3.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static class Call implements Callable<String> {

		private final String name ;
		
		public Call(String name) {
			this.name = name;
		}
		
		
		@Override
		public String call() throws Exception {
			for (int i = 0; i<100; i++) {
				System.out.println("#"+name+" : "+i);
			}
			return "#" + name + "done!";
		}
	}
}
