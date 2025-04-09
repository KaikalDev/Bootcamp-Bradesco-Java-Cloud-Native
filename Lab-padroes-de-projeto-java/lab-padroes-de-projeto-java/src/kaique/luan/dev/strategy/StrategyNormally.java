package kaique.luan.dev.strategy;

public class StrategyNormally implements Strategy {
    @Override
    public void move() {
        System.out.println("Movendo-se Normalmente...");
    }
}
