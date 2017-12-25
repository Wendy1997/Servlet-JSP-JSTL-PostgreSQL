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

    public FilmGenre getFilmGenre(String id, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM filmGenre where id = ? and storeusername = ? and status = true");
        ps.setInt(1, Integer.parseInt(id));
        ps.setString(2, storename);

        ResultSet rs = ps.executeQuery();

        FilmGenre output;
        if(rs.next()){
            output = new FilmGenre(rs.getString(1), rs.getString(2), rs.getString(3));
        } else{
            output = null;
        }
        return output;
    }

    public List<FilmGenre> getAllFilmGenre(String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM filmGenre where storeusername = ? and status = true");
        ps.setString(1, storename);
        ResultSet rs = ps.executeQuery();

        List<FilmGenre> filmGenres = new ArrayList<FilmGenre>();
        while(rs.next()){
            filmGenres.add(new FilmGenre(rs.getString(1), rs.getString(2), rs.getString(3)));
        }
        return filmGenres;
    }

    public void addFilmGenre(FilmGenre filmGenre) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO filmGenre (storeusername, genre) VALUES (?,?)");
        ps.setString(1, filmGenre.getStorename());
        ps.setString(2, filmGenre.getGenre());
        ps.executeUpdate();
    }

    public void deleteFilmGenre(String filmGenre, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE filmGenre set status = false where id = ? and storeusername = ?");
        ps.setInt(1, Integer.parseInt(filmGenre));
        ps.setString(2, storename);
        ps.executeUpdate();
    }

    public void updateFilmGenre(FilmGenre filmGenre) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE filmGenre set genre = ? where id = ? and storeusername = ? and status = true");
        ps.setString(1, filmGenre.getGenre());
        ps.setInt(2, Integer.parseInt(filmGenre.getId()));
        ps.setString(3, filmGenre.getStorename());
        ps.executeUpdate();
    }
}
