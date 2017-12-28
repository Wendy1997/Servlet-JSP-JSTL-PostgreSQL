package DAO;

import Model.StoreAccount;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
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

    public StoreAccount getStoreAccount(String storeAccount) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM storeaccount where username = ?");
        ps.setString(1, storeAccount);

        ResultSet rs = ps.executeQuery();

        StoreAccount output;
        if(rs.next()){
            output = new StoreAccount(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
        } else{
            output = null;
        }

        ps.close();
        return output;
    }
}
