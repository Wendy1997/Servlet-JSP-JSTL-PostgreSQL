package DAO;

import Model.FilmGenre;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FilmGenreDAO {
    Connection conn;

    public FilmGenreDAO(){

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

    public FilmGenre getFilmGenre(String id, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM filmGenre where id = ? and storeid = ?");
        ps.setInt(1, Integer.parseInt(id));
        ps.setInt(2, storeid);

        ResultSet rs = ps.executeQuery();

        FilmGenre output;
        if(rs.next()){
            output = new FilmGenre(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4));
        } else{
            output = null;
        }

        ps.close();
        return output;
    }

    public FilmGenre getFilmGenreTrue(String id, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM filmGenre where id = ? and storeid = ? and status = true");
        ps.setInt(1, Integer.parseInt(id));
        ps.setInt(2, storeid);

        ResultSet rs = ps.executeQuery();

        FilmGenre output;
        if(rs.next()){
            output = new FilmGenre(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4));
        } else{
            output = null;
        }

        ps.close();
        return output;
    }

    public List<FilmGenre> getAllFilmGenre(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM filmGenre where storeid = ? ORDER BY id");
        ps.setInt(1, storeid);
        ResultSet rs = ps.executeQuery();

        List<FilmGenre> filmGenres = new ArrayList<FilmGenre>();
        while(rs.next()){
            filmGenres.add(new FilmGenre(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4)));
        }

        ps.close();
        return filmGenres;
    }

    public List<FilmGenre> getAllFilmGenreTrue(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM filmGenre where storeid = ? and status = true ORDER BY id");
        ps.setInt(1, storeid);
        ResultSet rs = ps.executeQuery();

        List<FilmGenre> filmGenres = new ArrayList<FilmGenre>();
        while(rs.next()){
            filmGenres.add(new FilmGenre(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4)));
        }

        ps.close();
        return filmGenres;
    }

    public void addFilmGenre(FilmGenre filmGenre) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO filmGenre (storeid, genre) VALUES (?,?)");
        ps.setInt(1, filmGenre.getStoreID());
        ps.setString(2, filmGenre.getGenre());
        ps.executeUpdate();
        ps.close();
    }

    public void deleteFilmGenre(String filmGenre, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE filmGenre set status = false where id = ? and storeid = ?");
        ps.setInt(1, Integer.parseInt(filmGenre));
        ps.setInt(2, storeid);
        ps.executeUpdate();
        ps.close();
    }

    public void retrieveFilmGenre(String filmGenre, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE filmGenre set status = true where id = ? and storeid = ?");
        ps.setInt(1, Integer.parseInt(filmGenre));
        ps.setInt(2, storeid);
        ps.executeUpdate();
        ps.close();
    }

    public void updateFilmGenre(FilmGenre filmGenre) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE filmGenre set genre = ? where id = ? and storeid = ? and status = true");
        ps.setString(1, filmGenre.getGenre());
        ps.setInt(2, filmGenre.getId());
        ps.setInt(3, filmGenre.getStoreID());
        ps.executeUpdate();
        ps.close();
    }
}
