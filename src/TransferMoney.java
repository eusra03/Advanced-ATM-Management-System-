
import java.util.ArrayList;
public class TransferMoney {

    public String transfer(String fromAccount, String toAccount, double amount, ArrayList<Customer> customers) {
    	int Count=0;
    	for(Customer c : customers) {
    		if(c.getAccountNo().equals(fromAccount)) {
    			if(c.getBalance() >= amount) Count++;
    		}
    		if(c.getAccountNo().equals(toAccount)) {
    			Count++;
    		}
    	}
    	if(Count != 2) {
    		return "Coudn't transfer money";
    	}
    	for(Customer c : customers) {
    		if(c.getAccountNo().equals(fromAccount)) {
    			c.setBalance(c.getBalance()-amount);
    		}
    		if(c.getAccountNo().equals(toAccount)) {
    			c.setBalance(c.getBalance()+amount);
    		}
    	}
    	CustomerReadWrite crw = new CustomerReadWrite();
    	crw.writeCustomers(customers);
        return "Money Transferred Sucessfully.";
    }

}
