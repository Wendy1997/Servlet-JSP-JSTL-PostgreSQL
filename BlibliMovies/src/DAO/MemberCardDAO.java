package DAO;

import Model.MemberCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberCardDAO {
    public static final String database = "org.postgresql.Driver";
    public static final String url = "jdbc:postgresql://localhost:5432/bliblimovies";
    public static final String username = "postgres";
    public static final String password = "wendy1997";

    Connection conn;

    public MemberCardDAO(){

        try {
            Class.forName(database);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public MemberCard getMemberCard(String id) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM membercard where id = ?");
        ps.setString(1, id);

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

    public List<MemberCard> getAllMemberCard() throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM membercard");
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

    public void deleteMemberCard(String id) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("DELETE FROM membercard where username = ?");
        ps.setString(1, id);
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
