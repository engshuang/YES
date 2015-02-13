package net.yes.mom;

public class TestMom {
	public static void main(String[] args) throws InterruptedException {
		String url = "tcp://localhost:61616";
		String user = null;
		String password = null;
		
		if (args.length >= 1) {
			url = args[0];
		}
		
		if (args.length >= 2) {
			user = args[1];
		}

		if (args.length >= 3) {
			password = args[2];
		}
		
		Sender v = new Sender(url, user, password);
		Reciver r = new Reciver(url, user, password);
		
		new Thread(v, "Sender").start();
		new Thread(r, "Reciver").start();
		
	}
}
