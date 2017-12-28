package DAO;

import Model.FnB;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FnBDAO {

    Connection conn;

    public FnBDAO(){

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

    public FnB getFnB(String fnb, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM foodandbeverages where id = ? and storeid = ? and status = true");
        ps.setString(1, fnb);
        ps.setInt(2, storeid);

        ResultSet rs = ps.executeQuery();

        FnB output;
        if(rs.next()){
            output = new FnB(rs.getInt(1),
                    rs.getInt(2),
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

    public List<FnB> getAllFnB(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM foodandbeverages where storeid = ? and status = true");
        ps.setInt(1, storeid);

        ResultSet rs = ps.executeQuery();

        List<FnB> fnbs = new ArrayList<FnB>();
        while(rs.next()){
            fnbs.add(new FnB(rs.getInt(1),
                    rs.getInt(2),
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

        PreparedStatement ps = conn.prepareStatement("INSERT INTO foodandbeverages (storeid, cover, name, type, size, price) VALUES (?,?,?,?,?,?)");

        ps.setInt(1, fnb.getStoreID());
        ps.setString(2, fnb.getCover());
        ps.setString(3, fnb.getName());
        ps.setString(4, fnb.getType());
        ps.setString(5, fnb.getSize());
        ps.setInt(6, fnb.getPrice());

        ps.executeUpdate();
    }

    public void deleteFnB(String id, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE foodandbeverages set status = false where id = ? AND storeid = ?");
        ps.setString(1, id);
        ps.setInt(2, storeid);
        ps.executeUpdate();
    }

    public void updateFnB(FnB fnb) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE foodandbeverages set cover = ?, name = ?, type = ?, size = ?, price = ? where id = ? and storeid = ? and status = true");

        ps.setString(1, fnb.getCover());
        ps.setString(2, fnb.getName());
        ps.setString(3, fnb.getType());
        ps.setString(4, fnb.getSize());
        ps.setInt(5, fnb.getPrice());
        ps.setString(6, fnb.getId() + "");
        ps.setInt(7, fnb.getStoreID());

        ps.executeUpdate();
    }

}
