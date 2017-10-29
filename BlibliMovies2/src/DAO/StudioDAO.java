package DAO;

import Model.Film;
import Model.Studio;
import Model.Studio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudioDAO {
    public static final String database = "org.postgresql.Driver";
    public static final String url = "jdbc:postgresql://localhost:5432/bliblimovies";
    public static final String username = "postgres";
    public static final String password = "wendy1997";

    Connection conn;

    public StudioDAO(){

        try {
            Class.forName(database);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<Studio> getAllStudio(String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM studio where storeusername = ?");
        ps.setString(1, storename);

        ResultSet rs = ps.executeQuery();

        List<Studio> studios = new ArrayList<Studio>();
        while(rs.next()){
            studios.add(new Studio(Integer.parseInt(rs.getString(1)),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5)
            ));
        }
        return studios;
    }

    public Studio getStudio(String studio, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM studio where id = ? and storeusername = ?");
        ps.setString(1, studio);
        ps.setString(2, storename);

        ResultSet rs = ps.executeQuery();

        Studio output;
        if(rs.next()){
            output = new Studio(Integer.parseInt(rs.getString(1)),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5)
            );
        } else{
            output = null;
        }
        return output;
    }

    public void addStudio(Studio studio) throws SQLException{

        PreparedStatement ps = conn.prepareStatement("INSERT INTO studio (storeusername, name, type, price) VALUES (?,?,?,?)");

        ps.setString(1, studio.getStorename());
        ps.setString(2, studio.getName());
        ps.setString(3, studio.getType());
        ps.setInt(4, studio.getPrice());

        ps.executeUpdate();
    }

    public void deleteStudio(String id, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("DELETE FROM studio where id = ? AND storeusername = ?");
        ps.setString(1, id);
        ps.setString(2, storename);
        ps.executeUpdate();
    }

    public void updateStudio(Studio studio) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE studio set name = ?, type = ?, price = ? where id = ? and storeusername = ?");

        ps.setString(1, studio.getName());
        ps.setString(2, studio.getType());
        ps.setInt(3, studio.getPrice());
        ps.setString(4, studio.getId() + "");
        ps.setString(5, studio.getStorename() + "");

        ps.executeUpdate();
    }

}
