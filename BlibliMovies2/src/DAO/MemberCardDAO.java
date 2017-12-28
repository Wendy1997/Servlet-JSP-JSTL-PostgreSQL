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

            Class.forName(prop.getProperty("database"));
            conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public MemberCard getMemberCard(String id, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM membercard where id = ? and storeid = ? and status = true");
        ps.setString(1, id);
        ps.setInt(2, storeid);

        ResultSet rs = ps.executeQuery();

        MemberCard output;
        if(rs.next()){
            output = new MemberCard(rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getString(5).substring(0,10),
                    rs.getString(6),
                    rs.getString(7));
        } else{
            output = null;
        }
        return output;
    }

    public List<MemberCard> getAllMemberCard(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM membercard where storeid = ? and status = true");
        ps.setInt(1, storeid);
        ResultSet rs = ps.executeQuery();

        List<MemberCard> memberCards = new ArrayList<MemberCard>();
        while(rs.next()){
            memberCards.add(new MemberCard(rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getString(5).substring(0,10),
                    rs.getString(6),
                    rs.getString(7)));
        }
        return memberCards;
    }

    public void addMemberCard(MemberCard memberCard) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO membercard (storeid, fullname, gender, birthdate, phonenumber, email) VALUES (?,?,?,?,?,?)");
        ps.setInt(1,memberCard.getStoreID());
        ps.setString(2, memberCard.getFullname());
        ps.setInt(3, memberCard.getGender());
        ps.setDate(4, java.sql.Date.valueOf(memberCard.getBirthDate()));
        ps.setString(5, memberCard.getPhoneNumber());
        ps.setString(6, memberCard.getEmail());
        ps.executeUpdate();
    }

    public void deleteMemberCard(String id, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE membercard set status = false where id = ? and storeid = ?");
        ps.setString(1, id);
        ps.setInt(2, storeid);
        ps.executeUpdate();
    }

    public void updateMemberCard(MemberCard memberCard) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE membercard set fullname = ?, gender = ?, birthdate = ?, phonenumber = ?, email = ? where id = ? AND storeid = ?");
        ps.setString(1, memberCard.getFullname());
        ps.setInt(2, memberCard.getGender());
        ps.setDate(3, java.sql.Date.valueOf(memberCard.getBirthDate()));
        ps.setString(4, memberCard.getPhoneNumber());
        ps.setString(5, memberCard.getEmail());
        ps.setString(6, memberCard.getId() + "");
        ps.setInt(7,memberCard.getStoreID());

        ps.executeUpdate();
    }
}
