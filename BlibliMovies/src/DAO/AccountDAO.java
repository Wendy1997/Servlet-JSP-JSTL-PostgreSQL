package DAO;

import Model.Account;

import java.sql.*;
import java.util.ArrayList;

public class AccountDAO {
    public static final String database = "org.postgresql.Driver";
    public static final String url = "jdbc:postgresql://localhost:5432/bliblimovies";
    public static final String username = "postgres";
    public static final String password = "wendy1997";

    Connection conn;

    public AccountDAO(){
        try {
            Class.forName(database);
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Account getAccount(String account) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM account where username = ?");
        ps.setString(1, account);

        ResultSet rs = ps.executeQuery();

        Account output;
        if(rs.next()){
            output = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
        } else{
            output = null;
        }
        return output;
    }

    public ArrayList<Account> getAllAccount() throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM account");
        ResultSet rs = ps.executeQuery();

        ArrayList<Account> accounts = new ArrayList<Account>();
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

    }

    public void deleteAccount(Account account) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("DELETE FROM account where username = ?");
        ps.setString(1, account.getUsername());
        ps.executeUpdate();
    }

    public void updateAccount(Account account) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE account where username = ?");
        ps.setString(1, account.getUsername());
        ps.executeUpdate();
    }
}
