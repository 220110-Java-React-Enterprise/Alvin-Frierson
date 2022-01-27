package Persistence;

import Utils.ConnectionManager;
import Utils.DataSourceCRUD;

import java.io.IOException;
import java.sql.*;

public class AccountRepo implements DataSourceCRUD<AccountModel> {
    private final Connection connection;
    private AssociateModel associateModel = AssociateModel.getUserModel();
    private AccountModel accountModel = AccountModel.getUserModel();

    public AccountRepo() {
        connection = ConnectionManager.getConnection();
    }

    // accepts account model
    // returns account_id
    // if return value is -1, there was an error
    @Override
    public int create(AccountModel accModel) {
        //JDBC logic here

        try {
            String sql = "INSERT INTO accounts (balance, customer_id) VALUES (?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setFloat(1,accModel.balance);
            pstmt.setInt(2,associateModel.getUserId());


            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()){
                return rs.getInt("account_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return-1;
        }

        return -1;
    }

    @Override
    public AccountModel read(Integer id) {
        try {
            String sql = "SELECT * FROM accounts WHERE account_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            AccountModel account = new AccountModel();
                    //AccountModel(rs.getInt("account_id"), rs.getFloat("balance"), rs.getInt("customer_id"));
            while(rs.next()) {
                account.setAccountId(rs.getInt("account_id"));
                account.setBalance(rs.getFloat("balance"));
                account.setCustomerId(rs.getInt("customer_id"));

            }

            return account;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AccountModel update(AccountModel model) {
        try {
            String sql = "UPDATE accounts SET account_id = ?, balance = ?, customer_id = ? WHERE account = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, model.getAccountId());
            pstmt.setInt(2, model.getCustomerId());
            pstmt.setInt(3,model.getCustomerId());

            pstmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }

    @Override
    public void delete(Integer id) {
        try {
            String sql = "DELETE FROM accounts WHERE account_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public AccountModel authenticate(String username, String password) throws SQLException, IOException {
        String sql = "SELECT * FROM accounts WHERE account_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next() && rs.getString("password").equals(password)){
            return new AccountModel(rs.getString("account_id"),rs.getString("balance"));
        }
        return null;
    }

}
