package net.yes.pattern;

public class Test {

	public static void main(String[] args) {
		Watched watched = new ConcreteWatched();

		Watcher watcher1 = new ConcreteWatcher();
		Watcher watcher2 = new ConcreteWatcher();
		Watcher watcher3 = new ConcreteWatcher();	
		
		System.out.println("----------------添加观测者-------------");
		watched.addWatcher(watcher1);
		watched.addWatcher(watcher2);
		watched.addWatcher(watcher3);

		System.out.println("----------------发布信息1-------------");		
		watched.notifyWatchers("开心1");
		
		System.out.println("----------------发布信息2-------------");		
		watched.removeWatcher(watcher3);
		watched.notifyWatchers("开心2");
	}

}
