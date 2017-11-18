package DAO;

import Model.Account;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AccountDAO {
    Connection conn;

    public AccountDAO(){

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

    public Account getAccount(String username, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM account where username = ? and storeusername = ? and status = true");
        ps.setString(1, username);
        ps.setString(2, storename);

        ResultSet rs = ps.executeQuery();

        Account output;
        if(rs.next()){
            output = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
        } else{
            output = null;
        }
        return output;
    }

    public List<Account> getAllAccount(String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM account where storeusername = ? and status = true");
        ps.setString(1, storename);
        System.out.println(storename);
        ResultSet rs = ps.executeQuery();

        List<Account> accounts = new ArrayList<Account>();
        while(rs.next()){
            accounts.add(new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
        }
        return accounts;
    }

    public void addAccount(Account account) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO account (username, storeusername, password, type) VALUES (?,?,?,?)");
        ps.setString(1,account.getUsername());
        ps.setString(2, account.getStorename());
        ps.setString(3, account.getPassword());
        ps.setString(4, account.getRole());
        ps.executeUpdate();
    }

    public void deleteAccount(String account, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE account set status = false where username = ? and storeusername = ?");
        ps.setString(1, account);
        ps.setString(2, storename);
        ps.executeUpdate();
    }

    public void updateAccount(Account account) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE account set password = ?, type = ? where username = ? and storeusername = ?");
        ps.setString(1, account.getPassword());
        ps.setString(2, account.getRole());
        ps.setString(3, account.getUsername());
        ps.setString(4, account.getStorename());
        ps.executeUpdate();
    }
}
