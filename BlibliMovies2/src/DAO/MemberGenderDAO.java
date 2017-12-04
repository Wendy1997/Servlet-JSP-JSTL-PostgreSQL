package DAO;

import Model.MemberGender;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MemberGenderDAO {
    Connection conn;

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

    public MemberGender getMemberGender(String id, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM memberGender where id = ? and storeusername = ? and status = true");
        ps.setString(1, id);
        ps.setString(2, storename);

        ResultSet rs = ps.executeQuery();

        MemberGender output;
        if(rs.next()){
            output = new MemberGender(rs.getString(1), rs.getString(2), rs.getString(3));
        } else{
            output = null;
        }
        return output;
    }

    public List<MemberGender> getAllMemberGender(String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM memberGender where storeusername = ? and status = true");
        ps.setString(1, storename);
        ResultSet rs = ps.executeQuery();

        List<MemberGender> memberGenders = new ArrayList<MemberGender>();
        while(rs.next()){
            memberGenders.add(new MemberGender(rs.getString(1), rs.getString(2), rs.getString(3)));
        }
        return memberGenders;
    }

    public void addMemberGender(MemberGender memberGender) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO memberGender (storeusername, gender) VALUES (?,?)");
        ps.setString(1, memberGender.getStorename());
        ps.setString(2, memberGender.getGender());
        ps.executeUpdate();
    }

    public void deleteMemberGender(String memberGender, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE memberGender set status = false where id = ? and storeusername = ?");
        ps.setString(1, memberGender);
        ps.setString(2, storename);
        ps.executeUpdate();
    }

    public void updateMemberGender(MemberGender memberGender) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE memberGender set gender = ? where id = ? and storeusername = ? and status = true");
        ps.setString(1, memberGender.getGender());
        ps.setString(2, memberGender.getId());
        ps.setString(3, memberGender.getStorename());
        ps.executeUpdate();
    }
}
