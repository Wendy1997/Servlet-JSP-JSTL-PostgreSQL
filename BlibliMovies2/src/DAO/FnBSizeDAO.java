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

    public FnBSize getFnBSize(String id, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM fnBSize where id = ? and storeid = ?");
        ps.setInt(1, Integer.parseInt(id));
        ps.setInt(2, storeid);

        ResultSet rs = ps.executeQuery();

        FnBSize output;
        if(rs.next()){
            output = new FnBSize(rs.getInt(1), rs.getString(2), rs.getInt(3));
        } else{
            output = null;
        }

        ps.close();
        return output;
    }

    public FnBSize getFnBSizeTrue(String id, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM fnBSize where id = ? and storeid = ? and status = true");
        ps.setInt(1, Integer.parseInt(id));
        ps.setInt(2, storeid);

        ResultSet rs = ps.executeQuery();

        FnBSize output;
        if(rs.next()){
            output = new FnBSize(rs.getInt(1), rs.getString(2), rs.getInt(3));
        } else{
            output = null;
        }

        ps.close();
        return output;
    }


    public List<FnBSize> getAllFnBSize(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM fnBSize where storeid = ? ORDER BY id");
        ps.setInt(1, storeid);
        ResultSet rs = ps.executeQuery();

        List<FnBSize> fnBSizes = new ArrayList<FnBSize>();
        while(rs.next()){
            fnBSizes.add(new FnBSize(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }

        ps.close();
        return fnBSizes;
    }

    public List<FnBSize> getAllFnBSizeTrue(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM fnBSize where storeid = ? and status = true ORDER BY id");
        ps.setInt(1, storeid);
        ResultSet rs = ps.executeQuery();

        List<FnBSize> fnBSizes = new ArrayList<FnBSize>();
        while(rs.next()){
            fnBSizes.add(new FnBSize(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }

        ps.close();
        return fnBSizes;
    }

    public void addFnBSize(FnBSize fnBSize) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO fnBSize (storeid, size) VALUES (?,?)");
        ps.setInt(1, fnBSize.getStoreID());
        ps.setString(2, fnBSize.getSize());
        ps.executeUpdate();
        ps.close();
    }

    public void deleteFnBSize(String fnBSize, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE fnBSize set status = false where id = ? and storeid = ?");
        ps.setInt(1, Integer.parseInt(fnBSize));
        ps.setInt(2, storeid);
        ps.executeUpdate();
        ps.close();
    }

    public void updateFnBSize(FnBSize fnBSize) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE fnBSize set size = ? where id = ? and storeid = ? and status = true");
        ps.setString(1, fnBSize.getSize());
        ps.setInt(2, fnBSize.getId());
        ps.setInt(3, fnBSize.getStoreID());
        ps.executeUpdate();
        ps.close();
    }
}
