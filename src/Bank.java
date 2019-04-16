/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */
import java.util.ArrayList;

/**
 * The Bank class acts as the interface between a user and their money.
 * The Bank consists of an ArrayList of accounts and a interest rate for savings accounts.
 * Through this class users can create accounts, deposit to accounts,
 * withdraw from accounts, and transfer between accounts.
 */
public class Bank {
    private ArrayList<Account> accounts; // Structure holding all accounts.
    private double savingsInterestRate; // The interest rate given to savings account holders as a percent

    /**
     * Default constructor creates an empty accounts Array List and set initial interest rate to 0%
     */
    public Bank() {
        // the constructor of the method create a new array list, and set the interest rate to zero
        accounts = new ArrayList<>();
        savingsInterestRate = 0;
    }

    /**
     * Sets the savings interest rate
     * @param rate The rate which will be the new savings interest rate
     */
    public void setSavingsInterest(double rate) {
        // set the interest rate to rate
        savingsInterestRate = rate;
    }

    /**
     * Returns the number of checking accounts this bank has
     * @return the number of checking accounts in this bank
     */
    public int getNumberOfCheckingAccounts() {
        // return the number of checking accounts in the
        // array list by iterating the for loop
        int numOfCheck = 0;
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i) instanceof CheckingAccount) {
                numOfCheck++;
            }

        }
        return numOfCheck;
    }

    /**
     * Returns the number of savings accounts this bank has
     * @return the number of savings accounts in this bank
     */
    public int getNumberOfSavingsAccounts() {
        // get the number of saving account by iterating the for loop
        int numOfSave = 0;
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i) instanceof SavingsAccount) {
                numOfSave++;
            }

        }
        return numOfSave;
    }

    /**
     * Helper method to get the checking account associated with accountID
     *
     * @param accountID the id of the account to obtain
     * @return If there exists an checking account with accountID return it. Otherwise return null
     */
    private Account getCheckingAccount(String accountID) {
        // given an account, find the checking account associate with it if there is any
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getId().equals(accountID)
                    && accounts.get(i) instanceof CheckingAccount) {
                return accounts.get(i);
            }
        }
        return null;
    }

    /**
     * Helper method to get the savings account associated with accountID
     *
     * @param accountID the id of the account to obtain
     * @return If there exists an savings account with accountID return it. Otherwise return null
     */
    private Account getSavingsAccount(String accountID) {
        // given an account, find the saving account associate with it if there is any
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getId().equals(accountID)
                    && accounts.get(i) instanceof SavingsAccount) {
                return accounts.get(i);
            }
        }
        return null;
    }

    /**
     * Creates a checking account with the given accountID and returns true or false depending on success.
     * You also need to print the proper message in each case, check write up for more details.
     *
     * The accountID must not already be taken and the initialDeposit must be greater than $0
     *
     * @param accountID The id the user wants associated to this new account.
     * @param initialDeposit The starting balance of the new account in dollars
     * @return True if account created successfully. False if not.
     */
    public boolean createCheckingAccount(String accountID, double initialDeposit) {
        String successMSG = "Successully created checking account for " + accountID  + ".";
        String failMSG = accountID + " ALREADY HAD A CHECKING ACCOUNT!";
        String minimumMSG = "THE MINIMUM INITIAL DEPOSIT FOR A CHECKING ACCOUNT WAS NOT MET!";

        // create a checking account, if the accountID has been already taken, or
        // the initial deposit is insufficient, return false, otherwise return true.
        double miniDeposit = 0.01;
        if (getCheckingAccount(accountID) != null) {
            System.out.println(failMSG);
            return false;
        }
        else {
            try {
                accounts.add(new CheckingAccount(accountID, initialDeposit));
            }
            catch (InsufficientFundsException e) {
                System.out.println(minimumMSG);
                return false;
            }
            System.out.println(successMSG);
            return true;
        }
    }

    /**
     * Creates a savings account with the given accountID and returns true or false depending on success.
     * You also need to print the proper message in each case, check write up for more details.
     *
     * The accountID must not already be taken and the initialDeposit must be greater than $100
     *
     * @param accountID The id the user wants associated to this new account.
     * @param initialDeposit The starting balance of the new account in dollars
     * @return True if account created successfully. False if not.
     */
    public boolean createSavingsAccount(String accountID, double initialDeposit) {
        String successMSG = "Successully created savings account for " + accountID  + ".";
        String failMSG = accountID + " ALREADY HAD A SAVINGS ACCOUNT!";
        String minimumMSG = "THE MINIMUM INITIAL DEPOSIT FOR A CHECKING ACCOUNT WAS NOT MET!";

        // create a saving account, if the accountID has been already taken, or
        // the initial deposit is insufficient, return false, otherwise return true.
        double miniDeposit = 100;
        if (getSavingsAccount(accountID) != null) {
            System.out.println(failMSG);
            return false;
        }
        else {
            try {
                accounts.add(new SavingsAccount(accountID, initialDeposit));
            }
            catch (InsufficientFundsException e) {
                System.out.println(minimumMSG);
                return false;
            }
            System.out.println(successMSG);
            return true;
        }
    }

    /**
     * Helper method to get the account and print proper messages if no account is found.
     *
     * @param accountID the given accountID
     * @param isChecking true if is checking account, false otherwise
     * @return the proper account
     */
    public Account getAccount(String accountID, boolean isChecking) {
        String noCheckingMSG = accountID + " DOES NOT HAVE A CHECKING ACCOUNT!";
        String noSavingsMSG = accountID + " DOES NOT HAVE A SAVINGS ACCOUNT!";

        // return the account associated with the account ID, if there is no associated account
        // return null.
        if (isChecking) {
            if (getCheckingAccount(accountID) == null) {
                System.out.println(noCheckingMSG);
                return null;
            }
            return getCheckingAccount(accountID);
        } else {
            if (getSavingsAccount(accountID) == null) {
                System.out.println(noSavingsMSG);
                return null;
            }
            return getSavingsAccount(accountID);
        }
    }


    /**
     * Adds money to the account associated with accountID
     *
     * @param isChecking true if deposit to checking, false if to savings
     * @param accountID the id of the account to add money too
     * @param amount the amount of dollars to add to the account
     * @return The new balance of the account after deposit. Null if no account exists with accountID
     */
    public Double deposit(boolean isChecking, String accountID, double amount)  {
        // deposit the amount of money to the account associated with the ID
        // if there is no associated account, return null
        if (getAccount(accountID, isChecking) != null) {
            return getAccount(accountID, isChecking).deposit(amount);
        }


        return null;
    }

    /**
     * Removes money from the account associated with accountID
     *
     * @param isChecking true if withdraw from checking, false if from savings
     * @param accountID the id of the account to take money from
     * @param amount the amount of dollars to add to the account
     * @return The new balance of the account after withdrawal. Null if no account exists with accountID
     */
    public Double withdraw(boolean isChecking, String accountID, double amount) {

        // withdraw the amount of money from the account associated with the ID
        // if there is no associated account, return null
        if (getAccount(accountID, isChecking) != null) {
            try {
                getAccount(accountID, isChecking).withdraw(amount);
            } catch (InsufficientFundsException e) {
                System.out.println("USER: (" + accountID
                        + ") CANNOT MAKE A WITHDRAWAL FROM THEIR CHECKING");
                return null;
            }
            return getAccount(accountID, isChecking).getBalance();
        }
        return null;
    }




    /**
     * Moves money from an account to another account via "online" transfer. No check fees are charged.
     *
     * If either account does not exist the transfer will fail.
     * If the fromAccount does not have enough funds or rejects the withdrawal for any other reason the transfer fails.
     *
     * @param fromAccountID the id of the account to take money from
     * @param isFromChecking true if transfer from checking, false if from savings
     * @param toAccountID the id of the account to put money in
     * @param isToChecking true if transfer to checking, false if to savings
     * @param amount the amount of dollars to add to the account
     * @return Whether or not the transfer succeeded.
     */
    public boolean onlineTransfer(String fromAccountID, boolean isFromChecking, String toAccountID,
                                  boolean isToChecking, double amount) {
        // transfer money from one account to another,
        // while the account taken money from needs to have sufficient funds, and both of
        // these accounts need to exist
        if (getAccount(fromAccountID, isFromChecking) == null
                || getAccount(toAccountID, isToChecking) == null) {
            return false;
        }
        else {
            if (withdraw(isFromChecking, fromAccountID, amount) != null) {
                deposit(isToChecking, toAccountID, amount);
                return true;
            }
            else {
                return false;
            }
        }
    }

    /**
     * Moves money from an account to another account using a "check."
     *
     * If the from account is not a checking account the transfer will fail.
     * If either account does not exist the transfer will fail.
     * If the fromAccount does not have enough funds or rejects the withdrawal for any other reason the transfer fails.
     *
     * @param fromAccountID the id of the account to take money from
     * @param toAccountID the id of the account to put money in
     * @param amount the amount of dollars to add to the account
     * @return Whether or not the transfer succeeded.
     */
    public boolean checkTransfer(String fromAccountID,boolean isFromChecking, String toAccountID,
                                 boolean isToChecking, double amount) {
        String shouldUseCheckingMSG = fromAccountID + " SHOULD USE A CHECKING ACCOUNT!";
        // transfer money using checks, and in addition to all the rules from online transfer
        // there is check fee if the account taken money from is below initial deposit, and
        // $2 charged for over 3 incurs of check transfer
        double requiredDeposit = 0.01;
        if (!isFromChecking) {
            System.out.println(shouldUseCheckingMSG);
            return false; }
        else {
            if (getAccount(fromAccountID, true) == null
                    || getAccount(toAccountID, isToChecking) == null) {
                return false;
            }
            CheckingAccount a = (CheckingAccount) getAccount(fromAccountID, isFromChecking);
            try {
                a.withdrawUsingCheck(amount);
            }
            catch (InsufficientFundsException e) {
                System.out.println("USER: (" + fromAccountID + ") CANNOT MAKE A "
                        + "WITHDRAWAL FROM THEIR CHECKING " + "ACCOUNT UNTIL THEY "
                        + "COVER THEIR NEGATIVE BALANCE WITH A DEPOSIT OF AT LEAST "
                        + "($" + requiredDeposit + ")");
                return false;
            }
            getAccount(toAccountID, isToChecking).deposit(amount);
            return true;
        }
    }

    /**
     * Adds interest to every savings account.
     */
    public void addInterest() {
        // return the balance after the interest rate is added.
        int dividingFactor = 100;
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i) instanceof SavingsAccount) {
                accounts.get(i).balance = (1 + savingsInterestRate / dividingFactor)
                        * accounts.get(i).balance;
            }

        }

    }

    /**
     * First, if accountID has a checking account, print its information. Then, if accountID has a savings account,
     * print its information. Check write up for more details.
     *
     * @param accountID the id of the account to obtain
     */
    public void printAccount(String accountID) {
        // prints out the information of the account
        if (getCheckingAccount(accountID) != null) {
            if (getAccount(accountID, true) != null) {
                System.out.println("ID: " + accountID);
                System.out.println("Balance: " + getAccount(accountID, true).balance);
            }
            getAccount(accountID, true);
        }
        if (getSavingsAccount(accountID) != null) {
            if (getAccount(accountID, false) != null) {
                System.out.println("ID: " + accountID);
                System.out.println("Balance: " + getAccount(accountID, false).balance);
            }
            getAccount(accountID, false);
        }
    }

}
