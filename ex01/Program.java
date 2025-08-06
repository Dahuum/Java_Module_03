
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
        TurnManager traffic = new TurnManager();
        
        Thread EggThread = new Thread(new EggRunnable(count, traffic));
        Thread HenThread = new Thread(new HenRunnable(count, traffic));

        EggThread.start();
        HenThread.start();
    }
    
}