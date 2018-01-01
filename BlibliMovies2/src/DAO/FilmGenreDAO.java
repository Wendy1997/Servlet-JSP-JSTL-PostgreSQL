package DAO;

import Model.FilmGenre;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Sebuah kelas yang menghandle CRUD untuk film genre
 */
public class FilmGenreDAO {
    Connection conn;

    /**
     * Inisialisasi DB
     */
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

    /**
     * Sebuah method untuk mengambil data film genre dari db
     *
     * @param id
     * @param storeid
     * @return
     * @throws SQLException
     */
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
        conn.close();
        return output;
    }

    /**
     * Sebuah method yang akan mengambil data film genre yang statusnya aktif
     *
     * @param id
     * @param storeid
     * @return
     * @throws SQLException
     */
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
        conn.close();
        return output;
    }

    /**
     * Sebuah method yang akan mengambil seluruh data film genre
     *
     * @param storeid
     * @param offset
     * @return
     * @throws SQLException
     */
    public List<FilmGenre> getAllFilmGenre(int storeid, int offset) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM filmGenre where storeid = ? ORDER BY id LIMIT 10 OFFSET ?");
        ps.setInt(1, storeid);
        ps.setInt(2, offset);
        ResultSet rs = ps.executeQuery();

        List<FilmGenre> filmGenres = new ArrayList<FilmGenre>();
        while(rs.next()){
            filmGenres.add(new FilmGenre(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4)));
        }

        ps.close();
        conn.close();
        return filmGenres;
    }

    /**
     * Sebuah method yang akan mengambil seluruh data film genre untuk form
     *
     * @param storeid
     * @return
     * @throws SQLException
     */
    public List<FilmGenre> getShowAllFilmGenre(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM filmGenre where storeid = ? ORDER BY id");
        ps.setInt(1, storeid);
        ResultSet rs = ps.executeQuery();

        List<FilmGenre> filmGenres = new ArrayList<FilmGenre>();
        while(rs.next()){
            filmGenres.add(new FilmGenre(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4)));
        }

        ps.close();
        conn.close();
        return filmGenres;
    }

    /**
     * Sebuah method yang akan menghitung jumlah halaman dari seluruh data film genre
     *
     * @param storeid
     * @return
     * @throws SQLException
     */
    public int getCountAllFilmGenre(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("Select Count(*) from (SELECT * FROM filmGenre where storeid = ? ORDER BY id) as count");
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

    /**
     * Sebuah method yang akan mengambil seluruh data film genre yang memiliki status aktif
     *
     * @param storeid
     * @return
     * @throws SQLException
     */
    public List<FilmGenre> getAllFilmGenreTrue(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM filmGenre where storeid = ? and status = true ORDER BY id");
        ps.setInt(1, storeid);
        ResultSet rs = ps.executeQuery();

        List<FilmGenre> filmGenres = new ArrayList<FilmGenre>();
        while(rs.next()){
            filmGenres.add(new FilmGenre(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getBoolean(4)));
        }

        ps.close();
        conn.close();
        return filmGenres;
    }

    /**
     * Sebuah method yang akan menginput film genre pada db
     *
     * @param filmGenre
     * @throws SQLException
     */
    public void addFilmGenre(FilmGenre filmGenre) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO filmGenre (storeid, genre) VALUES (?,?)");
        ps.setInt(1, filmGenre.getStoreID());
        ps.setString(2, filmGenre.getGenre());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    /**
     * Sebuah method yang akan menghapus film genre pada db (soft)
     *
     * @param filmGenre
     * @param storeid
     * @throws SQLException
     */
    public void deleteFilmGenre(String filmGenre, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE filmGenre set status = false where id = ? and storeid = ?");
        ps.setInt(1, Integer.parseInt(filmGenre));
        ps.setInt(2, storeid);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    /**
     * Sebuah method yang akan mengembalikan film genre yang telah dihapus
     *
     * @param filmGenre
     * @param storeid
     * @throws SQLException
     */
    public void retrieveFilmGenre(String filmGenre, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE filmGenre set status = true where id = ? and storeid = ?");
        ps.setInt(1, Integer.parseInt(filmGenre));
        ps.setInt(2, storeid);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    /**
     * Sebuah method yang akan mengupdate data dari film genre
     *
     * @param filmGenre
     * @throws SQLException
     */
    public void updateFilmGenre(FilmGenre filmGenre) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE filmGenre set genre = ? where id = ? and storeid = ? and status = true");
        ps.setString(1, filmGenre.getGenre());
        ps.setInt(2, filmGenre.getId());
        ps.setInt(3, filmGenre.getStoreID());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
}
