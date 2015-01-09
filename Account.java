
public class Account {

	private volatile int balance;

	public Account(int balance) {
		this.balance = balance;
	}

	public int getBalance() {
		return balance;
	}

	public void add(int num) {
		int temp =  balance + num;
		Thread.yield();
		balance = temp;
//		balance = balance + num;
	}

	public void withdraw(int num) {
		int temp =  balance - num;
		Thread.yield();
		balance = temp;
//		balance = balance - num;
	}

	public static void main(String[] args) throws InterruptedException {
		Account account = new Account(0);
		Thread a = new Thread(new AddThread(account, 1), "add");
		Thread b = new Thread(new WithdrawThread(account, 1), "withdraw");
		a.start();
		b.start();
		a.join();
		b.join();
		System.out.println(account.getBalance());
	}

	static class AddThread implements Runnable {
		Account account;
		int amount;

		public AddThread(Account account, int amount) {
			this.account = account;
			this.amount = amount;
		}

		public void run() {
			for (int i = 0; i < 200000; i++) {
				account.add(amount);
			}
		}
	}

	static class WithdrawThread implements Runnable {
		Account account;
		int amount;

		public WithdrawThread(Account account, int amount) {
			this.account = account;
			this.amount = amount;
		}

		public void run() {
			for (int i = 0; i < 200000; i++) {
				account.withdraw(amount);
			}
		}
	}
}