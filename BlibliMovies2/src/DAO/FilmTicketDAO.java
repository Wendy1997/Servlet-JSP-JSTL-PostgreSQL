package DAO;

import Model.FilmTicket;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Sebuah kelas yang menghandle CRUD untuk Film Ticket
 */
public class FilmTicketDAO {
    Connection conn;

    /**
     * Inisialisasi DB
     */
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

    /**
     * Sebuah method yang akan menginput ticket pada db
     *
     * @param filmTicket
     * @throws SQLException
     */
    public void addFilmTicket(FilmTicket filmTicket) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("INSERT INTO filmTicket (filmid, studioid, storeid, seatnumber, screeningid, price, date) VALUES (?,?,?,?,?,?,?)");
        ps.setInt(1,filmTicket.getFilmId());
        ps.setInt(2, filmTicket.getStudioId());
        ps.setInt(3, filmTicket.getStoreID());
        ps.setString(4, filmTicket.getSeatNumber());
        ps.setInt(5, filmTicket.getScreeningId());
        ps.setInt(6, filmTicket.getPrice());
        ps.setDate(7, java.sql.Date.valueOf(filmTicket.getDate()));

        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    /**
     * Sebuah method untuk mengambil data tiket dari db
     *
     * @param id
     * @param film_id
     * @param storeid
     * @return
     * @throws SQLException
     */
    public FilmTicket getFilmTicket(String id, String film_id, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("select * from filmTicket where id = ? and storeid = ? and filmid = ? and status = true");
        ps.setInt(1, Integer.parseInt(id));
        ps.setInt(2, storeid);
        ps.setInt(3, Integer.parseInt(film_id));

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

        ps.close();
        conn.close();
        return output;
    }

    /**
     * Sebuah method untuk mengambil seluruh data akun dari db
     *
     * @param filmid
     * @param studio
     * @param screening
     * @param storeid
     * @return
     * @throws SQLException
     */
    public List<FilmTicket> getAllFilmTicket(String filmid, String studio, String screening, int storeid, String date) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("select * from filmTicket where storeid = ? and filmid = ? and screeningid=? and studioid=? and date = ?");
        ps.setInt(1, storeid);
        ps.setInt(2, Integer.parseInt(filmid));
        ps.setInt(3, Integer.parseInt(screening));
        ps.setInt(4, Integer.parseInt(studio));
        ps.setDate(5, java.sql.Date.valueOf(date));

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

        ps.close();
        conn.close();
        return outputList;
    }
}
