package Persistence;

import Persistence.AssociateModel;
import Utils.DataSourceCRUD;
import Utils.ConnectionManager;

import java.io.IOException;
import java.sql.*;

public class AssociateRepo implements DataSourceCRUD<AssociateModel> {
    private final Connection connection;

    // An instance of an associate model using an associate model singleton
    private AssociateModel associateModel = AssociateModel.getUserModel();

    // establishing db connection
    public AssociateRepo() {
        connection = ConnectionManager.getConnection();
    }


    @Override
    public int create(AssociateModel model) {
        //JDBC logic here

        try {
            String sql = "INSERT INTO customers (first_name, last_name, email, username, password) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, model.getFirstName());
            pstmt.setString(2, model.getLastName());
            pstmt.setString(3, model.getEmail());
            //pstmt.setInt(4, model.getAccountId());
            pstmt.setString(4, model.getUsername());
            pstmt.setString(5, model.getPassword());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()){
                return rs.getInt("customer_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

        return -1;
    }

    @Override
    public AssociateModel read(Integer id) {
        try {
            String sql = "SELECT * FROM customers WHERE customer_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            AssociateModel associate = associateModel;
                    // new AssociateModel(rs.getInt("customer_id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("account_id"),rs.getString("username"), rs.getString("password"));
            while(rs.next()) {
                associate.setUserId(rs.getInt("customer_id"));
                associate.setFirstName(rs.getString("firstName"));
                associate.setLastName(rs.getString("lastName"));
                associate.setEmail(rs.getString("email"));
                associate.setAccountId(rs.getInt("account_id"));
                associate.setUsername(rs.getString("username"));
                associate.setPassword(rs.getString("password"));

            }

            return associate;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AssociateModel update(AssociateModel associateModl) {

        try {
            String sql = "UPDATE customers SET first_name = ?, last_name = ?, email = ?, account_id = ?, username = ?, password = ? WHERE customer_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, associateModl.getUserId());
            pstmt.setString(2,associateModl.getFirstName());
            pstmt.setString(3,associateModl.getLastName());
            pstmt.setString(4,associateModl.getEmail());
            pstmt.setInt(5, associateModl.getAccountId());
            pstmt.setString(6,associateModl.getUsername());
            pstmt.setString(7,associateModl.getPassword());

            pstmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return associateModl;
    }

    @Override
    public void delete(Integer id) {
        try {
            String sql = "DELETE FROM customers WHERE customer_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public AssociateModel authenticate(String username, String password) throws SQLException, IOException {
        String sql = "SELECT * FROM customers WHERE username = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next() && rs.getString("password").equals(password)){
            associateModel.setUserId(rs.getInt("customer_id"));
            associateModel.setFirstName(rs.getString("first_name"));
            associateModel.setLastName(rs.getString("last_name"));
            associateModel.setEmail(rs.getString("email"));
            associateModel.setAccountId(rs.getInt("account_id"));
            associateModel.setUsername(rs.getString("username"));
            associateModel.setPassword(rs.getString("password"));

            return associateModel;
        }
        return null;
    }
}
