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

    public AccountRole getAccountRole(int id, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM accountRole where id = ? and storeid = ?");
        ps.setInt(1, id);
        ps.setInt(2, storeid);

        ResultSet rs = ps.executeQuery();

        AccountRole output;
        if(rs.next()){
            output = new AccountRole(rs.getInt(1), rs.getString(2), rs.getInt(3));
        } else{
            output = null;
        }

        ps.close();
        return output;
    }

    public AccountRole getAccountRoleTrue(int id, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM accountRole where id = ? and storeid = ? and status = true");
        ps.setInt(1, id);
        ps.setInt(2, storeid);

        ResultSet rs = ps.executeQuery();

        AccountRole output;
        if(rs.next()){
            output = new AccountRole(rs.getInt(1), rs.getString(2), rs.getInt(3));
        } else{
            output = null;
        }

        ps.close();
        return output;
    }

    public List<AccountRole> getAllAccountRole(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM accountRole where storeid = ? ORDER BY id");
        ps.setInt(1, storeid);
        ResultSet rs = ps.executeQuery();

        List<AccountRole> accountRoles = new ArrayList<AccountRole>();
        while(rs.next()){
            accountRoles.add(new AccountRole(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }

        ps.close();
        return accountRoles;
    }

    public List<AccountRole> getAllAccountRoleTrue(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM accountRole where storeid = ? and status = true ORDER BY id");
        ps.setInt(1, storeid);
        ResultSet rs = ps.executeQuery();

        List<AccountRole> accountRoles = new ArrayList<AccountRole>();
        while(rs.next()){
            accountRoles.add(new AccountRole(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }

        ps.close();
        return accountRoles;
    }

    public void addAccountRole(AccountRole accountRole) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO accountRole (storeid, role) VALUES (?,?)");
        ps.setInt(1, accountRole.getStoreID());
        ps.setString(2, accountRole.getRole());
        ps.executeUpdate();
        ps.close();
    }

    public void deleteAccountRole(String accountRole, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE accountRole set status = false where id = ? and storeid = ?");
        ps.setString(1, accountRole);
        ps.setInt(2, storeid);
        ps.executeUpdate();
        ps.close();
    }

    public void updateAccountRole(AccountRole accountRole) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE accountRole set role = ? where id = ? and storeid = ? and status = true");
        ps.setString(1, accountRole.getRole());
        ps.setInt(2, accountRole.getId());
        ps.setInt(3, accountRole.getStoreID());
        ps.executeUpdate();
        ps.close();
    }
}
