package net.yes.pattern.oberserver;

import java.util.ArrayList;
import java.util.List;

public class ConcreteWatched implements Watched {
	
	// 存放观察者
    private List<Watcher> list = new ArrayList<Watcher>();

    public void addWatcher(Watcher watcher)
    {
        list.add(watcher);
    }

    public void removeWatcher(Watcher watcher)
    {
        list.remove(watcher);
    }

    public void notifyWatchers(String str)
    {
        // 自动调用实际上是主题进行调用的
        for (Watcher watcher : list)
        {
            watcher.update(str);
        }
    }

}
