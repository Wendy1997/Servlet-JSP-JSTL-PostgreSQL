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

    public Account getAccount(String username, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM account where username = ? and storeid = ?");
        ps.setString(1, username);
        ps.setInt(2, storeid);

        ResultSet rs = ps.executeQuery();

        Account output;
        if(rs.next()){
            output = new Account(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(6), rs.getBoolean(5));
        } else{
            output = null;
        }

        ps.close();
        return output;
    }

    public Account getAccountTrue(String username, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM account where username = ? and storeid = ? and status = true");
        ps.setString(1, username);
        ps.setInt(2, storeid);

        ResultSet rs = ps.executeQuery();

        Account output;
        if(rs.next()){
            output = new Account(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(6), rs.getBoolean(5));
        } else{
            output = null;
        }

        ps.close();
        return output;
    }

    public List<Account> getAllAccount(int storeid, int offset) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM account where storeid = ? ORDER BY id LIMIT 10 OFFSET ?");
        ps.setInt(1, storeid);
        ps.setInt(2, offset);
        System.out.println(storeid);
        ResultSet rs = ps.executeQuery();

        List<Account> accounts = new ArrayList<Account>();
        while(rs.next()){
            accounts.add(new Account(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(6), rs.getBoolean(5)));
        }

        ps.close();
        return accounts;
    }

    public int getCountAllAccount(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT count(*) from (SELECT * FROM account where storeid = ? ORDER BY id) as count");
        ps.setInt(1, storeid);
        ResultSet rs = ps.executeQuery();

        int count = 1;
        if(rs.next()){
            count = rs.getInt(1);
            if(count < 10){
                count = 1;
            }
            else if(count%10 == 0){
                count = count/10;
            } else {
                count = count/10 + 1;
            }
        }


        ps.close();
        return count;
    }

    public List<Account> getAllAccountTrue(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM account where storeid = ? and status = true ORDER BY id");
        ps.setInt(1, storeid);
        System.out.println(storeid);
        ResultSet rs = ps.executeQuery();

        List<Account> accounts = new ArrayList<Account>();
        while(rs.next()){
            accounts.add(new Account(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(6), rs.getBoolean(5)));
        }

        ps.close();
        return accounts;
    }

    public void addAccount(Account account) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO account (username, storeid, password, roleid) VALUES (?,?,?,?)");
        ps.setString(1,account.getUsername());
        ps.setInt(2, account.getStoreid());
        ps.setString(3, account.getPassword());
        ps.setInt(4, account.getRoleid());
        ps.executeUpdate();
        ps.close();
    }

    public void deleteAccount(String account, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE account set status = false where username = ? and storeid = ?");
        ps.setString(1, account);
        ps.setInt(2, storeid);
        ps.executeUpdate();
        ps.close();
    }

    public void retrieveAccount(String account, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE account set status = true where username = ? and storeid = ?");
        ps.setString(1, account);
        ps.setInt(2, storeid);
        ps.executeUpdate();
        ps.close();
    }

    public void updateAccount(Account account) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE account set password = ?, roleid = ? where username = ? and storeid = ?");
        ps.setString(1, account.getPassword());
        ps.setInt(2, account.getRoleid());
        ps.setString(3, account.getUsername());
        ps.setInt(4, account.getStoreid());
        ps.executeUpdate();
        ps.close();
    }
}
