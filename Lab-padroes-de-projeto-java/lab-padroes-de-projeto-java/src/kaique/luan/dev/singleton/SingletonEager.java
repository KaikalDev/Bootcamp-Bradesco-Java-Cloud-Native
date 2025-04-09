package kaique.luan.dev.singleton;

public class SingletonEager {

    private final static SingletonEager instance = new SingletonEager();;

    private SingletonEager() {
        super();
    }

    public static SingletonEager getInstance() {
        return instance;
    }
}
