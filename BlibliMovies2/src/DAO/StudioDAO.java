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

    public List<Studio> getAllStudio(String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM studio where storeusername = ? and status = true");
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
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM studio where id = ? and storeusername = ? and status = true");
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
        PreparedStatement ps = conn.prepareStatement("update studio set status = false where id = ? AND storeusername = ?");
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
