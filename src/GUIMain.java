import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static javax.swing.SwingConstants.LEFT;
import static javax.swing.SwingConstants.TOP;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class GUIMain {
    private static final Admin admin = new Admin("admin123", "password", 100000);
    static ArrayList<Customer> customers = new ArrayList<>();
    public static void main(String[] args) {
         //MyFrame ef = new MyFrame();

        ImageIcon image = new ImageIcon("wc.jpg");
        JLabel label =  new JLabel();
        label.setText("\"Welcome to ATM!!\"");
        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Arial",Font.BOLD,20));
        label.setIconTextGap(-25);

        label.setOpaque(true);

        //label.setDebugGraphicsOptions(564);

        label.setHorizontalAlignment(LEFT);
        label.setVerticalAlignment(TOP);
        label.setBounds(200,10,1020,1000);
        JFrame f = new JFrame("User Frame");
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setVisible(true);
        f.setLayout(null);
        f.setSize(720,500);
        f.setIconImage(image.getImage());

        //f.pack();
        f.add(label);
        String choice = JOptionPane.showInputDialog("Enter your choice:");
        String name = JOptionPane.showInputDialog("Enter your username:");
        String pass = JOptionPane.showInputDialog("Enter your password:");
            for(Customer c : customers) {
                if (c.authenticate(name, pass)) {
                    ArrayList<Customer> customers = new ArrayList<>();
                    CustomerReadWrite crw = new CustomerReadWrite();
                    customers = crw.readCustomers(); // Reads all the customers from users.csv
                    JOptionPane.showMessageDialog(null, "Welcome " + name);
                    f.setVisible(false);
                } else if (admin.authenticate(name, pass)){
                    JOptionPane.showMessageDialog(null,"Welcome " + name);
                    f.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null, "Wrong username or password ");
                }

            }

    }
}
