package DAO;

import Model.StoreAccount;
import Model.StoreAccount;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StoreAccountDAO {

    Connection conn;

    public StoreAccountDAO(){

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

    public StoreAccount getStoreAccount(String StoreAccount) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM storeAccount where username = ?");
        ps.setString(1, StoreAccount);

        ResultSet rs = ps.executeQuery();

        StoreAccount output;
        if(rs.next()){
            output = new StoreAccount(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5));
        } else{
            output = null;
        }

        ps.close();
        return output;
    }

    public StoreAccount getStoreAccountTrue(String username) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM storeAccount where username = ? and status = true");
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();

        StoreAccount output;
        if(rs.next()){
            output = new StoreAccount(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5));
        } else{
            output = null;
        }

        ps.close();
        return output;
    }

    public List<StoreAccount> getAllStoreAccount(int offset) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM storeAccount ORDER BY id LIMIT 10 OFFSET ?");
        ps.setInt(1, offset);
        ResultSet rs = ps.executeQuery();

        List<StoreAccount> storeAccounts = new ArrayList<StoreAccount>();
        while(rs.next()){
            storeAccounts.add(new StoreAccount(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5)));
        }

        ps.close();
        return storeAccounts;
    }

    public int getCountAllStoreAccount() throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT count(*) from (SELECT * FROM storeaccount ORDER BY id) as count");
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

    public List<StoreAccount> getAllStoreAccountTrue() throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM storeAccount where status = true ORDER BY id");
        ResultSet rs = ps.executeQuery();

        List<StoreAccount> storeAccounts = new ArrayList<StoreAccount>();
        while(rs.next()){
            storeAccounts.add(new StoreAccount(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5)));
        }

        ps.close();
        return storeAccounts;
    }

    public void addStoreAccount(StoreAccount storeAccount) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO storeAccount (username, password, storename) VALUES (?,?,?)");
        ps.setString(1,storeAccount.getUsername());
        ps.setString(2, storeAccount.getPassword());
        ps.setString(3, storeAccount.getNama());
        ps.executeUpdate();
        ps.close();
    }

    public void deleteStoreAccount(int id) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE storeAccount set status = false where id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }

    public void retrieveStoreAccount(int id) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE storeAccount set status = true where id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }

    public void updateStoreAccount(StoreAccount storeAccount) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE storeAccount set password = ?, storename = ? where id = ?");
        ps.setString(1, storeAccount.getPassword());
        ps.setString(2, storeAccount.getNama());
        ps.setInt(3, storeAccount.getId());
        ps.executeUpdate();
        ps.close();
    }
}
