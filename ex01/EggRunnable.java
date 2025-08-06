
public class EggRunnable implements Runnable {
    private final int count;
    private TurnManager traffic;
    
    public EggRunnable(int count, TurnManager traffic) {
        this.count = count;
        this.traffic = traffic;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            traffic.printEgg();
        }
    }
}