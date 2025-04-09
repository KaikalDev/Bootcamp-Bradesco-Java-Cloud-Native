package kaique.luan.dev.strategy;

public class StrategyAggressive implements Strategy {
    @Override
    public void move() {
        System.out.println("Movendo-se Agressivamente...");
    }
}
