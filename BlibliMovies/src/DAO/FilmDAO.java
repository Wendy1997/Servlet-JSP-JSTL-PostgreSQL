package DAO;

import Model.Film;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FilmDAO {
    public static final String database = "org.postgresql.Driver";
    public static final String url = "jdbc:postgresql://localhost:5432/bliblimovies";
    public static final String username = "postgres";
    public static final String password = "wendy1997";

    Connection conn;

    public FilmDAO(){

        try {
            Class.forName(database);
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Film getFilm(String film) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM film where id = ?");
        ps.setString(1, film);

        ResultSet rs = ps.executeQuery();

        Film output;
        if(rs.next()){
            output = new Film(Integer.parseInt(rs.getString(1)),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    Integer.parseInt(rs.getString(6)),
                    rs.getString(7),
                    Integer.parseInt(rs.getString(8)),
                    Integer.parseInt(rs.getString(9)),
                    rs.getString(10),
                    rs.getString(11),
                    rs.getString(12),
                    rs.getString(13),
                    rs.getString(14),
                    rs.getString(15)
            );
        } else{
            output = null;
        }
        return output;
    }

    public List<Film> getAllFilm() throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM film");

        ResultSet rs = ps.executeQuery();

        List<Film> films = new ArrayList<Film>();
        while(rs.next()){
            films.add(new Film(Integer.parseInt(rs.getString(1)),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    Integer.parseInt(rs.getString(6)),
                    rs.getString(7),
                    Integer.parseInt(rs.getString(8)),
                    Integer.parseInt(rs.getString(9)),
                    rs.getString(10),
                    rs.getString(11),
                    rs.getString(12),
                    rs.getString(13),
                    rs.getString(14),
                    rs.getString(15)
                    ));
        }
        return films;
    }

    public void addFilm(Film film) throws SQLException{

        PreparedStatement ps = conn.prepareStatement("INSERT INTO film VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        ps.setString(1, film.getId() + "");
        ps.setString(2, film.getStorename());
        ps.setString(3, film.getCover());
        ps.setString(4, film.getTitle());
        ps.setString(5, film.getGenre());
        ps.setInt(6, film.getDuration());
        ps.setString(7, film.getDirector());
        ps.setDouble(8, film.getRating());
        ps.setInt(9, film.getReviewTotal());
        ps.setDate(10, java.sql.Date.valueOf(film.getStartTime()));
        ps.setDate(11, java.sql.Date.valueOf(film.getEndTime()));
        ps.setString(12, film.getLanguage());
        ps.setString(13, film.getSubtititle());
        ps.setString(14, film.getActor());
        ps.setString(15, film.getSinopsis());

        ps.executeUpdate();
    }

    public void deleteFilm(Film film) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("DELETE FROM film where id = ?");
        ps.setString(1, film.getId() + "");
        ps.executeUpdate();
    }

    public void updateFilm(Film film) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE film where id = ?");
        ps.setString(1, film.getId() + "");
        ps.executeUpdate();
    }

//    public static void main(String[] args){
//        Film film = new Film(2,
//                "blibli",
//                "blibli",
//                "blibli",
//                "blibli",
//                1,
//                "blibli",
//                2,
//                1,
//                "2017-06-01",
//                "2017-06-02",
//                "blibli",
//                "blibli",
//                "blibli",
//                "blibli"
//                );
//
//        try{
//            FilmDAO filmDAO = new FilmDAO();
//            filmDAO.addFilm(film);
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
}
