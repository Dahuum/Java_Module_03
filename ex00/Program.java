
public class Program {
    
    public static void main ( String [] args ) {
        
        int count = 0;
        if (args.length == 1 && args[0].startsWith("--count=")) {
            String [] param = args[0].split("=", 2);
            try {
                count = Integer.parseInt(param[1]);
            } catch (NumberFormatException e) { 
                System.err.println("Program: invalid count value");
                System.exit(1);
            }
        } else {
            System.err.println("Program: missing operand");
            System.err.println("Usage: java Program --count=NUMBER");
            System.exit(1);
        }    
        
        Thread EggThread = new Thread(new TaskRunnable(count, "Egg"));
        Thread HenThread = new Thread(new TaskRunnable(count, "Hen"));

        EggThread.start();
        HenThread.start();
        
        try {
            EggThread.join();
        } catch (Exception e) { System.err.println(e.getMessage()); }
        try {
            HenThread.join();
        } catch (Exception e) { System.err.println(e.getMessage()); }
        
        for (int i = 0; i < count; i++) {
            System.out.println("Human");
        }
        
    }
    
}