import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RegistrationRequest {
    private final String username;
    private final String password, confirmPass;
    private final double initialDeposit;

    public RegistrationRequest(String username, String password, String confirmPass, double initialDeposit) {
        this.username = username;
        this.password = password;
		this.confirmPass = confirmPass;
        this.initialDeposit = initialDeposit;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getInitialDeposit() {
        return initialDeposit;
    }
    
    
    public void storeNewUser() {
        if (password.equals(confirmPass)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("new-user.csv", true))) {

                writer.write(username + "," + password + "," + initialDeposit);
                writer.newLine();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean adminApproval() {

        boolean changed = false;
        File fr = new File("new-user.csv");
        File frtemp = new File("temp-new-user.csv");
        File fw = new File("users.csv");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fr));
            BufferedWriter writer = new BufferedWriter(new FileWriter(fw, true));
            BufferedWriter writerTemp = new BufferedWriter(new FileWriter(frtemp));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] user = line.split(",");
                if (user[0].equals(username)) {
                    writer.write(line);
                    writer.newLine();
                    changed = true;
                } else {
                    writerTemp.write(user[0] + "," + user[1] + "," + user[2]);
                    writerTemp.newLine();
                }
            }
            reader.close();
            writer.close();
            writerTemp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (changed) {
            fr.delete();
            frtemp.renameTo(fr);
            return true;
        } else {
            frtemp.delete();
        }
        return false;


    }
}
