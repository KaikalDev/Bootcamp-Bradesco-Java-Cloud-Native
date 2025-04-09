package kaique.luan.dev.singleton;

public class SingletonLazyHolder {

    private static class InstanceHolder {
         public static SingletonLazyHolder instance;
    }

    private SingletonLazyHolder() {
        super();
    }

    public static SingletonLazyHolder getInstance() {
        if (InstanceHolder.instance == null) {
            InstanceHolder.instance = new SingletonLazyHolder();
        }
        return InstanceHolder.instance;
    }
}
