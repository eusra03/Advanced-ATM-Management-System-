public class LogIn {
    private String accountNo;
    private String pin;
    public LogIn(){

    }
    public LogIn(String accountNo, String pin){
        this.accountNo = accountNo;
        this.pin = pin;
    }
    public String getAccountNo(){
        return accountNo ;
    }

    public String getPin() {
        return pin;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public boolean authenticate(String accountNo, String pin) {
        return this.accountNo.equals(accountNo) && this.pin.equals(pin);
    }
}
