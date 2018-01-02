package DAO;

import Model.Account;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Sebuah kelas yang menghandle CRUD untuk Account
 */
public class AccountDAO {
    Connection conn;

    /**
     * Inisialisasi DB
     */
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

    /**
     * Sebuah method untuk mengambil data akun dari db
     *
     * @param username
     * @param storeid
     * @return
     * @throws SQLException
     */
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
        conn.close();
        return output;
    }

    /**
     * Sebuah method yang akan mengambil data akun yang statusnya aktif
     *
     * @param username
     * @param storeid
     * @return
     * @throws SQLException
     */
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
        conn.close();
        return output;
    }

    /**
     * Sebuah method yang akan mengambil seluruh data akun
     *
     * @param storeid
     * @param offset
     * @return
     * @throws SQLException
     */
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
        conn.close();
        return accounts;
    }

    /**
     * Sebuah method yang akan menghitung jumlah halaman dari seluruh data akun
     *
     * @param storeid
     * @return
     * @throws SQLException
     */
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
        conn.close();
        return count;
    }

    /**
     * Sebuah method yang akan mengambil seluruh data akun yang memiliki status aktif
     *
     * @param storeid
     * @return
     * @throws SQLException
     */
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
        conn.close();
        return accounts;
    }

    /**
     * Sebuah method yang akan menginput akun pada db
     *
     * @param account
     * @throws SQLException
     */
    public void addAccount(Account account) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO account (username, storeid, password, roleid) VALUES (?,?,?,?)");
        ps.setString(1,account.getUsername());
        ps.setInt(2, account.getStoreid());
        ps.setString(3, account.getPassword());
        ps.setInt(4, account.getRoleid());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    /**
     * Sebuah method yang akan menghapus akun pada db (soft)
     *
     * @param account
     * @param storeid
     * @throws SQLException
     */
    public void deleteAccount(String account, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE account set status = false where username = ? and storeid = ?");
        ps.setString(1, account);
        ps.setInt(2, storeid);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    /**
     * Sebuah method yang akan mengembalikan akun yang telah dihapus
     *
     * @param account
     * @param storeid
     * @throws SQLException
     */
    public void retrieveAccount(String account, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE account set status = true where username = ? and storeid = ?");
        ps.setString(1, account);
        ps.setInt(2, storeid);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    /**
     * Sebuah method yang akan mengupdate data dari akun
     *
     * @param account
     * @throws SQLException
     */
    public void updateAccount(Account account) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE account set password = ?, roleid = ? where username = ? and storeid = ?");
        ps.setString(1, account.getPassword());
        ps.setInt(2, account.getRoleid());
        ps.setString(3, account.getUsername());
        ps.setInt(4, account.getStoreid());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    /**
     * Sebuah method yang akan mengupdate data dari akun
     *
     * @param account
     * @throws SQLException
     */
    public void updateAccountWithoutPass(Account account) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE account set roleid = ? where username = ? and storeid = ?");
        ps.setInt(1, account.getRoleid());
        ps.setString(2, account.getUsername());
        ps.setInt(3, account.getStoreid());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
}
