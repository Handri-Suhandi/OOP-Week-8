package week8;

public class CreditCardPayment extends Payment{
    private String cardNumber;

    public CreditCardPayment(double amount, String cardNumber){
        super(amount);
        this.cardNumber = cardNumber;
    }

    @Override
    void processPayment() {
        System.out.println("Processing credit card payment of %" + amount + " for card number" + cardNumber);
    }
}
