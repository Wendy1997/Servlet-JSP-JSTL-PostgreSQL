package DAO;

import Model.Film;
import Model.ScreeningTime;
import Model.ScreeningTime;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ScreeningTimeDAO {

    Connection conn;

    public ScreeningTimeDAO(){

        Properties prop = new Properties();
        InputStream input = null;

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            input = classLoader.getResourceAsStream("config.properties");

            // load a properties file
            prop.load(input);

            System.out.println(prop.getProperty("database"));

            Class.forName(prop.getProperty("database"));
            conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addScreeningTime(ScreeningTime screeningTime) throws SQLException{

        PreparedStatement ps = conn.prepareStatement("INSERT INTO screeningTime (filmid, studioid, storeusername, time, duration) VALUES (?,?,?,CAST(? AS TIME),?)");
        ps.setInt(1,screeningTime.getFilmId());
        ps.setInt(2, screeningTime.getStudioId());
        ps.setString(3, screeningTime.getStorename());
        ps.setString(4, screeningTime.getTime());
        ps.setInt(5, screeningTime.getDuration());

        ps.executeUpdate();
    }

    public ScreeningTime getScreeningTime(String id, String film_id, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("select * from screeningtime where id = ? and storeusername = ? and filmid = ? and status = true");
        ps.setString(1, id);
        ps.setString(2, storename);
        ps.setString(3, film_id);

        ResultSet rs = ps.executeQuery();

        ScreeningTime output;
        if(rs.next()){
            output =  new ScreeningTime(rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getString(5).substring(0,5),
                    rs.getInt(6));
        } else {
            output = null;
        }

        return output;
    }

    public List<ScreeningTime> getAllScreeningTime(String storename, String filmid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("select * from screeningtime where storeusername = ? and filmid = ? and status = true");
        ps.setString(1, storename);
        ps.setString(2, filmid);

        ResultSet rs = ps.executeQuery();

        List<ScreeningTime> outputList = new ArrayList<ScreeningTime>();
        while (rs.next()){
            outputList.add(new ScreeningTime(rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getString(5).substring(0,5),
                    rs.getInt(6))
            );
        }

        return outputList;
    }

    public void updateScreeningTime(ScreeningTime screeningTime) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE screeningtime set studioid = ?, time = CAST(? AS TIME), duration = ? where id = ? and storeusername = ? and filmid = ?");
        ps.setString(1, screeningTime.getStudioId() + "");
        ps.setString(2, screeningTime.getTime());
        ps.setInt(3, screeningTime.getDuration());
        ps.setString(4, screeningTime.getId() + "");
        ps.setString(5, screeningTime.getStorename());
        ps.setString(6, screeningTime.getFilmId() + "");
        ps.executeUpdate();
    }

    public void deleteScreeningTime(String id, String filmid, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE screeningtime set status = true where id = ? and filmid = ? and storeusername = ?");
        ps.setString(1, id + "");
        ps.setString(2, filmid + "");
        ps.setString(3, storename);
        ps.executeUpdate();
    }
}
