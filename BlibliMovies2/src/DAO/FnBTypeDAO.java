package DAO;

import Model.FnBType;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Sebuah kelas yang menghandle CRUD untuk FnB Type
 */
public class FnBTypeDAO {
    Connection conn;

    /**
     * Inisialisasi DB
     */
    public FnBTypeDAO(){

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
     * Sebuah method untuk mengambil data FnB Type dari db
     *
     * @param id
     * @param storeid
     * @return
     * @throws SQLException
     */
    public FnBType getFnBType(String id, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM fnBType where id = ? and storeid = ?");
        ps.setInt(1, Integer.parseInt(id));
        ps.setInt(2, storeid);

        ResultSet rs = ps.executeQuery();

        FnBType output;
        if(rs.next()){
            output = new FnBType(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4));
        } else{
            output = null;
        }

        ps.close();
        conn.close();
        return output;
    }

    /**
     * Sebuah method yang akan mengambil data FnB Type yang statusnya aktif
     *
     * @param id
     * @param storeid
     * @return
     * @throws SQLException
     */
    public FnBType getFnBTypeTrue(String id, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM fnBType where id = ? and storeid = ? and status = true");
        ps.setInt(1, Integer.parseInt(id));
        ps.setInt(2, storeid);

        ResultSet rs = ps.executeQuery();

        FnBType output;
        if(rs.next()){
            output = new FnBType(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4));
        } else{
            output = null;
        }

        ps.close();
        conn.close();
        return output;
    }

    /**
     * Sebuah method yang akan mengambil seluruh data FnB Type
     *
     * @param storeid
     * @param offset
     * @return
     * @throws SQLException
     */
    public List<FnBType> getAllFnBType(int storeid, int offset) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM fnBType where storeid = ? ORDER BY id LIMIT 10 OFFSET ?");
        ps.setInt(1, storeid);
        ps.setInt(2, offset);
        ResultSet rs = ps.executeQuery();

        List<FnBType> fnBTypes = new ArrayList<FnBType>();
        while(rs.next()){
            fnBTypes.add(new FnBType(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4)));
        }

        ps.close();
        conn.close();
        return fnBTypes;
    }

    /**
     * Sebuah method yang akan mengambil seluruh data FnB Type yang aktif untuk form
     *
     * @param storeid
     * @return
     * @throws SQLException
     */
    public List<FnBType> getShowAllFnBType(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM fnBType where storeid = ? ORDER BY id");
        ps.setInt(1, storeid);
        ResultSet rs = ps.executeQuery();

        List<FnBType> fnBTypes = new ArrayList<FnBType>();
        while(rs.next()){
            fnBTypes.add(new FnBType(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4)));
        }

        ps.close();
        conn.close();
        return fnBTypes;
    }

    /**
     * Sebuah method yang akan menghitung jumlah halaman dari seluruh data FnB Type
     *
     * @param storeid
     * @return
     * @throws SQLException
     */
    public int getCountAllFnBType(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT count(*) from (SELECT * FROM fnBType where storeid = ? ORDER BY id) as count");
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
     * Sebuah method yang akan mengambil seluruh data FnB Type yang memiliki status aktif
     *
     * @param storeid
     * @return
     * @throws SQLException
     */
    public List<FnBType> getAllFnBTypeTrue(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM fnBType where storeid = ? and status = true ORDER BY id");
        ps.setInt(1, storeid);
        ResultSet rs = ps.executeQuery();

        List<FnBType> fnBTypes = new ArrayList<FnBType>();
        while(rs.next()){
            fnBTypes.add(new FnBType(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4)));
        }

        ps.close();
        conn.close();
        return fnBTypes;
    }

    /**
     * Sebuah method yang akan menginput FnB Type pada db
     *
     * @param fnBType
     * @throws SQLException
     */
    public void addFnBType(FnBType fnBType) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO fnBType (storeid, type) VALUES (?,?)");
        ps.setInt(1, fnBType.getStoreID());
        ps.setString(2, fnBType.getType());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    /**
     * Sebuah method yang akan menghapus FnB Type pada db (soft)
     *
     * @param fnBType
     * @param storeid
     * @throws SQLException
     */
    public void deleteFnBType(String fnBType, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE fnBType set status = false where id = ? and storeid = ?");
        ps.setInt(1, Integer.parseInt(fnBType));
        ps.setInt(2, storeid);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    /**
     * Sebuah method yang akan mengembalikan FnB Type yang telah dihapus
     *
     * @param fnBType
     * @param storeid
     * @throws SQLException
     */
    public void retrieveFnBType(String fnBType, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE fnBType set status = true where id = ? and storeid = ?");
        ps.setInt(1, Integer.parseInt(fnBType));
        ps.setInt(2, storeid);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    /**
     * Sebuah method yang akan mengupdate data dari FnB Type
     *
     * @param fnBType
     * @throws SQLException
     */
    public void updateFnBType(FnBType fnBType) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE fnBType set type = ? where id = ? and storeid = ? and status = true");
        ps.setString(1, fnBType.getType());
        ps.setInt(2, fnBType.getId());
        ps.setInt(3, fnBType.getStoreID());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
}
