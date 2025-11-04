package week8;

public class BankTransferPayment extends Payment{
    private String bankAccount;

    public BankTransferPayment(double amount, String bankAccount) {
        super(amount);
        this.bankAccount = bankAccount;
    }

    @Override
    void processPayment() {
        System.out.printf("Processing bank transfer payment of $" + amount + " for bank account" + bankAccount);
    }
}
