import java.sql.Timestamp;
public class Customer extends LogIn{
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Customer(String accountNo, String pin, double balance) {
        super(accountNo, pin);
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + " Taka.  New balance: " + balance +" Taka "+ Timestamp.valueOf(" 2024-06-09 13:22:15"));

    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " Taka. Remaining balance: " + balance + " Taka " + Timestamp.valueOf(" 2024-06-09 13:22:15"));
        } else {
            System.out.println("Insufficient funds.");
        }
    }


    @Override
    public String toString() {
        return "Customer balance = " + balance + " Taka ";
    }
}
