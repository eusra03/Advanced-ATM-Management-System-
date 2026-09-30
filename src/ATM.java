//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Admin admin = new Admin("admin123", "password", 100000);
    private static ArrayList<Customer> customers = new ArrayList<>();

    public static void main(String[] args) {


            boolean exitSystem = false;
            while (!exitSystem) {
                System.out.println("Welcome to the ATM Management System!!");
                System.out.println("1. Admin Login");
                System.out.println("2. Customer Login");
                System.out.println("3. Customer Registration");
                System.out.println("4. Exit");
                System.out.print("Please enter your choice: ");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        adminLogin();
                        break;
                    case 2:
                        customerLogin();
                        break;
                    case 3:
                        Registration();
                        break;
                    case 4:
                        exitSystem = true;
                        System.out.println("Thank you for using the ATM Management System!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            }
        }


        private static void Registration () {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter name :");
            String name = scanner.nextLine();
            System.out.println("Enter pin :");
            String pin = scanner.nextLine();
            System.out.println("Enter pin again to confirm :");
            String confirmPin = scanner.nextLine();
            System.out.println("Enter initial deposit :");
            double deposit = scanner.nextDouble();

            RegistrationRequest newRequest = new RegistrationRequest(name, pin, confirmPin, deposit);
            admin.addRegistrationRequest(newRequest);
            System.out.println("Your registration request has been submitted for admin approval.");
        }
        
        

        private static void customerLogin () {
        	CustomerReadWrite crw = new CustomerReadWrite();
        	customers.clear();
        	customers = crw.readCustomers(); // Reads all the customers from users.csv

            scanner.nextLine();
            System.out.print("Enter customer username: ");
            String customerUsername = scanner.nextLine();
            System.out.print("Enter customer password: ");
            String customerPassword = scanner.nextLine();
            
            boolean customerFound = false;
            for(Customer c : customers) {
            	if(c.authenticate(customerUsername, customerPassword)) {
            		boolean customerSession = true;
            		customerFound=true;
                    while (customerSession) {
                        System.out.println("Customer Menu:");
                        System.out.println("1. Withdraw Money");
                        System.out.println("2. Deposit Money");
                        System.out.println("3. Transfer Money");
                        System.out.println("4. View Balance");
                        System.out.println("5. Log out");
                        System.out.print("Please enter your choice: ");

                        int customerChoice = scanner.nextInt();
                        switch (customerChoice) {
                            case 1:
                                System.out.println("Input the amount to withdraw : ");
                                double amount = scanner.nextDouble();
                                c.withdraw(amount);
                                crw.writeCustomers(customers);
                                admin.minusFromAtmBalance(amount);
                                System.out.println(" ");
                                break;
                            case 2:
                                System.out.println("Input the amount to deposit : ");
                                amount = scanner.nextDouble();
                                c.deposit(amount);
                                crw.writeCustomers(customers);
                                admin.addToAtmBalance(amount);
                                System.out.println(" ");
                                break;
                            case 3:
                            	scanner.nextLine();
                                System.out.println("Input the id to transfer money to:");
                                String username = scanner.nextLine();
                                System.out.println("Input the amount to transfer money to:");
                                amount = scanner.nextDouble();
                                TransferMoney t = new TransferMoney();
                                System.out.println(t.transfer(c.getAccountNo(),username,amount, customers));
                                customers.clear();
                            	customers = crw.readCustomers();
                                System.out.println(" ");
                                break;
                            case 4:
                                String string = c.toString();
                                System.out.println(string);
                                System.out.println(" ");
                                break;
                            case 5:
                                customerSession = false;
                                System.out.println("Logging out...");
                                System.out.println(" ");
                                break;
                            default:
                                System.out.println("Invalid choice. Please enter 1, 2, 3, 4 or 5.");
                                System.out.println(" ");
                        }
                    }
                 }
            }
            if(customerFound==false) {
            	System.out.println("Authentication failed. Please try again.");
                System.out.println(" ");
            }
            
            }


        private static void adminLogin () {
            scanner.nextLine();
            System.out.print("Enter admin username: ");
            String username = scanner.nextLine();
            System.out.print("Enter admin password: ");
            String password = scanner.nextLine();

            if (admin.authenticate(username, password)) {
                boolean adminSession = true;
                while (adminSession) {
                    System.out.println("Admin Menu:");
                    System.out.println("1. View Customer Account Details");
                    System.out.println("2. Check ATM Balance");
                    System.out.println("3. Approve Registration");
                    System.out.println("4. Logout");
                    System.out.print("Please enter your choice: ");

                    int adminChoice = scanner.nextInt();
                    switch (adminChoice) {
                        case 1:
                            admin.checkCustomerAccount();
                            System.out.println(" ");
                            break;
                        case 2:
                            admin.checkATMBalance();
                            System.out.println(" ");
                            break;
                        case 3:
                            approveRegistrationRequests();
                            System.out.println(" ");
                            break;
                        case 4:
                            adminSession = false;
                            System.out.println("Logging out...");
                            System.out.println(" ");
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter 1, 2, 3 or 4.");
                            System.out.println(" ");
                    }
                }
            } else {
                System.out.println("Authentication failed. Please try again.");
                System.out.println(" ");
            }
        }

        private static void approveRegistrationRequests () {
            admin.listRegistrationRequests();
            System.out.println("Enter the username of the customer to approve or type 'cancel' to return: ");
            scanner.nextLine();
            String input = scanner.nextLine();

            if ("cancel".equalsIgnoreCase(input)) {
                return;
            }
            
            RegistrationRequest toApprove = admin.getRegistrationRequestByUsername(input);
            if (toApprove != null) {
                Customer newCustomer = admin.approveRegistration(toApprove);
                System.out.println("Registration approved for: " + newCustomer.getAccountNo());
                System.out.println(" ");

                admin.removeRegistrationRequest(toApprove);
            } else {
                System.out.println("No registration request found for the username:" + input);
                System.out.println(" ");
            }
        }


    }


