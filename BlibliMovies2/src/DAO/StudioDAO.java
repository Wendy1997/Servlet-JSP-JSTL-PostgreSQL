package DAO;

import Model.Film;
import Model.Studio;
import Model.Studio;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StudioDAO {

    Connection conn;

    public StudioDAO(){

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

    public List<Studio> getAllStudio(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM studio where storeid = ? ORDER BY id");
        ps.setInt(1, storeid);

        ResultSet rs = ps.executeQuery();

        List<Studio> studios = new ArrayList<Studio>();
        while(rs.next()){
            studios.add(new Studio(Integer.parseInt(rs.getString(1)),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5)
            ));
        }

        ps.close();
        return studios;
    }

    public List<Studio> getAllStudioTrue(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM studio where storeid = ? and status = true ORDER BY id");
        ps.setInt(1, storeid);

        ResultSet rs = ps.executeQuery();

        List<Studio> studios = new ArrayList<Studio>();
        while(rs.next()){
            studios.add(new Studio(Integer.parseInt(rs.getString(1)),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5)
            ));
        }

        ps.close();
        return studios;
    }


    public Studio getStudio(String studio, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM studio where id = ? and storeid = ?");
        ps.setString(1, studio);
        ps.setInt(2, storeid);

        ResultSet rs = ps.executeQuery();

        Studio output;
        if(rs.next()){
            output = new Studio(Integer.parseInt(rs.getString(1)),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5)
            );
        } else{
            output = null;
        }

        ps.close();
        return output;
    }

    public Studio getStudioTrue(String studio, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM studio where id = ? and storeid = ? and status = true");
        ps.setString(1, studio);
        ps.setInt(2, storeid);

        ResultSet rs = ps.executeQuery();

        Studio output;
        if(rs.next()){
            output = new Studio(Integer.parseInt(rs.getString(1)),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5)
            );
        } else{
            output = null;
        }

        ps.close();
        return output;
    }

    public void addStudio(Studio studio) throws SQLException{

        PreparedStatement ps = conn.prepareStatement("INSERT INTO studio (storeid, name, type, price) VALUES (?,?,?,?)");

        ps.setInt(1, studio.getStoreID());
        ps.setString(2, studio.getName());
        ps.setInt(3, studio.getType());
        ps.setInt(4, studio.getPrice());

        ps.executeUpdate();
        ps.close();
    }

    public void deleteStudio(String id, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("update studio set status = false where id = ? AND storeid = ?");
        ps.setString(1, id);
        ps.setInt(2, storeid);
        ps.executeUpdate();
        ps.close();
    }

    public void updateStudio(Studio studio) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE studio set name = ?, type = ?, price = ? where id = ? and storeid = ?");

        ps.setString(1, studio.getName());
        ps.setInt(2, studio.getType());
        ps.setInt(3, studio.getPrice());
        ps.setString(4, studio.getId() + "");
        ps.setInt(5, studio.getStoreID());

        ps.executeUpdate();
        ps.close();
    }

}
