package Service;

import DAO.FilmDAO;
import DAO.StudioDAO;
import Model.Film;
import Model.Studio;

import java.sql.SQLException;
import java.util.List;

public class FilmServiceDatabase implements FilmService {
    FilmDAO filmDAO = new FilmDAO();
    StudioDAO studioDAO = new StudioDAO();

    public Film getFilm(String id) throws SQLException{
        return filmDAO.getFilm(id);
    }

    public List<Film> getAllFilm() throws SQLException{
        return filmDAO.getAllFilm();
    }

    public void addFilm(Film film) throws SQLException{
        filmDAO.addFilm(film);
    }

    public void deleteFilm(String id) throws SQLException{
        filmDAO.deleteFilm(id);

    }
    public void updateFilm(Film film) throws SQLException{
        filmDAO.updateFilm(film);
    }

    public List<Studio> getAllStudio() throws SQLException {
        return studioDAO.getAllStudio();
    }
}
