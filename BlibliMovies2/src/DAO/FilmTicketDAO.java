package DAO;

import Model.FilmTicket;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FilmTicketDAO {
    Connection conn;

    public FilmTicketDAO(){

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

    public void addFilmTicket(FilmTicket filmTicket) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("INSERT INTO filmTicket (filmid, studioid, storeusername, seatnumber, screeningid, price) VALUES (?,?,?,?,?,?)");
        ps.setInt(1,filmTicket.getFilmId());
        ps.setInt(2, filmTicket.getStudioId());
        ps.setString(3, filmTicket.getStoreusername());
        ps.setString(4, filmTicket.getSeatNumber());
        ps.setInt(5, filmTicket.getScreeningId());
        ps.setInt(6, filmTicket.getPrice());

        ps.executeUpdate();
    }

    public FilmTicket getFilmTicket(String id, String film_id, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("select * from filmTicket where id = ? and storeusername = ? and filmid = ? and status = true");
        ps.setString(1, id);
        ps.setString(2, storename);
        ps.setString(3, film_id);

        ResultSet rs = ps.executeQuery();

        FilmTicket output;
        if(rs.next()){
            output =  new FilmTicket(rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getString(7));
        } else {
            output = null;
        }

        return output;
    }

    public List<FilmTicket> getAllFilmTicket(String filmid, String studio, String screening, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("select * from filmTicket where storeusername = ? and filmid = ? and screeningid=? and studioid=?");
        ps.setString(1, storename);
        ps.setString(2, filmid);
        ps.setString(3, screening);
        ps.setString(4, studio);

        ResultSet rs = ps.executeQuery();

        List<FilmTicket> outputList = new ArrayList<FilmTicket>();
        while (rs.next()){
            outputList.add(new FilmTicket(rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getString(7)));
        }

        return outputList;
    }
}
