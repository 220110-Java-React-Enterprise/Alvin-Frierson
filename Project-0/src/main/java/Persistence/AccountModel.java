package Persistence;


public class AccountModel<T> {
    Integer accountId;
    float balance;
    Integer customerId;
    String username;
    String password;
    public static AccountModel model;

    public AccountModel() {
    }

    public AccountModel(Integer accountId, float balance, Integer customerId) {
        this.accountId = accountId;
        this.balance = balance;
        this.customerId = customerId;
    }


    public AccountModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static AccountModel getUserModel(){
        if(model == null){
            model = new AccountModel();
        }
        return model;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
