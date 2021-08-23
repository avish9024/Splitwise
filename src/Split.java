public class Split {
    enum SplitType {EXACT, PERCENT, EQUAL}
    double amount;
    int id;

    public Split(int id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
