/**
 *  TODO: add class header
 */
public class SunshineMarket {
    // Customer
    private static int[] customers1 = {3, 7, 20};
    private static int[] customers2 = {1, 3, 5, 4, 16, 8};
    private static int[] customers3 = {1, 1, 2, 3, 5, 7};
    
    private static QueueADT customersQueue;
    private static QueueADT[] registers;


    
    /**
     * This is the program entry where we will run our simulation
     *
     * @param args commandline arguments
     */
    public static void main(String[] args) {
        
        SunshineMarket.timeInfo(customers1, 3);
    }
    
    /**
     * Returns a string that contains information about total time
     * for checking out all customers and register idle time.
     * @param customers the customer queue
     * @param numberOfRegisters number of registers opened
     * @return a string that contains information about total time
     *         for checking out all customers and register idle time.
     */
//    change string to queaft
    public static String timeInfo(int[] customers, int numberOfRegisters) {
        int totalTime = 0;
        int registersIdleTime = 0;
        customersQueue = new CircularArrayQueue();
        registers = new QueueADT[numberOfRegisters];
        for (int i = 0; i < numberOfRegisters; i++) {
            registers[i] = new CircularArrayQueue();
        }
        for (int j = 0; j < customers.length; j++) {
            customersQueue.add(customers[j]);
            QueueADT temporaryRegister = findFirstEmptyRegister();
            for (int z = 0; z < customers[j]; z++) {
                temporaryRegister.add(1);
            }
            customersQueue.remove();
        }
        System.out.println(registers.length);
//        int j = 0;
//        while (j < customers.length) {
//
//        }





        return "With " + numberOfRegisters +
        " lines, the total time for checking out all customers was "
        + totalTime + " time units, and registers were idle for a total of "
        + registersIdleTime + " time units.\n";
    }
    
    /**
     * Helper method to find the first empty register. Return null if all registers are not empty.
     * @return the first empty register
     */
    private static QueueADT findFirstEmptyRegister() {
        
        for (int i = 0; i < registers.length; i++) {
            if (registers[i].isEmpty()) {
                return registers[i];
            }
        }
        return null;
    }
    
    /**
     * Helper method to determine if the market is empty
     *
     * @return true if the whole market is empty
     */
    private static boolean marketIsEmpty() {
        
        // TODO
        
        return false;
    }
}
