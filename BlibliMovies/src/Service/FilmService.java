package Service;

import DAO.FilmDAO;
import Model.Film;

import java.sql.SQLException;
import java.util.List;

public interface FilmService {
    FilmDAO filmDAO = new FilmDAO();

    Film getFilm(String id) throws SQLException;
    List<Film> getAllFilm() throws SQLException;
    void addFilm(Film film) throws SQLException;
    void deleteFilm(String id) throws SQLException;
    void updateFilm(Film film) throws SQLException;

}
