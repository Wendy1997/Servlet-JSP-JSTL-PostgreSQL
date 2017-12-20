package DAO;

import Model.FnBSize;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FnBSizeDAO {
    Connection conn;

    public FnBSizeDAO(){

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

    public FnBSize getFnBSize(String id, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM fnBSize where id = ? and storeusername = ? and status = true");
        ps.setInt(1, Integer.parseInt(id));
        ps.setString(2, storename);

        ResultSet rs = ps.executeQuery();

        FnBSize output;
        if(rs.next()){
            output = new FnBSize(rs.getString(1), rs.getString(2), rs.getString(3));
        } else{
            output = null;
        }
        return output;
    }

    public List<FnBSize> getAllFnBSize(String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM fnBSize where storeusername = ? and status = true");
        ps.setString(1, storename);
        ResultSet rs = ps.executeQuery();

        List<FnBSize> fnBSizes = new ArrayList<FnBSize>();
        while(rs.next()){
            fnBSizes.add(new FnBSize(rs.getString(1), rs.getString(2), rs.getString(3)));
        }
        return fnBSizes;
    }

    public void addFnBSize(FnBSize fnBSize) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO fnBSize (storeusername, size) VALUES (?,?)");
        ps.setString(1, fnBSize.getStorename());
        ps.setString(2, fnBSize.getSize());
        ps.executeUpdate();
    }

    public void deleteFnBSize(String fnBSize, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE fnBSize set status = false where id = ? and storeusername = ?");
        ps.setInt(1, Integer.parseInt(fnBSize));
        ps.setString(2, storename);
        ps.executeUpdate();
    }

    public void updateFnBSize(FnBSize fnBSize) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE fnBSize set size = ? where id = ? and storeusername = ? and status = true");
        ps.setString(1, fnBSize.getSize());
        ps.setInt(2, Integer.parseInt(fnBSize.getId()));
        ps.setString(3, fnBSize.getStorename());
        ps.executeUpdate();
    }
}
