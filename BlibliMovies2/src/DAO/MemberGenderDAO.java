package DAO;

import Model.MemberGender;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Sebuah kelas yang menghandle CRUD untuk Member gender
 */
public class MemberGenderDAO {
    Connection conn;

    /**
     * Inisialisasi DB
     */
    public MemberGenderDAO(){

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
     * Sebuah method yang akan mengambil seluruh data Member gender
     *
     * @param storeid
     * @return
     * @throws SQLException
     */
    public List<MemberGender> getAllMemberGender(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM memberGender where storeid = ? ORDER BY id");
        ps.setInt(1, storeid);
        ResultSet rs = ps.executeQuery();

        List<MemberGender> memberGenders = new ArrayList<MemberGender>();
        while(rs.next()){
            memberGenders.add(new MemberGender(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4)));
        }

        ps.close();
        conn.close();
        return memberGenders;
    }

    /**
     * Sebuah method yang akan mengambil seluruh data member gender yang memiliki status aktif
     *
     * @param storeid
     * @return
     * @throws SQLException
     */
    public List<MemberGender> getAllMemberGenderTrue(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM memberGender where storeid = ? and status = true ORDER BY id");
        ps.setInt(1, storeid);
        ResultSet rs = ps.executeQuery();

        List<MemberGender> memberGenders = new ArrayList<MemberGender>();
        while(rs.next()){
            memberGenders.add(new MemberGender(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4)));
        }

        ps.close();
        conn.close();
        return memberGenders;
    }

    /**
     * Sebuah method yang akan menginput member gender pada db
     *
     * @param memberGender
     * @throws SQLException
     */
    public void addMemberGender(MemberGender memberGender) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO memberGender (storeid, gender) VALUES (?,?)");
        ps.setInt(1, memberGender.getStoreID());
        ps.setString(2, memberGender.getGender());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
}
