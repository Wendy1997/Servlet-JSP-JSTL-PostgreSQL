package DAO;

import Model.FnB;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Sebuah kelas yang menghandle CRUD untuk FnB
 */
public class FnBDAO {

    Connection conn;

    /**
     * Inisialisasi DB
     */
    public FnBDAO(){

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
     * Sebuah method untuk mengambil data FnB dari db
     *
     * @param fnb
     * @param storeid
     * @return
     * @throws SQLException
     */
    public FnB getFnB(String fnb, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM foodandbeverages where id = ? and storeid = ?");
        ps.setInt(1, Integer.parseInt(fnb));
        ps.setInt(2, storeid);

        ResultSet rs = ps.executeQuery();

        FnB output;
        if(rs.next()){
            output = new FnB(rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getBoolean(8)
            );
        } else{
            output = null;
        }

        ps.close();
        conn.close();
        return output;
    }

    /**
     * Sebuah method yang akan mengambil data FnB yang statusnya aktif
     *
     * @param fnb
     * @param storeid
     * @return
     * @throws SQLException
     */
    public FnB getFnBTrue(String fnb, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM foodandbeverages where id = ? and storeid = ? and status = true");
        ps.setInt(1, Integer.parseInt(fnb));
        ps.setInt(2, storeid);

        ResultSet rs = ps.executeQuery();

        FnB output;
        if(rs.next()){
            output = new FnB(rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getBoolean(8)
            );
        } else{
            output = null;
        }

        ps.close();
        conn.close();
        return output;
    }

    /**
     * Sebuah method yang akan mengambil seluruh data fnb
     *
     * @param storeid
     * @param offset
     * @return
     * @throws SQLException
     */
    public List<FnB> getAllFnB(int storeid, int offset) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM foodandbeverages where storeid = ? ORDER BY id LIMIT 10 OFFSET ?");
        ps.setInt(1, storeid);
        ps.setInt(2, offset);

        ResultSet rs = ps.executeQuery();

        List<FnB> fnbs = new ArrayList<FnB>();
        while(rs.next()){
            fnbs.add(new FnB(rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getBoolean(8)
            ));
        }

        ps.close();
        conn.close();
        return fnbs;
    }

    /**
     * Sebuah method yang akan menghitung jumlah halaman dari seluruh data FnB
     *
     * @param storeid
     * @return
     * @throws SQLException
     */
    public int getCountAllFnB(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("Select count(*) from (SELECT * FROM foodandbeverages where storeid = ? ORDER BY id) as count");
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
     * Sebuah method yang akan mengambil seluruh data FnB yang memiliki status aktif
     *
     * @param storeid
     * @return
     * @throws SQLException
     */
    public List<FnB> getAllFnBTrue(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM foodandbeverages where storeid = ? and status = true ORDER BY id");
        ps.setInt(1, storeid);

        ResultSet rs = ps.executeQuery();

        List<FnB> fnbs = new ArrayList<FnB>();
        while(rs.next()){
            fnbs.add(new FnB(rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getBoolean(8)
            ));
        }

        ps.close();
        conn.close();
        return fnbs;
    }

    /**
     * Sebuah method yang akan menginput FnB pada db
     *
     * @param fnb
     * @throws SQLException
     */
    public void addFnB(FnB fnb) throws SQLException{

        PreparedStatement ps = conn.prepareStatement("INSERT INTO foodandbeverages (storeid, cover, name, type, size, price) VALUES (?,?,?,?,?,?)");

        ps.setInt(1, fnb.getStoreID());
        ps.setString(2, fnb.getCover());
        ps.setString(3, fnb.getName());
        ps.setInt(4, fnb.getType());
        ps.setInt(5, fnb.getSize());
        ps.setInt(6, fnb.getPrice());

        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    /**
     * Sebuah method yang akan menghapus FnB pada db (soft)
     *
     * @param id
     * @param storeid
     * @throws SQLException
     */
    public void deleteFnB(String id, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE foodandbeverages set status = false where id = ? AND storeid = ?");
        ps.setInt(1, Integer.parseInt(id));
        ps.setInt(2, storeid);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    /**
     * Sebuah method yang akan mengembalikan FnB yang telah dihapus
     *
     * @param id
     * @param storeid
     * @throws SQLException
     */
    public void retrieveFnB(String id, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE foodandbeverages set status = true where id = ? AND storeid = ?");
        ps.setInt(1, Integer.parseInt(id));
        ps.setInt(2, storeid);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    /**
     * Sebuah method yang akan mengupdate data dari FnB
     *
     * @param fnb
     * @throws SQLException
     */
    public void updateFnB(FnB fnb) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE foodandbeverages set cover = ?, name = ?, type = ?, size = ?, price = ? where id = ? and storeid = ? and status = true");

        ps.setString(1, fnb.getCover());
        ps.setString(2, fnb.getName());
        ps.setInt(3, fnb.getType());
        ps.setInt(4, fnb.getSize());
        ps.setInt(5, fnb.getPrice());
        ps.setInt(6, fnb.getId());
        ps.setInt(7, fnb.getStoreID());

        ps.executeUpdate();
        ps.close();
        conn.close();
    }

}
