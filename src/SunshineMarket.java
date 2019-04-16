/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
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
//        SunshineMarket.timeInfo(customers1, 1);
        SunshineMarket.timeInfo(customers2, 2);
        SunshineMarket.timeInfo(customers2, 3);
        SunshineMarket.timeInfo(customers2, 4);
        SunshineMarket.marketIsEmpty();

    }
    
    /**
     * Returns a string that contains information about total time
     * for checking out all customers and register idle time.
     * @param customers the customer queue
     * @param numberOfRegisters number of registers opened
     * @return a string that contains information about total time
     *         for checking out all customers and register idle time.
     */

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
            if (temporaryRegister != null) {
                for (int z = 0; z < customers[j]; z++) {
                    temporaryRegister.add(1);
                }
            }
            else {
                QueueADT temporaryRegister2 = registers[0];
                for (int k = 0; k < numberOfRegisters; k++) {
                    if (registers[k].size() < temporaryRegister2.size()) {
                        temporaryRegister2 = registers[k];
                    }
                }
                for (int z = 0; z < customers[j]; z++) {
                    temporaryRegister2.add(1);
                }
            }
            customersQueue.remove();
        }
        for (int k = 0; k < numberOfRegisters; k++) {
            if (totalTime < registers[k].size()) {
                totalTime = registers[k].size();
            }
        }
        int removedTime = 0;
        for (int f = 0; f < totalTime; f++) {
            for (int j = 0; j < numberOfRegisters; j++) {
                if (!registers[j].isEmpty()) {
                    registers[j].remove();
                }
                else {
                    registersIdleTime++;
                }
                removedTime += registers[j].size();
            }
        }
//        System.out.println(Arrays.toString(((CircularArrayQueue)registers[0]).getter()));
//        System.out.println(Arrays.toString(((CircularArrayQueue)registers[1]).getter()));
//        System.out.println(Arrays.toString(((CircularArrayQueue)registers[2]).getter()));
//        System.out.println(totalTime);
//        System.out.println(registersIdleTime);
        return "With " + numberOfRegisters
        + " lines, the total time for checking out all customers was "
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
        boolean result = false;
        for (int i = 0; i < registers.length; i++) {
            if (registers[i].isEmpty()) {
                result = true;
            }
        }
//        System.out.println((result == true && customersQueue.isEmpty()));
        return (result && customersQueue.isEmpty());
    }
}
