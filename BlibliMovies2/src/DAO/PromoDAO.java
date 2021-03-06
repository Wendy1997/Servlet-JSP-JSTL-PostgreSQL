package DAO;

import Model.Promo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Sebuah kelas yang menghandle CRUD untuk Promo
 */
public class PromoDAO {

    Connection conn;

    /**
     * Inisialisasi DB
     */
    public PromoDAO(){

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
     * Sebuah method untuk mengambil data promo dari db
     *
     * @param storeid
     * @return
     * @throws SQLException
     */
    public Promo getPromo(int storeid) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM promo where storeid = ?");
        ps.setInt(1, storeid);

        ResultSet rs = ps.executeQuery();

        Promo output;
        if(rs.next()){
            output = new Promo(rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getBoolean(5),
                    rs.getInt(6));
        } else{
            output = null;
        }

        ps.close();
        conn.close();
        return output;
    }

    /**
     * Sebuah method yang akan menginput promo pada db
     *
     * @param promo
     * @throws SQLException
     */
    public void addPromo(Promo promo) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("insert into promo (storeid, name, description, discountamount) VALUES (?,?,?,?)");
        ps.setInt(1, promo.getStoreID());
        ps.setString(2, promo.getName());
        ps.setString(3, promo.getDescription());
        ps.setInt(4, promo.getDiscountAmount());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
}
