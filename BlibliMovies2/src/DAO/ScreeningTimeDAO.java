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

/**
 * Sebuah kelas yang menghandle CRUD untuk Screening Time
 */
public class ScreeningTimeDAO {

    Connection conn;

    /**
     * Inisialisasi DB
     */
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

    /**
     * Sebuah method yang akan menginput screening time pada db
     *
     * @param screeningTime
     * @throws SQLException
     */
    public void addScreeningTime(ScreeningTime screeningTime) throws SQLException{

        PreparedStatement ps = conn.prepareStatement("INSERT INTO screeningTime (filmid, studioid, storeid, time, duration) VALUES (?,?,?,CAST(? AS TIME),?)");
        ps.setInt(1,screeningTime.getFilmId());
        ps.setInt(2, screeningTime.getStudioId());
        ps.setInt(3, screeningTime.getStoreID());
        ps.setString(4, screeningTime.getTime());
        ps.setInt(5, screeningTime.getDuration());

        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    /**
     * Sebuah method untuk mengambil data screening time dari db
     *
     * @param id
     * @param film_id
     * @param storeid
     * @return
     * @throws SQLException
     */
    public ScreeningTime getScreeningTime(String id, String film_id, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("select * from screeningtime where id = ? and storeid = ? and filmid = ?");
        ps.setInt(1, Integer.parseInt(id));
        ps.setInt(2, storeid);
        ps.setInt(3, Integer.parseInt(film_id));

        ResultSet rs = ps.executeQuery();

        ScreeningTime output;
        if(rs.next()){
            output =  new ScreeningTime(rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getString(5).substring(0,5),
                    rs.getInt(6),
                    rs.getBoolean(7));
        } else {
            output = null;
        }

        ps.close();
        conn.close();
        return output;
    }

    /**
     * Sebuah method yang akan mengambil data screening time yang statusnya aktif
     *
     * @param id
     * @param film_id
     * @param storeid
     * @return
     * @throws SQLException
     */
    public ScreeningTime getScreeningTimeTrue(String id, String film_id, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("select * from screeningtime where id = ? and storeid = ? and filmid = ? and status = true");
        ps.setInt(1, Integer.parseInt(id));
        ps.setInt(2, storeid);
        ps.setInt(3, Integer.parseInt(film_id));

        ResultSet rs = ps.executeQuery();

        ScreeningTime output;
        if(rs.next()){
            output =  new ScreeningTime(rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getString(5).substring(0,5),
                    rs.getInt(6),
                    rs.getBoolean(7));
        } else {
            output = null;
        }

        ps.close();
        conn.close();
        return output;
    }

    /**
     * Sebuah method yang akan mengambil seluruh data screening time
     *
     * @param storeid
     * @param filmid
     * @param offset
     * @return
     * @throws SQLException
     */
    public List<ScreeningTime> getAllScreeningTime(int storeid, String filmid, int offset) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("select * from screeningtime where storeid = ? and filmid = ? ORDER BY id LIMIT 10 OFFSET ?");
        ps.setInt(1, storeid);
        ps.setInt(2, Integer.parseInt(filmid));
        ps.setInt(3, offset);

        ResultSet rs = ps.executeQuery();

        List<ScreeningTime> outputList = new ArrayList<ScreeningTime>();
        while (rs.next()){
            outputList.add(new ScreeningTime(rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getString(5).substring(0,5),
                    rs.getInt(6),
                    rs.getBoolean(7))
            );
        }

        ps.close();
        conn.close();
        return outputList;
    }

    /**
     * Sebuah method yang akan menghitung jumlah halaman dari seluruh data screening time
     *
     * @param storeid
     * @param filmid
     * @return
     * @throws SQLException
     */
    public int getCountAllScreeningTime(int storeid, String filmid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("select count(*) from (select * from screeningtime where storeid = ? and filmid = ? ORDER BY id) as count");
        ps.setInt(1, storeid);
        ps.setInt(2, Integer.parseInt(filmid));

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

    /**
     * Sebuah method yang akan mengambil seluruh data screening time yang memiliki status aktif
     *
     * @param storeid
     * @param filmid
     * @return
     * @throws SQLException
     */
    public List<ScreeningTime> getAllScreeningTimeTrue(int storeid, String filmid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("select * from screeningtime where storeid = ? and filmid = ? and status = true ORDER BY id");
        ps.setInt(1, storeid);
        ps.setInt(2, Integer.parseInt(filmid));

        ResultSet rs = ps.executeQuery();

        List<ScreeningTime> outputList = new ArrayList<ScreeningTime>();
        while (rs.next()){
            outputList.add(new ScreeningTime(rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getString(5).substring(0,5),
                    rs.getInt(6),
                    rs.getBoolean(7))
            );
        }

        ps.close();
        conn.close();
        return outputList;
    }

    /**
     * Sebuah method yang akan mengupdate data dari screening time
     *
     * @param screeningTime
     * @throws SQLException
     */
    public void updateScreeningTime(ScreeningTime screeningTime) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE screeningtime set studioid = ?, time = CAST(? AS TIME), duration = ? where id = ? and storeid = ? and filmid = ?");
        ps.setInt(1, screeningTime.getStudioId());
        ps.setString(2, screeningTime.getTime());
        ps.setInt(3, screeningTime.getDuration());
        ps.setInt(4, screeningTime.getId());
        ps.setInt(5, screeningTime.getStoreID());
        ps.setInt(6, screeningTime.getFilmId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    /**
     * Sebuah method yang akan menghapus Screening time pada db (soft)
     *
     * @param id
     * @param filmid
     * @param storeid
     * @throws SQLException
     */
    public void deleteScreeningTime(String id, String filmid, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE screeningtime set status = false where id = ? and filmid = ? and storeid = ?");
        ps.setInt(1, Integer.parseInt(id));
        ps.setInt(2, Integer.parseInt(filmid));
        ps.setInt(3, storeid);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    /**
     * Sebuah method yang akan mengembalikan screening time yang telah dihapus
     *
     * @param id
     * @param filmid
     * @param storeid
     * @throws SQLException
     */
    public void retrieveScreeningTime(String id, String filmid, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE screeningtime set status = true where id = ? and filmid = ? and storeid = ?");
        ps.setInt(1, Integer.parseInt(id));
        ps.setInt(2, Integer.parseInt(filmid));
        ps.setInt(3, storeid);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
}
