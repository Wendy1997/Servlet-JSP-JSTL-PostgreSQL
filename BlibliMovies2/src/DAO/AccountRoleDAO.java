package DAO;

import Model.AccountRole;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AccountRoleDAO {
    Connection conn;

    public AccountRoleDAO(){

        Properties prop = new Properties();
        InputStream input = null;

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            input = classLoader.getResourceAsStream("config.properties");

            // load a properties file
            prop.load(input);

            Class.forName(prop.getProperty("database"));
            conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public AccountRole getAccountRole(String id, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM accountRole where id = ? and storeusername = ? and status = true");
        ps.setString(1, id);
        ps.setString(2, storename);

        ResultSet rs = ps.executeQuery();

        AccountRole output;
        if(rs.next()){
            output = new AccountRole(rs.getString(1), rs.getString(2), rs.getString(3));
        } else{
            output = null;
        }
        return output;
    }

    public List<AccountRole> getAllAccountRole(String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM accountRole where storeusername = ? and status = true");
        ps.setString(1, storename);
        ResultSet rs = ps.executeQuery();

        List<AccountRole> accountRoles = new ArrayList<AccountRole>();
        while(rs.next()){
            accountRoles.add(new AccountRole(rs.getString(1), rs.getString(2), rs.getString(3)));
        }
        return accountRoles;
    }

    public void addAccountRole(AccountRole accountRole) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO accountRole (storeusername, role) VALUES (?,?)");
        ps.setString(1, accountRole.getStorename());
        ps.setString(2, accountRole.getRole());
        ps.executeUpdate();
    }

    public void deleteAccountRole(String accountRole, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE accountRole set status = false where id = ? and storeusername = ?");
        ps.setString(1, accountRole);
        ps.setString(2, storename);
        ps.executeUpdate();
    }

    public void updateAccountRole(AccountRole accountRole) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE accountRole set role = ? where id = ? and storeusername = ? and status = true");
        ps.setString(1, accountRole.getRole());
        ps.setString(2, accountRole.getId());
        ps.setString(3, accountRole.getStorename());
        ps.executeUpdate();
    }
}
