package DAO;

import Model.FnBType;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FnBTypeDAO {
    Connection conn;

    public FnBTypeDAO(){

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

    public FnBType getFnBType(String id, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM fnBType where id = ? and storeusername = ? and status = true");
        ps.setString(1, id);
        ps.setString(2, storename);

        ResultSet rs = ps.executeQuery();

        FnBType output;
        if(rs.next()){
            output = new FnBType(rs.getString(1), rs.getString(2), rs.getString(3));
        } else{
            output = null;
        }
        return output;
    }

    public List<FnBType> getAllFnBType(String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM fnBType where storeusername = ? and status = true");
        ps.setString(1, storename);
        ResultSet rs = ps.executeQuery();

        List<FnBType> fnBTypes = new ArrayList<FnBType>();
        while(rs.next()){
            fnBTypes.add(new FnBType(rs.getString(1), rs.getString(2), rs.getString(3)));
        }
        return fnBTypes;
    }

    public void addFnBType(FnBType fnBType) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO fnBType (storeusername, type) VALUES (?,?)");
        ps.setString(1, fnBType.getStorename());
        ps.setString(2, fnBType.getType());
        ps.executeUpdate();
    }

    public void deleteFnBType(String fnBType, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE fnBType set status = false where id = ? and storeusername = ?");
        ps.setString(1, fnBType);
        ps.setString(2, storename);
        ps.executeUpdate();
    }

    public void updateFnBType(FnBType fnBType) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE fnBType set type = ? where id = ? and storeusername = ? and status = true");
        ps.setString(1, fnBType.getType());
        ps.setString(2, fnBType.getId());
        ps.setString(3, fnBType.getStorename());
        ps.executeUpdate();
    }
}
