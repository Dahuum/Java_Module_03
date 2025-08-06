public class Program {
    private static volatile boolean running = true;
    private static int slow = 0, fast = 0;
    
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            try { 
                long start = System.nanoTime() ;
                while (running) { 
                    System.out.println("Hi!  " + (System.nanoTime() - start)/1000000.0 + "ms");
                    Thread.sleep(1);
                }
            }  // Infinite loop
            catch (InterruptedException e) {}
        };
        
        Thread t = new Thread(task);
        t.setDaemon(true);  // <-- THE MAGIC LINE
        long start = System.nanoTime();
        t.start();
        
        try { Thread.sleep(3); }  // Give daemon thread time
        catch (InterruptedException e) {}
        
        System.out.println("Main exiting after " + (System.nanoTime()-start)/1000000.0 + "ms");
        
        Thread fastThread = new Thread(() -> {
            while (running) {
                double x = Math.pow(Math.PI, Math.E); // CPU word
                Thread.yield();
                slow++;
            }
        });
        fastThread.setPriority(Thread.MAX_PRIORITY);
        
        Thread slowThread = new Thread(() -> {
            while (running) {
                double x = Math.pow(Math.PI, Math.E); // CPU word
                fast++;
            }
        });
        slowThread.setPriority(Thread.MIN_PRIORITY);
        
        fastThread.start();
        slowThread.start();
        Thread.sleep(500);
        running = false;
        
        System.out.println("\n\nslow: " + slow);
        System.out.println("fast: " + fast);
        System.exit(0);
    }
}