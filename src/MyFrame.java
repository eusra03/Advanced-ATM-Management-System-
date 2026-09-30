import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class MyFrame extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    ArrayList<Customer> customers = new ArrayList<>();
    List<RegistrationRequest> RegistrationRequests = new ArrayList<>();
    CustomerReadWrite crw = new CustomerReadWrite();
    Admin admin = new Admin("admin123", "password", 100000);
    double balance = 10000;

    JButton LoginButton, RequestButton, withdraw, deposit, transfer, viewCust, approveReq;
    JTextField AccountId, password, choice, money;
    JTextField textField, textField2, textField3;
    JTextArea textArea;
    String data[];
    int x;


    void showCustomers() {
        textArea.setText("");
        ArrayList<Customer> customers = new ArrayList<>();
        CustomerReadWrite crw = new CustomerReadWrite();
        customers = crw.readCustomers(); // Reads all the customers from users.csv
        for(Customer c : customers) {
            textArea.append("Username: " + c.getAccountNo() + ", Balance " + c.getBalance()+"\n");
        }
    }
    void withdraw(double amount) {
        ArrayList<Customer> customers = new ArrayList<>();
        CustomerReadWrite crw = new CustomerReadWrite();
        customers = crw.readCustomers(); // Reads all the customers from users.csv
        for(Customer c : customers) {
            if (amount <= c.getBalance()) {
                double balance = c.getBalance();
                balance -= amount;
                textArea.append("\nWithdrawn: " + amount + " Taka. Remaining balance: " + balance + " Taka " + Timestamp.valueOf(" 2024-06-09 13:22:15"));
            } else {
                textArea.append("Insufficient funds.");
            }
        }

    }

    MyFrame() {
        money = new JTextField();
        money.setBounds(10, 10, 300, 20);

        textField2 = new JTextField();
        textField2.setBounds(460, 10, 150, 20);
        textField2.setText("Automated Teller Machine");

        textField3 = new JTextField();
        textField3.setBounds(460, 40, 150, 20);
        textField3.setText("Bashundhara Branch");

        withdraw = new JButton();
        withdraw.setBounds(320, 10, 100, 20);
        withdraw.setText("Confirm");
        withdraw.setFocusable(false);
        withdraw.addActionListener(this);

        LoginButton = new JButton();
        LoginButton.setBounds(320, 10, 100, 20);
        LoginButton.setText("Login");
        LoginButton.setFocusable(false);
        LoginButton.addActionListener(this);

        RequestButton = new JButton();
        RequestButton.setBounds(320, 10, 100, 20);
        RequestButton.setText("Request");
        RequestButton.setFocusable(false);
        RequestButton.addActionListener(this);

        deposit = new JButton();
        deposit.setBounds(320, 10, 100, 20);
        deposit.setText("Confirm");
        deposit.setFocusable(false);
        deposit.addActionListener(this);

        transfer = new JButton();
        transfer.setBounds(320, 10, 100, 20);
        transfer.setText("Confirm");
        transfer.setFocusable(false);
        transfer.addActionListener(this);

        viewCust = new JButton();
        viewCust.setBounds(320, 10, 100, 20);
        viewCust.setText("Confirm");
        viewCust.setFocusable(false);
        viewCust.addActionListener(this);

        approveReq = new JButton();
        approveReq.setBounds(320, 10, 100, 20);
        approveReq.setText("Confirm");
        approveReq.setFocusable(false);
        approveReq.addActionListener(this);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 100, 415, 450);

        this.setTitle("ATM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 600);
        this.setLayout(null);
        this.setVisible(true);

        this.add(withdraw);
        this.add(RequestButton);
        this.add(LoginButton);
        this.add(deposit);
        this.add(transfer);
        this.add(viewCust);
        this.add(approveReq);

        this.add(money);
        this.add(textField2);
        this.add(textField3);
        this.add(scrollPane);
        showCustomers();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewCust) {
            textArea.setText("");
            showCustomers();
            x = 1;
        } else if (e.getSource() == withdraw) {
            double amount = Double.parseDouble(money.getText());
            withdraw(amount);

            }
        }


}
