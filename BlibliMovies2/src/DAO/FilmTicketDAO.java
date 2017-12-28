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

        PreparedStatement ps = conn.prepareStatement("INSERT INTO filmTicket (filmid, studioid, storeid, seatnumber, screeningid, price) VALUES (?,?,?,?,?,?)");
        ps.setInt(1,filmTicket.getFilmId());
        ps.setInt(2, filmTicket.getStudioId());
        ps.setInt(3, filmTicket.getStoreID());
        ps.setString(4, filmTicket.getSeatNumber());
        ps.setInt(5, filmTicket.getScreeningId());
        ps.setInt(6, filmTicket.getPrice());

        ps.executeUpdate();
    }

    public FilmTicket getFilmTicket(String id, String film_id, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("select * from filmTicket where id = ? and storeid = ? and filmid = ? and status = true");
        ps.setString(1, id);
        ps.setInt(2, storeid);
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
                    rs.getInt(7));
        } else {
            output = null;
        }

        return output;
    }

    public List<FilmTicket> getAllFilmTicket(String filmid, String studio, String screening, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("select * from filmTicket where storeid = ? and filmid = ? and screeningid=? and studioid=?");
        ps.setInt(1, storeid);
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
                    rs.getInt(7)));
        }

        return outputList;
    }
}
