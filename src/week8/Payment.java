package week8;

public abstract class Payment {
    protected double amount;

    public Payment(double amount){
        this.amount = amount;
    }

    abstract void processPayment();

    public void paymentDetails(){
        System.out.printf("Processing payment of $" + amount);
    }
}
