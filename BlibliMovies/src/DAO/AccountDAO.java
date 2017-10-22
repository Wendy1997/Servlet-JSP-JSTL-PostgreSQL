package DAO;

import Model.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    public static final String database = "org.postgresql.Driver";
    public static final String url = "jdbc:postgresql://localhost:5432/bliblimovies";
    public static final String username = "postgres";
    public static final String password = "wendy1997";

    Connection conn;

    public AccountDAO(){

        try {
            Class.forName(database);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public Account getAccount(String username, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM account where username = ? and storeusername = ?");
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
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM account where storeusername = ?");
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
        PreparedStatement ps = conn.prepareStatement("INSERT INTO account VALUES (?,?,?,?)");
        ps.setString(1,account.getUsername());
        ps.setString(2, account.getStorename());
        ps.setString(3, account.getPassword());
        ps.setString(4, account.getRole());
        ps.executeUpdate();
    }

    public void deleteAccount(String account, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("DELETE FROM account where username = ? and storeusername = ?");
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
