package DAO;

import Model.MemberCard;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MemberCardDAO {

    Connection conn;

    public MemberCardDAO(){

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

    public MemberCard getMemberCard(String id, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM membercard where id = ? and storeusername = ? and status = true");
        ps.setString(1, id);
        ps.setString(2, storename);

        ResultSet rs = ps.executeQuery();

        MemberCard output;
        if(rs.next()){
            output = new MemberCard(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5).substring(0,10),
                    rs.getString(6),
                    rs.getString(7));
        } else{
            output = null;
        }
        return output;
    }

    public List<MemberCard> getAllMemberCard(String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM membercard where storeusername = ? and status = true");
        ps.setString(1, storename);
        ResultSet rs = ps.executeQuery();

        List<MemberCard> memberCards = new ArrayList<MemberCard>();
        while(rs.next()){
            memberCards.add(new MemberCard(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5).substring(0,10),
                    rs.getString(6),
                    rs.getString(7)));
        }
        return memberCards;
    }

    public void addMemberCard(MemberCard memberCard) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO membercard (storeusername, fullname, gender, birthdate, phonenumber, email) VALUES (?,?,?,?,?,?)");
        ps.setString(1,memberCard.getStorename());
        ps.setString(2, memberCard.getFullname());
        ps.setString(3, memberCard.getGender());
        ps.setDate(4, java.sql.Date.valueOf(memberCard.getBirthDate()));
        ps.setString(5, memberCard.getPhoneNumber());
        ps.setString(6, memberCard.getEmail());
        ps.executeUpdate();
    }

    public void deleteMemberCard(String id, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE membercard set status = false where username = ? and storeusername = ?");
        ps.setString(1, id);
        ps.setString(2, storename);
        ps.executeUpdate();
    }

    public void updateMemberCard(MemberCard memberCard) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE membercard set fullname = ?, gender = ?, birthdate = ?, phonenumber = ?, email = ? where id = ? AND storeusername = ?");
        ps.setString(1, memberCard.getFullname());
        ps.setString(2, memberCard.getGender());
        ps.setDate(3, java.sql.Date.valueOf(memberCard.getBirthDate()));
        ps.setString(4, memberCard.getPhoneNumber());
        ps.setString(5, memberCard.getEmail());
        ps.setString(6, memberCard.getId() + "");
        ps.setString(7,memberCard.getStorename());

        ps.executeUpdate();
    }
}
