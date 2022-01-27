package Persistence;

import java.util.IdentityHashMap;

public class AssociateModel<T> {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private int accountId;
    private String username;
    private String password;
    private static AssociateModel model;


    private AssociateModel() {
    }

    public AssociateModel(int Id, String firstName, String lastName , String email, int accountId ,String username, String password) {
        this.userId = Id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.accountId = accountId;
        this.username = username;
        this.password = password;

    }

    public static AssociateModel getUserModel(){
        if(model == null){
            model = new AssociateModel();
        }
        return model;
    }


    public AssociateModel(String username, String password) {
        this.username = username;
        this.password = password;
    }



    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}