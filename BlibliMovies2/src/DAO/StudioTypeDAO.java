package DAO;

import Model.StudioType;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StudioTypeDAO {
    Connection conn;

    public StudioTypeDAO(){

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

    public StudioType getStudioType(String id, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM studioType where id = ? and storeusername = ? and status = true");
        ps.setInt(1, Integer.parseInt(id));
        ps.setString(2, storename);

        ResultSet rs = ps.executeQuery();

        StudioType output;
        if(rs.next()){
            output = new StudioType(rs.getString(1), rs.getString(2), rs.getString(3));
        } else{
            output = null;
        }
        return output;
    }

    public List<StudioType> getAllStudioType(String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM studioType where storeusername = ? and status = true");
        ps.setString(1, storename);
        ResultSet rs = ps.executeQuery();

        List<StudioType> studioTypes = new ArrayList<StudioType>();
        while(rs.next()){
            studioTypes.add(new StudioType(rs.getString(1), rs.getString(2), rs.getString(3)));
        }
        return studioTypes;
    }

    public void addStudioType(StudioType studioType) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO studioType (storeusername, type) VALUES (?,?)");
        ps.setString(1, studioType.getStorename());
        ps.setString(2, studioType.getType());
        ps.executeUpdate();
    }

    public void deleteStudioType(String studioType, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE studioType set status = false where id = ? and storeusername = ?");
        ps.setInt(1, Integer.parseInt(studioType));
        ps.setString(2, storename);
        ps.executeUpdate();
    }

    public void updateStudioType(StudioType studioType) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE studioType set type = ? where id = ? and storeusername = ? and status = true");
        ps.setString(1, studioType.getType());
        ps.setInt(2, Integer.parseInt(studioType.getId()));
        ps.setString(3, studioType.getStorename());
        ps.executeUpdate();
    }
}
