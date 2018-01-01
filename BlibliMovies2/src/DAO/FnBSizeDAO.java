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
            output = new FnBSize(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4));
        } else{
            output = null;
        }

        ps.close();
        conn.close();
        return output;
    }

    public FnBSize getFnBSizeTrue(String id, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM fnBSize where id = ? and storeid = ? and status = true");
        ps.setInt(1, Integer.parseInt(id));
        ps.setInt(2, storeid);

        ResultSet rs = ps.executeQuery();

        FnBSize output;
        if(rs.next()){
            output = new FnBSize(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4));
        } else{
            output = null;
        }

        ps.close();
        conn.close();
        return output;
    }


    public List<FnBSize> getAllFnBSize(int storeid, int offset) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM fnBSize where storeid = ? ORDER BY id LIMIT 10 OFFSET ?");
        ps.setInt(1, storeid);
        ps.setInt(2, offset);
        ResultSet rs = ps.executeQuery();

        List<FnBSize> fnBSizes = new ArrayList<FnBSize>();
        while(rs.next()){
            fnBSizes.add(new FnBSize(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4)));
        }

        ps.close();
        conn.close();
        return fnBSizes;
    }

    public List<FnBSize> getShowAllFnBSize(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM fnBSize where storeid = ? ORDER BY id");
        ps.setInt(1, storeid);
        ResultSet rs = ps.executeQuery();

        List<FnBSize> fnBSizes = new ArrayList<FnBSize>();
        while(rs.next()){
            fnBSizes.add(new FnBSize(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4)));
        }

        ps.close();
        conn.close();
        return fnBSizes;
    }

    public int getCountAllFnBSize(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT count(*) from (SELECT * FROM fnBSize where storeid = ? ORDER BY id) as count");
        ps.setInt(1, storeid);
        ResultSet rs = ps.executeQuery();

        int count = 1;
        if(rs.next()){
            count = rs.getInt(1);
            if(count < 10){
                count = 1;
            }
            else if(count%10 == 0){
                count = count/10;
            } else {
                count = count/10 + 1;
            }
        }


        ps.close();
        conn.close();
        return count;
    }

    public List<FnBSize> getAllFnBSizeTrue(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM fnBSize where storeid = ? and status = true ORDER BY id");
        ps.setInt(1, storeid);
        ResultSet rs = ps.executeQuery();

        List<FnBSize> fnBSizes = new ArrayList<FnBSize>();
        while(rs.next()){
            fnBSizes.add(new FnBSize(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4)));
        }

        ps.close();
        conn.close();
        return fnBSizes;
    }

    public void addFnBSize(FnBSize fnBSize) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO fnBSize (storeid, size) VALUES (?,?)");
        ps.setInt(1, fnBSize.getStoreID());
        ps.setString(2, fnBSize.getSize());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void deleteFnBSize(String fnBSize, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE fnBSize set status = false where id = ? and storeid = ?");
        ps.setInt(1, Integer.parseInt(fnBSize));
        ps.setInt(2, storeid);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void retrieveFnBSize(String fnBSize, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE fnBSize set status = true where id = ? and storeid = ?");
        ps.setInt(1, Integer.parseInt(fnBSize));
        ps.setInt(2, storeid);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void updateFnBSize(FnBSize fnBSize) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE fnBSize set size = ? where id = ? and storeid = ? and status = true");
        ps.setString(1, fnBSize.getSize());
        ps.setInt(2, fnBSize.getId());
        ps.setInt(3, fnBSize.getStoreID());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
}
