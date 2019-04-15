/**
 * The SavingsAccount class extends Account.
 * The minimum amount a customer can keep in a savings account is $100.
 * If the customer deposits at least $5000 initially the bank gives them an additional $100 bonus
 * This account earns interest at a rate specified by the bank.
 * It allows deposits with no fees.
 * Withdrawals are charged $2 per withdrawal.
 * The balance cannot go below $100 through a withdrawal.
 *
 */
public class SavingsAccount extends Account {

    /**
     * Constructor for our banks savings accounts
     *
     * If initial deposit is at least $5000 the customer recieves a $100 bonus
     *
     * @param userid The identifier associated with this Savings Account
     * @param initialDeposit The starting balance for this new Savings Account
     * @throws InsufficientFundsException if initialDeposit is too low
     * */
    public SavingsAccount(String userid, double initialDeposit) throws InsufficientFundsException{
        // TODO
        super(userid, initialDeposit);
        int miniDeposit = 100;
        if (initialDeposit < miniDeposit) {
            throw new InsufficientFundsException();
        }
        int bonusBalance = 5000;
        int bonusAmount = 100;
        if (balance >= bonusBalance) {
            balance += bonusAmount;
        }

    }

    /**
     * Returns a string that contains the id, the type of account (Checking or Savings), and balance.
     * For example, if the user id is "ghosh", account type is savings account, and balance is "120.30",
     * the string returned will be as follows (identical punctuation and spaces):
     * ID: ghosh, Type: Savings, Balance: 120.30
     * @return the string that represents this saving account
     */
    public String toString() {
        String id = this.id;
        double balance = this.balance;

        // TODO: set both local variables to proper value

        return "ID: " + id + ", Type: Savings, Balance: " + balance;
    }

    /**
     * Implements abstract withdraw method.
     * Savings accounts at our bank charge a WITHDRAWAL_FEE.
     * Rejects withdrawal if the withdrawal would put the account bellow the minimum balance and throws an exception.
     *
     * @param amount The amount the customer wishes to redeem from the account.
     * @throws InsufficientFundsException if withdrawal would put account under MINIMUM_BALANCE with a message
     *                                      indicating the maximum amount they could withdraw.
     * @return The balance left in the account
     */
    public double withdraw(double amount) throws InsufficientFundsException {
        int withdrawFee = 2;
        int miniDeposit = 100;
        double MAXIMUM_POSSIBLE_WITHDRAWAL = balance - miniDeposit - withdrawFee;
        if ((MAXIMUM_POSSIBLE_WITHDRAWAL) < amount || MAXIMUM_POSSIBLE_WITHDRAWAL < 0) {
            throw new InsufficientFundsException("THE MAXIMUM AMOUNT THE USER: (" + id +
                    ") CAN WITHDRAW FROM THEIR SAVINGS ACCOUNT IS ($" + MAXIMUM_POSSIBLE_WITHDRAWAL + ")");
        }
        else {
            balance = balance - amount - withdrawFee;
        }
        return balance;
    }


    /**
     * Method that the bank will use to add an interest payment to the savings account.
     *
     * NewBalance = OldBalance + OldBalance*InterestRate
     * ie. If OldBalance was $1000.00 and InterestRate = 2%
     *        NewBalance would be $1020.00
     *
     * @param rate the interest rate in percent. For example 2% => rate is 2.0
     * @return The update balance in the account*/
    public double addInterest(double rate){
        int percentageFactor = 100;
        balance = balance * (1 + rate/percentageFactor);
        return balance;
    }
}
