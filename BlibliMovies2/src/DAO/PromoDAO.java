package DAO;

import Model.Promo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class PromoDAO {

    Connection conn;

    public PromoDAO(){

        Properties prop = new Properties();
        InputStream input = null;

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            input = classLoader.getResourceAsStream("config.properties");

            // load a properties file
            prop.load(input);

            System.out.println(prop.getProperty("database"));

            Class.forName(prop.getProperty("database"));
            conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Promo getPromo(String id, String storename) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM promo where id = ? and storeusername = ? and status = true");
        ps.setString(1, id);
        ps.setString(2, storename);

        ResultSet rs = ps.executeQuery();

        Promo output;
        if(rs.next()){
            output = new Promo(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getBoolean(5),
                    rs.getInt(6));
        } else{
            output = null;
        }
        return output;
    }
}
