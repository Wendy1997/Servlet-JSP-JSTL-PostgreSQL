package Service;

import DAO.FilmDAO;
import Model.Film;
import Model.Studio;

import java.sql.SQLException;
import java.util.List;

public interface FilmService {
    FilmDAO filmDAO = new FilmDAO();

    Film getFilm(String id, String storename) throws SQLException;
    List<Film> getAllFilm(String storename) throws SQLException;
    void addFilm(Film film) throws SQLException;
    void deleteFilm(String id, String storename) throws SQLException;
    void updateFilm(Film film) throws SQLException;

    List<Studio> getAllStudio(String storename) throws SQLException;

}