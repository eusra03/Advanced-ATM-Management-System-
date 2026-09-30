import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
public class Admin extends LogIn{

   private final List<RegistrationRequest> RegistrationRequests;
    private double atmBalance; // Assuming there's a field to track ATM balance

    public Admin(String username, String password, double atmBalance) {
        super(username, password);
        this.RegistrationRequests = new ArrayList<>();
        this.atmBalance = atmBalance;
    }

    public List<RegistrationRequest> getRegistrationRequests() {
        return RegistrationRequests;
    }

    public double getAtmBalance() {
        return atmBalance;
    }

    public void setAtmBalance(double atmBalance) {
        this.atmBalance = atmBalance;
    }
    public double addToAtmBalance(double amount){
        atmBalance =+amount;
        return atmBalance;
    }
    public double minusFromAtmBalance(double amount){
        atmBalance -= amount;
        return atmBalance;
    }
    public void checkCustomerAccount() {
        ArrayList<Customer> customers = new ArrayList<>();
        CustomerReadWrite crw = new CustomerReadWrite();
        customers = crw.readCustomers(); // Reads all the customers from users.csv
        for(Customer c : customers) {
            System.out.println("Username: " + c.getAccountNo() + ", Balance " + c.getBalance());
        }

    }

    public void checkATMBalance() {
        System.out.println("The current ATM balance is: " + atmBalance + " Taka " +Timestamp.valueOf("  2024-06-09 13:22:15") );
    }


    public Customer approveRegistration(RegistrationRequest request) {
        // Add logic to save the new customer to the database or file system
    	request.adminApproval();
        return new Customer(request.getUsername(), request.getPassword(), request.getInitialDeposit());
    }

    public void addRegistrationRequest(RegistrationRequest request) {
        RegistrationRequests.add(request);
        request.storeNewUser();
    }

    public void listRegistrationRequests() {
    	addRegistrationRequestsFromFile();
        for (RegistrationRequest request : RegistrationRequests) {
            System.out.println("Username: " + request.getUsername() + ", Initial Deposit " + request.getInitialDeposit());
        }
    }

    public void removeRegistrationRequest(RegistrationRequest toApprove) {
    	for(int i=0; i<RegistrationRequests.size(); i++) {
    		if(RegistrationRequests.get(i).getUsername() == toApprove.getUsername()) {
    			RegistrationRequests.remove(i);
    		}
    	}
    }
    
    public void addRegistrationRequestsFromFile() {
    	RegistrationRequests.clear();
    	File fr = new File("new-user.csv");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fr));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] user = line.split(",");
                String username = user[0];
                String pass = user[1];
                double balance = Double.parseDouble(user[2]);
                RegistrationRequest rr = new RegistrationRequest(username, pass, pass, balance);
                RegistrationRequests.add(rr);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RegistrationRequest getRegistrationRequestByUsername(String input) {
    	
    	for (RegistrationRequest request : RegistrationRequests) {
            if(request.getUsername().equalsIgnoreCase(input)) {
            	return request;
            }
        }
        return null;
    }
}
