
public class TaskRunnable implements Runnable {
    private final int count;
    private final String toPrint;
    
    public TaskRunnable(int count, String toPrint) {
        this.count = count;
        this.toPrint = toPrint;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println(toPrint);
        }
    }
}