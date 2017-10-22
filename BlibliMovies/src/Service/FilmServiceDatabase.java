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

    @Override
    public Film getFilm(String id) throws SQLException{
        return filmDAO.getFilm(id);
    }

    @Override
    public List<Film> getAllFilm() throws SQLException{
        return filmDAO.getAllFilm();
    }

    @Override
    public void addFilm(Film film) throws SQLException{
        filmDAO.addFilm(film);
    }

    @Override
    public void deleteFilm(String id) throws SQLException{
        filmDAO.deleteFilm(id);

    }

    @Override
    public void updateFilm(Film film) throws SQLException{
        filmDAO.updateFilm(film);
    }

    @Override
    public List<Studio> getAllStudio() throws SQLException {
        return studioDAO.getAllStudio();
    }
}
