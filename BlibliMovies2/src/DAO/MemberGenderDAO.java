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

    public MemberGender getMemberGender(String id, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM memberGender where id = ? and storeid = ?");
        ps.setString(1, id);
        ps.setInt(2, storeid);

        ResultSet rs = ps.executeQuery();

        MemberGender output;
        if(rs.next()){
            output = new MemberGender(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4));
        } else{
            output = null;
        }

        ps.close();
        return output;
    }

    public MemberGender getMemberGenderTrue(String id, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM memberGender where id = ? and storeid = ? and status = true");
        ps.setString(1, id);
        ps.setInt(2, storeid);

        ResultSet rs = ps.executeQuery();

        MemberGender output;
        if(rs.next()){
            output = new MemberGender(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4));
        } else{
            output = null;
        }

        ps.close();
        return output;
    }


    public List<MemberGender> getAllMemberGender(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM memberGender where storeid = ? ORDER BY id");
        ps.setInt(1, storeid);
        ResultSet rs = ps.executeQuery();

        List<MemberGender> memberGenders = new ArrayList<MemberGender>();
        while(rs.next()){
            memberGenders.add(new MemberGender(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4)));
        }

        ps.close();
        return memberGenders;
    }

    public List<MemberGender> getAllMemberGenderTrue(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM memberGender where storeid = ? and status = true ORDER BY id");
        ps.setInt(1, storeid);
        ResultSet rs = ps.executeQuery();

        List<MemberGender> memberGenders = new ArrayList<MemberGender>();
        while(rs.next()){
            memberGenders.add(new MemberGender(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4)));
        }

        ps.close();
        return memberGenders;
    }

    public void addMemberGender(MemberGender memberGender) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO memberGender (storeid, gender) VALUES (?,?)");
        ps.setInt(1, memberGender.getStoreID());
        ps.setString(2, memberGender.getGender());
        ps.executeUpdate();
        ps.close();
    }

    public void deleteMemberGender(String memberGender, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE memberGender set status = false where id = ? and storeid = ?");
        ps.setString(1, memberGender);
        ps.setInt(2, storeid);
        ps.executeUpdate();
        ps.close();
    }

    public void retrieveMemberGender(String memberGender, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE memberGender set status = true where id = ? and storeid = ?");
        ps.setString(1, memberGender);
        ps.setInt(2, storeid);
        ps.executeUpdate();
        ps.close();
    }

    public void updateMemberGender(MemberGender memberGender) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE memberGender set gender = ? where id = ? and storeid = ? and status = true");
        ps.setString(1, memberGender.getGender());
        ps.setInt(2, memberGender.getId());
        ps.setInt(3, memberGender.getStoreID());
        ps.executeUpdate();
        ps.close();
    }
}
