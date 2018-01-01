package DAO;

import Model.Film;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FilmDAO {

    Connection conn;

    public FilmDAO(){

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

    public Film getFilm(String film, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM film where id = ? and storeid = ?");
        ps.setString(1, film);
        ps.setInt(2, storeid);

        ResultSet rs = ps.executeQuery();

        Film output;
        if(rs.next()){
            output = new Film(rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getString(7),
                    rs.getInt(8),
                    rs.getInt(9),
                    rs.getString(10).substring(0,10),
                    rs.getString(11).substring(0,10),
                    rs.getString(12),
                    rs.getString(13),
                    rs.getString(14),
                    rs.getString(15),
                    rs.getBoolean(16)
            );
        } else{
            output = null;
        }

        ps.close();
        conn.close();
        return output;
    }

    public Film getFilmTrue(String film, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM film where id = ? and storeid = ? and status = true");
        ps.setString(1, film);
        ps.setInt(2, storeid);

        ResultSet rs = ps.executeQuery();

        Film output;
        if(rs.next()){
            output = new Film(rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getString(7),
                    rs.getInt(8),
                    rs.getInt(9),
                    rs.getString(10).substring(0,10),
                    rs.getString(11).substring(0,10),
                    rs.getString(12),
                    rs.getString(13),
                    rs.getString(14),
                    rs.getString(15),
                    rs.getBoolean(16)
            );
        } else{
            output = null;
        }

        ps.close();
        conn.close();
        return output;
    }

    public List<Film> getAllFilm(int storeid, int offset) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM film where storeid = ? ORDER BY id LIMIT 10 OFFSET ?");
        ps.setInt(1, storeid);
        ps.setInt(2, offset);

        ResultSet rs = ps.executeQuery();

        List<Film> films = new ArrayList<Film>();
        while(rs.next()){
            films.add(new Film(rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getString(7),
                    rs.getInt(8),
                    rs.getInt(9),
                    rs.getString(10),
                    rs.getString(11),
                    rs.getString(12),
                    rs.getString(13),
                    rs.getString(14),
                    rs.getString(15),
                    rs.getBoolean(16)
                    ));
        }

        ps.close();
        conn.close();
        return films;
    }

    public int getCountAllFilm(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("Select count(*) from (SELECT * FROM film where storeid = ? ORDER BY id) as count");
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

    public List<Film> getAllFilmTrue(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM film where storeid = ? and status = true ORDER BY id");
        ps.setInt(1, storeid);

        ResultSet rs = ps.executeQuery();

        List<Film> films = new ArrayList<Film>();
        while(rs.next()){
            films.add(new Film(rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getString(7),
                    rs.getInt(8),
                    rs.getInt(9),
                    rs.getString(10),
                    rs.getString(11),
                    rs.getString(12),
                    rs.getString(13),
                    rs.getString(14),
                    rs.getString(15),
                    rs.getBoolean(16)
            ));
        }

        ps.close();
        conn.close();
        return films;
    }

    public void addFilm(Film film) throws SQLException{

        PreparedStatement ps = conn.prepareStatement("INSERT INTO film (storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        ps.setInt(1, film.getStoreID());
        ps.setString(2, film.getCover());
        ps.setString(3, film.getTitle());
        ps.setInt(4, film.getGenre());
        ps.setInt(5, film.getDuration());
        ps.setString(6, film.getDirector());
        ps.setDouble(7, film.getRating());
        ps.setInt(8, film.getReviewTotal());
        ps.setDate(9, java.sql.Date.valueOf(film.getStartTime()));
        ps.setDate(10, java.sql.Date.valueOf(film.getEndTime()));
        ps.setString(11, film.getLanguage());
        ps.setString(12, film.getSubtitle());
        ps.setString(13, film.getActor());
        ps.setString(14, film.getSinopsis());

        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void deleteFilm(String id, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("update film set status = false where id = ? and storeid = ?");
        ps.setString(1, id);
        ps.setInt(2, storeid);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void retrieveFilm(String id, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("update film set status = true where id = ? and storeid = ?");
        ps.setString(1, id);
        ps.setInt(2, storeid);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void updateFilm(Film film) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE film set cover = ?, title = ?, genre = ?, duration = ?, director = ?, rating = ?, reviewtotal = ?, starttime = ?, endtime = ?, language = ?, subtitle = ?, actor = ?, synopsis = ? where id = ? and storeid = ?");
        ps.setString(1, film.getCover());
        ps.setString(2, film.getTitle());
        ps.setInt(3, film.getGenre());
        ps.setInt(4, film.getDuration());
        ps.setString(5, film.getDirector());
        ps.setDouble(6, film.getRating());
        ps.setInt(7, film.getReviewTotal());
        ps.setDate(8, java.sql.Date.valueOf(film.getStartTime()));
        ps.setDate(9, java.sql.Date.valueOf(film.getEndTime()));
        ps.setString(10, film.getLanguage());
        ps.setString(11, film.getSubtitle());
        ps.setString(12, film.getActor());
        ps.setString(13, film.getSinopsis());
        ps.setString(14, film.getId() + "");
        ps.setInt(15, film.getStoreID());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

}
