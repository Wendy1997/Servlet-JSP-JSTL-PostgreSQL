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

            Class.forName(prop.getProperty("database"));
            conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addScreeningTime(ScreeningTime screeningTime) throws SQLException{

        PreparedStatement ps = conn.prepareStatement("INSERT INTO screeningTime (filmid, studioid, storeid, time, duration) VALUES (?,?,?,CAST(? AS TIME),?)");
        ps.setInt(1,screeningTime.getFilmId());
        ps.setInt(2, screeningTime.getStudioId());
        ps.setInt(3, screeningTime.getStoreID());
        ps.setString(4, screeningTime.getTime());
        ps.setInt(5, screeningTime.getDuration());

        ps.executeUpdate();
    }

    public ScreeningTime getScreeningTime(String id, String film_id, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("select * from screeningtime where id = ? and storeid = ? and filmid = ? and status = true");
        ps.setString(1, id);
        ps.setInt(2, storeid);
        ps.setString(3, film_id);

        ResultSet rs = ps.executeQuery();

        ScreeningTime output;
        if(rs.next()){
            output =  new ScreeningTime(rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getString(5).substring(0,5),
                    rs.getInt(6));
        } else {
            output = null;
        }

        return output;
    }

    public List<ScreeningTime> getAllScreeningTime(int storeid, String filmid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("select * from screeningtime where storeid = ? and filmid = ? and status = true");
        ps.setInt(1, storeid);
        ps.setString(2, filmid);

        ResultSet rs = ps.executeQuery();

        List<ScreeningTime> outputList = new ArrayList<ScreeningTime>();
        while (rs.next()){
            outputList.add(new ScreeningTime(rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getString(5).substring(0,5),
                    rs.getInt(6))
            );
        }

        return outputList;
    }

    public void updateScreeningTime(ScreeningTime screeningTime) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE screeningtime set studioid = ?, time = CAST(? AS TIME), duration = ? where id = ? and storeid = ? and filmid = ?");
        ps.setString(1, screeningTime.getStudioId() + "");
        ps.setString(2, screeningTime.getTime());
        ps.setInt(3, screeningTime.getDuration());
        ps.setString(4, screeningTime.getId() + "");
        ps.setInt(5, screeningTime.getStoreID());
        ps.setString(6, screeningTime.getFilmId() + "");
        ps.executeUpdate();
    }

    public void deleteScreeningTime(String id, String filmid, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE screeningtime set status = true where id = ? and filmid = ? and storeid = ?");
        ps.setString(1, id + "");
        ps.setString(2, filmid + "");
        ps.setInt(3, storeid);
        ps.executeUpdate();
    }
}
