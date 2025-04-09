package kaique.luan.dev.strategy;

public class StrategyDefensive implements Strategy {
    @Override
    public void move() {
        System.out.println("Movendo-se Defencivamente...");
    }
}
