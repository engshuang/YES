package net.yes.pattern.oberserver;

public class ConcreteWatcher implements Watcher {

	public void update(String str) {
		System.out.println(this.hashCode() + ":" +str);
	}

}
