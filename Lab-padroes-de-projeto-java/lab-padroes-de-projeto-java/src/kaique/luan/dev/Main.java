package kaique.luan.dev;

import kaique.luan.dev.facade.Facade;
import kaique.luan.dev.singleton.*;
import kaique.luan.dev.strategy.*;

public class Main {
    public static void main(String[] args) {
        // Singleton
        System.out.println("\n \n--------Singleton--------\n \n");

        SingletonLazy lazy = SingletonLazy.getInstance();
        System.out.println("Lazy: " + lazy);
        if (lazy == SingletonLazy.getInstance()) {
            System.out.println("Success");
        } else {
            System.out.println("Fail");
        }

        SingletonEager eager = SingletonEager.getInstance();
        System.out.println("Eager: " + eager);
        if (eager == SingletonEager.getInstance()) {
            System.out.println("Success");
        } else {
            System.out.println("Fail");
        }

        SingletonLazyHolder lazyHolder = SingletonLazyHolder.getInstance();
        System.out.println("Lazy Holder: " + lazyHolder);
        if (lazyHolder == SingletonLazyHolder.getInstance()) {
            System.out.println("Success");
        } else {
            System.out.println("Fail");
        }

        //Strategy
        System.out.println("\n \n--------Strategy--------\n \n");

        Strategy normally = new StrategyNormally();
        Strategy aggressive = new StrategyAggressive();
        Strategy defensive = new StrategyDefensive();

        Robot robot = new Robot();

        robot.setStrategy(normally);
        robot.move();

        robot.setStrategy(defensive);
        robot.move();

        robot.setStrategy(aggressive);
        robot.move();

        //Strategy
        System.out.println("\n \n--------Facade--------\n \n");

        Facade facade = Facade.getInstance();
        facade.migrarCliente("KaikalDev", "999999-999");
    }
}
