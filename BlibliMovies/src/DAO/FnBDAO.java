package DAO;

import Model.FnB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FnBDAO {
    public static final String database = "org.postgresql.Driver";
    public static final String url = "jdbc:postgresql://localhost:5432/bliblimovies";
    public static final String username = "postgres";
    public static final String password = "wendy1997";

    Connection conn;

    public FnBDAO(){

        try {
            Class.forName(database);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public FnB getFnB(String fnb) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM foodandbeverages where id = ?");
        ps.setString(1, fnb);

        ResultSet rs = ps.executeQuery();

        FnB output;
        if(rs.next()){
            output = new FnB(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getInt(7)
            );
        } else{
            output = null;
        }
        return output;
    }

    public List<FnB> getAllFnB() throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM foodandbeverages");

        ResultSet rs = ps.executeQuery();

        List<FnB> fnbs = new ArrayList<FnB>();
        while(rs.next()){
            fnbs.add(new FnB(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getInt(7)
            ));
        }
        return fnbs;
    }

    public void addFnB(FnB fnb) throws SQLException{

        PreparedStatement ps = conn.prepareStatement("INSERT INTO foodandbeverages (storeusername, cover, name, type, size, price) VALUES (?,?,?,?,?,?)");

        ps.setString(1, fnb.getStorename());
        ps.setString(2, fnb.getCover());
        ps.setString(3, fnb.getName());
        ps.setString(4, fnb.getType());
        ps.setString(5, fnb.getSize());
        ps.setInt(6, fnb.getPrice());

        ps.executeUpdate();
    }

    public void deleteFnB(String id) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("DELETE FROM foodandbeverages where id = ?");
        ps.setString(1, id);
        ps.executeUpdate();
    }

    public void updateFnB(FnB fnb) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE foodandbeverages set cover = ?, name = ?, type = ?, size = ?, price = ? where id = ? and storeusername = ?");

        ps.setString(1, fnb.getCover());
        ps.setString(2, fnb.getName());
        ps.setString(3, fnb.getType());
        ps.setString(4, fnb.getSize());
        ps.setInt(5, fnb.getPrice());
        ps.setString(6, fnb.getId() + "");
        ps.setString(7, fnb.getStorename() + "");

        ps.executeUpdate();
    }

}
