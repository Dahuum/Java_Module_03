public class TurnManager {

    private boolean eggTurn = true;
    
    public synchronized void printEgg() {
        while (!eggTurn) {
            try { wait(); } 
            catch (InterruptedException e) { System.err.println(e.getMessage()); }
        }
        System.out.println("Egg");
        eggTurn = false;
        notify(); // Hen!!!!!!!!!
    }
    
    public synchronized void printHen() {
        while (eggTurn) {
            try { wait(); } 
            catch (InterruptedException e) { System.err.println(e.getMessage()); }
        }
        System.out.println("Hen");
        eggTurn = true;
        notify(); // Egg!!!!!!
    }
    
}