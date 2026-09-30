import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CustomerReadWrite {
	public ArrayList<Customer> readCustomers() {
		ArrayList<Customer> customers = new ArrayList<>();
    	File fr = new File("users.csv");  // adding customers from file
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fr));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] user = line.split(",");
                String username = user[0];
                String pass = user[1];
                double balance = Double.parseDouble(user[2]);
                Customer cus = new Customer(username, pass, balance);
                customers.add(cus);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }
	
	public void writeCustomers(ArrayList<Customer> customers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.csv"))) {
        	for(Customer c : customers) {
        		writer.write(c.getAccountNo() + "," + c.getPin() + "," + c.getBalance());
        		writer.newLine();
        	}
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
