abstract class Payment {
    double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public abstract boolean process();
}

class CashPayment extends Payment {
    public CashPayment(double amount) {
        super(amount);
    }

    public boolean process() {
        return true; // Assume success/fail
    }
}

class CardPayment extends Payment {
    public CardPayment(double amount) {
        super(amount);
    }

    public boolean process() {
        return true; // Assume success/fail
    }
}
